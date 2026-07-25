package org.example.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public record CommandOption(OptionType optionType, String name, String description, boolean required, boolean autoComplete){
    public String toString(){
        return "[" + optionType.toString() + ", \"" + this.name + "\", \"" + this.description + "\" (" + this.description.length() + "/100), " + this.required + ", " + this.autoComplete + "]";
    }
}