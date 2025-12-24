package org.example.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Random;

import static org.example.main.Global.*;

enum RangeType{
    BETWEEN,
    GREATER_OR_EQUAL,
    LESS_OR_EQUAL
}

class IntRange{
    private final int min;
    private final int max;
    private final RangeType type;

    public IntRange(int min, int max){
        this.min = min;
        this.max = max;
        this.type = RangeType.BETWEEN;
    }

    public IntRange(int min, boolean greater){
        this.min = min;
        this.max = 0;
        if(greater){
            this.type = RangeType.GREATER_OR_EQUAL;
        }else{
            this.type = RangeType.LESS_OR_EQUAL;
        }
    }

    public boolean contains(double value){
        switch(type){
            case BETWEEN:
                return value >= this.min && value <= this.max;
            case GREATER_OR_EQUAL:
                return value >= this.min;
            case LESS_OR_EQUAL:
                return value <= this.min;
            default:
                return false;
        }
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    public RangeType getType(){
        return this.type;
    }

    public String toString(String format, double multiplier, boolean ensembleFormat, String start, String end){
        String stringMin = "";
        String stringMax = "";

        if(format.equals("")){
            stringMin = String.valueOf(this.min*multiplier);
            stringMax = String.valueOf(this.max*multiplier);
        }else{
            try{
                stringMin = String.format(format, this.min*multiplier);
                stringMax = String.format(format, this.max*multiplier);
            }catch(IllegalFormatException e){
                stringMin = String.valueOf(this.min*multiplier);
                stringMax = String.valueOf(this.max*multiplier);
            }
        }

        if(ensembleFormat){
            return switch (this.type){
                case GREATER_OR_EQUAL -> start + "[" + stringMin + "; \u221e[" + end;
                case LESS_OR_EQUAL -> start + "]\u221e; " + stringMin + "]" + end;
                default -> stringMin.equals(stringMax) ? start + stringMin + end : start + "[" + stringMin + "; " + stringMax + "]" + end;
            };
        }else{
            return switch (this.type){
                case GREATER_OR_EQUAL -> start + ">" + stringMin + end;
                case LESS_OR_EQUAL -> start + "<" + stringMin + end;
                default -> stringMin.equals(stringMax) ? start + stringMin + end : start + stringMin + " - " + stringMax + end;
            };
        }
    }

    public String toString(){
        return this.toString("%.0f", 1, false, "", "");
    }
}

class WandStat{
    private String filePath = "./src/main/java/org/example/image/wand/";
    private String fileName;
    private boolean shuffle;
    private IntRange nbDraw;
    private IntRange castDelay;
    private IntRange rechargeTime;
    private IntRange nbSlot;
    private IntRange spread;

    public WandStat(String filePath, String fileName, boolean shuffle, IntRange nbDraw, IntRange castDelay, IntRange rechargeTime, IntRange nbSlot, IntRange spread){
        this.filePath = filePath;
        this.fileName = fileName;
        this.shuffle = shuffle;
        this.nbDraw = nbDraw;
        this.castDelay = castDelay;
        this.rechargeTime = rechargeTime;
        this.nbSlot = nbSlot;
        this.spread = spread;
    }

    public WandStat(String fileName, boolean shuffle, IntRange nbDraw, IntRange castDelay, IntRange rechargeTime, IntRange nbSlot, IntRange spread){
        this.fileName = fileName;
        this.shuffle = shuffle;
        this.nbDraw = nbDraw;
        this.castDelay = castDelay;
        this.rechargeTime = rechargeTime;
        this.nbSlot = nbSlot;
        this.spread = spread;
    }

