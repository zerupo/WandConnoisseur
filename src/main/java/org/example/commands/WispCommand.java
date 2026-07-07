package org.example.commands;

import org.example.main.Global;
import org.example.main.ProjectileComponent;
import org.example.main.SpellList;
import org.example.projectiles.Projectile;
import org.example.spells.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

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

public class WispCommand implements Command{
    @Override
    public String getName(){
        return "wisp";
    }

    @Override
    public String getDescription(){
        return "Renvoie la liste des modifiers pour faire un wisp au format .csv (Excel/LibreOffice Calc/etc)";
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
        OptionMapping spellOption = event.getOption("sort");
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
            event.reply("Sort \"" + spellsInput + "\" inconnu, si vous ne renseignez aucun sort valide, vous devez saisir le lifetime manuellement").setEphemeral(true).queue();
            return;
        }

        DecimalFormat df = new DecimalFormat("0.##");
        if(validSpellFound && lifetimeMinOption == null && lifetimeMaxOption == null){
            stringResult.append("Recherche de wisps pour le sort ").append(spell.getName()).append(", lifetime: [").append(lifetimeMin).append(", ").append(lifetimeMax).append("] (").append(df.format(100.0/(lifetimeMax - lifetimeMin + 1))).append("%), max modifier: ").append(nbModifier);
        }else{
            stringResult.append("Recherche de wisps pour le lifetime: [").append(lifetimeMin).append(", ").append(lifetimeMax).append("] (").append(df.format(100.0/(lifetimeMax - lifetimeMin + 1))).append("%) , max modifier: ").append(nbModifier);
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
            stringResult.append("\n\nAucune solution solution trouvée");
            event.getHook().editOriginal(stringResult.toString()).queue();
            return;
        }

        stringResult.append("\n\n").append(result.length).append(" solutions trouvées");

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
            stringResult.append("\nErreur lors de l'écriture du fichier");
            event.getHook().editOriginal(stringResult.toString()).queue();
            System.out.println("error writing the file \"" + "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv" + "\" " + e.getMessage());
        }
    }
}