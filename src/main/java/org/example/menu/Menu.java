package org.example.menu;

import java.io.File;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;

public abstract class Menu{
    protected String id;
    protected final String title;
    protected final String description;
    protected final File imageFile;
    protected final String imageFallBack;

    public Menu(String id, String title, String description, File imageFile, String imageFallBack){
        this.id = id;
        this.title = title.equals("") ? "\u200B" : title;
        this.description = description;
        this.imageFile = imageFile;
        this.imageFallBack = imageFallBack;
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

    public MessageEmbed toMessageEmbed(){
        EmbedBuilder embed = new EmbedBuilder().setTitle(this.title).setDescription(this.description + (this.imageFile != null && !this.imageFile.exists() ? "\n" + this.imageFallBack : ""));
        if(!this.imageFallBack.equals("") && (this.imageFile == null || !this.imageFile.exists())){
            if(this.title.equals("\u200B")){
                embed.setTitle(this.imageFallBack).setDescription(this.description);
            }else{
                embed.setTitle(this.title).setDescription(this.description + (this.description.equals("") ? "" : "\n") + this.imageFallBack);
            }
        }else{
            embed.setTitle(this.title).setDescription(this.description);
        }
        if(this.imageFile != null){
            embed.setImage("attachment://" + this.id + ".png");
        }
        return embed.build();
    }

    public abstract ActionRow getActionRow();
    public abstract Menu getMenuById(String id);
    public abstract void replyHookEvent(SlashCommandInteractionEvent event);
    public abstract void editEvent(StringSelectInteractionEvent event);

    // to override
    public void deleteFiles(){
        if(this.imageFile == null || !this.imageFile.exists()){
            return;
        }
        if(!imageFile.delete()){
            System.out.println("failed to delete file \"" + this.imageFile.getAbsoluteFile() + "\"");
        }
    }
}
