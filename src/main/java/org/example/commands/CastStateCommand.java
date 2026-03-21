package org.example.commands;

import org.example.main.CastState;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.menu.MenuTree;
import org.example.spells.Spell;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


public class CastStateCommand implements Command{
    @Override
    public String getName() {
        return "cast_state";
    }

    @Override
    public String getDescription() {
        return "Renvoie un menu contenant les différent cast states.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        String fileName = event.getId() + ".png";
        Wand wand = Global.slashInteractionToWand(event);
        Spell[] spells;
        String spellsEmote = "";
        CastState[] castStates;

        if(wand == null){
            return;
        }

        event.deferReply(false).queue();

        spells = wand.getSpells();
        for(Spell spell : spells){
            spellsEmote += spell.getEmote();
            wand.putSpellEnd(spell);
        }
        castStates = wand.getCastState(true);

        MenuTree menu = new MenuTree(event.getId(), "Cast state", "", Global.JPanelToFile(wand.getWandJPanel(), Global.getPathAutoDelete() + "wand_" + fileName), spellsEmote);
        for(int i=0; i < castStates.length; i++){
            menu.addChild(castStates[i].toMenuTree("",(i + 1) + ") Cast state initial"));
        }

        Global.menuManager.add(menu);
        menu.replyHookEvent(event);
    }
}