    public String getSprite(){
        return this.filePath + this.fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String toString(){
        String result = "\"" + this.fileName + "\" Shuffle: ";

        if(this.shuffle){
            result += "yes";
        }else{
            result += "no";
        }
        result += ", draw \u2208 " + this.nbDraw.toString() + ", slot \u2208 " + this.nbSlot.toString() + ", recharge time \u2208 " + this.rechargeTime.toString() + ", cast delay \u2208 " + this.castDelay.toString() + ", spread \u2208 " + this.spread.toString();
        return result;
    }

    public boolean isValid(boolean shuffle, int nbDraw, int castDelay, int rechargeTime, int nbSlot, double spread){
        if(this.shuffle != shuffle){
            return false;
        }
        if(!this.nbDraw.contains(nbDraw)){
            return false;
        }
        if(!this.nbSlot.contains(nbSlot)){
            return false;
        }
        if(!this.rechargeTime.contains(rechargeTime)){
            return false;
        }
        if(!this.castDelay.contains(castDelay)){
            return false;
        }
        if(!this.spread.contains(spread)){
            return false;
        }
        return true;
    }

    // order: good -> bad
    // 1 = good order
    // 0 = same
    // -1 = invert
    public static int compare(WandStat wand1, WandStat wand2){
        // shuffle
        if(!wand1.shuffle && wand2.shuffle){
            return 6;
        }else if(wand1.shuffle && !wand2.shuffle){
            return -6;
        }

        // nbDraw <
        if(wand1.nbDraw.getType() == RangeType.LESS_OR_EQUAL && wand2.nbDraw.getType() != RangeType.LESS_OR_EQUAL){
            return 5;
        }else if(wand1.nbDraw.getType() != RangeType.LESS_OR_EQUAL && wand2.nbDraw.getType() == RangeType.LESS_OR_EQUAL){
            return -5;
        }else if(wand1.nbDraw.getMin() < wand2.nbDraw.getMin()){
            return 5;
        }else if(wand1.nbDraw.getMin() > wand2.nbDraw.getMin()){
            return -5;
        }else if(wand1.nbDraw.getType() != RangeType.GREATER_OR_EQUAL && wand2.nbDraw.getType() == RangeType.GREATER_OR_EQUAL){
            return 5;
        }else if(wand1.nbDraw.getType() == RangeType.GREATER_OR_EQUAL && wand2.nbDraw.getType() != RangeType.GREATER_OR_EQUAL){
            return -5;
        }else if(wand1.nbDraw.getMax() < wand2.nbDraw.getMax()){
            return 5;
        }else if(wand1.nbDraw.getMax() > wand2.nbDraw.getMax()){
            return -5;
        }

        // nbSlot >
        if(wand1.nbSlot.getType() == RangeType.GREATER_OR_EQUAL && wand2.nbSlot.getType() != RangeType.GREATER_OR_EQUAL){
            return 4;
        }else if(wand1.nbSlot.getType() != RangeType.GREATER_OR_EQUAL && wand2.nbSlot.getType() == RangeType.GREATER_OR_EQUAL){
            return -4;
        }else if(wand1.nbSlot.getMax() > wand2.nbSlot.getMax()){
            return 4;
        }else if(wand1.nbSlot.getMax() < wand2.nbSlot.getMax()){
            return -4;
        }else if(wand1.nbSlot.getType() != RangeType.LESS_OR_EQUAL && wand2.nbSlot.getType() == RangeType.LESS_OR_EQUAL){
            return 4;
        }else if(wand1.nbSlot.getType() == RangeType.LESS_OR_EQUAL && wand2.nbSlot.getType() != RangeType.LESS_OR_EQUAL){
            return -4;
        }else if(wand1.nbSlot.getMin() > wand2.nbSlot.getMin()){
            return 4;
        }else if(wand1.nbSlot.getMin() < wand2.nbSlot.getMin()){
            return -4;
        }

        // rechargeTime <
        if(wand1.rechargeTime.getType() == RangeType.LESS_OR_EQUAL && wand2.rechargeTime.getType() != RangeType.LESS_OR_EQUAL){
            return 3;
        }else if(wand1.rechargeTime.getType() != RangeType.LESS_OR_EQUAL && wand2.rechargeTime.getType() == RangeType.LESS_OR_EQUAL){
            return -3;
        }else if(wand1.rechargeTime.getMin() < wand2.rechargeTime.getMin()){
            return 3;
        }else if(wand1.rechargeTime.getMin() > wand2.rechargeTime.getMin()){
            return -3;
        }else if(wand1.rechargeTime.getType() != RangeType.GREATER_OR_EQUAL && wand2.rechargeTime.getType() == RangeType.GREATER_OR_EQUAL){
            return 3;
        }else if(wand1.rechargeTime.getType() == RangeType.GREATER_OR_EQUAL && wand2.rechargeTime.getType() != RangeType.GREATER_OR_EQUAL){
            return -3;
        }else if(wand1.rechargeTime.getMax() < wand2.rechargeTime.getMax()){
            return 3;
        }else if(wand1.rechargeTime.getMax() > wand2.rechargeTime.getMax()){
            return -3;
        }

        // castDelay <
        if(wand1.castDelay.getType() == RangeType.LESS_OR_EQUAL && wand2.castDelay.getType() != RangeType.LESS_OR_EQUAL){
            return 2;
        }else if(wand1.castDelay.getType() != RangeType.LESS_OR_EQUAL && wand2.castDelay.getType() == RangeType.LESS_OR_EQUAL){
            return -2;
        }else if(wand1.castDelay.getMin() < wand2.castDelay.getMin()){
            return 2;
        }else if(wand1.castDelay.getMin() > wand2.castDelay.getMin()){
            return -2;
        }else if(wand1.castDelay.getType() != RangeType.GREATER_OR_EQUAL && wand2.castDelay.getType() == RangeType.GREATER_OR_EQUAL){
            return 2;
        }else if(wand1.castDelay.getType() == RangeType.GREATER_OR_EQUAL && wand2.castDelay.getType() != RangeType.GREATER_OR_EQUAL){
            return -2;
        }else if(wand1.castDelay.getMax() < wand2.castDelay.getMax()){
            return 2;
        }else if(wand1.castDelay.getMax() > wand2.castDelay.getMax()){
            return -2;
        }

        // spread <
        if(wand1.spread.getType() == RangeType.LESS_OR_EQUAL && wand2.spread.getType() != RangeType.LESS_OR_EQUAL){
            return 1;
        }else if(wand1.spread.getType() != RangeType.LESS_OR_EQUAL && wand2.spread.getType() == RangeType.LESS_OR_EQUAL){
            return -1;
        }else if(wand1.spread.getMin() < wand2.spread.getMin()){
            return 1;
        }else if(wand1.spread.getMin() > wand2.spread.getMin()){
            return -1;
        }else if(wand1.spread.getType() != RangeType.GREATER_OR_EQUAL && wand2.spread.getType() == RangeType.GREATER_OR_EQUAL){
            return 1;
        }else if(wand1.spread.getType() == RangeType.GREATER_OR_EQUAL && wand2.spread.getType() != RangeType.GREATER_OR_EQUAL){
            return -1;
        }else if(wand1.spread.getMax() < wand2.spread.getMax()){
            return 1;
        }else if(wand1.spread.getMax() > wand2.spread.getMax()){
            return -1;
        }

        return 0;
    }

    public static int compare(Wand wand, WandStat wandStat){
        // shuffle
        if(!wand.getShuffle() && wandStat.shuffle){
            return 6;
        }else if(wand.getShuffle() && !wandStat.shuffle){
            return -6;
        }

        // nbDraw <
        switch(wandStat.nbDraw.getType()){
            case GREATER_OR_EQUAL:
                if(wand.getNbDraw() < wandStat.nbDraw.getMin()){
                    return 5;
                }
            case LESS_OR_EQUAL:
                if(wand.getNbDraw() > wandStat.nbDraw.getMin()){
                    return -5;
                }
            default:
                if(wand.getNbDraw() < wandStat.nbDraw.getMin()){
                    return 5;
                }else if(wand.getNbDraw() > wandStat.nbDraw.getMax()){
                    return -5;
                }
        }

        // nbSlot >
        switch(wandStat.nbSlot.getType()){
            case LESS_OR_EQUAL:
                if(wand.getNbSlot() > wandStat.nbSlot.getMin()){
                    return 4;
                }
            case GREATER_OR_EQUAL:
                if(wand.getNbSlot() < wandStat.nbSlot.getMin()){
                    return -4;
                }
            default:
                if(wand.getNbSlot() > wandStat.nbSlot.getMin()){
                    return 4;
                }else if(wand.getNbSlot() < wandStat.nbSlot.getMax()){
                    return -4;
                }
        }

        // rechargeTime <
        switch(wandStat.rechargeTime.getType()){
            case GREATER_OR_EQUAL:
                if(wand.getRechargeTime() < wandStat.rechargeTime.getMin()){
                    return 3;
                }
            case LESS_OR_EQUAL:
                if(wand.getRechargeTime() > wandStat.rechargeTime.getMin()){
                    return -3;
                }
            default:
                if(wand.getRechargeTime() < wandStat.rechargeTime.getMin()){
                    return 3;
                }else if(wand.getRechargeTime() > wandStat.rechargeTime.getMax()){
                    return -3;
                }
        }

        // castDelay <
        switch(wandStat.castDelay.getType()){
            case GREATER_OR_EQUAL:
                if(wand.getCastDelay() < wandStat.castDelay.getMin()){
                    return 2;
                }
            case LESS_OR_EQUAL:
                if(wand.getCastDelay() > wandStat.castDelay.getMin()){
                    return -2;
                }
            default:
                if(wand.getCastDelay() < wandStat.castDelay.getMin()){
                    return 2;
                }else if(wand.getCastDelay() > wandStat.castDelay.getMax()){
                    return -2;
                }
        }

        // spread <
        switch(wandStat.spread.getType()){
            case GREATER_OR_EQUAL:
                if(wand.getSpread() < wandStat.spread.getMin()){
                    return 1;
                }
            case LESS_OR_EQUAL:
                if(wand.getSpread() > wandStat.spread.getMin()){
                    return -1;
                }
            default:
                if(wand.getSpread() < wandStat.spread.getMin()){
                    return 1;
                }else if(wand.getSpread() > wandStat.spread.getMax()){
                    return -1;
                }
        }

        return 0;
    }

    public JPanel getJPanel(){
        int baseIconSize = Global.getBaseIconSize();
        int imageScaleFactor = Global.getImageScaleFactor();
        int margin = Global.getMargin();
        Color backgroundColor = new Color(0, 0, 0);
        Color textColor = new Color(255, 255, 255);
        BufferedImage statJPanelImage;
        int JPanelWidth;
        int JPanelHeight;
        String path = "./src/main/java/org/example/image/other/";
        String[] stats = new String[]{"Shuffle", "Spells/Cast", "Cast delay", "Rechrg. Time", "Capacity", "Spread"};
        String[] statImages = new String[]{"shuffle.png", "spell_cast.png", "cast_delay.png", "recharge_time.png", "slot.png", "spread.png"};
        String[] statValues = new String[6];
        BufferedImage currentImage;
        BufferedImage wandSprite = rotateImageByDegrees(scaleImage(loadImage(this.getSprite()), 2*imageScaleFactor), -90);;
        Font font;
        FontMetrics fm;
        int textHeight;
        //int textWidth;//
        int maxWidth = 0;
        int maxWidth2 = 0;
        Graphics2D g2d;
        JPanel result;

        if(this.shuffle) {
            statValues[0] = "Yes";
        }else{
            statValues[0] = "No";
        }
        statValues[1] = this.nbDraw.toString();
        statValues[2] = this.castDelay.toString("%1$3.2f", 1.0/60.0, false, "", " s");
        statValues[3] = this.rechargeTime.toString("%1$3.2f", 1.0/60.0, false, "", " s");
        statValues[4] = this.nbSlot.toString();
        statValues[5] = this.spread.toString("%1$3.2f", 1, false, "", " DEG");

        try{
            InputStream myStream = new BufferedInputStream(new FileInputStream("./src/main/java/org/example/PixelFont.ttf"));
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            font = baseFont.deriveFont(Font.PLAIN, baseIconSize*imageScaleFactor);
        }catch(Exception e){
            System.out.println("Pixel font not loaded.");
            font = new Font("Arial", Font.PLAIN, baseIconSize*imageScaleFactor);
        }

        Graphics2D gTemp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        fm = gTemp.getFontMetrics(font);
        gTemp.dispose();

        for(int i=0; i < stats.length; i++){
            maxWidth = Math.max(maxWidth, fm.stringWidth(stats[i]));
        }
        for(int i=0; i < statValues.length; i++){
            maxWidth2 = Math.max(maxWidth2, fm.stringWidth(statValues[i]));
        }

        JPanelWidth = baseIconSize*imageScaleFactor + maxWidth + maxWidth2 + wandSprite.getWidth() + 5*margin;
        JPanelHeight = Math.max(stats.length*baseIconSize*imageScaleFactor + (stats.length + 1)*margin, wandSprite.getHeight() + 2*margin);
        statJPanelImage = new BufferedImage(JPanelWidth, JPanelHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = statJPanelImage.createGraphics();

        g2d.setColor(textColor);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();

        for(int i=0; i < Math.min(stats.length, statImages.length); i++){
            textHeight = fm.getHeight();
            //textWidth = fm.stringWidth(statValues[i]);//
            currentImage = loadImage(path + statImages[i]);

            if(currentImage != null){
                g2d.drawImage(scaleImage(currentImage, imageScaleFactor), margin, i*baseIconSize*imageScaleFactor + (i + 1)*margin, null);
            }
            //g2d.setColor(this.textColor);//
            g2d.drawString(stats[i], baseIconSize*imageScaleFactor + 2*margin, i*baseIconSize*imageScaleFactor + (i + 1)*margin + (textHeight + baseIconSize*imageScaleFactor)/2 - fm.getMaxDescent());
            g2d.drawString(statValues[i], maxWidth + baseIconSize*imageScaleFactor + 3*margin, i*baseIconSize*imageScaleFactor + (i + 1)*margin + (textHeight + baseIconSize*imageScaleFactor)/2 - fm.getMaxDescent());

            //g2d.setColor(Color.RED);
            //g2d.drawRect(0, i*baseIconSize*imageScaleFactor + (i + 1)*this.margin + (textHeight + baseIconSize*imageScaleFactor)/2 - fm.getMaxDescent() - textHeight,  baseIconSize*imageScaleFactor + textWidth + maxWidth + 3*this.margin, textHeight + fm.getMaxDescent()*2);
            //g2d.setColor(Color.GREEN);
            //g2d.drawRect(0, i*baseIconSize*imageScaleFactor + (i + 1)*this.margin + (textHeight + baseIconSize*imageScaleFactor)/2 - fm.getMaxDescent(), baseIconSize*imageScaleFactor + textWidth + maxWidth + 3*this.margin, 0);
            //g2d.setColor(Color.BLUE);
            //g2d.drawRect(0, i*baseIconSize*imageScaleFactor + (i + 1)*this.margin + (textHeight + baseIconSize*imageScaleFactor)/2 - fm.getMaxDescent() - textHeight/2 + fm.getMaxDescent(), baseIconSize*imageScaleFactor + textWidth + maxWidth + 3*this.margin, 0);
        }

        if(wandSprite != null){
            g2d.drawImage(wandSprite,  baseIconSize*imageScaleFactor + maxWidth + maxWidth2 + 4*margin, Math.max((JPanelHeight - wandSprite.getHeight())/2, 0), null);
            //g2d.setColor(Color.RED);//
            //g2d.drawRect(baseIconSize*imageScaleFactor + maxWidth + maxWidth2 + 4*margin, Math.max((JPanelHeight - wandSprite.getHeight())/2, 0), wandSprite.getWidth(), wandSprite.getHeight());//
        }

        result = new JPanel(null){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(statJPanelImage, 0, 0, this);
            }
        };

        result.setBackground(backgroundColor);
        result.setPreferredSize(new Dimension(JPanelWidth, JPanelHeight));
        result.setBounds(0, 0, JPanelWidth, JPanelHeight);
        return result;
    }
}

public class WandList{
    WandStat[] wandList;

