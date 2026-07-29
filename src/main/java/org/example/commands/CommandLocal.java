package org.example.commands;

import org.example.localization.LocalizedText;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class CommandLocal{
    LocalizedText name;
    LocalizedText description;
    CommandOption[] commandOptions;
    public LocalizedText getName(){
        return this.name;
    }

    public LocalizedText getDescription(){
        return this.description;
    }

    public CommandOption[] getCommandOptions(){
        return this.commandOptions;
    }

    public abstract void executeSlash(SlashCommandInteractionEvent event);
}