package org.example.commands;

import org.example.main.Global;
import org.example.spells.*;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SpellListCommand implements Command{
    @Override
    public String getName(){
        return "liste_sorts";
    }

    @Override
    public String getDescription(){
        return "Renvoie la liste des sorts disponibles";
    }

    public void executeSlash(SlashCommandInteractionEvent event){
        Spell[] spellList = Global.getSpellList().getSpells();
        StringBuilder result = new StringBuilder();
        boolean alias = false;
        String[] aliasList;
        OptionMapping aliasOption = event.getOption("alias");

        if(aliasOption != null){
            alias = aliasOption.getAsBoolean();
        }

        for(int i=0; i < spellList.length; i++){
            // uncomment temporarily to create all spell emotes when using this command
            //spellList[i].createEmote();
            result.append(spellList[i].getEmote() + " " + spellList[i].getName());
            if(alias){
                aliasList = spellList[i].getAlias();
                if(aliasList.length > 0){
                    result.append(" (");
                }
                for(int j=0; j < aliasList.length; j++){
                    if(j == 0){
                        result.append("\"" + aliasList[j] + "\"");
                    }else{
                        result.append(", \"" + aliasList[j] + "\"");
                    }
                }
                if(aliasList.length > 0){
                    result.append(")");
                }
            }
            if(i + 1 != spellList.length){
                result.append("\n");
            }
        }

        int chunkSize = 1980;
        String chunk = "";
        String[] lines = result.toString().split("\\r?\\n");
        for(int i=0; i < lines.length; i++){
            if(chunk.length() + lines[i].length() > chunkSize){
                if(chunk.length() <= chunkSize){
                    event.getChannel().sendMessage(chunk).queue();
                }else{
                    event.getChannel().sendMessage("```ansi\nError message too long```").queue();
                }
                chunk = lines[i];
            }else{
                chunk += "\n" + lines[i];
            }
            if(i + 1 == lines.length && !chunk.equals("")){
                event.getChannel().sendMessage(chunk).queue();
            }
        }

        event.reply("OK").setEphemeral(true).queue(message -> {
            message.deleteOriginal().queue();
        });
    }
}