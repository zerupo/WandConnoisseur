package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellList;
import org.example.projectiles.Projectile;
import org.example.spells.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;

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
                    for(int[] subPossibility : subPossibilities){
                        possibilities.add(subPossibility);
                    }
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
        if(minLifetime + sum > -1 && minLifetime + sumNew < 0 || maxLifetime + sum < -1 && maxLifetime + sumNew >= -1){
            return true;
        }else{
            return false;
        }
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
        event.deferReply(true).queue(message -> {
            SpellList spellListRelatedProjectile = Global.getSpellListRelatedProjectile();
            OptionMapping spellOption = event.getOption("sort");
            OptionMapping lifetimeMinOption = event.getOption("lifetime_min");
            OptionMapping lifetimeMaxOption = event.getOption("lifetime_max");
            OptionMapping nbModifierOption = event.getOption("nb_modifier");
            String spellsInput = "";
            Spell spell = null;
            int lifetimeMin = 0;
            int lifetimeMax = 0;
            int nbModifier = 11;
            boolean validSpellFound = false;
            String outputPath = Global.getPathOutput();

            if(spellOption != null){
                System.out.println("sort");
                spellsInput = spellOption.getAsString();
                spell = spellListRelatedProjectile.getSpell(spellsInput);
                if(spell != null){
                    // get spell lifetime
                    Projectile relatedProjectile = spell.getRelatedProjectile();
                    if(relatedProjectile != null){
                        lifetimeMin = relatedProjectile.getLifetimeMin();
                        lifetimeMax = relatedProjectile.getLifetimeMax();
                        validSpellFound = true;
                    }
                }
            }
            // lifetime override
            if(lifetimeMinOption != null){
                System.out.println("lifetime min");
                lifetimeMin = lifetimeMinOption.getAsInt();
            }
            if(lifetimeMaxOption != null){
                System.out.println("lifetime max");
                lifetimeMax = lifetimeMaxOption.getAsInt();
            }
            if(nbModifierOption != null){
                System.out.println("nb modifier");
                nbModifier = Math.min(Math.max(nbModifierOption.getAsInt(), 0), 21);
            }

            System.out.println((lifetimeMaxOption != null) + " && " + (lifetimeMinOption != null) + " || " + validSpellFound + " = " + (lifetimeMaxOption != null && lifetimeMinOption != null || validSpellFound));
            if(lifetimeMinOption != null && lifetimeMaxOption != null || validSpellFound){
                message.deleteOriginal().queue();
            }else{
                event.getHook().editOriginal("Sort \"" + spellsInput + "\" inconnu, si vous ne renseignez aucun sort valide, vous devez saisir le lifetime manuellement").queue();
                return;
            }

            System.out.println("ici");
            if(validSpellFound && lifetimeMinOption == null && lifetimeMaxOption == null){
                System.out.println("valide");
                event.getChannel().sendMessage("Recherche de wisps pour le sort " + spell.getName() + ", lifetime: [" + lifetimeMin + ", " + lifetimeMax + "], max modifier: " + nbModifier).queue();
            }else{
                System.out.println("pas valide");
                event.getChannel().sendMessage("Recherche de wisps pour le lifetime: [" + lifetimeMin + ", " + lifetimeMax + "], max modifier: " + nbModifier).queue();
            }

            Spell[] spellsLifetimeModifier = Global.getSpellListLifetimeModifier().getSpells();

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

            /*for(int i=0; i < modifiers.length; i++){
                System.out.println(modifiers[i].name + ", " + modifiers[i].lifetime);
            }*/

            int[][] result = getValues(modifiers, lifetimeMin, lifetimeMax, nbModifier);

            event.getChannel().sendMessage(result.length + " solutions trouvées").queue();

            if(result.length == 0){
                return;
            }

            final int N = result[0].length - 1;
            Arrays.sort(result, (a, b) -> {
                if (a[N] != b[N]) return Integer.compare(a[N], b[N]);
                else return Integer.compare(a[N-1], b[N-1]);
            });

            /*for(int i=0; i < result.length; i++){
                for(int j=0; j < result[i].length; j++){
                    System.out.print(result[i][j] + ", ");
                }
                System.out.println();
            }*/

            try{
                FileWriter FW = new FileWriter(outputPath + "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv");
                FW.write("lifetime:;" + lifetimeMin + "; -> ;" + lifetimeMax + "\n");
                for(int i=0; i < modifiers.length; i++){
                    FW.write(modifiers[i].name + ";");
                }
                FW.write("total modifiers;types of modifiers\n");
                for(int i=0; i < modifiers.length; i++){
                    if(i != 0){
                        FW.write(";");
                    }
                    FW.write(String.valueOf(modifiers[i].lifetime));
                }
                FW.write("\n\n");
                for(int i=0; i < result.length; i++){
                    for(int j=0; j < result[i].length; j++){
                        if(j != 0){
                            FW.write(";");
                        }
                        FW.write(String.valueOf(result[i][j]));
                    }
                    FW.write("\n");
                }
                FW.close();
                System.out.println("\"wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv\" successfully written");

                File csv = new File(outputPath + "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv");
                event.getChannel().sendFiles(FileUpload.fromData(csv, "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv")).queue();
                if(!csv.delete()){
                    System.out.println("\"" + outputPath + "wisp_" + lifetimeMin + "_" + lifetimeMax + ".csv\" not deleted");
                }
            }catch(IOException e){
                System.out.println("error writing the file");
                e.printStackTrace();
            }
        });
    }
}