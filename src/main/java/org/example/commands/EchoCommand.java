package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class EchoCommand extends Command{
    public EchoCommand(){
        this.name = "echo";
        this.description = "Répond avec votre message.";
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, "texte", "Le texte à renvoyer.", true, false)
        };
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