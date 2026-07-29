package org.example.commands;

import org.example.localization.LocalizedText;
import org.example.main.Global;
import org.example.main.SpellList;
import org.example.spells.Spell;

import java.io.File;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.FileUpload;

public class SpellInfoCommand extends CommandLocal{
    private static final LocalizedText COMMAND_SPELL_INFO = Global.getLanguageManager().get("COMMAND_SPELL_INFO");
    private static final LocalizedText COMMAND_SPELL_INFO_DESCRIPTION = Global.getLanguageManager().get("COMMAND_SPELL_INFO_DESCRIPTION");
    private static final LocalizedText COMMAND_SPELL_INFO_NAME = Global.getLanguageManager().get("COMMAND_SPELL_INFO_NAME");
    private static final LocalizedText COMMAND_SPELL_INFO_NAME_DESCRIPTION = Global.getLanguageManager().get("COMMAND_SPELL_INFO_NAME_DESCRIPTION");
    private static final LocalizedText COMMAND_SPELL_INFO_FILE = Global.getLanguageManager().get("COMMAND_SPELL_INFO_FILE");
    private static final LocalizedText COMMAND_SPELL_INFO_FILE_DESCRIPTION = Global.getLanguageManager().get("COMMAND_SPELL_INFO_FILE_DESCRIPTION");
    private static final LocalizedText ERROR_UNKNOWN_SPELL = Global.getLanguageManager().get("ERROR_UNKNOWN_SPELL");

    public SpellInfoCommand(){
        this.name = COMMAND_SPELL_INFO;
        this.description = COMMAND_SPELL_INFO_DESCRIPTION;
        this.commandOptions = new CommandOption[]{
            new CommandOption(OptionType.STRING, COMMAND_SPELL_INFO_NAME, COMMAND_SPELL_INFO_NAME_DESCRIPTION, true, true),
            new CommandOption(OptionType.BOOLEAN, COMMAND_SPELL_INFO_FILE, COMMAND_SPELL_INFO_FILE_DESCRIPTION, false, false)
        };
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        SpellList spellList = Global.getSpellList();
        OptionMapping nameOption = event.getOption("name");
        OptionMapping fileOption = event.getOption("file");
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
            event.reply(ERROR_UNKNOWN_SPELL.get(event, spellString)).setEphemeral(true).queue();
            return;
        }

        if(file){
            textFile = new File(spellPath + spell.getClass().getSimpleName() + ".java");
            event.reply("").addFiles(FileUpload.fromData(textFile, spell.getClass().getSimpleName() + ".java")).queue();
        }else{
            event.reply(spell.getEmote()).complete();
            event.getChannel().sendMessage(spell.getInfoString(true)).queue();
        }
    }
}