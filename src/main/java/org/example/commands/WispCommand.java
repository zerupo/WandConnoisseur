package org.example.commands;

import org.example.localization.LocalizedText;
import org.example.main.Global;
import org.example.main.ProjectileComponent;
import org.example.main.SpellList;
import org.example.projectiles.Projectile;
import org.example.spells.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

class Modifier{
    public String name;
    public int lifetime;

    Modifier(String name, int lifetime){
        this.name = name;
        this.lifetime = lifetime;
    }

    Modifier(Modifier modifier){
        this.name = modifier.name;
        this.lifetime = modifier.lifetime;
    }
}

public class WispCommand extends CommandLocal{
    private static final LocalizedText COMMAND_WISP = Global.getLanguageManager().get("COMMAND_WISP");
    private static final LocalizedText COMMAND_WISP_DESCRIPTION = Global.getLanguageManager().get("COMMAND_WISP_DESCRIPTION");
    private static final LocalizedText COMMAND_WISP_SPELL = Global.getLanguageManager().get("COMMAND_WISP_SPELL");
    private static final LocalizedText COMMAND_WISP_SPELL_DESCRIPTION = Global.getLanguageManager().get("COMMAND_WISP_SPELL_DESCRIPTION");
    private static final LocalizedText COMMAND_WISP_LIFETIME_MIN = Global.getLanguageManager().get("COMMAND_WISP_LIFETIME_MIN");
    private static final LocalizedText COMMAND_WISP_LIFETIME_MIN_DESCRIPTION = Global.getLanguageManager().get("COMMAND_WISP_LIFETIME_MIN_DESCRIPTION");
    private static final LocalizedText COMMAND_WISP_LIFETIME_MAX = Global.getLanguageManager().get("COMMAND_WISP_LIFETIME_MAX");
    private static final LocalizedText COMMAND_WISP_LIFETIME_MAX_DESCRIPTION = Global.getLanguageManager().get("COMMAND_WISP_LIFETIME_MAX_DESCRIPTION");
    private static final LocalizedText COMMAND_WISP_NB_MODIFIER = Global.getLanguageManager().get("COMMAND_WISP_NB_MODIFIER");
    private static final LocalizedText COMMAND_WISP_NB_MODIFIER_DESCRIPTION = Global.getLanguageManager().get("COMMAND_WISP_NB_MODIFIER_DESCRIPTION");
    private static final LocalizedText ERROR_GENERATING_FILE = Global.getLanguageManager().get("ERROR_GENERATING_FILE");
    private static final LocalizedText ERROR_UNKNOWN_SPELL_LIFETIME = Global.getLanguageManager().get("ERROR_UNKNOWN_SPELL_LIFETIME");
    private static final LocalizedText MESSAGE_WISP_LIFETIME = Global.getLanguageManager().get("MESSAGE_WISP_LIFETIME");
    private static final LocalizedText MESSAGE_WISP_NO_SOLUTION = Global.getLanguageManager().get("MESSAGE_WISP_NO_SOLUTION");
    private static final LocalizedText MESSAGE_WISP_SOLUTION = Global.getLanguageManager().get("MESSAGE_WISP_SOLUTION");
    private static final LocalizedText MESSAGE_WISP_SPELL = Global.getLanguageManager().get("MESSAGE_WISP_SPELL");

