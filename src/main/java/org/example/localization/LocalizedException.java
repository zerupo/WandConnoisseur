package org.example.localization;

import static org.example.localization.LanguageManager.Language;

import java.util.Locale;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class LocalizedException extends RuntimeException{
    private final LocalizedText localizedText;
    private final String[] args;

    public LocalizedException(LocalizedText localizedText, String[] args){
        this.localizedText = localizedText;
        this.args = args;
    }

    public LocalizedException(LocalizedText localizedText, String arg){
        this.localizedText = localizedText;
        this.args = new String[]{arg};
    }

    public LocalizedException(LocalizedText localizedText){
        this.localizedText = localizedText;
        this.args = new String[0];
    }

    public String toString(SlashCommandInteractionEvent event){
        return this.localizedText.get(event, this.args);
    }

    public String toString(StringSelectInteractionEvent event){
        return this.localizedText.get(event, this.args);
    }

    public String toString(ButtonInteractionEvent event){
        return this.localizedText.get(event, this.args);
    }

    public String toString(ModalInteractionEvent event){
        return this.localizedText.get(event, this.args);
    }

    public String toString(Language language){
        return this.localizedText.get(language, this.args);
    }

    public String toString(Locale language){
        return this.localizedText.get(language, this.args);
    }
}