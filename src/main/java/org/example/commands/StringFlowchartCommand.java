package org.example.commands;

import net.dv8tion.jda.api.utils.FileUpload;
import org.example.main.Global;
import org.example.main.SpellList;
import org.example.main.Wand;
import org.example.spells.*;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFlowchartCommand implements Command{
    @Override
    public String getName(){
        return "flowchart_texte";
    }

    @Override
    public String getDescription(){
        return "Renvoie la flowchart sous forme textuelle.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        SpellList spellList = Global.getSpellList();
        OptionMapping spellsOption = event.getOption("sorts_");
        OptionMapping drawOption = event.getOption("draw");
        OptionMapping castDelayOption = event.getOption("cast_delay");
        OptionMapping rechargeTimeOption = event.getOption("recharge_time");
        OptionMapping manaMaxOption = event.getOption("mana_max");
        OptionMapping manaRegenOption = event.getOption("mana_regen");
        OptionMapping spreadOption = event.getOption("spread");
        OptionMapping speedOption = event.getOption("speed");
        OptionMapping fileOption = event.getOption("fichier");
        String spellsInput = "";
        int draw = 1;
        int castDelay = 0;
        int rechargeTime = 0;
        int manaMax = 1000000;
        int manaRegen = 1000000;
        double spread = 0.0;
        double speed = 1.0;
        boolean file = false;
        String unknownSpell = "";
        String result = "";
        String wandSpells = "";
        Spell currentSpell;
        String[] spellsString;
        int chunkSize = 1980;
        String chunk = "";
        String outputPath = "./src/main/java/org/example/fileOutput/";
        Wand wand;
        boolean statChanged = false;
        Pattern p = Pattern.compile("^(inf|max|[0-9]+):(.*)$");
        Matcher m;

        if(drawOption != null){
            draw = Math.max(drawOption.getAsInt(), 1);
            statChanged = true;
        }
        if(spellsOption != null){
            spellsInput = spellsOption.getAsString();
        }
        if(castDelayOption != null){
            castDelay = castDelayOption.getAsInt();
            statChanged = true;
        }
        if(rechargeTimeOption != null){
            rechargeTime = rechargeTimeOption.getAsInt();
            statChanged = true;
        }
        if(manaMaxOption != null){
            manaMax = manaMaxOption.getAsInt();
            statChanged = true;
        }
        if(manaRegenOption != null){
            manaRegen = manaRegenOption.getAsInt();
            statChanged = true;
        }
        if(spreadOption != null){
            spread = spreadOption.getAsDouble();
            statChanged = true;
        }
        if(speedOption != null){
            speed = speedOption.getAsDouble();
            statChanged = true;
        }
        if(fileOption != null){
            file = fileOption.getAsBoolean();
        }

        spellsString = spellsInput.split(",");
        for(int i=0; i < spellsString.length; i++){
            spellsString[i] = spellsString[i].trim().toLowerCase();
        }
        wand = new Wand(draw, castDelay, rechargeTime, manaMax, manaRegen, spellsString.length, spread, speed);
        for(int i=0; i < spellsString.length; i++){
            m = p.matcher(spellsString[i]);
            if(m.find()){
                currentSpell = spellList.getSpell(m.group(2));
                if(currentSpell != null){
                    switch(m.group(1)){
                        case "inf" -> currentSpell.makeInfinite();
                        case "max" -> currentSpell.refillCharges();
                        default -> currentSpell.setCharges(Integer.parseInt(m.group(1)));
                    }
                    if(!wandSpells.equals("")){
                        wandSpells += ", ";
                    }
                    wandSpells += m.group(1) + ":" + currentSpell.getName();
                }
            }else{
                currentSpell = spellList.getSpell(spellsString[i]);
                if(currentSpell != null){
                    currentSpell.makeInfinite();
                    if(!wandSpells.equals("")){
                        wandSpells += ", ";
                    }
                    wandSpells += currentSpell.getName();
                }
            }
            if(currentSpell != null){
                wand.putSpell(currentSpell, i);
                /*if(wandSpells.equals("")){
                    wandSpells += currentSpell.getName();
                }else{
                    wandSpells += ", " + currentSpell.getName();
                }*/
            }else{
                if(unknownSpell.equals("")){
                    unknownSpell += "\"" + spellsString[i] + "\"";
                }else{
                    unknownSpell += ", \"" + spellsString[i] + "\"";
                }
            }
        }

        if(statChanged){
            if(wand.getShuffle()){
                result = "Shuffle: Yes";
            }else{
                result = "Shuffle: No";
            }
            result += ", Spells/cast: " + draw + ", Cast delay: " + castDelay + ", Recharge time: " + rechargeTime + ", Max mana: " + manaMax + ", Mana regen: " + manaRegen + ", Slots: " + spellsString.length + ", Spread: " + spread + ", Speed: " + speed + "\n";
        }
        result += "Spells: " + wandSpells + "\n\n" + wand.getFlowchartString(true, !file);

        if(unknownSpell.equals("")){
            event.reply("OK").setEphemeral(true).queue(message -> {
                message.deleteOriginal().queue();
            });
        }else{
            event.reply("Sorts inconnus: " + unknownSpell).setEphemeral(true).queue();
        }

        if(file){
            try{
                FileWriter fw = new FileWriter(outputPath + "text_flowchart.txt");
                fw.write(result);
                fw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            File textFile = new File(outputPath + "text_flowchart.txt");
            event.getChannel().sendFiles(FileUpload.fromData(textFile, "text_flowchart.txt")).queue();
            if(!textFile.delete()){
                System.out.println("\"" + outputPath + "text_flowchart.txt\" not deleted");
            }
        }else{
            String[] lines = result.split("\\r?\\n");
            for(int i=0; i < lines.length; i++){
                if(chunk.length() + lines[i].length() > chunkSize){
                    if(chunk.length() <= chunkSize){
                        event.getChannel().sendMessage("```ansi\n" + chunk + "```").queue();
                    }else{
                        event.getChannel().sendMessage("```ansi\nError message too long```").queue();
                    }
                    chunk = lines[i];
                }else{
                    chunk += "\n" + lines[i];
                }
                if(i + 1 == lines.length && !chunk.equals("")){
                    event.getChannel().sendMessage("```ansi\n" + chunk + "```").queue();
                }
            }
        }
    }
}