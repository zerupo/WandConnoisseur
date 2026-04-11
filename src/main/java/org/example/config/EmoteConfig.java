package org.example.config;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class EmoteConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = EmoteConfig.class.getClassLoader()
                .getResourceAsStream("emotes.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger emotes.properties", e);
        }
    }

    /**
     * Retourne le format emote Discord : <:nom:id>
     * Si l'id n'existe pas dans le fichier, retourne le nom comme fallback
     */
    public static String get(String name) {
        String id = props.getProperty(name);
        if (id == null) return ":" + name + ":"; // fallback texte
        return "<:" + name + ":" + id + ">";
    }
}