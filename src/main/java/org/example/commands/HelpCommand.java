package org.example.commands;

import org.example.main.Global;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class HelpCommand implements Command{
    @Override
    public String getName(){
        return "help";
    }

    @Override
    public String getDescription(){
        return "Liste toutes les commandes disponibles.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping commandeOption = event.getOption("commande");
        StringBuilder result = new StringBuilder();

        if(commandeOption != null){
            String commandName = commandeOption.getAsString();
            net.dv8tion.jda.api.interactions.commands.Command command = Global.getCommand(commandName);

            if(command == null){
                event.reply("Commande \"" + commandName + "\" inconnue").setEphemeral(true).queue();
                return;
            }

            for(net.dv8tion.jda.api.interactions.commands.Command.Option option : command.getOptions()){
                result.append("\n").append("- **").append(option.getName()).append("**: ").append(option.getDescription());
            }

            if(result.isEmpty()){
                event.reply("Aucune option pour la commande " + commandName + "").setEphemeral(true).queue();
            }else{
                event.reply("Liste des options disponibles pour la commande **" + commandName + "**:\n" + result).queue();
            }
        }else{
            net.dv8tion.jda.api.interactions.commands.Command[] commandList = Global.getCommandList();

            for(net.dv8tion.jda.api.interactions.commands.Command command : commandList){
                result.append("\n").append("- **").append(command.getName()).append("**: ").append(command.getDescription());
            }

            if(result.isEmpty()){
                event.reply("Aucune commande disponible").setEphemeral(true).queue();
            }else{
                event.reply("Liste des commandes disponibles:\n" + result).queue();
            }
        }
    }
}