    // https://wondible.com/noita-know-your-wand/
    public WandList(){
        wandList = new WandStat[]{
            //new WandStat("wand_0000.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0001.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0002.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0003.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0004.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0005.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0006.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0007.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0008.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0009.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0010.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0011.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0012.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0013.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0014.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0015.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0016.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0017.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0018.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0019.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0020.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0021.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0022.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0023.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0024.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0025.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0026.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0027.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0028.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0029.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0030.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0031.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0032.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0033.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            //new WandStat("wand_0034.png", AA, new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA), new IntRange(AA, AA)),
            new WandStat("wand_0035.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(30, false), new IntRange(23, true), new IntRange(5, 7)),
            new WandStat("wand_0103.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(30, 60), new IntRange(20, 22), new IntRange(7, true)),
            new WandStat("wand_0105.png", true, new IntRange(1, 1), new IntRange(16, 16), new IntRange(30, 60), new IntRange(1, 4), new IntRange(7, true)),
            new WandStat("wand_0140.png", false, new IntRange(3, true), new IntRange(2, 2), new IntRange(30, 60), new IntRange(20, 22), new IntRange(5, false)),
            new WandStat("wand_0173.png", false, new IntRange(1, 1), new IntRange(9, 9), new IntRange(30, 60), new IntRange(23, true), new IntRange(7, true)),
            new WandStat("wand_0232.png", false, new IntRange(1, 1), new IntRange(9, 9), new IntRange(60, true), new IntRange(23, true), new IntRange(5, 7)),
            new WandStat("wand_0236.png", false, new IntRange(1, 1), new IntRange(2, 2), new IntRange(30, false), new IntRange(17, 19), new IntRange(5, false)),
            new WandStat("wand_0260.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(60, true), new IntRange(23, true), new IntRange(7, true)),
            new WandStat("wand_0338.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(30, 60), new IntRange(23, true), new IntRange(5, 7)),
            new WandStat("wand_0362.png", false, new IntRange(3, true), new IntRange(23, 23), new IntRange(30, 60), new IntRange(20, 22), new IntRange(5, false)),
            new WandStat("wand_0366.png", true, new IntRange(1, 1), new IntRange(23, 23), new IntRange(30, 60), new IntRange(1, 4), new IntRange(5, 7)),
            new WandStat("wand_0371.png", true, new IntRange(1, 1), new IntRange(16, 16), new IntRange(30, 60), new IntRange(1, 4), new IntRange(5, false)),
            new WandStat("wand_0441.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(30, false), new IntRange(20, 22), new IntRange(7, true)),
            new WandStat("wand_0454.png", false, new IntRange(3, true), new IntRange(30, 30), new IntRange(30, 60), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0455.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(60, true), new IntRange(23, true), new IntRange(5, 7)),
            new WandStat("wand_0478.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(30, 60), new IntRange(20, 22), new IntRange(5, 7)),
            new WandStat("wand_0481.png", false, new IntRange(3, true), new IntRange(2, 2), new IntRange(30, 60), new IntRange(23, true), new IntRange(7, true)),
            new WandStat("wand_0504.png", false, new IntRange(3, true), new IntRange(30, 30), new IntRange(30, false), new IntRange(20, 22), new IntRange(7, true)),
            new WandStat("wand_0541.png", true, new IntRange(1, 1), new IntRange(30, 30), new IntRange(30, false), new IntRange(1, 4), new IntRange(5, false)),
            new WandStat("wand_0562.png", true, new IntRange(1, 1), new IntRange(30, 30), new IntRange(30, 60), new IntRange(1, 4), new IntRange(7, true)),
            new WandStat("wand_0595.png", false, new IntRange(3, true), new IntRange(30, 30), new IntRange(30, 60), new IntRange(20, 22), new IntRange(5, 7)),
            new WandStat("wand_0596.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(30, 60), new IntRange(23, true), new IntRange(7, true)),
            new WandStat("wand_0607.png", true, new IntRange(1, 1), new IntRange(30, 30), new IntRange(30, false), new IntRange(1, 4), new IntRange(7, true)),
            new WandStat("wand_0621.png", false, new IntRange(1, 1), new IntRange(30, 30), new IntRange(30, 60), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0629.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(30, false), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0642.png", true, new IntRange(1, 1), new IntRange(30, 30), new IntRange(30, 60), new IntRange(1, 4), new IntRange(5, false)),
            new WandStat("wand_0650.png", false, new IntRange(3, true), new IntRange(2, 2), new IntRange(30, 60), new IntRange(20, 22), new IntRange(5, false)),
            new WandStat("wand_0701.png", false, new IntRange(3, true), new IntRange(23, 23), new IntRange(60, true), new IntRange(20, 22), new IntRange(5, false)),
            new WandStat("wand_0761.png", false, new IntRange(1, 1), new IntRange(9, 9), new IntRange(60, true), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0789.png", false, new IntRange(2, 2), new IntRange(2, 2), new IntRange(30, false), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0802.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(30, 60), new IntRange(20, 22), new IntRange(7, true)),
            new WandStat("wand_0816.png", false, new IntRange(3, true), new IntRange(16, 16), new IntRange(60, false), new IntRange(23, true), new IntRange(7, true)),
            new WandStat("wand_0821.png", false, new IntRange(1, 1), new IntRange(2, 2), new IntRange(30, false), new IntRange(23, true), new IntRange(5, false)),
            new WandStat("wand_0833.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(30, false), new IntRange(23, true), new IntRange(5, 7)),
            new WandStat("wand_0847.png", true, new IntRange(1, 1), new IntRange(2, 2), new IntRange(30, 60), new IntRange(1, 4), new IntRange(5, 7)),
            new WandStat("wand_0896.png", false, new IntRange(3, true), new IntRange(23, 23), new IntRange(60, true), new IntRange(20, 22), new IntRange(7, true)),
            new WandStat("wand_0897.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(60, true), new IntRange(20, 22), new IntRange(5, 7)),
            new WandStat("wand_0963.png", false, new IntRange(3, true), new IntRange(9, 9), new IntRange(30, 60), new IntRange(23, true), new IntRange(5, 7))
        };
        this.sort();
    }

    public void generateAllSprites(){
        JPanel wandJPanel;
        String outputPath = Global.getPathOutput();
        for(int i=0; i < this.wandList.length; i++){
            System.out.println(this.wandList[i].toString());
            wandJPanel = this.wandList[i].getJPanel();
            if(wandJPanel != null && wandJPanel.getSize().width > 0 && wandJPanel.getSize().height > 0){
                BufferedImage bi = new BufferedImage(wandJPanel.getSize().width, wandJPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                wandJPanel.paint(g);
                g.dispose();
                try{
                    ImageIO.write(bi,"png",new File(outputPath + "wandStat_" + this.wandList[i].getFileName()));
                }catch(Exception e){
                    System.out.println("error while saving image \"" + outputPath + "wandStat_" + this.wandList[i].getFileName() + "\"");
                }
            }
        }
    }

    public void sort(){
        WandStat tmp;

        for(int i=0; i < this.wandList.length; i++){
            for(int j=1; j < this.wandList.length; j++){
                if(WandStat.compare(this.wandList[j - 1], this.wandList[j]) < 0){
                    tmp = this.wandList[j - 1];
                    this.wandList[j - 1] = this.wandList[j];
                    this.wandList[j] = tmp;
                }
            }
        }
    }

    public String getSprite(Wand wand){
        int min = 0;
        int max = this.wandList.length - 1;
        int current;
        int comparaisonResult;
        boolean stop = false;

        while(min < max && !stop){
            current = min + (max - min)/2;
            comparaisonResult = WandStat.compare(wand, this.wandList[current]);
            if(comparaisonResult > 0){
                max = current;
            }else if(comparaisonResult < 0){
                min = current;
            }else{
                min = current;
                max = current;
                while(min - 1 >= 0 && WandStat.compare(wand, this.wandList[min - 1]) == 0){
                    min--;
                }
                while(max + 1 < this.wandList.length && WandStat.compare(wand, this.wandList[max + 1]) == 0){
                    max++;
                }
            }
            if(min + 1 == max){
                comparaisonResult = Math.abs(WandStat.compare(wand, this.wandList[min])) - Math.abs(WandStat.compare(wand, this.wandList[max]));
                if(comparaisonResult > 0){
                    min = max;
                }else if(comparaisonResult < 0){
                    max = min;
                }else{
                    stop = true;
                }
            }
            System.out.println("range found: [" + min + ";" + max + "]");
        }

        System.out.println("valid wands:");
        for(int i=min; i <= max; i++){
            System.out.println(this.wandList[i].toString());
        }

        Random random = new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        System.out.println("wand N°" + randomNumber + " " + this.wandList[randomNumber].getSprite());
        return this.wandList[randomNumber].getSprite();
    }
}