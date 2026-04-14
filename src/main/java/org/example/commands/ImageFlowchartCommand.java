package org.example.commands;

import org.example.main.Global;
import org.example.main.Wand;

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
        Wand wand = Global.slashInteractionToWand(event);
        FileUpload wandStatImage = null;
        FileUpload wandImage;
        FileUpload flowchartImage;
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
            wandStatImage = Global.JPanelToUpload(wand.getStatJPanel(), "wandstats.png");
        }
        wandImage = Global.JPanelToUpload(wand.getWandJPanel(), "wand.png");
        flowchartImage = Global.bufferedImageToUpload(wand.getFlowchartImage(true), "flowchart.png");

        if(wandStatImage != null){
            event.getHook().editOriginal("").setFiles(wandStatImage).queue();
            eventReplied = true;
        }

        if(wandImage != null){
            if(eventReplied){
                event.getChannel().sendFiles(wandImage).queue();
            }else{
                event.getHook().editOriginal("").setFiles(wandImage).queue();
            }
            eventReplied = true;
        }

        if(eventReplied){
            event.getChannel().sendFiles(flowchartImage).queue();
        }else{
            event.getHook().editOriginal("").setFiles(flowchartImage).queue();
        }
    }
}