package org.example.commands;

import org.example.localization.LocalizedText;
import org.example.main.Global;

import java.util.Locale;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class HelpCommand extends CommandLocal{
    private static final LocalizedText COMMAND_HELP = Global.getLanguageManager().get("COMMAND_HELP");
    private static final LocalizedText COMMAND_HELP_DESCRIPTION = Global.getLanguageManager().get("COMMAND_HELP_DESCRIPTION");
    private static final LocalizedText COMMAND_HELP_COMMAND = Global.getLanguageManager().get("COMMAND_HELP_COMMAND");
    private static final LocalizedText COMMAND_HELP_COMMAND_DESCRIPTION = Global.getLanguageManager().get("COMMAND_HELP_COMMAND_DESCRIPTION");
    private static final LocalizedText ERROR_NO_COMMAND = Global.getLanguageManager().get("ERROR_NO_COMMAND");
    private static final LocalizedText ERROR_UNKNOWN_COMMAND = Global.getLanguageManager().get("ERROR_UNKNOWN_COMMAND");
    private static final LocalizedText ERROR_UNKNOWN_OPTION = Global.getLanguageManager().get("ERROR_UNKNOWN_OPTION");
    private static final LocalizedText MESSAGE_LIST_COMMAND = Global.getLanguageManager().get("MESSAGE_LIST_COMMAND");
    private static final LocalizedText MESSAGE_LIST_OPTION = Global.getLanguageManager().get("MESSAGE_LIST_OPTION");

    public HelpCommand(){
        this.name = COMMAND_HELP;
        this.description = COMMAND_HELP_DESCRIPTION;
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, COMMAND_HELP_COMMAND, COMMAND_HELP_COMMAND_DESCRIPTION, false, true)
        };
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping commandOption = event.getOption("command");
        StringBuilder result = new StringBuilder();
        Locale guildLanguage = event.getGuildLocale().toLocale();
        Locale userLanguage = event.getUserLocale().toLocale();

        if(commandOption != null){
            String commandName = commandOption.getAsString();
            CommandLocal commandLocal = Global.getCommandLocal(commandName, userLanguage);

            if(commandLocal == null){
                if(guildLanguage != userLanguage){
                    commandLocal = Global.getCommandLocal(commandName, guildLanguage);
                }
                if(commandLocal == null){
                    event.reply(ERROR_UNKNOWN_COMMAND.get(guildLanguage, commandName)).setEphemeral(true).queue();
                    return;
                }
            }

            for(CommandOption option : commandLocal.getCommandOptions()){
                result.append("\n").append("- **").append(option.name().get(guildLanguage)).append("**: ").append(option.description().get(guildLanguage));
            }

            if(result.isEmpty()){
                event.reply(ERROR_UNKNOWN_OPTION.get(guildLanguage, commandName)).setEphemeral(true).queue();
            }else{
                event.reply(MESSAGE_LIST_OPTION.get(guildLanguage, commandLocal.name.get(guildLanguage)) + "\n" + result).queue();
            }
        }else{
            CommandLocal[] commandList = Global.getCommandLocalList();

            for(CommandLocal commandLocal : commandList){
                result.append("\n").append("- **").append(commandLocal.getName().get(guildLanguage)).append("**: ").append(commandLocal.getDescription().get(guildLanguage));
            }

            if(result.isEmpty()){
                event.reply(ERROR_NO_COMMAND.get(guildLanguage)).setEphemeral(true).queue();
            }else{
                event.reply(MESSAGE_LIST_COMMAND.get(guildLanguage) + "\n" + result).queue();
            }
        }
    }
}