    public WispCommand(){
        this.name = COMMAND_WISP;
        this.description = COMMAND_WISP_DESCRIPTION;
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, COMMAND_WISP_SPELL, COMMAND_WISP_SPELL_DESCRIPTION, false, true),
            new CommandOption(OptionType.INTEGER, COMMAND_WISP_LIFETIME_MIN, COMMAND_WISP_LIFETIME_MIN_DESCRIPTION, false, false),
            new CommandOption(OptionType.INTEGER, COMMAND_WISP_LIFETIME_MAX, COMMAND_WISP_LIFETIME_MAX_DESCRIPTION, false, false),
            new CommandOption(OptionType.INTEGER, COMMAND_WISP_NB_MODIFIER, COMMAND_WISP_NB_MODIFIER_DESCRIPTION, false, false)
        };
    }

    private static int[][] getValues(Modifier[] modifiers, int minLifetime, int maxLifetime, int nbMaxModifier){
        int[] coefficients = new int[modifiers.length];
        int[] lifetime = new int[modifiers.length];
        for(int i=0; i < modifiers.length; i++){
            lifetime[i] = modifiers[i].lifetime;
        }
        return getValues(0, coefficients, lifetime, minLifetime, maxLifetime, nbMaxModifier);
    }

    private static int[][] getValues(int depth, int[] coefficients, int[] lifetime, int minLifetime, int maxLifetime, int nbMaxModifier){
        if(depth == lifetime.length){
            int sum = getTotal(coefficients, lifetime);
            if(sum <= -minLifetime - 1 && sum >= -maxLifetime - 1){
                int[][] possibility = new int[1][];
                int sumLifetime = 0;
                int sumModifier = 0;
                possibility[0] = new int[coefficients.length + 2];
                for(int i=0; i < coefficients.length; i++){
                    if(coefficients[i] != 0){
                        sumModifier += 1;
                    }
                    sumLifetime += coefficients[i];
                    possibility[0][i] = coefficients[i];
                }
                possibility[0][possibility[0].length - 2] = sumLifetime;
                possibility[0][possibility[0].length - 1] = sumModifier;
                return possibility;
            }else{
                return new int[0][];
            }
        }else{
            List<int[]> possibilities = new ArrayList<>();
            if(pruning(depth, coefficients, lifetime, minLifetime, maxLifetime, nbMaxModifier)){
                for(int i = 0; i <= nbMaxModifier; i++){
                    coefficients[depth] = i;
                    int[][] subPossibilities = getValues(depth + 1, coefficients, lifetime, minLifetime, maxLifetime, nbMaxModifier);
                    possibilities.addAll(Arrays.asList(subPossibilities));
                }
            }
            return possibilities.toArray(new int[0][]);
        }
    }

    private static boolean pruning(int depth, int[] coefficients, int[] lifetime, int minLifetime, int maxLifetime, int nbMaxModifier){
        int sum = 0;
        int sumNew = 0;
        int[] testCoeff = coefficients.clone();

        for(int i=depth; i < testCoeff.length; i++){
            testCoeff[i] = 0;
        }
        sum = getTotal(testCoeff, lifetime);
        if(sum <= -minLifetime - 1 && sum >= -maxLifetime - 1){
            return true;
        }

        for(int i=depth; i < testCoeff.length; i++){
            if(minLifetime + sum > -1 && lifetime[i] < 0 || maxLifetime + sum < -1 && lifetime[i] > 0){;
                testCoeff[i] = nbMaxModifier;
            }
        }
        sumNew = getTotal(testCoeff, lifetime);

        return minLifetime + sum > -1 && minLifetime + sumNew < 0 || maxLifetime + sum < -1 && maxLifetime + sumNew >= -1;
    }

    private static int getTotal(int[] coefficients, int[] lifetime){
        int sum = 0;
        for(int i=0; i < lifetime.length; i++){
            sum += coefficients[i] * lifetime[i];
        }
        return sum;
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        SpellList spellListProjectileComponent = Global.getSpellListProjectileComponent();
        OptionMapping spellOption = event.getOption("spell");
        OptionMapping lifetimeMinOption = event.getOption("lifetime_min");
        OptionMapping lifetimeMaxOption = event.getOption("lifetime_max");
        OptionMapping nbModifierOption = event.getOption("nb_modifier");
        StringBuilder stringResult = new StringBuilder();
        String spellsInput = "";
        Spell spell = null;
        int lifetimeMin = 0;
        int lifetimeMax = 0;
        int nbModifier = 11;
        boolean validSpellFound = false;
        String outputPath = Global.getPathOutput();

        if(spellOption != null){
            spellsInput = spellOption.getAsString();
            spell = spellListProjectileComponent.getSpell(spellsInput);
            if(spell != null){
                // get spell lifetime
                Projectile relatedProjectile = spell.getRelatedProjectile();
                if(relatedProjectile != null){
                    ProjectileComponent projectileComponent = relatedProjectile.getProjectileComponent();
                    if(projectileComponent != null){
                        lifetimeMin = projectileComponent.getLifetimeMin();
                        lifetimeMax = projectileComponent.getLifetimeMax();
                        validSpellFound = true;
                    }
                }
            }
        }
        // lifetime override
        if(lifetimeMinOption != null){
            lifetimeMin = lifetimeMinOption.getAsInt();
        }
        if(lifetimeMaxOption != null){
            lifetimeMax = lifetimeMaxOption.getAsInt();
        }
        if(lifetimeMin > lifetimeMax){
            int temp = lifetimeMin;
            lifetimeMin = lifetimeMax;
            lifetimeMax = temp;
        }
        if(nbModifierOption != null){
            nbModifier = Math.min(Math.max(nbModifierOption.getAsInt(), 0), 21);
        }

        if(!validSpellFound && lifetimeMinOption == null && lifetimeMaxOption == null){
            event.reply(ERROR_UNKNOWN_SPELL_LIFETIME.get(event, spellsInput)).setEphemeral(true).queue();
            return;
        }

        if(validSpellFound && lifetimeMinOption == null && lifetimeMaxOption == null){
            stringResult.append(MESSAGE_WISP_SPELL.get(event, spell.getName())).append(", lifetime: [").append(lifetimeMin).append(", ").append(lifetimeMax).append("] (").append(Global.format(100.0/(lifetimeMax - lifetimeMin + 1))).append("%), max modifier: ").append(nbModifier);
        }else{
            stringResult.append(MESSAGE_WISP_LIFETIME.get(event)).append(": [").append(lifetimeMin).append(", ").append(lifetimeMax).append("] (").append(Global.format(100.0/(lifetimeMax - lifetimeMin + 1))).append("%) , max modifier: ").append(nbModifier);
        }
        event.reply(stringResult.toString()).queue();

        Spell[] spellsLifetimeModifier = Global.getSpellListLifetimeModifier().getSpells(false);

        List<Modifier> modifiersList = new ArrayList<>();
        for(int i = 0; i < spellsLifetimeModifier.length; i++){
            if(i > 0 && spellsLifetimeModifier[i].getLifetime() == spellsLifetimeModifier[i - 1].getLifetime()){
                Modifier last = modifiersList.get(modifiersList.size() - 1);
                last.name += "/" + spellsLifetimeModifier[i].getName();
            }else{
                modifiersList.add(new Modifier(spellsLifetimeModifier[i].getName(), spellsLifetimeModifier[i].getLifetime()));
            }
        }
        Modifier[] modifiers = modifiersList.toArray(new Modifier[0]);

        int[][] result = getValues(modifiers, lifetimeMin, lifetimeMax, nbModifier);
        if(result.length == 0){
            stringResult.append("\n\n").append(MESSAGE_WISP_NO_SOLUTION.get(event));
            event.getHook().editOriginal(stringResult.toString()).queue();
            return;
        }

        stringResult.append("\n\n").append(result.length).append(" ").append(MESSAGE_WISP_SOLUTION.get(event));

        final int N = result[0].length - 1;
        Arrays.sort(result, (a, b) -> {
            if (a[N] != b[N]) return Integer.compare(a[N], b[N]);
            else return Integer.compare(a[N-1], b[N-1]);
        });

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);

            writer.write("lifetime:;" + lifetimeMin + "; -> ;" + lifetimeMax + ";\n\n");
            for(Modifier modifier : modifiers){
                writer.write(modifier.name + ";");
            }
            writer.write("total modifiers;types of modifiers;\n");
            for(Modifier modifier : modifiers){
                writer.write(modifier.lifetime + ";");
            }
            writer.write("\n");
            for(int[] entry : result){
                for(int count : entry){
                    writer.write(count + ";");
                }
                writer.write("\n");
            }

            writer.close();
            event.getHook().editOriginal(stringResult.toString()).setFiles(Global.byteToUpload(out.toByteArray(), "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv")).queue();
        }catch(IOException e){
            stringResult.append("\n").append(ERROR_GENERATING_FILE.get(event));
            event.getHook().editOriginal(stringResult.toString()).queue();
            System.out.println("error writing the file \"" + "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv" + "\" " + e.getMessage());
        }
    }
}