package org.example.commands;

import org.example.main.Global;
import org.example.main.SpellList;
import org.example.spells.Spell;

import java.io.File;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;

public class SpellInfoCommand implements Command{
    @Override
    public String getName(){
        return "sort_info";
    }

    @Override
    public String getDescription(){
        return "Renvoie les informations d'un sort.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        SpellList spellList = Global.getSpellList();
        OptionMapping nameOption = event.getOption("nom");
        OptionMapping fileOption = event.getOption("fichier");
        String spellString = "";
        String spellPath = "./src/main/java/org/example/spells/";
        Spell spell;
        File textFile;
        boolean file = false;

        if(nameOption != null){
            spellString = nameOption.getAsString().trim().toLowerCase();
        }
        if(fileOption != null){
            file = fileOption.getAsBoolean();
        }

        spell = spellList.getSpell(spellString);

        if(spell == null){
            event.reply("Sort \"" + spellString + "\" inconnu").setEphemeral(true).queue();
            return;
        }

        if(file){
            textFile = new File(spellPath + spell.getClass().getSimpleName() + ".java");
            event.reply("").addFiles(FileUpload.fromData(textFile, spell.getClass().getSimpleName() + ".java")).queue();
        }else{
            event.reply(spell.getEmote()).queue();
            event.getChannel().sendMessage(spell.getInfoString(true)).queue();
        }
    }
}