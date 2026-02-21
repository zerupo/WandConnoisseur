package org.example.main;

import org.example.spells.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class Global{
    private final static String pathOutput = "./src/main/java/org/example/fileOutput/";
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

    // getters
    public static String getPathOutput(){
        return pathOutput;
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

    public static void sendMessage(String message, MessageChannelUnion channel, boolean ansiFormatting){
        int chunkSize = 1970;
        String chunk = "";
        String[] lines = message.split("\\r?\\n");
        for(int i=0; i < lines.length; i++){
            if(chunk.length() + lines[i].length() + 1 > chunkSize){
                if(chunk.length() <= chunkSize){
                    channel.sendMessage(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
                }else{
                    channel.sendMessage(ansiFormatting ? "```ansi\nError message too long```" : "Error message too long").queue();
                }
                chunk = lines[i];
            }else{
                chunk += "\n" + lines[i];
            }
            if(i + 1 == lines.length && !chunk.equals("")){
                channel.sendMessage(ansiFormatting ? "```ansi\n" + chunk + "```" : chunk).queue();
            }
        }
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
}