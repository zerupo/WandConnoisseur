package org.example.menu;

import java.io.File;
import java.util.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.requests.restaction.interactions.MessageEditCallbackAction;
import net.dv8tion.jda.api.requests.restaction.WebhookMessageEditAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class MenuTree extends Menu{
    private int autoIncrement = 0;
    private MenuTree parent;
    private final List<MenuTree> children = new ArrayList<>();

    public MenuTree(String id, String title, String description, File imageFile, String imageFallBack){
        super(id, title, description, imageFile, imageFallBack);
        this.parent = null;
    }

    public void addChild(MenuTree menu){
        menu.id = this.id + ";" + this.autoIncrement;
        this.autoIncrement++;

        // node can't be in several trees
        if(menu.parent != null){
            menu.parent.children.remove(menu);
        }
        menu.parent = this;

        this.children.add(menu);
        menu.updateChildId();
    }

    public MenuTree getParent(){
        return this.parent;
    }

    public MenuTree getRoot(){
        MenuTree currentNode = this;
        while(currentNode.parent != null){
            currentNode = currentNode.parent;
        }
        return currentNode;
    }

    public List<MenuTree> getChildren(){
        return this.children;
    }

    public Menu getMenuById(String id){
        return getMenuCastStateById(id);
    }

    private void updateChildId(){
        this.autoIncrement = 0;
        for(MenuTree child : this.children){
            child.id = this.id + ";" + this.autoIncrement;
            child.updateChildId();
            this.autoIncrement++;
        }
    }

    public MenuTree getMenuCastStateById(String id){
        MenuTree result = this.getRoot();
        String[] idArray = id.split(";");
        String currentID = idArray[0];

        if(result.id.equals(id)){
            return result;
        }

        for(int i=1; i < idArray.length; i++){
            currentID += ";" + idArray[i];
            for(MenuTree child : result.children){
                if(child.id.equals(currentID)){
                    result = child;
                    break;
                }
            }
            if(result.id.equals(id)){
                return result;
            }
        }
        return null;
    }

    public ActionRow getActionRow(){
        MenuTree parent = this.getParent();
        int childCount = 0;

        StringSelectMenu.Builder menu = StringSelectMenu.create("menu:" + this.getId());

        for(MenuTree child : this.getChildren()){
            if(childCount >= 24){
                break;
            }
            childCount++;
            menu.addOption(child.getTitle(), child.getId());
        }

        if(parent != null){
            menu.addOption("Retour", parent.getId());
        }

        return ActionRow.of(menu.build());
    }

    public void replyHookEvent(SlashCommandInteractionEvent event){
        WebhookMessageEditAction<Message> callback = event.getHook().editOriginal(MessageEditData.fromEmbeds(this.toMessageEmbed()));

        if(this.imageFile != null){
            callback.setFiles(FileUpload.fromData(this.imageFile, this.id + ".png"));
        }
        if(this.getChildren().size() > 0 || this.getParent() != null){
            callback.setComponents(this.getActionRow());
        }

        callback.queue();
    }

    public void editEvent(StringSelectInteractionEvent event){
        MessageEditCallbackAction callback = event.editMessageEmbeds(this.toMessageEmbed());

        if(this.imageFile != null){
            try{
                callback.setFiles(FileUpload.fromData(this.imageFile, this.id + ".png"));
            }catch(Exception e){
                callback.setFiles((Collection<? extends FileUpload>) null);
            }
        }else{
            callback.setFiles((Collection<? extends FileUpload>) null);
        }
        if(this.getChildren().size() > 0 || this.getParent() != null){
            callback.setComponents(this.getActionRow());
        }
        callback.queue();
    }

    @Override
    public void deleteFiles(){
        if(this.imageFile != null && this.imageFile.exists() && !imageFile.delete()){
            System.out.println("failed to delete file \"" + this.imageFile.getAbsoluteFile() + "\"");
        }
        for(MenuTree menuTree : this.children){
            menuTree.deleteFiles();
        }
    }
}