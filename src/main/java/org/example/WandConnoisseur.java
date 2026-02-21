package org.example;

import org.example.config.BotConfig;
import org.example.listeners.CommandListener;
import org.example.main.Global;
import org.example.main.WandList;
import org.example.spells.Spell;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandConnoisseur{
    private static final Logger logger = LoggerFactory.getLogger(WandConnoisseur.class);
    public static JDA jda;

    public static String[] getSpellAutocomplete(String[] spellAlias, String input, int maxOutput){
        int min = 0;
        int max = spellAlias.length - 1;
        int middle;
        int comparaisonResult;
        boolean stop = false;
        String[] result;

        while(min < max && !stop){
            middle = min + (max - min)/2;
            comparaisonResult = input.compareTo(spellAlias[middle]);
            if(comparaisonResult == 0){
                max = middle;
                stop = true;
            }
            if(comparaisonResult > 0){
                min = middle;
            }else{
                max = middle;
            }
            if(min + 1 == max){
                if(spellAlias[min].startsWith(input)){
                    max = min;
                }else{
                    min = max;
                }
            }
        }
        min = Math.max(Math.min(min, max), 0);
        max = min;

        while(max < spellAlias.length && spellAlias[max].startsWith(input)){
            max++;
        }
        result = new String[Math.min(max - min, maxOutput)];
        for(int i=0; i < result.length && i < maxOutput; i++){
            result[i] = spellAlias[i + min];
        }

        return result;
    }

    // TODO
    // better autocomplete for conditions
    // add more stats for query
    // add cast states with menu
    // mob quizz (audio ?)
    // shuffle
    // refactoring wand (delay)
    // fix charges removal
    // code every spell
    // list of 1k wands
    // put on the raspberry
    // create parameter for standalone with deck evaluation, might be possible with menu instead ?
    public static void main(String[] args){
        String botToken = BotConfig.getBotToken();
        boolean generateEmotes = false;
        boolean generateWandstat = false;

        for(int i=0; i < args.length; i++){
            switch(args[i]){
                case "-emote" -> generateEmotes = true;
                case "-wandstat" -> generateWandstat = true;
                default -> {System.err.println("Unknown option: " + args[i]);System.exit(1);}
            }
        }

        if(generateEmotes){
            Spell[] spells = Global.getSpellList().getSpells();
            for(int i=0; i < spells.length; i++){
                spells[i].createEmote();
            }
        }
        if(generateWandstat){
            new WandList().generateAllSprites();
        }

        if(botToken == null || botToken.isEmpty()){
            logger.error("Bot token not found in config.properties. Please provide a valid token.");
            return;
        }

        try{
            // Build JDA instance
            jda = JDABuilder.createDefault(botToken)
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .addEventListeners(new CommandListener())
                    .build();

            jda.awaitReady();
            logger.info("Bot is online and ready!");

            registerSlashCommands();
        }catch(Exception e){
            logger.error("Error starting the bot: ", e);
        }
    }

    private static void registerSlashCommands(){
        if(jda == null){
            logger.error("JDA instance is not initialized. Cannot register slash commands.");
            return;
        }

        jda.addEventListener(new ListenerAdapter(){
            @Override
            public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event){
                List<net.dv8tion.jda.api.interactions.commands.Command.Choice> options = new ArrayList<>();
                String currentInput = event.getFocusedOption().getValue().toLowerCase();
                String preInput = "";
                String postInput = "";
                String[] validSpells;
                Pattern p;
                Matcher m;
                int maxOutput = 25;
                //System.out.println("receiving " + event.getName() + ":" + event.getFocusedOption().getName() + " -> " + currentInput);

                if(currentInput.strip().equalsIgnoreCase("quoi")){
                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("Feur", "Feur"));
                    event.replyChoices(options).queue();
                    return;
                }

                if(Arrays.asList(new String[]{"sorts", "propriete", "tri"}).contains(event.getFocusedOption().getName())){
                    preInput = currentInput.substring(0, currentInput.lastIndexOf(',') + 1) + " ";
                    currentInput = currentInput.substring(currentInput.lastIndexOf(',') + 1).strip();
                }

                switch(event.getName()){
                    case "wisp":
                        if(event.getFocusedOption().getName().equals("sort")){
                            currentInput = currentInput.strip();
                            if(currentInput.equals("")){
                                validSpells = Arrays.copyOf(Global.getAliasListRelatedProjectile(), Math.min(maxOutput, Global.getAliasListRelatedProjectile().length));
                            }else{
                                validSpells = getSpellAutocomplete(Global.getAliasListRelatedProjectile(), currentInput, maxOutput);
                            }

                            for(int i=0; i < validSpells.length; i++){
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(validSpells[i], validSpells[i]));
                            }
                            break;
                        }
                        break;
                    default:
                        switch(event.getFocusedOption().getName()){
                            case "propriete":
                                if(currentInput.equals("")){
                                    validSpells = Arrays.copyOf(Global.getSpellStringProperties(), Math.min(maxOutput, Global.getSpellStringProperties().length));
                                }else{
                                    validSpells = getSpellAutocomplete(Global.getSpellStringProperties(), currentInput, maxOutput);
                                }

                                for(int i=0; i < validSpells.length; i++){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i], preInput + validSpells[i]));
                                }
                                break;
                            case "condition":
                                int charId = Math.max(Math.max(currentInput.lastIndexOf(' '), currentInput.lastIndexOf('(')), currentInput.lastIndexOf(')'));
                                preInput = currentInput.substring(0, charId + 1);
                                currentInput = currentInput.substring(charId + 1).strip();

                                if(currentInput.equals("")){
                                    validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                                }else{
                                    validSpells = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                                }

                                for(int i=0; i < validSpells.length; i++){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice( preInput + validSpells[i], preInput + validSpells[i]));
                                }
                                break;
                            case "tri":
                                if(currentInput.equals("")){
                                    validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                                }else{
                                    p = Pattern.compile("^ *(?i)([a-z0-9_]+) *(|ASC|DESC) *$");
                                    m = p.matcher(currentInput);
                                    if(m.find()){
                                        currentInput = m.group(1);
                                        postInput += m.group(2).equalsIgnoreCase("DESC") ? " DESC" : "";
                                    }
                                    if(currentInput.equals("")){
                                        validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                                    }else{
                                        validSpells = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                                    }
                                }

                                for(int i=0; i < validSpells.length; i++){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i] + postInput, preInput + validSpells[i] + postInput));
                                }
                                break;
                            case "sorts":
                                if(currentInput.equals("")){
                                    validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                                }else{
                                    p = Pattern.compile("^(?:(inf|max|[0-9]+):)?([^:]*)(?::([0-9]+))?$");
                                    m = p.matcher(currentInput);
                                    if(m.find()){
                                        currentInput = m.group(2);
                                        if(m.group(1) != null){
                                            preInput += m.group(1) + ":";
                                        }
                                        if(m.group(3) != null){
                                            postInput += ":" + m.group(3);
                                        }
                                    }
                                    if(currentInput.equals("")){
                                        validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                                    }else{
                                        validSpells = getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput);
                                    }
                                }

                                for(int i=0; i < validSpells.length; i++){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i] + postInput, preInput + validSpells[i] + postInput));
                                }
                                break;
                            case "nom":
                                currentInput = currentInput.strip();
                                if(currentInput.equals("")){
                                    validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                                }else{
                                    validSpells = getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput);
                                }

                                for(int i=0; i < validSpells.length; i++){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(validSpells[i], validSpells[i]));
                                }
                                break;
                            case "cast_delay":
                            case "recharge_time":
                                p = Global.getDelayPattern();
                                m = p.matcher(currentInput);
                                int intDelay = 0;
                                String[] StringDelay;

                                if(m.find()){
                                    switch(m.group(3)){
                                        case "s" -> intDelay = (int)(Double.parseDouble(m.group(1))*60.0);
                                        case "f" -> {
                                            if(m.group(2).equals("")){
                                                intDelay = Integer.parseInt(m.group(1));
                                            }
                                        }
                                        default -> {
                                            if(m.group(2).equals("")){
                                                intDelay = Integer.parseInt(m.group(1));
                                            }else{
                                                intDelay = (int)(Double.parseDouble(m.group(1))*60.0);
                                            }
                                        }
                                    }
                                }

                                StringDelay = new String[]{String.format("%1$d f", intDelay).replace(',', '.'), String.format("%1$3.2f s", intDelay/60.0).replace(',', '.')};
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(StringDelay[0], StringDelay[0]));
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(StringDelay[1], StringDelay[1]));
                                break;
                            case "font":
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("pixel", "pixel"));
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("title", "title"));
                                options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("glyph", "glyph"));
                                break;
                            case "commande":
                                RestAction<List<net.dv8tion.jda.api.interactions.commands.Command>> commandList = jda.retrieveCommands();
                                String commandInput = currentInput.strip();
                                commandList.queue(commands -> {
                                    for(net.dv8tion.jda.api.interactions.commands.Command command : commands){
                                        if(commandInput.equals("") || command.getName().contains(commandInput)){
                                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(command.getName(), command.getName()));
                                        }
                                    }
                                    event.replyChoices(options).queue();
                                }, failure -> {
                                    System.out.println("Failed to retrieve commands: " + failure.getMessage());
                                });
                                return;
                        }
                        break;
                }
                if(event.getFocusedOption().getType() == OptionType.BOOLEAN){
                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("true", "true"));
                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("false", "false"));
                }
                event.replyChoices(options).queue();
            }
        });

        logger.info("Registering Slash Commands...");
        jda.updateCommands().addCommands(
            Commands.slash("ping", "Vérifie la latence du bot."),
                //.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
            Commands.slash("echo", "Répond avec votre message.")
                .addOption(OptionType.STRING, "texte", "Le texte à renvoyer.", true),
            Commands.slash("liste_sorts", "Renvoie la liste des sorts disponibles.")
                .addOption(OptionType.STRING, "propriete", "Liste des propriétés à afficher, spérarées par des \",\"", false, true)
                .addOption(OptionType.STRING, "condition", "Condition de sélection des sorts.", false, true)
                .addOption(OptionType.STRING, "tri", "Ordre d'affichage des sorts.", false, true),
            Commands.slash("flowchart_texte", "Renvoie la flowchart sous forme textuelle.")
                .addOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true)
                .addOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false)
                .addOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true)
                .addOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true)
                .addOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false)
                .addOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false)
                .addOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false)
                .addOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false)
                .addOption(OptionType.BOOLEAN, "fichier", "Renvoie la flowchart sous forme de fichier sans formatage ansi (défaut: true).", false),
            Commands.slash("flowchart_image", "Renvoie la flowchart sous forme d'une image.")
                .addOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true)
                .addOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false)
                .addOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true)
                .addOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true)
                .addOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false)
                .addOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false)
                .addOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false)
                .addOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false),
            Commands.slash("sort_info", "Renvoie les informations d'un sort.")
                .addOption(OptionType.STRING, "nom", "Sort à décrire.", true, true)
                .addOption(OptionType.BOOLEAN, "fichier", "Renvoie le code du sort sous forme de fichier à la place (défaut: false).", false),
            Commands.slash("wisp", "Renvoie la liste des modifiers pour faire un wisp au format .csv (Excel/LibreOffice Calc/etc).")
                .addOption(OptionType.STRING, "sort", "Sort à transformer en wisp.", false, true)
                .addOption(OptionType.INTEGER, "lifetime_min", "Lifetime minimum du projectile, remplace celui du projectile sélectionné.", false)
                .addOption(OptionType.INTEGER, "lifetime_max", "Lifetime maximum du projectile, remplace celui du projectile sélectionné.", false)
                .addOption(OptionType.INTEGER, "nb_modifier", "Nombre de modificateurs max de chaque type (défaut: 11, max: 21).", false),
            Commands.slash("texte", "Renvoie une image du texte sur fond transparent écrit avec une font de noita.")
                .addOption(OptionType.STRING, "texte", "Texte à écrire.", true)
                .addOption(OptionType.STRING, "font", "Font à utiliser pour le texte.", false, true)
                .addOption(OptionType.INTEGER, "taille", "Taille de la font à utiliser pour le texte.", false)
                .addOption(OptionType.STRING, "couleur", "Couleur du texte au format hexa RRGGBB(AA) (défaut: FFFFFFFF).", false),
            Commands.slash("feur", "Quoi ?"),
            Commands.slash("help", "Liste toutes les commandes disponibles.")
                .addOption(OptionType.STRING, "commande", "Nom d'une commande spécifique pour connaitre ses options.", false, true)
        ).queue(success -> logger.info("Slash commands registered successfully!"), failure -> logger.error("Failed to register slash commands: ", failure));
    }
}