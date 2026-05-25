package org.example.listeners;

import org.example.commands.*;

import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener extends ListenerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(CommandListener.class);
    private final Map<String, Command> commands = new HashMap<>();

    public CommandListener(){
        commands.put("cast_state", new CastStateCommand());
        commands.put("deck_animation", new DeckAnimationCommand());
        commands.put("echo", new EchoCommand());
        commands.put("feur", new FeurCommand());
        commands.put("flowchart_image", new ImageFlowchartCommand());
        commands.put("flowchart_texte", new StringFlowchartCommand());
        commands.put("help", new HelpCommand());
        commands.put("liste_sorts", new SpellListCommand());
        commands.put("ping", new PingCommand());
        commands.put("sort_info", new SpellInfoCommand());
        commands.put("texte", new TextCommand());
        commands.put("wisp", new WispCommand());
        logger.info("Registered " + commands.size() + " commands.");
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
}