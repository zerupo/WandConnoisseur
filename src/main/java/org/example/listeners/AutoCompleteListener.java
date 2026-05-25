package org.example.listeners;

import org.example.main.Global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoCompleteListener extends ListenerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(AutoCompleteListener.class);

    @Override
    public void onReady(@NotNull ReadyEvent event){
        logger.info("AutoCompleteListener is ready!");
    }

    public static String[] getSpellAutocomplete(String[] spellAlias, String input, int maxOutput){
        int min = 0;
        int max = spellAlias.length - 1;
        int middle;
        int comparaisonResult;
        boolean stop = false;
        String[] result;

        while(min < max && !stop){
            middle = min + (max - min)/2;
            comparaisonResult = input.compareTo(spellAlias[middle]);
            if(comparaisonResult == 0){
                max = middle;
                stop = true;
            }
            if(comparaisonResult > 0){
                min = middle;
            }else{
                max = middle;
            }
            if(min + 1 == max){
                if(spellAlias[min].startsWith(input)){
                    max = min;
                }else{
                    min = max;
                }
            }
        }
        min = Math.max(Math.min(min, max), 0);
        max = min;

        while(max < spellAlias.length && spellAlias[max].startsWith(input)){
            max++;
        }
        result = new String[Math.min(max - min, maxOutput)];
        for(int i=0; i < result.length && i < maxOutput; i++){
            result[i] = spellAlias[i + min];
        }

        return result;
    }

    private List<String> getAutoCompleteValues(@NotNull CommandAutoCompleteInteractionEvent event){
        List<String> values = new LinkedList<>();
        String currentInput = event.getFocusedOption().getValue().toLowerCase();
        String preInput = "";
        String postInput = "";
        String[] validOptions;
        Matcher m;
        int maxOutput = 25;

        if(event.getFocusedOption().getType() == OptionType.BOOLEAN){
            values.add("true");
            values.add("false");
            return values;
        }

        if(currentInput.strip().equalsIgnoreCase("quoi")){
            values.add("Feur");
            return values;
        }

        if(Arrays.asList(new String[]{"sorts", "propriete", "tri"}).contains(event.getFocusedOption().getName())){
            preInput = currentInput.substring(0, currentInput.lastIndexOf(',') + 1) + " ";
            currentInput = currentInput.substring(currentInput.lastIndexOf(',') + 1).strip();
        }

        switch(event.getName()){
            case "wisp":
                if(event.getFocusedOption().getName().equals("sort")){
                    currentInput = currentInput.strip();
                    if(currentInput.equals("")){
                        values.addAll(Arrays.asList(Arrays.copyOf(Global.getAliasListRelatedProjectile(), Math.min(maxOutput, Global.getAliasListRelatedProjectile().length))));
                    }else{
                        values.addAll(Arrays.asList(getSpellAutocomplete(Global.getAliasListRelatedProjectile(), currentInput, maxOutput)));
                    }
                    break;
                }
                break;
            default:
                switch(event.getFocusedOption().getName()){
                    case "propriete" -> {
                        if(currentInput.equals("")){
                            values.addAll(Arrays.asList(Arrays.copyOf(Global.getSpellStringProperties(), Math.min(maxOutput, Global.getSpellStringProperties().length))));
                        }else{
                            values.addAll(Arrays.asList(getSpellAutocomplete(Global.getSpellStringProperties(), currentInput, maxOutput)));
                        }
                    }
                    case "condition" -> {
                        int charId = Math.max(Math.max(currentInput.lastIndexOf(' '), currentInput.lastIndexOf('(')), currentInput.lastIndexOf(')'));
                        preInput = currentInput.substring(0, charId + 1);
                        currentInput = currentInput.substring(charId + 1).strip();

                        if(currentInput.equals("")){
                            validOptions = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                        }else{
                            validOptions = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                        }

                        for(String validOption : validOptions){
                            values.add(preInput + validOption);
                        }
                    }
                    case "tri" -> {
                        if(currentInput.equals("")){
                            validOptions = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                        }else{
                            m = Pattern.compile("^ *(?i)([a-z0-9_]+) *(|ASC|DESC) *$").matcher(currentInput);
                            if(m.find()){
                                currentInput = m.group(1);
                                postInput += m.group(2).equalsIgnoreCase("DESC") ? " DESC" : "";
                            }
                            if(currentInput.equals("")){
                                validOptions = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                            }else{
                                validOptions = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                            }
                        }

                        for(String validOption : validOptions){
                            values.add(preInput + validOption + postInput);
                        }
                    }
                    case "sorts" -> {
                        if(currentInput.equals("")){
                            validOptions = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                        }else{
                            m = Global.getSpellPattern().matcher(currentInput);
                            if(m.find()){
                                currentInput = m.group(2);
                                if(m.group(1) != null){
                                    preInput += m.group(1) + ":";
                                }
                                if(m.group(3) != null){
                                    postInput += ":" + m.group(3);
                                }
                            }
                            if(currentInput.equals("")){
                                validOptions = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                            }else{
                                validOptions = getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput);
                            }
                        }

                        for(String validOption : validOptions){
                            values.add(preInput + validOption + postInput);
                        }
                    }
                    case "nom" -> {
                        currentInput = currentInput.strip();
                        if(currentInput.equals("")){
                            values.addAll(Arrays.asList(Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length))));
                        }else{
                            values.addAll(Arrays.asList(getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput)));
                        }
                    }
                    case "cast_delay", "recharge_time" -> {
                        int intDelay = 0;

                        try{
                            intDelay = Global.stringToDelay(currentInput);
                        }catch(Exception e){
                            break;
                        }

                        values.add(String.format("%1$d f", intDelay).replace(',', '.'));
                        values.add(String.format("%1$3.2f s", intDelay/60.0).replace(',', '.'));
                    }
                    case "font" -> {
                        values.add("pixel");
                        values.add("title");
                        values.add("glyph");
                    }
                    case "commande" -> {
                        currentInput = currentInput.strip();
                        Command[] commandList = currentInput.equals("") ? Global.getCommandList() : Global.getCommandList(currentInput);

                        for(Command command : commandList){
                            values.add(command.getName());
                        }

                        return values;
                    }
                }
        }

        return values;
    }

    private void replyAutoComplete(@NotNull CommandAutoCompleteInteractionEvent event){
        List<String> values = getAutoCompleteValues(event);
        List<Command.Choice> options = new ArrayList<>();

        for(String value : values){
            if(value.length() >= 100){
                value = value.substring(0, 100);
            }
            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(value, value));
        }
        event.replyChoices(options).queue();
    }

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event){
        long startTime = System.nanoTime();

        try{
            logger.debug("User \033[0;34m" + event.getUser().getName() + "\u001b[0;0m executing AutoComplete event " + event.getName() + ":" + event.getFocusedOption().getName() + " \"" + event.getFocusedOption().getValue() + "\"");
            replyAutoComplete(event);
            logger.debug("Executed AutoComplete event in " + (System.nanoTime() - startTime)/1000000.0 + "ms \"" + event.getFocusedOption().getValue() + "\"");
        }catch(Exception e){
            logger.warn("Failed AutoComplete event in " + (System.nanoTime() - startTime)/1000000.0 + "ms \"" + event.getFocusedOption().getValue() + "\"");
            e.printStackTrace();
        }
    }
}