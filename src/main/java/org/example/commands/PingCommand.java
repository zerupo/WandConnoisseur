package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand extends Command{
    public PingCommand(){
        this.name = "ping";
        this.description = "Vérifie la latence du bot.";
        this.commandOptions = new CommandOption[0];
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        long ping = event.getJDA().getGatewayPing();
        event.replyFormat("Pong! Gateway Ping: %dms", ping).queue();
    }

}
