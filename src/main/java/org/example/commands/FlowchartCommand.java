package org.example.commands;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.example.main.Global;
import org.example.main.Wand;
import org.example.spells.Spell;

import java.nio.charset.StandardCharsets;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.FileUpload;

public class FlowchartCommand extends Command{
    public FlowchartCommand(){
        this.name = "flowchart";
        this.description = "Renvoie la flowchart sous forme sous la forme demandé.";
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, "sorts", "Sorts à séparer par des \",\", précéder par 0: max: ou inf: pour modifier les charges (défaut: inf:).", true, true),
            new CommandOption(OptionType.INTEGER, "draw", "Nombre de sorts/lancer de la baguette (défaut: 1).", false, false),
            new CommandOption(OptionType.STRING, "cast_delay", "Délais des sorts en frames ou secondes, ajouter \"f\" ou \"s\" à la fin pour préciser (défaut: 0).", false, true),
            new CommandOption(OptionType.STRING, "recharge_time", "Temps de recharge de la baguette en frames ou secondes, ajouter \"f\" ou \"s\" à la fin (défaut: 0).", false, true),
            new CommandOption(OptionType.INTEGER, "mana_max", "Mana max de la baguette (défaut: 1000000).", false, false),
            new CommandOption(OptionType.INTEGER, "mana_regen", "Régénération de mana de la baguette en mana/sec (défaut: 1000000).", false, false),
            new CommandOption(OptionType.NUMBER, "spread", "Dispersion de la baguette (défaut: 0.0).", false, false),
            new CommandOption(OptionType.NUMBER, "speed", "Multiplicateur caché de la vitesse des projectiles (défaut: 1.0).", false, false),
            new CommandOption(OptionType.STRING, "type", "format de la flowchart (défaut: png).", false, true)
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
                case "text" -> type = 3;
                case "text_file" -> type = 4;
                default -> {
                    event.reply("\"" + typeOption.getAsString() + "\" n'est pas un type valide.").setEphemeral(true).queue();
                    return;
                }
            }
        }

        Wand wand = Global.slashInteractionToWand(event);
        String[] statOptions = new String[]{"draw", "cast_delay", "recharge_time", "mana_max", "mana_regen", "spread", "speed"};
        boolean statChanged = false;
        boolean eventReplied = false;

        if(wand == null){
            return;
        }
        for(String statOption : statOptions){
            if(event.getOption(statOption) != null){
                statChanged = true;
                break;
            }
        }

        event.deferReply(false).queue();

        if(type <= 2){
            FileUpload wandStatImage = null;
            FileUpload wandImage;
            FileUpload flowchartImage;

            if(statChanged){
                wandStatImage = Global.JPanelToUpload(wand.getStatJPanel(), "wandstats.png");
            }
            wandImage = Global.JPanelToUpload(wand.getWandJPanel(), "wand.png");
            flowchartImage = switch(type){
                case 0 -> Global.bufferedImageToUpload(wand.getFlowchartImage(true), "flowchart.png");
                case 1 -> Global.byteToUpload(wand.getFlowchartImageSVG(true, true), "flowchart.svg");
                case 2 -> Global.byteToUpload(wand.getFlowchartImageSVG(true, false), "flowchart.svg");
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

            if(eventReplied){
                event.getChannel().sendFiles(flowchartImage).queue();
            }else{
                event.getHook().editOriginal("").setFiles(flowchartImage).queue();
            }
        }else{
            StringBuilder result = new StringBuilder();
            Spell[] spells = wand.getSpells(false);

            if(statChanged){
                if(wand.getShuffle()){
                    result.append("Shuffle: Yes");
                }else{
                    result.append("Shuffle: No");
                }
                result.append(", Spells/cast: ").append(wand.getNbDraw()).append(", Cast delay: ").append(Global.delayFormat(wand.getCastDelay())).append(", Recharge time: ").append(Global.delayFormat(wand.getRechargeTime())).append(", Max mana: ").append(wand.getMaxMana()).append(", Mana regen: ").append(wand.getRegenMana()).append(", Slots: ").append(wand.getNbSlot()).append(", Spread: ").append(wand.getSpread()).append("°, Speed: x").append(wand.getSpeed()).append("\n");
            }
            result.append("Spells: ");
            for(int i=0; i < spells.length; i++){
                if(i > 0){
                    result.append(", ");
                }
                result.append(spells[i].getName());
            }
            result.append("\n\n").append(wand.getFlowchartString(true, type != 4));

            if(type == 4){
                try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                    baos.write(result.toString().getBytes(StandardCharsets.UTF_8));
                    event.getHook().editOriginal("").setFiles(FileUpload.fromData(baos.toByteArray(), "text_flowchart.txt")).queue();
                }catch(Exception e){
                    event.getHook().editOriginal("Erreur lors de l'écriture du fichier.").queue();
                    e.printStackTrace();
                }
            }else{
                Global.sendMessage(result.toString(), event, true, true);
            }
        }

        Global.setLastWand(wand);
    }
}