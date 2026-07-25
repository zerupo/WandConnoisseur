package org.example.listeners;

import org.example.commands.*;
import org.example.WandConnoisseur;

import java.util.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener extends ListenerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(CommandListener.class);
    private static final Map<String, Command> commands = createCommandMap();

    public CommandListener(){
        // nothing
    }

    @Override
    public void onReady(@NotNull ReadyEvent event){
        SelfUser user = event.getJDA().getSelfUser();

        logger.info("JDA is ready! Logged in as " + user.getName() + "#" + user.getDiscriminator());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event){
        String commandName = event.getName();
        Command command = commands.get(commandName);

        if(command != null){
            long startTime = System.nanoTime();
            logger.debug("User \033[0;31m" + event.getUser().getName() + "\u001b[0;0m executing slash command \"" + event.getInteraction().getCommandString() + "\"");
            command.executeSlash(event);
            logger.debug("Executed slash command in " + (System.nanoTime() - startTime)/1000000.0 + "ms \"" + event.getInteraction().getCommandString() + "\"");
        }else{
            logger.warn("Unknown slash command: " + commandName + " from user: \033[0;31m" + event.getUser().getName() + "\u001b[0;0m");
            event.reply("Unknown command!").setEphemeral(true).queue();
        }
    }

    private static Map<String, Command> createCommandMap(){
        Map<String, Command> map = new HashMap<>();

        for(Class<? extends Command> clazz : new Reflections("org.example.commands").getSubTypesOf(Command.class)){
            try{
                Command command = clazz.getDeclaredConstructor().newInstance();
                map.put(command.getName(), command);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        logger.info("Registered " + map.size() + " commands.");

        return map;
    }

    public static void registerSlashCommands(){
        JDA jda = WandConnoisseur.jda;

        if(jda == null){
            logger.error("JDA instance is not initialized. Cannot register slash commands.");
            return;
        }

        CommandListUpdateAction commandsList = jda.updateCommands();
        SlashCommandData currentCommand;

        logger.info("Registering Slash Commands...");

        for(Command command : commands.values()){
            currentCommand = Commands.slash(command.getName(), command.getDescription());
            for(CommandOption option : command.getCommandOptions()){
                currentCommand.addOption(option.optionType(), option.name(), option.description(), option.required(), option.autoComplete());
            }
            commandsList = commandsList.addCommands(currentCommand);
        }
        commandsList.queue(success -> logger.info("Slash commands registered successfully!"), failure -> logger.error("Failed to register slash commands: ", failure));
    }
}