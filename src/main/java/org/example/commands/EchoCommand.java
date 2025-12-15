package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class EchoCommand implements Command {
    @Override
    public String getName(){
        return "echo";
    }

    @Override
    public String getDescription(){
        return "Répond avec votre message.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping textOption = event.getOption("texte");
        String textToEcho = "You didn't provide any text to echo!";

        if(textOption != null){
            textToEcho = textOption.getAsString();
        }

        event.reply(textToEcho).setEphemeral(false).queue();
    }
}