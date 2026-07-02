package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellFilter;
import org.example.spells.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
        OptionMapping typeOption = event.getOption("type");
        String propertyFilter = "";
        String filter = "";
        String sort = "";
        int type = 0;
        String[][] properties = new String[0][0];
        Spell[] spellList = Global.getSpellList().getSpells(false);
        SpellFilter spellFilter = Global.getSpellFilter();
        StringBuilder result = new StringBuilder();

        if(typeOption != null){
            switch(typeOption.getAsString()){
                case "message" -> {}
                case "csv" -> type = 1;
                default -> {
                    event.reply("\"" + typeOption.getAsString() + "\" n'est pas un type valide.").setEphemeral(true).queue();
                    return;
                }
            }
        }
        if(propertyOption != null){
            propertyFilter = propertyOption.getAsString().trim();
        }
        if(filterOption != null){
            filter = filterOption.getAsString().trim();
        }
        if(sortOption != null){
            sort = sortOption.getAsString().trim();
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
        result.append(type == 0 ? " :\n\n" : "");

        event.deferReply(false).queue();
        event.getHook().editOriginal(result.toString()).queue();

        switch(type){
            case 0 -> {
                for(int i=0; i < spellList.length; i++){
                    if(i != 0){
                        result.append("\n");
                    }
                    result.append(spellList[i].getEmote()).append(" ").append(spellList[i].getName());
                    if(propertyOption != null){
                        for(String[] property : properties){
                            result.append("\n\u200E        \u2022 **").append(property[0]).append("**: ").append(property[i + 1]);
                        }
                    }
                }
                Global.sendMessage(result.toString(), event, true, false);
            }
            case 1 -> {
                try{
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);

                    writer.write(filter.isEmpty() ? "" : "condition:;" + filter);
                    writer.write(sort.isEmpty() ? "" : "tri:;" + sort);
                    writer.write(filter.isEmpty() && sort.isEmpty() ? "" : "\n\n");

                    writer.write("spell;");
                    if(propertyOption != null){
                        for(int i=0; i < properties.length; i++){
                            writer.write(properties[i][0] + ";");
                        }
                    }
                    writer.write("\n");

                    for(int i=0; i < spellList.length; i++){
                        writer.write(spellList[i].getClass().getSimpleName() + ";");
                        if(propertyOption != null){
                            for(String[] property : properties){
                                writer.write(property[i + 1] + ";");
                            }
                        }
                        writer.write("\n");
                    }

                    writer.close();
                    event.getHook().editOriginal(result.toString()).setFiles(Global.byteToUpload(out.toByteArray(), "spell_list.csv")).queue();
                }catch(Exception e){
                    System.out.println("error writing the file \"" + "spell_list_.csv" + "\" " + e.getMessage());
                    event.getHook().editOriginal(result + "\n\nErreur lors de la génération du fichier." ).queue();
                }
            }
        }
    }
}