package org.example.commands;

import org.example.main.Global;
import org.example.main.Wand;
import org.example.spells.*;

import java.nio.charset.StandardCharsets;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class StringFlowchartCommand implements Command{
    @Override
    public String getName(){
        return "flowchart_texte";
    }

    @Override
    public String getDescription(){
        return "Renvoie la flowchart sous forme textuelle.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping fileOption = event.getOption("fichier");
        boolean file = false;
        StringBuilder result = new StringBuilder();
        Wand wand = Global.slashInteractionToWand(event);
        Spell[] spells;
        String[] statOptions = new String[]{"draw", "cast_delay", "recharge_time", "mana_max", "mana_regen", "spread", "speed"};
        boolean statChanged = false;

        if(wand == null){
            return;
        }
        for(int i=0; i < statOptions.length; i++){
            if(event.getOption(statOptions[i]) != null){
                statChanged = true;
                break;
            }
        }
        if(fileOption != null){
            file = fileOption.getAsBoolean();
        }

        event.deferReply(false).queue();

        if(statChanged){
            if(wand.getShuffle()){
                result.append("Shuffle: Yes");
            }else{
                result.append("Shuffle: No");
            }
            result.append(", Spells/cast: ").append(wand.getNbDraw()).append(", Cast delay: ").append(String.format("%1$df (%2$3.2fs)", wand.getCastDelay(), wand.getCastDelay()/60.0)).append(", Recharge time: ").append(String.format("%1$df (%2$3.2fs)", wand.getRechargeTime(), wand.getRechargeTime()/60.0)).append(", Max mana: ").append(wand.getMaxMana()).append(", Mana regen: ").append(wand.getRegenMana()).append(", Slots: ").append(wand.getNbSlot()).append(", Spread: ").append(wand.getSpread()).append("°, Speed: x").append(wand.getSpeed()).append("\n");
        }
        result.append("Spells: ");
        spells = wand.getSpells();
        for(int i=0; i < spells.length; i++){
            if(i > 0){
                result.append(", ");
            }
            result.append(spells[i].getName());
        }
        result.append("\n\n").append(wand.getFlowchartString(true, !file));

        if(file){
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
}