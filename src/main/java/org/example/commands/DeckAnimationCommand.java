package org.example.commands;

import org.example.main.CardHistory;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.menu.MenuDeckAnimation;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


public class DeckAnimationCommand implements Command{
    @Override
    public String getName() {
        return "deck_animation";
    }

    @Override
    public String getDescription() {
        return "Renvoie un menu permettant de voir les différentes étapes du deck/main/défausse";
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
    }
}