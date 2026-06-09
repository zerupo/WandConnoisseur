package org.example;

import org.example.config.BotConfig;
import org.example.config.EmoteConfig;
import org.example.listeners.AutoCompleteListener;
import org.example.listeners.CommandListener;
import org.example.listeners.MenuListener;
import org.example.main.CardPool;
import org.example.main.CastState;
import org.example.main.Global;
import org.example.main.WandList;
import org.example.projectiles.Projectile;
import org.example.script.Script;
import org.example.spells.Spell;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.entities.emoji.ApplicationEmoji;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandConnoisseur{
    private static final Logger logger = LoggerFactory.getLogger(WandConnoisseur.class);
    public static JDA jda;

    private static void generateEmotes(boolean newOnly){
        Spell[] spells = Global.getSpellList().getSpells(false);
        Projectile[] projectiles = Global.getProjectileList().getProjectiles(false);
        Script[] scripts = Global.getScriptList().getScripts(false);
        Set<String> emojis = null;

        if(newOnly){
            emojis = jda.retrieveApplicationEmojis().complete().stream().map(ApplicationEmoji::getName).collect(Collectors.toSet());
        }


        for(Spell spell : spells){
            if(!newOnly || !emojis.contains(Global.truncate(spell.getClass().getSimpleName().toLowerCase(), 32))){
                spell.createEmote();
            }
        }
        for(Projectile projectile : projectiles){
            if(!newOnly || !emojis.contains(Global.truncate(projectile.getClass().getSimpleName().toLowerCase(), 32))){
                projectile.createEmote();
            }
        }
        for(Script script : scripts){
            if(!newOnly || !emojis.contains(Global.truncate(script.getClass().getSimpleName().toLowerCase(), 32))){
                script.createEmote();
            }
        }

        // default emotes
        if(!newOnly || !emojis.contains("spell")){
            new Spell(){
                @Override protected void initialization(){}
                @Override protected void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){}
            }.createEmote();
        }
        if(!newOnly || !emojis.contains("projectile")){
            new Projectile(){
                @Override protected void initialization(){}
            }.createEmote();
        }
        if(!newOnly || !emojis.contains("script")){
            new Script(){
                @Override protected void initialization(){}
            }.createEmote();
        }
    }

    // TODO
    // better autocomplete for conditions
    // mob quizz (audio ?)
    // shuffle
    // always cast
    // better structure for projectiles
    // code every spell
    // list of 1k wands
    // put on the raspberry
    public static void main(String[] args){
        String botToken = BotConfig.getBotToken();
        boolean generateEmotes = false;
        boolean generateNewEmotes = false;
        boolean generateWandstat = false;

        Global.autoDeleteFiles();

        for(String arg : args){
            switch(arg){
                case "-emote" -> generateEmotes = true;
                case "-emote_new" -> generateNewEmotes = true;
                case "-wandstat" -> generateWandstat = true;
                default -> {
                    System.err.println("Unknown option: " + arg);
                    System.exit(1);
                }
            }
        }

        if(generateEmotes){
            generateEmotes(false);
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
                .addEventListeners(new AutoCompleteListener())
                .addEventListeners(new CommandListener())
                .addEventListeners(new MenuListener())
                .build();

            jda.awaitReady();

            registerSlashCommands();
            if(generateNewEmotes){
                generateEmotes(true);
            }
            EmoteConfig.initConfig(true);
            logger.info("Bot is online and ready!");
        }catch(Exception e){
            logger.error("Error starting the bot: ", e);
        }
    }

    private static void registerSlashCommands(){
        if(jda == null){
            logger.error("JDA instance is not initialized. Cannot register slash commands.");
            return;
        }

        logger.info("Registering Slash Commands...");
        jda.updateCommands().addCommands(
            Commands.slash("cast_state", "Renvoie une image ou un menu contenant les différent cast states.")
                .addOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true)
                .addOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false)
                .addOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true)
                .addOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true)
                .addOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false)
                .addOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false)
                .addOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false)
                .addOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false)
                .addOption(OptionType.STRING, "type", "format du cast state (défaut: png).", false, true),
            Commands.slash("deck_animation", "Renvoie un menu permettant de voir les différentes étapes du deck/main/défausse")
                .addOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true)
                .addOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false)
                .addOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true)
                .addOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true)
                .addOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false)
                .addOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false)
                .addOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false)
                .addOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false),
            Commands.slash("echo", "Répond avec votre message.")
                .addOption(OptionType.STRING, "texte", "Le texte à renvoyer.", true),
            Commands.slash("feur", "Quoi ?"),
            Commands.slash("flowchart_image", "Renvoie la flowchart sous forme d'une image.")
                .addOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true)
                .addOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false)
                .addOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true)
                .addOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true)
                .addOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false)
                .addOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false)
                .addOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false)
                .addOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false)
                .addOption(OptionType.STRING, "type", "format de la flowchart (défaut: png).", false, true),
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
            Commands.slash("help", "Liste toutes les commandes disponibles.")
                .addOption(OptionType.STRING, "commande", "Nom d'une commande spécifique pour connaitre ses options.", false, true),
            Commands.slash("liste_sorts", "Renvoie la liste des sorts disponibles.")
                .addOption(OptionType.STRING, "propriete", "Liste des propriétés à afficher, spérarées par des \",\"", false, true)
                .addOption(OptionType.STRING, "condition", "Condition de sélection des sorts.", false, true)
                .addOption(OptionType.STRING, "tri", "Ordre d'affichage des sorts.", false, true),
            Commands.slash("ping", "Vérifie la latence du bot."),
            Commands.slash("sort_info", "Renvoie les informations d'un sort.")
                .addOption(OptionType.STRING, "nom", "Sort à décrire.", true, true)
                .addOption(OptionType.BOOLEAN, "fichier", "Renvoie le code du sort sous forme de fichier à la place (défaut: false).", false),
            Commands.slash("texte", "Renvoie une image du texte sur fond transparent écrit avec une font de noita.")
                .addOption(OptionType.STRING, "texte", "Texte à écrire.", true)
                .addOption(OptionType.STRING, "font", "Font à utiliser pour le texte.", false, true)
                .addOption(OptionType.INTEGER, "taille", "Taille de la font à utiliser pour le texte.", false)
                .addOption(OptionType.STRING, "couleur", "Couleur du texte au format hexa RRGGBB(AA) (défaut: FFFFFFFF).", false),
            Commands.slash("wisp", "Renvoie la liste des modifiers pour faire un wisp au format .csv (Excel/LibreOffice Calc/etc).")
                .addOption(OptionType.STRING, "sort", "Sort à transformer en wisp.", false, true)
                .addOption(OptionType.INTEGER, "lifetime_min", "Lifetime minimum du projectile, remplace celui du projectile sélectionné.", false)
                .addOption(OptionType.INTEGER, "lifetime_max", "Lifetime maximum du projectile, remplace celui du projectile sélectionné.", false)
                .addOption(OptionType.INTEGER, "nb_modifier", "Nombre de modificateurs max de chaque type (défaut: 11, max: 21).", false)
        ).queue(success -> logger.info("Slash commands registered successfully!"), failure -> logger.error("Failed to register slash commands: ", failure));
    }
}