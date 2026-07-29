package org.example.commands;

import org.example.localization.LocalizedText;
import org.example.main.Global;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FeurCommand extends CommandLocal{
    private static final LocalizedText COMMAND_FEUR = Global.getLanguageManager().get("COMMAND_FEUR");
    private static final LocalizedText COMMAND_FEUR_DESCRIPTION = Global.getLanguageManager().get("COMMAND_FEUR_DESCRIPTION");
    public FeurCommand(){
        this.name = COMMAND_FEUR;
        this.description = COMMAND_FEUR_DESCRIPTION;
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