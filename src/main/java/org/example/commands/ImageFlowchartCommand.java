package org.example.commands;

import org.example.main.Global;
import org.example.main.Wand;

import java.io.File;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
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
        String fileName = event.getId() + ".png";
        File wandStatImage = null;
        File wandImage;
        File flowchartImage;
        Wand wand = Global.slashInteractionToWand(event);
        String[] statOptions = new String[]{"draw", "cast_delay", "recharge_time", "mana_max", "mana_regen", "spread", "speed"};
        boolean statChanged = false;
        boolean eventReplied = false;

        if(wand == null){
            return;
        }
        for(int i=0; i < statOptions.length; i++){
            if(event.getOption(statOptions[i]) != null){
                statChanged = true;
                break;
            }
        }

        event.deferReply(false).queue();

        if(statChanged){
            wandStatImage = Global.JPanelToFile(wand.getStatJPanel(), Global.getPathOutput() + "wandstats_" + fileName);
        }
        wandImage = Global.JPanelToFile(wand.getWandJPanel(), Global.getPathOutput() + "wand_" + fileName);
        wand.saveFlowchartImage(Global.getPathOutput() + "flowchart_" + fileName, true);
        flowchartImage = new File(Global.getPathOutput() + "flowchart_" + fileName);

        if(wandStatImage != null){
            event.getHook().editOriginal("").setFiles(FileUpload.fromData(wandStatImage, "wandstats.png")).queue();
            if(!wandStatImage.delete()){
                System.out.println("\"" + wandStatImage.getAbsolutePath() + "\" not deleted");
            }
            eventReplied = true;
        }

        if(wandImage != null){
            if(eventReplied){
                event.getChannel().sendFiles(FileUpload.fromData(wandImage, "wand.png")).queue();
            }else{
                event.getHook().editOriginal("").setFiles(FileUpload.fromData(wandImage, "wand.png")).queue();
            }
            if(!wandImage.delete()){
                System.out.println("\"" + wandImage.getAbsolutePath() + "\" not deleted");
            }
            eventReplied = true;
        }

        if(eventReplied){
            event.getChannel().sendFiles(FileUpload.fromData(flowchartImage, "flowchart.png")).queue();
        }else{
            event.getHook().editOriginal("").setFiles(FileUpload.fromData(flowchartImage, "flowchart.png")).queue();
        }
        if(!flowchartImage.delete()){
            System.out.println("\"" + flowchartImage.getAbsolutePath() + "\" not deleted");
        }
    }
}