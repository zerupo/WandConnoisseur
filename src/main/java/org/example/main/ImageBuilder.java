package org.example.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Path2D;

public class ImageBuilder{
    private int margin = 20;
    private BufferedImage canvas;
    private Graphics2D g2d;
    private int canvasWidth = 1;
    private int canvasHeight = 1;
    private Color backgroundColor = Color.WHITE;

    public ImageBuilder(Color backgroundColor){
        this.backgroundColor = backgroundColor;
        createCanvas();
    }

    private void createCanvas(){
        this.canvas = new BufferedImage(this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin, BufferedImage.TYPE_INT_ARGB);
        this.g2d = this.canvas.createGraphics();
        clearCanvas();
    }

    private void clearCanvas(){
        this.g2d.setComposite(AlphaComposite.Clear);
        this.g2d.fillRect(0, 0, this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin);
        this.g2d.setComposite(AlphaComposite.SrcOver);
    }

    private void expandCanvas(int newWidth, int newHeight){
        newWidth = Math.max(this.canvasWidth, newWidth);
        newHeight = Math.max(this.canvasHeight, newHeight);
        if(newWidth <= this.canvasWidth && newHeight <= this.canvasHeight){
            return;
        }
        BufferedImage newCanvas = new BufferedImage(newWidth + 2*this.margin, newHeight + 2*this.margin, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newCanvas.createGraphics();
        g.drawImage(this.canvas, 0, 0, null);
        g.dispose();

        this.canvas = newCanvas;
        this.g2d = this.canvas.createGraphics();
        this.canvasWidth = newWidth;
        this.canvasHeight = newHeight;
    }

    public void addImage(BufferedImage image, int x, int y){
        int newWidth = Math.max(this.canvasWidth, x + image.getWidth());
        int newHeight = Math.max(this.canvasHeight, y + image.getHeight());

        expandCanvas(newWidth, newHeight);

        this.g2d.drawImage(image, x + this.margin, y + this.margin, null);
    }

    public void drawArrow(int x1, int y1, int x2, int y2, Color color, boolean drawUnder){
        int radius = 0;
        int tmp;
        boolean right;

        if(y1 > y2){
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        radius = (y2 - y1)/2;
        radius = Math.min(radius, Math.abs((x2 - x1)/2));
        radius = Math.max(radius, 0);
        expandCanvas(Math.max(x1, x2), y2);
        this.g2d.setColor(color);

        if(drawUnder){
            BufferedImage tempImage = new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D tempG2D = tempImage.createGraphics();
            tempG2D.setColor(color);
            //tempG2D.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if(x2 > x1){
                tempG2D.drawLine(x1 + this.margin, y1 + this.margin, x1 + (x2 - x1)/2 - radius + this.margin, y1 + this.margin);
                tempG2D.drawArc(x1 + (x2 - x1)/2 - 2*radius + this.margin, y1 + this.margin, 2*radius, 2*radius, 90, -90);
                tempG2D.drawLine((x2 + x1)/2 + this.margin, y1 + radius + this.margin, (x2 + x1)/2 + this.margin, y2 - radius + this.margin);
                tempG2D.drawArc(x2 - (x2 - x1)/2 + this.margin, y2 - 2*radius + this.margin, 2*radius, 2*radius, 180, 90);
                tempG2D.drawLine(x2 - (x2 - x1)/2 + radius + this.margin, y2 + this.margin, x2 + this.margin, y2 + this.margin);
                this.drawArrowhead(x2 + 1, y2, true);
            }else{
                tempG2D.drawArc(x1 - (x1 - x2)/2 + this.margin, y1 + this.margin, 2*radius, 2*radius, 90, 90);
                tempG2D.drawLine((x2 + x1)/2 + this.margin, y1 + radius + this.margin, (x2 + x1)/2 + this.margin, y2 - radius + this.margin);
                tempG2D.drawArc(x2 + (x1 - x2)/2 - 2*radius + this.margin, y2 - 2*radius + this.margin, 2*radius, 2*radius, 0, -90);
                tempG2D.drawLine(x2 + (x1 - x2)/2 - radius + this.margin, y2 + this.margin, x2 + this.margin, y2 + this.margin);
                this.drawArrowhead(x2, y2, false);
            }
            tempG2D.dispose();
            combineImages(tempImage);
            this.g2d.dispose();
            this.g2d = this.canvas.createGraphics();
        }else{
            if(x2 > x1){
                this.g2d.drawLine(x1 + this.margin, y1 + this.margin, x1 + (x2 - x1)/2 - radius + this.margin, y1 + this.margin);
                this.g2d.drawArc(x1 + (x2 - x1)/2 - 2*radius + this.margin, y1 + this.margin, 2*radius, 2*radius, 90, -90);
                this.g2d.drawLine((x2 + x1)/2 + this.margin, y1 + radius + this.margin, (x2 + x1)/2 + this.margin, y2 - radius + this.margin);
                this.g2d.drawArc(x2 - (x2 - x1)/2 + this.margin, y2 - 2*radius + this.margin, 2*radius, 2*radius, 180, 90);
                this.g2d.drawLine(x2 - (x2 - x1)/2 + radius + this.margin, y2 + this.margin, x2 + this.margin, y2 + this.margin);
                this.drawArrowhead(x2 + 1, y2, true);
            }else{
                this.g2d.drawArc(x1 - (x1 - x2)/2 + this.margin, y1 + this.margin, 2*radius, 2*radius, 90, 90);
                this.g2d.drawLine((x2 + x1)/2 + this.margin, y1 + radius + this.margin, (x2 + x1)/2 + this.margin, y2 - radius + this.margin);
                this.g2d.drawArc(x2 + (x1 - x2)/2 - 2*radius + this.margin, y2 - 2*radius + this.margin, 2*radius, 2*radius, 0, -90);
                this.g2d.drawLine(x2 + (x1 - x2)/2 - radius + this.margin, y2 + this.margin, x2 + this.margin, y2 + this.margin);
                this.drawArrowhead(x2, y2, false);
            }
        }
    }

    private void combineImages(BufferedImage newImage){
        BufferedImage combinedImage = new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D combinedG2D = combinedImage.createGraphics();
        combinedG2D.drawImage(newImage, 0, 0, null);
        combinedG2D.drawImage(this.canvas, 0, 0, null);
        this.canvas = combinedImage;
        combinedG2D.dispose();
    }

    private void drawArrowhead(int x, int y, boolean right){
        int arrowLength = 8;
        int arrowWidth = 4;
        int x1;
        int y1;
        int x2;
        int y2;

        y1 = y - arrowWidth;
        y2 = y + arrowWidth;
        if(right){
            x1 = x - arrowLength;
            x2 = x - arrowLength;
        }else{
            x1 = x + arrowLength;
            x2 = x + arrowLength;
        }

        Path2D arrowhead = new Path2D.Double();
        arrowhead.moveTo(x + this.margin, y + this.margin);
        arrowhead.lineTo(x1 + this.margin, y1 + this.margin);
        arrowhead.lineTo(x2 + this.margin, y2 + this.margin);
        arrowhead.closePath();
        this.g2d.fill(arrowhead);
    }

    public void drawCurlyBrackets(int x1, int y1, int x2, int y2, int nb, Color color){
        int radius = 0;
        int tmp;

        if(y1 > y2){
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        if(x1 > x2){
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        if(y2 - y1 >= this.g2d.getFontMetrics().getHeight()){
            y2 -= this.g2d.getFontMetrics().getHeight();
        }else{
            y2 = y1;
        }

        radius = (y2 - y1)/2;
        radius = Math.min(radius, (x2 - x1)/4);
        radius = Math.max(radius, 0);

        expandCanvas(x2, y2);
        this.g2d.setColor(color);

        this.g2d.drawLine(x1 + this.margin, y1 + this.margin, x1 + this.margin, (y2 + y1)/2 - radius + this.margin);
        this.g2d.drawArc(x1 + this.margin, (y2 + y1)/2 - 2*radius + this.margin, 2*radius, 2*radius, 180, 90);
        this.g2d.drawLine(x1 + radius + this.margin, (y2 + y1)/2 + this.margin, (x2 + x1)/2 - radius + this.margin, (y2 + y1)/2 + this.margin);
        this.g2d.drawArc((x2 + x1)/2 - 2*radius + this.margin, (y2 + y1)/2 + this.margin, 2*radius, 2*radius, 0, 90);
        this.g2d.drawLine((x2 + x1)/2 + this.margin, (y2 + y1)/2 + radius + this.margin, (x2 + x1)/2 + this.margin, y2 + this.margin);
        this.g2d.drawArc((x2 + x1)/2 + this.margin, (y2 + y1)/2 + this.margin, 2*radius, 2*radius, 180, -90);
        this.g2d.drawLine((x2 + x1)/2 + radius + this.margin, (y2 + y1)/2 + this.margin, x2 - radius + this.margin, (y2 + y1)/2 + this.margin);
        this.g2d.drawArc(x2 - 2*radius + this.margin, (y2 + y1)/2 - 2*radius + this.margin, 2*radius, 2*radius, 0, -90);
        this.g2d.drawLine(x2 + this.margin, (y2 + y1)/2 - radius + this.margin, x2 + this.margin, y1 + this.margin);
        this.g2d.drawString("x" + nb, (x2 + x1)/2 - this.g2d.getFontMetrics().stringWidth("x" + nb)/2 + this.margin, y2 + this.g2d.getFontMetrics().getHeight() + this.margin);
    }

    public BufferedImage toImage(){
        BufferedImage outputImage = new BufferedImage(this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = outputImage.createGraphics();

        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin);

        g.drawImage(this.canvas, 0, 0, null);
        g.dispose();

        return outputImage;
    }

    public boolean saveToFile(String filePath){
        BufferedImage outputImage = new BufferedImage(this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = outputImage.createGraphics();

        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin);

        g.drawImage(this.canvas, 0, 0, null);
        g.dispose();

        try{
            ImageIO.write(outputImage, "png", new File(filePath));
            return true;
        }catch(IOException e){
            return false;
        }
    }
}