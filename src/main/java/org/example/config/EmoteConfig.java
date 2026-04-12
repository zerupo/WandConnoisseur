package org.example.config;

import org.example.main.Global;
import static org.example.WandConnoisseur.jda;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import net.dv8tion.jda.api.entities.emoji.ApplicationEmoji;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmoteConfig{
    private static final Logger logger = LoggerFactory.getLogger(EmoteConfig.class);
    private static final String CONFIG_FILE = Global.getPathConfig() + "emote.properties";
    private static final Properties properties = new Properties();
    private static boolean loaded = false;
    private static boolean triedInit = false;

    static{
        initConfig(false);
    }

    public static void initConfig(boolean forceRetry){
        if(loaded || triedInit && !forceRetry){
            return;
        }
        InputStream input;

        triedInit = true;
        try{
            input = new FileInputStream(CONFIG_FILE);
        }catch(FileNotFoundException e){
            createEmoteConfig();
            try{
                input = new FileInputStream(CONFIG_FILE);
            }catch(FileNotFoundException e2){
                logger.error("Error in emote configuration: " + e2.getMessage());
                return;
            }
        }

        try{
            properties.load(input);
            logger.info("Configuration loaded from \"" + CONFIG_FILE + "\"");
            loaded = true;
        }catch(IOException e){
            logger.error("Error loading configuration file \"" + CONFIG_FILE + "\": " + e);
        }
    }

    public static String getEmote(String className){
        if(!loaded){
            initConfig(false);
        }
        return properties.getProperty(className, properties.getProperty("spell", "<:" + className + ":>"));
    }

    public static void createEmoteConfig(){
        if(jda == null){
            return;
        }
        StringBuilder sb = new StringBuilder();
        List<ApplicationEmoji> emojis = jda.retrieveApplicationEmojis().complete();

        emojis.forEach(emote -> sb.append(emote.getName()).append("=<:").append(emote.getName()).append(":").append(emote.getId()).append(">\n"));

        try{
            Files.write(Paths.get(CONFIG_FILE), sb.toString().getBytes());
            System.out.println("File \"" + CONFIG_FILE + "\" written successfully.");
            System.out.println("Please restart to load emotes");
            System.exit(0);
        }catch(IOException e){
            logger.error("Error writing file \"" + CONFIG_FILE + "\": " + e.getMessage());
            e.printStackTrace();
        }
    }
}