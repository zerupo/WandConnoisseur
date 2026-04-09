package org.example.main;

import org.example.menu.MenuManager;
import org.example.spells.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Global{
    private final static String pathOutput = "./src/main/java/org/example/fileOutput/";
    private final static String pathAutoDelete = "./src/main/java/org/example/fileOutput/autoDelete/";
    private final static SpellFilter spellFilter = new SpellFilter();
    private final static String[] spellProperties = SpellFilter.PROPERTY_RESOLVERS.keySet().stream().sorted().toArray(String[]::new);
    private final static String[] spellStringProperties = SpellFilter.STRING_PROPERTY_RESOLVERS.keySet().stream().sorted().toArray(String[]::new);
    private final static SpellList spellList = new SpellList();
    private final static SpellList spellListRelatedProjectile = new SpellList(spell -> spell.getRelatedProjectile() != null);
    private final static SpellList spellListLifetimeModifier = new SpellList(spell -> spell.getLifetime() != 0, Comparator.comparing(Spell::getLifetime).thenComparing(Spell::getName));
    private final static String[] aliasList = spellList.getAllAlias();
    private final static String[] aliasListRelatedProjectile = spellListRelatedProjectile.getAllAlias();
    private final static String[] aliasListLifetimeModifier = spellListLifetimeModifier.getAllAlias();
    private final static WandList wandList = new WandList();
    private final static int baseImageSize = 16;
    private final static int baseIconSize = 7;
    private final static int imageScaleFactor = 5;
    private final static int margin = 4*getImageScaleFactor();
    private final static Pattern delayPattern = Pattern.compile("^ *([+-]?[0-9]+(|\\.[0-9]+)?)(?: *(|[fs]))? *$");
    private final static Pattern spellPattern = Pattern.compile("^(?:(inf|max|[0-9]+):)?([^:]*)(?::([0-9]+))?$");
    public final static MenuManager menuManager = new MenuManager();
    public static long currentFrame = 0;

    // getters
    public static String getPathOutput(){
        return pathOutput;
    }

    public static String getPathAutoDelete(){
        return pathAutoDelete;
    }

    public static SpellFilter getSpellFilter(){
        return spellFilter;
    }

    public static String[] getSpellProperties(){
        return spellProperties;
    }

    public static String[] getSpellStringProperties(){
        return spellStringProperties;
    }

    public static SpellList getSpellList(){
        return spellList;
    }

    public static SpellList getSpellListRelatedProjectile(){
        return spellListRelatedProjectile;
    }

    public static SpellList getSpellListLifetimeModifier(){
        return spellListLifetimeModifier;
    }

    public static String[] getAliasList(){
        return aliasList;
    }

    public static String[] getAliasListRelatedProjectile(){
        return aliasListRelatedProjectile;
    }

    public static String[] getAliasListLifetimeModifier(){
        return aliasListLifetimeModifier;
    }

    public static WandList getWandList(){
        return wandList;
    }

    public static int getBaseImageSize(){
        return baseImageSize;
    }

    public static int getBaseIconSize(){
        return baseIconSize;
    }

    public static int getImageScaleFactor(){
        return imageScaleFactor;
    }

    public static int getMargin(){
        return margin;
    }

    public static Pattern getDelayPattern(){
        return delayPattern;
    }

    public static Pattern getSpellPattern(){
        return spellPattern;
    }

    public static long getCurrentFrame(){
        return currentFrame;
    }

    public static void nextFrame(){
        if(currentFrame == Long.MAX_VALUE){
            currentFrame = 0;
        }else{
            currentFrame++;
        }
    }

    public static void nextFrame(int nb){
        if(nb < 0){
            nb = -nb;
        }

        if(currentFrame > Long.MAX_VALUE - nb){
            // overflow
            currentFrame = - Long.MAX_VALUE + currentFrame + nb;
        }else{
            currentFrame += nb;
        }
    }

    private Global(){
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle){
        if(img == null){
            return null;
        }
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int)Math.floor(w*cos + h*sin);
        int newHeight = (int)Math.floor(h*cos + w*sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w)/2.0, (newHeight - h)/2.0);

        at.rotate(rads, w/2.0, h/2.0);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

    public static BufferedImage scaleImage(BufferedImage originalImage, int scaleFactor){
        if(originalImage == null){
            return null;
        }
        int newWidth = originalImage.getWidth()*scaleFactor;
        int newHeight = originalImage.getHeight()*scaleFactor;

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    public static BufferedImage loadImage(String path){
        BufferedImage result = null;
        try{
            result = ImageIO.read(new File(path));
        }catch(IOException e){
            System.out.println("image not found: \"" + path + "\"");
        }
        return result;
    }

    public static void sendMessage(String message, SlashCommandInteractionEvent event, boolean replyEvent, boolean ansiFormatting){
        int chunkSize = 1970;
        String chunk = "";
        String[] lines = message.split("\\r?\\n");

        for(int i=0; i < lines.length; i++){
            if(chunk.length() + lines[i].length() + 1 > chunkSize){
                if(chunk.length() <= chunkSize){
                    if(replyEvent){
                        event.getHook().editOriginal(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
                        replyEvent = false;
                    }else{
                        event.getChannel().sendMessage(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
                    }
                }else{
                    if(replyEvent){
                        event.getHook().editOriginal(ansiFormatting ? "```ansi\nError message too long```" : "Error message too long").queue();
                        replyEvent = false;
                    }else{
                        event.getChannel().sendMessage(ansiFormatting ? "```ansi\nError message too long```" : "Error message too long").queue();
                    }
                }
                chunk = lines[i];
            }else{
                chunk += chunk.isEmpty() ? lines[i] : "\n" + lines[i];
            }
            if(i + 1 == lines.length && !chunk.equals("")){
                if(replyEvent){
                    event.getHook().editOriginal(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
                    replyEvent = false;
                }else{
                    event.getChannel().sendMessage(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
                }
            }
        }
    }

    public static Wand slashInteractionToWand(SlashCommandInteractionEvent event){
        SpellList spellList = Global.getSpellList();
        OptionMapping spellsOption = event.getOption("sorts");
        OptionMapping drawOption = event.getOption("draw");
        OptionMapping castDelayOption = event.getOption("cast_delay");
        OptionMapping rechargeTimeOption = event.getOption("recharge_time");
        OptionMapping manaMaxOption = event.getOption("mana_max");
        OptionMapping manaRegenOption = event.getOption("mana_regen");
        OptionMapping spreadOption = event.getOption("spread");
        OptionMapping speedOption = event.getOption("speed");
        String spellsInput = "";
        StringBuilder unknownSpells = new StringBuilder();
        int draw = 1;
        int castDelay = 0;
        int rechargeTime = 0;
        int manaMax = 1000000;
        int manaRegen = 1000000;
        double spread = 0.0;
        double speed = 1.0;
        Spell currentSpell = null;
        int currentSpellCount = 1;
        ArrayList<Spell> spells = new ArrayList<>();
        String[] spellsString;
        Wand wand;
        Matcher m;

        if(drawOption != null){
            draw = Math.max(drawOption.getAsInt(), 1);
        }
        if(spellsOption != null){
            spellsInput = spellsOption.getAsString();
        }
        if(castDelayOption != null){
            try{
                castDelay = Global.stringToDelay(castDelayOption.getAsString());
            }catch(Exception e){
                event.reply("cast_delay: " + e.getMessage()).setEphemeral(true).queue();
                return null;
            }
        }
        if(rechargeTimeOption != null){
            try{
                rechargeTime = Global.stringToDelay(rechargeTimeOption.getAsString());
            }catch(Exception e){
                event.reply("recharge_time: " + e.getMessage()).setEphemeral(true).queue();
                return null;
            }
        }
        if(manaMaxOption != null){
            manaMax = manaMaxOption.getAsInt();
        }
        if(manaRegenOption != null){
            manaRegen = manaRegenOption.getAsInt();
        }
        if(spreadOption != null){
            spread = spreadOption.getAsDouble();
        }
        if(speedOption != null){
            speed = speedOption.getAsDouble();
        }

        spellsString = spellsInput.split(",");
        for(int i=0; i < spellsString.length; i++){
            spellsString[i] = spellsString[i].trim().toLowerCase();
        }

        for(int i=0; i < spellsString.length; i++){
            m = spellPattern.matcher(spellsString[i]);
            if(m.find()){
                currentSpell = spellList.getSpell(m.group(2));
                if(currentSpell != null){
                    switch((m.group(1) != null) ? m.group(1) : "inf"){
                        case "inf" -> currentSpell.makeInfinite();
                        case "max" -> currentSpell.refillCharges();
                        default -> currentSpell.setCharges(Integer.parseInt(m.group(1)));
                    }
                }
                currentSpellCount = (m.group(3) != null) ? Integer.parseInt(m.group(3)) : 1;
            }else{
                currentSpell = null;
            }
            if(currentSpell != null){
                spells.add(currentSpell);
                for(int j=1; j < currentSpellCount; j++){
                    spells.add(currentSpell.clone());
                }
            }else{
                unknownSpells.append(unknownSpells.isEmpty() ? "" : ", ").append("\"").append(spellsString[i]).append("\"");
            }
        }

        if(!unknownSpells.isEmpty()){
            event.reply("Sorts inconnus: " + unknownSpells).setEphemeral(true).queue();
            return null;
        }

        wand = new Wand(draw, castDelay, rechargeTime, manaMax, manaRegen, spells.size(), spread, speed);
        for(Spell spell : spells){
            wand.putSpellEnd(spell);
        }
        return wand;
    }

    public static int stringToDelay(String input){
        Matcher m = delayPattern.matcher(input);
        int result;

        if(m.find()){
            switch(m.group(3)){
                case "s" -> result = (int)(Double.parseDouble(m.group(1))*60.0);
                case "f" -> {
                    if(!m.group(2).equals("")){
                        throw new IllegalArgumentException("\"" + input + "\" -> une valeur en frame doit être un entier");
                    }
                    result = Integer.parseInt(m.group(1));
                }
                default -> {
                    if(m.group(2).equals("")){
                        result = Integer.parseInt(m.group(1));
                    }else{
                        result = (int)(Double.parseDouble(m.group(1))*60.0);
                    }
                }
            }
        }else{
            throw new IllegalArgumentException("\"" + input + "\" n'est pas un délais valide");
        }

        return result;
    }

    public static void autoDeleteFiles(){
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathAutoDelete))){
            for(Path file : stream){
                if(Files.isRegularFile(file)){
                    Files.deleteIfExists(file);
                }
            }
        }catch(Exception e){
            System.out.println("failed to auto delete folder content \"" + pathAutoDelete + "\" " + e.getMessage());
        }
    }

    public static File JPanelToFile(JPanel jPanel, String filePath){
        if(jPanel == null || jPanel.getSize().width <= 0 || jPanel.getSize().height <= 0){
            return null;
        }

        BufferedImage bi = new BufferedImage(jPanel.getSize().width, jPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        jPanel.paint(g);
        g.dispose();

        try{
            ImageIO.write(bi,"png",new File(filePath));
        }catch(Exception e){
            System.out.println("Error writing file \"" + filePath + "\" " + e.getMessage());
            return null;
        }

        return new File(filePath);
    }
}