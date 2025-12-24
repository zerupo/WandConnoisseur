package org.example.main;

import org.example.spells.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class Global{
    private final static String pathOutput = "./src/main/java/org/example/fileOutput/";
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

    // getters
    public static String getPathOutput(){
        return pathOutput;
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
}