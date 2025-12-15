package org.example.main;

import org.example.spells.*;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Color;

import java.awt.Point;

public class Flowchart{
    private Spell myself;
    private int chargesLeft = 0;
    private int count = 1;
    private List<Flowchart> spells = new ArrayList<>();
    private boolean isRoot;
    private boolean isCopy = false;
    private boolean copyFailed = false;
    private String copyFailedImagePath = "./src/main/java/org/example/image/other/";
    //private String copyFailedImagePath = "./image/other/";
    private String copyFailedImageFile = "failed.png";
    private BufferedImage copyFailedImage;
    private String rootName = "wand";
    private int width = 3;
    private String copyColor = "\u001b[0;33m"; // yellow
    //private String copyColor = "\033[0;32m"; // green
    private String failedColor = "\u001b[0;31m"; // red
    //private String failedColor = "\033[0;31m"; // red
    private String resetColor = "\u001b[0;0m"; // "\u001B[0m";
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
        this.copyColor = null;
        this.failedColor = null;
        this.resetColor = null;
        this.flowchartString = null;
        this.copyFailedImagePath = null;
        this.copyFailedImageFile = null;
        this.copyFailedImage = null;
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

        subNodes1 = node1.spells.toArray(new Flowchart[node1.spells.size()]);
        subNodes2 = node2.spells.toArray(new Flowchart[node2.spells.size()]);

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

    public BufferedImage getCopyFailedImage(){
        if(this.isRoot){
            if(this.copyFailedImage == null){
                this.copyFailedImage = Global.loadImage(this.copyFailedImagePath + this.copyFailedImageFile);
                //this.loadImage();
            }
            return this.copyFailedImage;
        }else{
            return this.root.getCopyFailedImage();
        }
    }

    public String getCopyColor(){
        if(this.isRoot){
            return this.copyColor;
        }else{
            return this.root.getCopyColor();
        }
    }

    public String getFailedColor(){
        if(this.isRoot){
            return this.failedColor;
        }else{
            return this.root.getFailedColor();
        }
    }

    public String getResetColor(){
        if(this.isRoot){
            return this.resetColor;
        }else{
            return this.root.getResetColor();
        }
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

    public void setCopyColor(String copyColor){
        if(this.isRoot){
            this.copyColor = copyColor;
        }else{
            this.root.setCopyColor(copyColor);
        }
    }

    public void setFailedColor(String failedColor){
        if(this.isRoot){
            this.failedColor = failedColor;
        }else{
            this.root.setFailedColor(failedColor);
        }
    }

    public void setResetColor(String resetColor){
        if(this.isRoot){
            this.resetColor = resetColor;
        }else{
            this.root.setResetColor(resetColor);
        }
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

    public String toString(boolean formating){
        if(this.isRoot){
            this.flowchartString = new StringBuilder(this.rootName + "\n");
            this.toString(this.flowchartString, "", formating);
            return this.flowchartString.toString();
        }else{
            return this.root.toString(formating);
        }
    }

    private void toString(StringBuilder sb, String currentFlow, boolean formating){
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
            if(formating){
                if(failed){
                    sb.append(this.getFailedColor());
                }else if(copy){
                    sb.append(this.getCopyColor());
                }
            }
            if(last){
                sb.append("\u2514");
            }else{
                sb.append("\u251c");
            }
            sb.append("\u2500".repeat(this.getWidth()));
            if(formating && (copy || failed)){
                sb.append(this.getResetColor());
            }
            if(currentFlowchart.myself != null){
                if(currentFlowchart.copyFailed){
                    if(formating){
                        sb.append(this.getFailedColor() + currentFlowchart.myself.getName() + this.getResetColor());
                    }else{
                        sb.append("(" + currentFlowchart.myself.getName() + ")");
                    }
                }else{
                    sb.append(currentFlowchart.myself.getName());
                }
                /*if(currentFlowchart.myself.getHasCharges() && !currentFlowchart.isCopy){
                    sb.append(" (" + currentFlowchart.chargesLeft + ")");
                }*/
                if(currentFlowchart.count > 1){
                    sb.append(" (x" + currentFlowchart.count + ")");
                }
                sb.append("\n");
            }else{
                sb.append("X\n");
            }
            if(last){
                currentFlowchart.toString(sb, currentFlow + " ".repeat(this.getWidth() + 1), formating);
            }else{
                if(formating && flowchartArray[i+1].myself == null){
                    currentFlowchart.toString(sb, currentFlow + this.getFailedColor() + "\u2502" + this.getResetColor() + " ".repeat(this.getWidth()), formating);
                }else if(formating && flowchartArray[i+1].isCopy){
                    currentFlowchart.toString(sb, currentFlow + this.getCopyColor() + "\u2502" + this.getResetColor() + " ".repeat(this.getWidth()), formating);
                }else{
                    currentFlowchart.toString(sb, currentFlow + "\u2502" + " ".repeat(this.getWidth()), formating);
                }
            }
        }
    }

    public void saveToImage(String filename){
        if(this.isRoot){
            ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
            this.toImageNode(image, 0, 0);
            image.saveToFile(filename);
        }else{
            this.root.toImage();
        }
    }

    public BufferedImage toImage(){
        if(this.isRoot){
            ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
            this.toImageNode(image, 0, 0);
            return image.toImage();
        }else{
            return this.root.toImage();
        }
    }

    public int toImage(ImageBuilder image, int x, int y){
        if(this.isRoot){
            return (int)this.toImageNode(image, x, y).getY() + 40;
        }else{
            return this.root.toImage(image, x, y);
        }
    }

    private Point toImageNode(ImageBuilder image, int x, int y){
        Flowchart[] flowchartArray = this.spells.toArray(new Flowchart[this.spells.size()]);
        Flowchart currentFlowchart;
        BufferedImage currentImage = null;
        BufferedImage failedImage = this.getCopyFailedImage();
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
            if(currentFlowchart.isCopy){
                copy = true;
            }else{
                copy = false;
            }

            if(currentFlowchart.myself != null){
                currentImage = currentFlowchart.myself.getImage();
                if(currentImage != null){
                    image.addImage(currentImage, x + arrowSizeX + (imageSize - currentImage.getWidth())/2, nextY - (currentImage.getHeight() - 1)/2);
                    maxX = Math.max(maxX, x + arrowSizeX + imageSize - currentImage.getWidth()/2);
                }
            }
            if(failedImage != null && (currentFlowchart.copyFailed || currentFlowchart.myself == null)){
                image.addImage(failedImage, x + arrowSizeX + (imageSize - failedImage.getWidth())/2, nextY - (failedImage.getHeight() - 1)/2);
                maxX = Math.max(maxX, x + arrowSizeX + imageSize - failedImage.getWidth()/2);
            }
            if(currentFlowchart.myself == null){
                image.drawArrow(x, y, x + arrowSizeX - 1, nextY, Color.RED, true);
            }else if(copy){
                image.drawArrow(x, y, x + arrowSizeX - 1, nextY, Color.YELLOW, true);
            }else{
                image.drawArrow(x, y, x + arrowSizeX - 1, nextY, Color.WHITE, true);
            }
            maxX = Math.max(maxX, x + arrowSizeX - 1);
            //System.out.println("(" + x + ", " + y + ") -> (" + (x + arrowSizeX - 1) + ", " + nextY + ")");

            maxPoint = currentFlowchart.toImageNode(image, nextX , nextY);
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
        //System.out.println(this.myself.getName() + " (" + this.count + ") return " + maxX);
        return new Point(maxMaxX, nextY);
    }
}