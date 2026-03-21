package org.example.listeners;

import org.example.main.Global;
import org.example.menu.Menu;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MenuListener extends ListenerAdapter{
    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event){
        String menuId = event.getValues().get(0);
        String rootMenuId = Menu.getRootId(menuId);

        Menu selectedNode = Global.menuManager.getById(rootMenuId);

        if(selectedNode == null){
            event.reply("Erreur: ce menu est trop ancien pour être intéragi").setEphemeral(true).queue();
            return;
        }

        selectedNode = selectedNode.getMenuById(menuId);

        if(selectedNode == null){
            event.reply("Erreur: ce menu est trop ancien pour être intéragi").setEphemeral(true).queue();
            return;
        }

        selectedNode.editEvent(event);
    }
}