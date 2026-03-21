package org.example.listeners;

import org.example.WandConnoisseur;
import org.example.main.Global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;

public class AutoCompleteListener extends ListenerAdapter{
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

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event){
        List<Command.Choice> options = new ArrayList<>();
        String currentInput = event.getFocusedOption().getValue().toLowerCase();
        String preInput = "";
        String postInput = "";
        String[] validSpells;
        Pattern p;
        Matcher m;
        int maxOutput = 25;

        if(currentInput.strip().equalsIgnoreCase("quoi")){
            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("Feur", "Feur"));
            event.replyChoices(options).queue();
            return;
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
                        validSpells = Arrays.copyOf(Global.getAliasListRelatedProjectile(), Math.min(maxOutput, Global.getAliasListRelatedProjectile().length));
                    }else{
                        validSpells = getSpellAutocomplete(Global.getAliasListRelatedProjectile(), currentInput, maxOutput);
                    }

                    for(int i=0; i < validSpells.length; i++){
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(validSpells[i], validSpells[i]));
                    }
                    break;
                }
                break;
            default:
                switch(event.getFocusedOption().getName()){
                    case "propriete":
                        if(currentInput.equals("")){
                            validSpells = Arrays.copyOf(Global.getSpellStringProperties(), Math.min(maxOutput, Global.getSpellStringProperties().length));
                        }else{
                            validSpells = getSpellAutocomplete(Global.getSpellStringProperties(), currentInput, maxOutput);
                        }

                        for(int i=0; i < validSpells.length; i++){
                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i], preInput + validSpells[i]));
                        }
                        break;
                    case "condition":
                        int charId = Math.max(Math.max(currentInput.lastIndexOf(' '), currentInput.lastIndexOf('(')), currentInput.lastIndexOf(')'));
                        preInput = currentInput.substring(0, charId + 1);
                        currentInput = currentInput.substring(charId + 1).strip();

                        if(currentInput.equals("")){
                            validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                        }else{
                            validSpells = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                        }

                        for(int i=0; i < validSpells.length; i++){
                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice( preInput + validSpells[i], preInput + validSpells[i]));
                        }
                        break;
                    case "tri":
                        if(currentInput.equals("")){
                            validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                        }else{
                            p = Pattern.compile("^ *(?i)([a-z0-9_]+) *(|ASC|DESC) *$");
                            m = p.matcher(currentInput);
                            if(m.find()){
                                currentInput = m.group(1);
                                postInput += m.group(2).equalsIgnoreCase("DESC") ? " DESC" : "";
                            }
                            if(currentInput.equals("")){
                                validSpells = Arrays.copyOf(Global.getSpellProperties(), Math.min(maxOutput, Global.getSpellProperties().length));
                            }else{
                                validSpells = getSpellAutocomplete(Global.getSpellProperties(), currentInput, maxOutput);
                            }
                        }

                        for(int i=0; i < validSpells.length; i++){
                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i] + postInput, preInput + validSpells[i] + postInput));
                        }
                        break;
                    case "sorts":
                        if(currentInput.equals("")){
                            validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                        }else{
                            p = Pattern.compile("^(?:(inf|max|[0-9]+):)?([^:]*)(?::([0-9]+))?$");
                            m = p.matcher(currentInput);
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
                                validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                            }else{
                                validSpells = getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput);
                            }
                        }

                        for(int i=0; i < validSpells.length; i++){
                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(preInput + validSpells[i] + postInput, preInput + validSpells[i] + postInput));
                        }
                        break;
                    case "nom":
                        currentInput = currentInput.strip();
                        if(currentInput.equals("")){
                            validSpells = Arrays.copyOf(Global.getAliasList(), Math.min(maxOutput, Global.getAliasList().length));
                        }else{
                            validSpells = getSpellAutocomplete(Global.getAliasList(), currentInput, maxOutput);
                        }

                        for(int i=0; i < validSpells.length; i++){
                            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(validSpells[i], validSpells[i]));
                        }
                        break;
                    case "cast_delay":
                    case "recharge_time":
                        p = Global.getDelayPattern();
                        m = p.matcher(currentInput);
                        int intDelay = 0;
                        String[] StringDelay;

                        if(m.find()){
                            switch(m.group(3)){
                                case "s" -> intDelay = (int)(Double.parseDouble(m.group(1))*60.0);
                                case "f" -> {
                                    if(m.group(2).equals("")){
                                        intDelay = Integer.parseInt(m.group(1));
                                    }
                                }
                                default -> {
                                    if(m.group(2).equals("")){
                                        intDelay = Integer.parseInt(m.group(1));
                                    }else{
                                        intDelay = (int)(Double.parseDouble(m.group(1))*60.0);
                                    }
                                }
                            }
                        }

                        StringDelay = new String[]{String.format("%1$d f", intDelay).replace(',', '.'), String.format("%1$3.2f s", intDelay/60.0).replace(',', '.')};
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(StringDelay[0], StringDelay[0]));
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(StringDelay[1], StringDelay[1]));
                        break;
                    case "font":
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("pixel", "pixel"));
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("title", "title"));
                        options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("glyph", "glyph"));
                        break;
                    case "commande":
                        RestAction<List<Command>> commandList = WandConnoisseur.jda.retrieveCommands();
                        String commandInput = currentInput.strip();
                        commandList.queue(commands -> {
                            for(net.dv8tion.jda.api.interactions.commands.Command command : commands){
                                if(commandInput.equals("") || command.getName().contains(commandInput)){
                                    options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice(command.getName(), command.getName()));
                                }
                            }
                            event.replyChoices(options).queue();
                        }, failure -> {
                            System.out.println("Failed to retrieve commands: " + failure.getMessage());
                        });
                        return;
                }
                break;
        }
        if(event.getFocusedOption().getType() == OptionType.BOOLEAN){
            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("true", "true"));
            options.add(new net.dv8tion.jda.api.interactions.commands.Command.Choice("false", "false"));
        }
        event.replyChoices(options).queue();
    }
}