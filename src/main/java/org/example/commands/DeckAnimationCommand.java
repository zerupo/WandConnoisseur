package org.example.commands;

import org.example.main.CardHistory;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.menu.MenuDeckAnimation;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;


public class DeckAnimationCommand extends Command{
    public DeckAnimationCommand(){
        this.name = "deck_animation";
        this.description = "Renvoie un menu permettant de voir les différentes étapes du deck/main/défausse.";
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true),
            new CommandOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false, false),
            new CommandOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true),
            new CommandOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true),
            new CommandOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false, false),
            new CommandOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false, false),
            new CommandOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false, false),
            new CommandOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false, false)
        };
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        Wand wand = Global.slashInteractionToWand(event);
        CardHistory cardHistory;

        if(wand == null){
            return;
        }

        cardHistory = wand.getCardHistory(true);
        event.deferReply(false).queue();
        MenuDeckAnimation menu = new MenuDeckAnimation(event.getId(), "titre", "description", cardHistory);

        Global.menuManager.add(menu);
        menu.replyHookEvent(event);
        Global.setLastWand(wand);
    }
}