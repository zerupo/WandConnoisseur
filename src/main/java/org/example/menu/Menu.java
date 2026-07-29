package org.example.menu;

import java.util.Locale;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;

public abstract class Menu{
    protected String id;
    protected final String title;
    protected final String description;

    public Menu(String id, String title, String description){
        this.id = id;
        this.title = title.equals("") ? "\u200B" : title;
        this.description = description;
    }

    public String getId(){
        return this.id;
    }

    public static String getRootId(String id){
        int semicolonIndex = id.indexOf(";");
        return semicolonIndex != -1 ? id.substring(0, semicolonIndex) : id;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }

    // abstract
    public abstract ActionRow getActionRow(Locale language);
    public abstract void replyHookEvent(SlashCommandInteractionEvent event);

    // to override
    public boolean editEvent(StringSelectInteractionEvent event){
        return false;
    }

    public boolean editEvent(ButtonInteractionEvent event){
        return false;
    }

    public boolean editEvent(ModalInteractionEvent event){
        return false;
    }

    public Menu getMenuById(String id){
        return this.id.equals(id) ? this : null;
    }

    public MessageEmbed toMessageEmbed(){
        EmbedBuilder embed = new EmbedBuilder().setTitle(this.title).setDescription(this.description);
        embed.setTitle(this.title).setDescription(this.description);
        return embed.build();
    }

    public void deleteFiles(){
        // nothing
    }
}
