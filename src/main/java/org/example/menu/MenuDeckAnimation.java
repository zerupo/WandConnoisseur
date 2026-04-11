package org.example.menu;

import org.example.export.HtmlAnimationExporter;
import org.example.main.CardHistory;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class MenuDeckAnimation extends Menu {

    private final CardHistory cardHistory;

    public MenuDeckAnimation(String id, String title, String description, CardHistory cardHistory) {
        super(id, title, description);
        this.cardHistory = cardHistory;
    }

    // -------------------------------------------------------------------------
    // Réponse — upload sur FreeKit et envoie le lien
    // La commande doit avoir appelé deferReply() en amont.
    // -------------------------------------------------------------------------
    public void replyHookEvent(SlashCommandInteractionEvent event) {
        try {
            String label = "wand_" + event.getUser().getId();
            String url   = HtmlAnimationExporter.exportAsUrl(cardHistory, label, "1h");
            event.getHook()
                    .sendMessage("🪄 **" + label + "** → " + url)
                    .queue();
        } catch (Exception e) {
            event.getHook()
                    .sendMessage("❌ Erreur lors de la génération : " + e.getMessage())
                    .queue();
        }
    }

    // -------------------------------------------------------------------------
    // Inutilisés — conservés pour satisfaire l'interface Menu
    // -------------------------------------------------------------------------
    @Override
    public boolean editEvent(ButtonInteractionEvent event) { return false; }

    @Override
    public boolean editEvent(ModalInteractionEvent event) { return false; }

    @Override
    public MessageEmbed toMessageEmbed() { return null; }

    @Override
    public ActionRow getActionRow() {
        throw new UnsupportedOperationException("Unimplemented method 'getActionRow'");
    }
}