package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class Command{
    String name;
    String description;
    CommandOption[] commandOptions;
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public CommandOption[] getCommandOptions(){
        return this.commandOptions;
    }

    public abstract void executeSlash(SlashCommandInteractionEvent event);
}