package org.example.main;

import static org.example.main.Global.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

enum RangeType{BETWEEN, GREATER_OR_EQUAL, LESS_OR_EQUAL}

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
        return switch(type){
            case BETWEEN -> value >= this.min && value <= this.max;
            case GREATER_OR_EQUAL -> value >= this.min;
            case LESS_OR_EQUAL -> value <= this.min;
        };
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
            return switch(this.type){
                case GREATER_OR_EQUAL -> start + "[" + stringMin + "; \u221e[" + end;
                case LESS_OR_EQUAL -> start + "]\u221e; " + stringMin + "]" + end;
                default -> stringMin.equals(stringMax) ? start + stringMin + end : start + "[" + stringMin + "; " + stringMax + "]" + end;
            };
        }else{
            return switch(this.type){
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
        if(!filePath.equals("")){
            this.filePath = filePath;
        }
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
            case GREATER_OR_EQUAL -> {
                if(wand.getNbDraw() < wandStat.nbDraw.getMin()){
                    return 5;
                }
            }
            case LESS_OR_EQUAL -> {
                if(wand.getNbDraw() > wandStat.nbDraw.getMin()){
                    return -5;
                }
            }
            default -> {
                if(wand.getNbDraw() < wandStat.nbDraw.getMin()){
                    return 5;
                }else if(wand.getNbDraw() > wandStat.nbDraw.getMax()){
                    return -5;
                }
            }
        }

        // nbSlot >
        switch(wandStat.nbSlot.getType()){
            case LESS_OR_EQUAL -> {
                if(wand.getNbSlot() > wandStat.nbSlot.getMin()){
                    return 4;
                }
            }
            case GREATER_OR_EQUAL -> {
                if(wand.getNbSlot() < wandStat.nbSlot.getMin()){
                    return -4;
                }
            }
            default -> {
                if(wand.getNbSlot() > wandStat.nbSlot.getMin()){
                    return 4;
                }else if(wand.getNbSlot() < wandStat.nbSlot.getMax()){
                    return -4;
                }
            }
        }

        // rechargeTime <
        switch(wandStat.rechargeTime.getType()){
            case GREATER_OR_EQUAL -> {
                if(wand.getRechargeTime() < wandStat.rechargeTime.getMin()){
                    return 3;
                }
            }
            case LESS_OR_EQUAL -> {
                if(wand.getRechargeTime() > wandStat.rechargeTime.getMin()){
                    return -3;
                }
            }
            default -> {
                if(wand.getRechargeTime() < wandStat.rechargeTime.getMin()){
                    return 3;
                }else if(wand.getRechargeTime() > wandStat.rechargeTime.getMax()){
                    return -3;
                }
            }
        }

        // castDelay <
        switch(wandStat.castDelay.getType()){
            case GREATER_OR_EQUAL -> {
                if(wand.getCastDelay() < wandStat.castDelay.getMin()){
                    return 2;
                }
            }
            case LESS_OR_EQUAL -> {
                if(wand.getCastDelay() > wandStat.castDelay.getMin()){
                    return -2;
                }
            }
            default -> {
                if(wand.getCastDelay() < wandStat.castDelay.getMin()){
                    return 2;
                }else if(wand.getCastDelay() > wandStat.castDelay.getMax()){
                    return -2;
                }
            }
        }

        // spread <
        switch(wandStat.spread.getType()){
            case GREATER_OR_EQUAL -> {
                if(wand.getSpread() < wandStat.spread.getMin()){
                    return 1;
                }
            }
            case LESS_OR_EQUAL -> {
                if(wand.getSpread() > wandStat.spread.getMin()){
                    return -1;
                }
            }
            default -> {
                if(wand.getSpread() < wandStat.spread.getMin()){
                    return 1;
                }else if(wand.getSpread() > wandStat.spread.getMax()){
                    return -1;
                }
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
        Font font = Global.getPixelFont().deriveFont(Font.PLAIN, baseIconSize*imageScaleFactor);
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

// https://wondible.com/noita-know-your-wand/
public class WandList{
    static final WandStat[] wandList = readFile();

    private static WandStat[] readFile(){
        ArrayList<WandStat> wands = new ArrayList<>();
        File file = new File(Global.getPathConfig() + "wand.properties");

        try(Scanner myReader = new Scanner(file)){
            while(myReader.hasNextLine()){
                String[] data = myReader.nextLine().split(";");

                wands.add(new WandStat(
                    data[0], // folder
                    data[1], // file
                    data[2].equals("true"), // shuffle
                    switch(data[4]){ // draw
                        case "true" -> new IntRange(Integer.parseInt(data[3]), true);
                        case "false" -> new IntRange(Integer.parseInt(data[3]), false);
                        default -> new IntRange(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    }, switch(data[6]){ // cast delay
                        case "true" -> new IntRange(Integer.parseInt(data[5]), true);
                        case "false" -> new IntRange(Integer.parseInt(data[5]), false);
                        default -> new IntRange(Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                    }, switch(data[8]){ // recharge time
                        case "true" -> new IntRange(Integer.parseInt(data[7]), true);
                        case "false" -> new IntRange(Integer.parseInt(data[7]), false);
                        default -> new IntRange(Integer.parseInt(data[7]), Integer.parseInt(data[8]));
                    }, switch(data[10]){ // slot
                        case "true" -> new IntRange(Integer.parseInt(data[9]), true);
                        case "false" -> new IntRange(Integer.parseInt(data[9]), false);
                        default -> new IntRange(Integer.parseInt(data[9]), Integer.parseInt(data[10]));
                    }, switch(data[12]){ // spread
                        case "true" -> new IntRange(Integer.parseInt(data[11]), true);
                        case "false" -> new IntRange(Integer.parseInt(data[11]), false);
                        default -> new IntRange(Integer.parseInt(data[11]), Integer.parseInt(data[12]));
                    }
                ));
            }
        }catch(Exception e){
            System.out.println("Error reading wand config: " + e.getMessage());
            return null;
        }

        // sort
        WandStat[] result = wands.toArray(new WandStat[0]);
        WandStat tmp;

        for(int i=0; i < result.length; i++){
            for(int j=1; j < result.length; j++){
                if(WandStat.compare(result[j - 1], result[j]) < 0){
                    tmp = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = tmp;
                }
            }
        }

        /*for(WandStat wand : result){
            System.out.println(wand.toString());
        }*/

        return result;
    }

    public WandList(){
        // empty
    }

    public void generateAllSprites(){
        if(wandList == null){
            System.out.println("error wand file not loaded at \"" + Global.getPathConfig() + "wand.properties" + "\", can't generate sprites");
            return;
        }

        JPanel wandJPanel;
        String outputPath = Global.getPathOutput();
        for(WandStat wandStat : wandList){
            //System.out.println(wandStat.toString());
            wandJPanel = wandStat.getJPanel();
            if(wandJPanel != null && wandJPanel.getSize().width > 0 && wandJPanel.getSize().height > 0){
                BufferedImage bi = new BufferedImage(wandJPanel.getSize().width, wandJPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                wandJPanel.paint(g);
                g.dispose();
                try{
                    ImageIO.write(bi, "png", new File(outputPath + "wandStat_" + wandStat.getFileName()));
                }catch(Exception e){
                    System.out.println("error while saving image \"" + outputPath + "wandStat_" + wandStat.getFileName() + "\"");
                }
            }
        }
    }

    public String getSprite(Wand wand){
        if(wandList == null){
            System.out.println("error wand file not loaded at \"" + Global.getPathConfig() + "wand.properties" + "\", returning default sprite");
            return "./src/main/java/org/example/image/wand/wand_0832.png";
        }

        int min = 0;
        int max = wandList.length - 1;
        int current;
        int comparaisonResult;
        boolean stop = false;

        while(min < max && !stop){
            current = min + (max - min)/2;
            comparaisonResult = WandStat.compare(wand, wandList[current]);
            if(comparaisonResult > 0){
                max = current;
            }else if(comparaisonResult < 0){
                min = current;
            }else{
                min = current;
                max = current;
                while(min - 1 >= 0 && WandStat.compare(wand, wandList[min - 1]) == 0){
                    min--;
                }
                while(max + 1 < wandList.length && WandStat.compare(wand, wandList[max + 1]) == 0){
                    max++;
                }
            }
            if(min + 1 == max){
                comparaisonResult = Math.abs(WandStat.compare(wand, wandList[min])) - Math.abs(WandStat.compare(wand, wandList[max]));
                if(comparaisonResult > 0){
                    min = max;
                }else if(comparaisonResult < 0){
                    max = min;
                }else{
                    stop = true;
                }
            }
            //System.out.println("range found: [" + min + ";" + max + "]\n ->" + wandList[min].toString() + "\n ->" + wandList[max].toString() + "\n");
        }

        /*System.out.println("valid wands (" + min + ", " + max + "):");
        for(int i=min; i <= max; i++){
            System.out.println(wandList[i].toString());
        }*/

        if(min == max){
            Random random = new Random();
            int randomNumber = random.nextInt(max + 1 - min) + min;
            //System.out.println("wand N°" + randomNumber + " " + wandList[randomNumber].getSprite());
            return wandList[randomNumber].getSprite();
        }else{
            return wandList[min].getSprite();
        }
    }
}