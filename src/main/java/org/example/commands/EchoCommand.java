package org.example.commands;

import org.example.localization.LocalizedText;
import org.example.main.Global;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class EchoCommand extends CommandLocal{
    private static final LocalizedText COMMAND_ECHO = Global.getLanguageManager().get("COMMAND_ECHO");
    private static final LocalizedText COMMAND_ECHO_DESCRIPTION = Global.getLanguageManager().get("COMMAND_ECHO_DESCRIPTION");
    private static final LocalizedText COMMAND_ECHO_TEXT = Global.getLanguageManager().get("COMMAND_ECHO_TEXT");
    private static final LocalizedText COMMAND_ECHO_TEXT_DESCRIPTION = Global.getLanguageManager().get("COMMAND_ECHO_TEXT_DESCRIPTION");
    private static final LocalizedText ERROR_ECHO = Global.getLanguageManager().get("ERROR_ECHO");

    public EchoCommand(){
        this.name = COMMAND_ECHO;
        this.description = COMMAND_ECHO_DESCRIPTION;
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, COMMAND_ECHO_TEXT, COMMAND_ECHO_TEXT_DESCRIPTION, true, false)
        };
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping textOption = event.getOption("text");
        String textToEcho = ERROR_ECHO.get(event);

        if(textOption != null){
            textToEcho = textOption.getAsString();
        }

        event.reply(textToEcho).setEphemeral(false).queue();
    }
}