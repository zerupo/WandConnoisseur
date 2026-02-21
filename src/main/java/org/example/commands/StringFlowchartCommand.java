package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellList;
import org.example.main.Wand;
import org.example.spells.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;

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
        event.deferReply(true).queue(message -> {
            SpellList spellList = Global.getSpellList();
            OptionMapping spellsOption = event.getOption("sorts");
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
            Spell currentSpell = null;
            int currentSpellCount = 1;
            ArrayList<Spell> spells = new ArrayList<>();
            String[] spellsString;
            String outputPath = Global.getPathOutput();
            Wand wand;
            boolean statChanged = false;
            Pattern p = Pattern.compile("^(?:(inf|max|[0-9]+):)?([^:]*)(?::([0-9]+))?$");
            Matcher m;

            if(drawOption != null){
                draw = Math.max(drawOption.getAsInt(), 1);
                statChanged = true;
            }
            if(spellsOption != null){
                spellsInput = spellsOption.getAsString();
            }
            if(castDelayOption != null){
                try{
                    castDelay = Global.stringToDelay(castDelayOption.getAsString());
                }catch(Exception e){
                    event.getHook().editOriginal("cast_delay: " + e.getMessage()).queue();
                    return;
                }
                statChanged = true;
            }
            if(rechargeTimeOption != null){
                try{
                    rechargeTime = Global.stringToDelay(rechargeTimeOption.getAsString());
                }catch(Exception e){
                    event.getHook().editOriginal("recharge_time: " + e.getMessage()).queue();
                    return;
                }
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

            for(int i=0; i < spellsString.length; i++){
                m = p.matcher(spellsString[i]);
                if(m.find()){
                    currentSpell = spellList.getSpell(m.group(2));
                    if(currentSpell != null){
                        switch((m.group(1) != null) ? m.group(1) : "inf"){
                            case "inf" -> currentSpell.makeInfinite();
                            case "max" -> currentSpell.refillCharges();
                            default -> currentSpell.setCharges(Integer.parseInt(m.group(1)));
                        }
                    }
                    currentSpellCount = (m.group(3) != null) ? Integer.parseInt(m.group(3)) : 1;
                }else{
                    currentSpell = null;
                }
                if(currentSpell != null){
                    spells.add(currentSpell);
                    for(int j=1; j < currentSpellCount; j++){
                        spells.add(currentSpell.clone());
                    }
                }else{
                    if(unknownSpell.equals("")){
                        unknownSpell += "\"" + spellsString[i] + "\"";
                    }else{
                        unknownSpell += ", \"" + spellsString[i] + "\"";
                    }
                }
            }

            if(!unknownSpell.equals("")){
                event.getHook().editOriginal("Sorts inconnus: " + unknownSpell).queue();
                if(spells.size() == 0){
                    return;
                }
            }

            wand = new Wand(draw, castDelay, rechargeTime, manaMax, manaRegen, spells.size(), spread, speed);
            for(Spell spell : spells){
                wand.putSpellEnd(spell);
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
                message.deleteOriginal().queue();
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
                Global.sendMessage(result, event.getChannel(), true);
            }
        });

    }
}