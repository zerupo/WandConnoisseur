package org.example.localization;

import org.example.main.Global;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageManager{
    private static final Logger logger = LoggerFactory.getLogger(LanguageManager.class);
    public static final DiscordLocale[] discordLocales = new DiscordLocale[]{DiscordLocale.ENGLISH_US, DiscordLocale.FRENCH};
    public enum Language{en, fr}
    private static final Map<String, LocalizedText> MESSAGE_MAP = load(Global.getPathConfig() + "localization.txt");
    private static final LocalizedText DEFAULT_TEXT = new LocalizedText(new Part[0][0]);
    public LanguageManager(){}

    private static Map<String, LocalizedText> load(String resource){
        File file = new File(resource);
        Map<String, LocalizedText> map = new HashMap<>();

        try(Scanner myReader = new Scanner(file)){
            while(myReader.hasNextLine()){
                String line = myReader.nextLine();
                List<Part> currentList = new LinkedList<>();
                List<List<Part>> result = new LinkedList<>();
                result.add(currentList);
                StringBuilder currentString = new StringBuilder();
                StringBuilder key = new StringBuilder();
                boolean escaped = false;
                int start = 0;

                while(start < line.length() && line.charAt(start) != ';'){
                    key.append(line.charAt(start));
                    start++;
                }
                start++;

                for(int i=start; i < line.length(); i++){
                    char c = line.charAt(i);
                    if(escaped){
                        currentString.append(c);
                        escaped = false;
                    }else if(c == '\\'){
                        escaped = true;
                    }else if(c == ';'){
                        if(!currentString.isEmpty()){
                            currentList.add(new LiteralPart(currentString.toString()));
                        }
                        currentList = new LinkedList<>();
                        result.add(currentList);
                        currentString.setLength(0);
                    }else if(c == '%'){
                        int currentInt = 0;
                        boolean hasDigits = false;

                        while(i + 1 < line.length() && Character.isDigit(line.charAt(i + 1))){
                            hasDigits = true;
                            i++;
                            currentInt = currentInt * 10 + (line.charAt(i) - '0');
                        }

                        if(hasDigits){
                            if(!currentString.isEmpty()){
                                currentList.add(new LiteralPart(currentString.toString()));
                                currentString.setLength(0);
                            }
                            currentList.add(new ArgumentPart(currentInt));
                        }else{
                            currentString.append('%');
                        }
                    }else{
                        currentString.append(c);
                    }
                }
                if(!currentString.isEmpty()){
                    currentList.add(new LiteralPart(currentString.toString()));
                }

                map.put(key.toString(), new LocalizedText(result.stream().map(list -> list.toArray(new Part[0])).toArray(Part[][]::new)));
            }
        }catch(Exception e){
            logger.error("Error reading wand config \"" + Global.getPathConfig() + "localization.txt\": " + e.getMessage());
            return new HashMap<>();
        }

        return map;
    }

    public LocalizedText get(String key){
        return MESSAGE_MAP.getOrDefault(key, DEFAULT_TEXT);
    }

    public static DiscordLocale languageToDiscordLocale(Language language){
        return discordLocales[language.ordinal()];
    }
}