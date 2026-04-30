package org.example.commands;

import net.dv8tion.jda.api.utils.FileUpload;
import org.example.main.CastState;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.menu.MenuTree;
import org.example.spells.Spell;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

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
        OptionMapping menuOption = event.getOption("menu");
        boolean menuFormat = false;
        String fileName = event.getId() + ".png";
        Wand wand = Global.slashInteractionToWand(event);
        Spell[] spells;
        String spellsEmote = "";
        CastState[] castStates;
        boolean eventReplied = false;

        if(wand == null){
            return;
        }

        if(menuOption != null){
            menuFormat = menuOption.getAsBoolean();
        }

        event.deferReply(false).queue();

        spells = wand.getSpells();
        for(Spell spell : spells){
            spellsEmote += spell.getEmote();
            wand.putSpellEnd(spell);
        }
        castStates = wand.getCastState(true);

        if(menuFormat){
            MenuTree menu = new MenuTree(event.getId(), "Cast state", "", Global.JPanelToFile(wand.getWandJPanel(), Global.getPathAutoDelete() + "wand_" + fileName), spellsEmote);
            for(int i=0; i < castStates.length; i++){
                menu.addChild(castStates[i].toMenuTree("",(i + 1) + ") Cast state initial"));
            }

            Global.menuManager.add(menu);
            menu.replyHookEvent(event);
        }else{
            FileUpload wandImage = Global.JPanelToUpload(wand.getWandJPanel(), "wand.png");
            FileUpload castStateImage = Global.bufferedImageToUpload(CastState.toImage(castStates), "castState.png");

            if(wandImage != null){
                event.getHook().editOriginal("").setFiles(wandImage).queue();
                eventReplied = true;
            }
            if(castStateImage != null){
                if(eventReplied){
                    event.getChannel().sendFiles(castStateImage).queue();
                }else{
                    event.getHook().editOriginal("").setFiles(castStateImage).queue();
                }
                eventReplied = true;
            }
            if(!eventReplied){
                event.getHook().editOriginal("Erreur lors de la création de l'image").queue();
            }
        }
    }
}