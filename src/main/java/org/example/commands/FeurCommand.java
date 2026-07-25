package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FeurCommand extends Command{
    public FeurCommand(){
        this.name = "feur";
        this.description = "Quoi ?";
        this.commandOptions = new CommandOption[0];
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        event.reply("OK").setEphemeral(true).queue(message -> {
            message.deleteOriginal().queue();
        });
        event.getChannel().sendMessage("https://tenor.com/view/theobabac-feur-meme-theobabac-feur-gif-11339780952727019434").queue();
    }
}