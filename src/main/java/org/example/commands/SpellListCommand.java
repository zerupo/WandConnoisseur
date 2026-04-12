package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellFilter;
import org.example.spells.*;

import java.util.concurrent.TimeUnit;
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
        OptionMapping propertyOption = event.getOption("propriete");
        OptionMapping filterOption = event.getOption("condition");
        OptionMapping sortOption = event.getOption("tri");
        String propertyFilter = "";
        String filter = "";
        String sort = "";
        String[][] properties = new String[0][0];
        Spell[] spellList = Global.getSpellList().getSpells(false);
        SpellFilter spellFilter = Global.getSpellFilter();
        StringBuilder result = new StringBuilder();

        if(propertyOption != null){
            propertyFilter = propertyOption.getAsString();
        }
        if(filterOption != null){
            filter = filterOption.getAsString();
        }
        if(sortOption != null){
            sort = sortOption.getAsString();
        }

        if(filterOption != null){
            try{
                spellList = spellFilter.filter(spellList, filter);
            }catch(IllegalArgumentException e){
                event.reply(e.getMessage()).setEphemeral(true).queue();
                return;
            }
            if(spellList.length == 0){
                event.reply("Aucun sort de respect la condition `" + filter + "`").setEphemeral(true).queue();
                return;
            }
            result.append("Liste des sorts respectant la condition `").append(filter).append("`");
        }else{
            result.append("Liste de tout les sorts disponibles");
        }

        if(sortOption != null){
            try{
                spellList = spellFilter.sort(spellList, sort);
            }catch(IllegalArgumentException e){
                event.reply(e.getMessage()).setEphemeral(true).queue();
                return;
            }
            result.append(", triée par `").append(sort).append("`");
        }
        if(propertyOption != null){
            try{
                properties = spellFilter.getStringProperties(spellList, propertyFilter);
            }catch(IllegalArgumentException e){
                event.reply(e.getMessage()).setEphemeral(true).queue();
                return;
            }
        }
        result.append(" :\n\n");

        event.deferReply(false).queue();
        event.getHook().editOriginal(result.toString()).queue();

        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i < spellList.length; i++){
            if(i != 0){
                result.append("\n");
            }
            result.append(spellList[i].getEmote()).append(" ").append(spellList[i].getName());
            if(propertyOption != null){
                for(int j=0; j < properties.length; j++){
                    result.append("\n\u200E        \u2022 **").append(properties[j][0]).append("**: ").append(properties[j][i + 1]);
                }
            }
        }
        Global.sendMessage(result.toString(), event, true, false);
    }
}