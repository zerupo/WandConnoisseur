package org.example.commands;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.WandConnoisseur.jda;

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
        String commandName;
        RestAction<List<net.dv8tion.jda.api.interactions.commands.Command>> commandList = jda.retrieveCommands();
        AtomicBoolean commandFound = new AtomicBoolean(false); // why ???

        if(commandeOption != null){
            commandName = commandeOption.getAsString();
        }else{
            commandName = "";
        }

        commandList.queue(commands -> {
            List<String> result = new ArrayList<>();
            for(net.dv8tion.jda.api.interactions.commands.Command command : commands){
                if(commandeOption == null){
                    result.add("- **" + command.getName() + "**: " + command.getDescription());
                }else if(command.getName().equals(commandName)){
                    commandFound.set(true);
                    List<net.dv8tion.jda.api.interactions.commands.Command.Option> options = command.getOptions();
                    for(net.dv8tion.jda.api.interactions.commands.Command.Option option : options){
                        result.add("- **" + option.getName() + "**: " + option.getDescription());
                    }
                    break;
                }
            }
            if(commandeOption == null){
                result.sort(String::compareTo);
            }
            if(result.size() == 0){
                if(commandeOption == null){
                    event.reply("Aucune commande n'as été trouvée.").setEphemeral(true).queue();
                }else{
                    if(commandFound.get()){
                        event.reply("Aucune option pour la commande " + commandName).setEphemeral(true).queue();
                    }else{
                        event.reply("Commande inconnue").setEphemeral(true).queue();
                    }
                }
            }else{
                event.reply("OK").setEphemeral(true).queue(message -> {
                    message.deleteOriginal().queue();
                });
                if(commandeOption == null){
                    event.getChannel().sendMessage("Liste des commandes disponibles:\n\n" + String.join("\n", result)).queue();
                }else{
                    event.getChannel().sendMessage("Liste des options disponibles pour la commande **" + commandName + "**:\n\n" + String.join("\n", result)).queue();
                }
            }
        }, failure -> {
            event.reply("Erreur lors de la récupération des commandes").setEphemeral(true).queue();
            System.out.println("Failed to retrieve commands: " + failure.getMessage());
        });
    }
}