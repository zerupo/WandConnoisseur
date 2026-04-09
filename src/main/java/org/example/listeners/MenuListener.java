package org.example.listeners;

import org.example.main.Global;
import org.example.menu.Menu;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MenuListener extends ListenerAdapter{
    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event){
        Menu menu = Global.menuManager.getById(Menu.getRootId(event.getValues().get(0)));

        if(menu == null){
            event.reply("Erreur: ce menu est trop ancien pour être intéragi.").setEphemeral(true).queue();
            return;
        }

        if(!menu.editEvent(event)){
            event.reply("Erreur lors de l'interaction.").setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        Menu menu = Global.menuManager.getById(Menu.getRootId(event.getComponentId()));

        if(menu == null){
            event.reply("Erreur: ce menu est trop ancien pour être intéragi.").setEphemeral(true).queue();
            return;
        }

        if(!menu.editEvent(event)){
            event.reply("Erreur lors de l'interaction.").setEphemeral(true).queue();
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event){
        Menu menu = Global.menuManager.getById(Menu.getRootId(event.getModalId()));

        if(menu == null){
            event.reply("Erreur: ce menu est trop ancien pour être intéragi.").setEphemeral(true).queue();
            return;
        }

        if(!menu.editEvent(event)){
            event.reply("Erreur lors de l'interaction.").setEphemeral(true).queue();
        }
    }
}