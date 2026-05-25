package org.example.main;

import org.example.spells.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Flowchart{
    private static final BufferedImage copyFailedImage = Global.loadImage("./src/main/java/org/example/image/other/failed.png");
    private static final String copyColor = "\u001b[0;33m"; // yellow
    //private static String copyColor = "\033[0;32m"; // green
    private static final String failedColor = "\u001b[0;31m"; // red
    //private static String failedColor = "\033[0;31m"; // red
    private static final String resetColor = "\u001b[0;0m"; // "\u001B[0m";
    private Spell myself;
    private int chargesLeft = 0;
    private int count = 1;
    private List<Flowchart> spells = new ArrayList<>();
    private boolean isRoot;
    private boolean isCopy = false;
    private boolean copyFailed = false;
    private String rootName = "wand";
    private int width = 3;
    private StringBuilder flowchartString = new StringBuilder();
    private Flowchart root;

    public Spell getSelf(){
        return this.myself;
    }

    public Flowchart(){
        this.isRoot = true;
        this.myself = null;
        this.root = this;
    }

    public Flowchart(String rootName){
        this.isRoot = true;
        this.myself = null;
        this.rootName = rootName;
        this.root = this;
    }

    private Flowchart(Spell spell){
        this.isRoot = false;
        this.myself = spell;
        this.rootName = null;
        this.width = 0;
        this.flowchartString = null;
        if(spell != null && spell.getHasCharges()){
            this.chargesLeft = spell.getChargesLeft();
        }
    }

    public Flowchart add(Spell spell){
        Flowchart currentNode = new Flowchart(spell);
        currentNode.root = this.root;
        this.spells.add(currentNode);
        return currentNode;
    }

    public void refactor(){
        Flowchart[] subNodes = this.spells.toArray(new Flowchart[this.spells.size()]);

        for(int i=0; i < subNodes.length; i++){
            subNodes[i].refactor();
        }

        for(int i=0; i < subNodes.length - 1; i++){
            for(int j=i+1; j < subNodes.length; j++){
                if(subNodes[i] != null && subNodes[j] != null && equals(subNodes[i], subNodes[j])){
                    subNodes[i].count += subNodes[j].count;
                    this.spells.remove(subNodes[j]);
                    subNodes[j] = null;
                }else{
                    break;
                }
            }
        }
    }

    public boolean equals(Flowchart node1, Flowchart node2){
        Flowchart[] subNodes1;
        Flowchart[] subNodes2;

        if(node1.spells.size() != node2.spells.size()){
            return false;
        }
        if(node1.myself == null && node2.myself != null || node1.myself != null && node2.myself == null){
            return false;
        }
        if(node1.myself != null && node2.myself != null && node1.myself.getClass() != node2.myself.getClass()){
            return false;
        }
        if(node1.spells.size() == 0){
            return true;
        }

        subNodes1 = node1.spells.toArray(new Flowchart[0]);
        subNodes2 = node2.spells.toArray(new Flowchart[0]);

        for(int i=0; i < subNodes1.length; i++){
            if(!equals(subNodes1[i], subNodes2[i])){
                return false;
            }
        }
        return true;
    }

    // getters
    public int getWidth(){
        if(this.isRoot){
            return this.width;
        }else{
            return this.root.getWidth();
        }
    }

    public boolean getIsCopy(){
        return this.isCopy;
    }

    public boolean getCopyFailed(){
        return this.copyFailed;
    }

    public String getRootName(){
        if(this.isRoot){
            return this.rootName;
        }else{
            return this.root.getRootName();
        }
    }

    // setters
    public void setWidth(int width){
        if(this.isRoot){
            this.width = width;
        }else{
            this.root.setWidth(width);
        }
    }

    public void setIsCopy(boolean isCopy){
        this.isCopy = isCopy;
    }

    public void setCopyFailed(boolean copyFailed){
        this.copyFailed = copyFailed;
    }

    public void setRootName(String rootName){
        if(this.isRoot){
            this.rootName = rootName;
        }else{
        this.root.setRootName(rootName);
        }
    }

    public void reset(){
        this.spells = new ArrayList<>();
        if(this.isRoot){
            this.flowchartString = new StringBuilder();
        }
    }

    public String toString(boolean formatting){
        if(this.isRoot){
            this.flowchartString = new StringBuilder(this.rootName + "\n");
            this.toString(this.flowchartString, "", formatting);
            return this.flowchartString.toString();
        }else{
            return this.root.toString(formatting);
        }
    }

    private void toString(StringBuilder sb, String currentFlow, boolean formatting){
        Flowchart[] flowchartArray = this.spells.toArray(new Flowchart[0]);
        Flowchart currentFlowchart;
        boolean last = false;
        boolean copy = false;
        boolean failed = false;

        for(int i=0; i < flowchartArray.length; i++){
            // is last
            if(i + 1 == flowchartArray.length){
                last = true;
            }else{
                last = false;
            }
            // is copy
            currentFlowchart = flowchartArray[i];
            if(currentFlowchart.isCopy){
                copy = true;
            }else{
                copy = false;
            }
            // is failed
            if(currentFlowchart.myself != null){
                failed = false;
            }else{
                failed = true;
            }

            sb.append(currentFlow);
            if(formatting){
                if(failed){
                    sb.append(failedColor);
                }else if(copy){
                    sb.append(copyColor);
                }
            }
            if(last){
                sb.append("\u2514");
            }else{
                sb.append("\u251c");
            }
            sb.append("\u2500".repeat(this.getWidth()));
            if(formatting && (copy || failed)){
                sb.append(resetColor);
            }
            if(currentFlowchart.myself != null){
                if(currentFlowchart.copyFailed){
                    if(formatting){
                        sb.append(failedColor).append(currentFlowchart.myself.getName()).append(resetColor);
                    }else{
                        sb.append("(").append(currentFlowchart.myself.getName()).append(")");
                    }
                }else{
                    sb.append(currentFlowchart.myself.getName());
                }
                /*if(currentFlowchart.myself.getHasCharges() && !currentFlowchart.isCopy){
                    sb.append(" (" + currentFlowchart.chargesLeft + ")");
                }*/
                if(currentFlowchart.count > 1){
                    sb.append(" (x").append(currentFlowchart.count).append(")");
                }
                sb.append("\n");
            }else{
                sb.append("X\n");
            }
            if(last){
                currentFlowchart.toString(sb, currentFlow + " ".repeat(this.getWidth() + 1), formatting);
            }else{
                if(formatting && flowchartArray[i+1].myself == null){
                    currentFlowchart.toString(sb, currentFlow + failedColor + "\u2502" + resetColor + " ".repeat(this.getWidth()), formatting);
                }else if(formatting && flowchartArray[i+1].isCopy){
                    currentFlowchart.toString(sb, currentFlow + copyColor + "\u2502" + resetColor + " ".repeat(this.getWidth()), formatting);
                }else{
                    currentFlowchart.toString(sb, currentFlow + "\u2502" + " ".repeat(this.getWidth()), formatting);
                }
            }
        }
    }

    public boolean saveToImage(String filename){
        if(this.isRoot){
            ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
            image.setFont(Global.getPixelFont().deriveFont((float)15));
            this.toImageNode(image, 0, 0);
            return image.saveToFile(filename);
        }else{
            return this.root.saveToImage(filename);
        }
    }

    public BufferedImage toImage(){
        if(this.isRoot){
            ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
            image.setFont(Global.getPixelFont().deriveFont((float)15));
            this.toImageNode(image, 0, 0);
            return image.toImage();
        }else{
            return this.root.toImage();
        }
    }

    public Point toImage(ImageBuilder image, int x, int y){
        if(this.isRoot){
            image.setFont(Global.getPixelFont().deriveFont((float)15));
            Point tempPoint = this.toImageNode(image, x, y);
            tempPoint.y += 40;
            return tempPoint;
        }else{
            return this.root.toImage(image, x, y);
        }
    }

    private Point toImageNode(ImageBuilder image, int x, int y){
        Flowchart[] flowchartArray = this.spells.toArray(new Flowchart[0]);
        Flowchart currentFlowchart;
        BufferedImage currentImage = null;
        boolean copy = false;
        int imageSize = 16;
        int arrowSizeX = 40;
        int arrowSizeY = 40;
        int nextX = x + imageSize + arrowSizeX;
        int nextY = y;
        int maxX = x;
        int maxMaxX = x;
        Point maxPoint = new Point(maxX, nextY);

        for(int i=0; i < flowchartArray.length; i++){
            maxX = x;
            currentFlowchart = flowchartArray[i];
            copy = currentFlowchart.isCopy;

            if(currentFlowchart.myself != null){
                currentImage = currentFlowchart.myself.getImage();
                if(currentImage != null){
                    image.addImage(currentImage, x + arrowSizeX + (imageSize - currentImage.getWidth())/2, nextY + (imageSize - currentImage.getHeight())/2);
                    maxX = Math.max(maxX, x + arrowSizeX + imageSize - currentImage.getWidth()/2);
                }
            }
            if(copyFailedImage != null && (currentFlowchart.copyFailed || currentFlowchart.myself == null)){
                image.addImage(copyFailedImage, x + arrowSizeX + (imageSize - copyFailedImage.getWidth())/2, nextY + (imageSize - copyFailedImage.getHeight())/2);
                maxX = Math.max(maxX, x + arrowSizeX + imageSize - copyFailedImage.getWidth()/2);
            }
            if(currentFlowchart.myself == null){
                image.drawArrow(x, y + imageSize/2, x + arrowSizeX - 1, nextY + imageSize/2, Color.RED, true, true);
            }else if(copy){
                image.drawArrow(x, y + imageSize/2, x + arrowSizeX - 1, nextY + imageSize/2, Color.YELLOW, true, true);
            }else{
                image.drawArrow(x, y + imageSize/2, x + arrowSizeX - 1, nextY + imageSize/2, Color.WHITE, true, true);
            }
            maxX = Math.max(maxX, x + arrowSizeX - 1);

            maxPoint = currentFlowchart.toImageNode(image, nextX, nextY);
            nextY = (int)maxPoint.getY();
            maxX = Math.max(maxX, (int)maxPoint.getX());
            maxMaxX = Math.max(maxMaxX, maxX);
            if(currentFlowchart.count > 1){
                nextY += arrowSizeY;
                image.drawCurlyBrackets(x + arrowSizeX, nextY - arrowSizeY/2, maxX, nextY + arrowSizeY/2, currentFlowchart.count, Color.WHITE);
            }
            if(i + 1 != flowchartArray.length){
                nextY += arrowSizeY;
            }
        }

        return new Point(maxMaxX, nextY);
    }
}