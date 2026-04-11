package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;


public class DumpEmotesCommand implements Command {

    @Override
    public String getName() {
        return "dump_emotes";
    }

    @Override
    public String getDescription() {
        return "Renvoie la listes des emotes avec leur nom et ID";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        
        event.deferReply().queue();

        event.getJDA().retrieveApplicationEmojis().queue(emojis -> {
            StringBuilder sb = new StringBuilder();
            
            emojis.forEach(emote ->
                sb.append(emote.getName())
                .append("=")
                .append(emote.getId())
                .append("\n")
            );

            event.getHook().sendFiles(
                FileUpload.fromData(sb.toString().getBytes(), "emotes.properties")
            ).queue();
        });
    }
}