package org.example.commands;

import org.example.main.CastState;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.menu.MenuTree;
import org.example.spells.Spell;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.FileUpload;

public class CastStateCommand extends Command{
    public CastStateCommand(){
        this.name = "cast_state";
        this.description = "Renvoie une image ou un menu contenant les différent cast states.";
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true),
            new CommandOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false, false),
            new CommandOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true),
            new CommandOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true),
            new CommandOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false, false),
            new CommandOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false, false),
            new CommandOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false, false),
            new CommandOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false, false),
            new CommandOption(OptionType.STRING, "type", "format du cast state (défaut: png).", false, true)
        };
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping typeOption = event.getOption("type");
        int type = 0;

        if(typeOption != null){
            switch(typeOption.getAsString()){
                case "png" -> {}
                case "svg" -> type = 1;
                case "svg_light" -> type = 2;
                case "menu" -> type = 3;
                default -> {
                    event.reply("\"" + typeOption.getAsString() + "\" n'est pas un type valide.").setEphemeral(true).queue();
                    return;
                }
            }
        }

        String fileName = event.getId() + ".png";
        String[] statOptions = new String[]{"draw", "cast_delay", "recharge_time", "mana_max", "mana_regen", "spread", "speed"};
        Wand wand = Global.slashInteractionToWand(event);
        boolean eventReplied = false;
        boolean statChanged = false;

        if(wand == null){
            return;
        }

        event.deferReply(false).queue();

        if(type == 3){
            CastState[] castStates = wand.getCastState(true);
            Spell[] spells = wand.getSpells(false);
            StringBuilder spellsEmote = new StringBuilder();

            for(Spell spell : spells){
                spellsEmote.append(spell.getEmote());
                wand.putSpellEnd(spell);
            }

            MenuTree menu = new MenuTree(event.getId(), "Cast state", "", Global.JPanelToFile(wand.getWandJPanel(), Global.getPathAutoDelete() + "wand_" + fileName), spellsEmote.toString());
            for(int i=0; i < castStates.length; i++){
                menu.addChild(castStates[i].toMenuTree("",(i + 1) + ") Cast state initial"));
            }

            Global.menuManager.add(menu);
            menu.replyHookEvent(event);
        }else{
            FileUpload wandStatImage = null;
            FileUpload castStateImage;

            for(String statOption : statOptions){
                if(event.getOption(statOption) != null){
                    statChanged = true;
                    break;
                }
            }

            if(statChanged){
                wandStatImage = Global.JPanelToUpload(wand.getStatJPanel(), "wandstats.png");
            }
            FileUpload wandImage = Global.JPanelToUpload(wand.getWandJPanel(), "wand.png");
            castStateImage = switch(type){
                case 0 -> Global.bufferedImageToUpload(wand.getCastStateImage(true), "caststate.png");
                case 1 -> Global.byteToUpload(wand.getCastStateImageSVG(true, true), "caststate.svg");
                case 2 -> Global.byteToUpload(wand.getCastStateImageSVG(true, false), "caststate.svg");
                default -> null;
            };

            if(wandStatImage != null){
                event.getHook().editOriginal("").setFiles(wandStatImage).queue();
                eventReplied = true;
            }
            if(wandImage != null){
                if(eventReplied){
                    event.getChannel().sendFiles(wandImage).queue();
                }else{
                    event.getHook().editOriginal("").setFiles(wandImage).queue();
                }
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

        Global.setLastWand(wand);
    }
}