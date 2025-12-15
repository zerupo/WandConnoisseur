package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellList;
import org.example.main.Wand;
import org.example.spells.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;

public class ImageFlowchartCommand implements Command{
    @Override
    public String getName(){
        return "flowchart_image";
    }

    @Override
    public String getDescription(){
        return "Renvoie la flowchart sous forme d'une image.";
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
            String spellsInput = "";
            int draw = 1;
            int castDelay = 0;
            int rechargeTime = 0;
            int manaMax = 1000000;
            int manaRegen = 1000000;
            double spread = 0.0;
            double speed = 1.0;
            String outputPath = "./src/main/java/org/example/fileOutput/";
            String fileName = event.getId() + ".png";
            String unknownSpell = "";
            Spell currentSpell;
            String[] spellsString;
            Wand wand;
            JPanel wandJPanel;
            boolean statChanged = false;
            File wandStatImage = null;
            File wandImage = null;
            File flowchartImage;
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
                    }
                }else{
                    currentSpell = spellList.getSpell(spellsString[i]);
                    if(currentSpell != null){
                        currentSpell.makeInfinite();
                    }
                }
                if(currentSpell != null){
                    wand.putSpell(currentSpell, i);
                }else{
                    if(unknownSpell.equals("")){
                        unknownSpell += "\"" + spellsString[i] + "\"";
                    }else{
                        unknownSpell += ", \"" + spellsString[i] + "\"";
                    }
                }
            }

            if(statChanged){
                wandJPanel = wand.getStatJPanel();
                if(wandJPanel != null && wandJPanel.getSize().width > 0 && wandJPanel.getSize().height > 0){
                    BufferedImage bi = new BufferedImage(wandJPanel.getSize().width, wandJPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
                    Graphics g = bi.createGraphics();
                    wandJPanel.paint(g);
                    g.dispose();
                    try{
                        ImageIO.write(bi,"png",new File(outputPath + "wandstats_" + fileName));
                    }catch(Exception e){
                        // nothing
                    }
                    wandStatImage = new File(outputPath + "wandstats_" + fileName);
                }
            }

            wandJPanel = wand.getWandJPanel();
            if(wandJPanel != null && wandJPanel.getSize().width > 0 && wandJPanel.getSize().height > 0){
                BufferedImage bi = new BufferedImage(wandJPanel.getSize().width, wandJPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                wandJPanel.paint(g);
                g.dispose();
                try{
                    ImageIO.write(bi,"png",new File(outputPath + "wand_" + fileName));
                }catch(Exception e){
                    // nothing
                }
                wandImage = new File(outputPath + "wand_" + fileName);
            }

            wand.saveFlowchartImage(outputPath + "flowchart_" + fileName, true);
            flowchartImage = new File(outputPath + "flowchart_" + fileName);

            if(wandStatImage != null){
                event.getChannel().sendFiles(FileUpload.fromData(wandStatImage, "wandstats.png")).queue();
            }
            if(wandImage != null){
                event.getChannel().sendFiles(FileUpload.fromData(wandImage, "wand.png")).queue();
            }
            event.getChannel().sendFiles(FileUpload.fromData(flowchartImage, "flowchart.png")).queue();

            if(unknownSpell.equals("")){
                message.deleteOriginal().queue();
            }else{
                event.getHook().editOriginal("Sorts inconnus: " + unknownSpell).queue();
            }

            if(wandStatImage != null){
                if(!wandStatImage.delete()){
                    System.out.println("\"" + outputPath + "wandstats_" + fileName + "\" not deleted");
                }
            }
            if(wandImage != null){
                if(!wandImage.delete()){
                    System.out.println("\"" + outputPath + "wand_" + fileName + "\" not deleted");
                }
            }
            if(!flowchartImage.delete()){
                System.out.println("\"" + outputPath + "flowchart_" + fileName + "\" not deleted");
            }
        });
    }
}