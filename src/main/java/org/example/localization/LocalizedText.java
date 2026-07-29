package org.example.localization;

import static org.example.localization.LanguageManager.Language;

import java.util.Locale;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class LocalizedText{
    private final Part[][] parts = new Part[Language.values().length][];

    public LocalizedText(Part[][] parts){
        for(int i=0; i < this.parts.length; i++){
            if(i < parts.length){
                this.parts[i] = parts[i];
            }else{
                this.parts[i] = new Part[0];
            }
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        int i = 0;

        for(Language language : Language.values()){
            if(result.length() > 0){
                result.append("\n");
            }
            result.append(language.toString()).append(":");
            for(Part part : this.parts[i]){
                result.append(part.toString());
            }
            i++;
        }

        return result.toString();
    }

    private static Language localeToLanguage(Locale locale){
        try{
            return Language.valueOf(locale.getLanguage().toLowerCase());
        }catch(IllegalArgumentException e){
            System.out.println("language not found: \"" + Language.valueOf(locale.getLanguage().toLowerCase()) + "\"");
            return Language.values()[0];
        }
    }

    public String get(Language language, String[] args){
        StringBuilder stringBuilder = new StringBuilder();
        Part[] parts = this.parts[language.ordinal()].length == 0 ? this.parts[Language.en.ordinal()] : this.parts[language.ordinal()];

        for(Part part : parts){
            part.append(stringBuilder, args);
        }

        return stringBuilder.toString();
    }

    public String get(SlashCommandInteractionEvent event, String[] args){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), args);
    }

    public String get(StringSelectInteractionEvent event, String[] args){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), args);
    }

    public String get(ButtonInteractionEvent event, String[] args){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), args);
    }

    public String get(ModalInteractionEvent event, String[] args){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), args);
    }

    public String get(Locale language, String[] args){
        return this.get(localeToLanguage(language), args);
    }

    public String get(Language language, String arg){
        StringBuilder stringBuilder = new StringBuilder();
        Part[] parts = this.parts[language.ordinal()].length == 0 ? this.parts[Language.en.ordinal()] : this.parts[language.ordinal()];

        for(Part part : parts){
            part.append(stringBuilder, arg);
        }

        return stringBuilder.toString();
    }

    public String get(SlashCommandInteractionEvent event, String arg){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), arg);
    }

    public String get(StringSelectInteractionEvent event, String arg){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), arg);
    }

    public String get(ButtonInteractionEvent event, String arg){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), arg);
    }

    public String get(ModalInteractionEvent event, String arg){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()), arg);
    }

    public String get(Locale language, String arg){
        return this.get(localeToLanguage(language), arg);
    }

    public String get(Language language){
        StringBuilder stringBuilder = new StringBuilder();
        Part[] parts = this.parts[language.ordinal()].length == 0 ? this.parts[Language.en.ordinal()] : this.parts[language.ordinal()];

        for(Part part : parts){
            part.append(stringBuilder);
        }

        return stringBuilder.toString();
    }

    public String get(SlashCommandInteractionEvent event){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()));
    }

    public String get(StringSelectInteractionEvent event){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()));
    }

    public String get(ButtonInteractionEvent event){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()));
    }

    public String get(ModalInteractionEvent event){
        return this.get(localeToLanguage(event.getGuildLocale().toLocale()));
    }

    public String get(Locale language){
        return this.get(localeToLanguage(language));
    }
}