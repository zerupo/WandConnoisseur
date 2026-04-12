package org.example.config;

import org.example.main.Global;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotConfig{
    private static final Logger logger = LoggerFactory.getLogger(BotConfig.class);
    private static final String CONFIG_FILE = Global.getPathConfig() + "config.properties";
    private static final Properties properties = new Properties();

    static{
        initConfig();
    }

    public static void initConfig(){
        try{
            InputStream input = new FileInputStream(CONFIG_FILE);
            properties.load(input);
            logger.info("Configuration loaded from \"" + CONFIG_FILE + "\"");
        }catch(IOException e){
            logger.error("Error loading configuration file: " + e);
        }
    }

    public static String getBotToken(){
        return properties.getProperty("botToken");
    }
}