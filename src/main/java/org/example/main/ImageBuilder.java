package org.example.main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class ImageBuilder{
    private final int margin = 20;
    private BufferedImage canvas;
    private Graphics2D g2d;
    private int canvasWidth = 1;
    private int canvasHeight = 1;
    private final Color backgroundColor;
    private LinkedList<DrawAction> drawActions = new LinkedList<>();

    static class DrawAction{
        Consumer<Object[]> function;
        Object[] parameters;

        DrawAction(Consumer<Object[]> function, Object[] parameters){
            this.function = function;
            this.parameters = parameters;
        }

        public void callFunction(){
            function.accept(parameters);
        }
    }

    public ImageBuilder(Color backgroundColor){
        this.backgroundColor = backgroundColor;
    }

    private void createG2D(){
        this.canvas = new BufferedImage(this.canvasWidth + 2 * this.margin, this.canvasHeight + 2 * this.margin, BufferedImage.TYPE_INT_ARGB);
        this.g2d = this.canvas.createGraphics();

        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        this.g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    }

    public void clear(){
        if(!this.autoExpandCanvas()){
            this.g2d.setColor(this.backgroundColor);
            this.g2d.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        }
        this.drawActions.clear();
    }

    private boolean autoExpandCanvas(){
        if(this.g2d == null){
            this.createG2D();
            this.g2d.setColor(this.backgroundColor);
            this.g2d.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
            return true;
        }else if(this.canvas.getWidth() >= this.canvasWidth + 2*this.margin && this.canvas.getHeight() >= this.canvasHeight + 2*this.margin){
            return false;
        }

        BufferedImage newCanvas = new BufferedImage(this.canvasWidth + 2*this.margin, this.canvasHeight + 2*this.margin, BufferedImage.TYPE_INT_ARGB);
        this.g2d = newCanvas.createGraphics();
        this.g2d.setColor(this.backgroundColor);
        this.g2d.fillRect(0, 0, newCanvas.getWidth(), newCanvas.getHeight());
        this.g2d.drawImage(this.canvas, 0, 0, null);
        this.canvas = newCanvas;

        return true;
    }

    public void addImage(BufferedImage image, int x, int y){
        this.canvasWidth = Math.max(this.canvasWidth, x + image.getWidth());
        this.canvasHeight = Math.max(this.canvasHeight, y + image.getHeight());
        drawActions.add(new DrawAction(this::addImage, new Object[]{image, x, y}));
    }

    public void drawArrow(int x1, int y1, int x2, int y2, Color color, boolean priorityX, boolean drawUnder){
        this.canvasWidth = Math.max(this.canvasWidth, Math.max(x1, x2));
        this.canvasHeight = Math.max(this.canvasHeight, Math.max(y1, y2));
        if(drawUnder){
            this.drawActions.addFirst(new DrawAction(this::drawArrow, new Object[]{x1, y1, x2, y2, color, priorityX}));
        }else{
            this.drawActions.add(new DrawAction(this::drawArrow, new Object[]{x1, y1, x2, y2, color, priorityX}));
        }
    }

    public void drawCurlyBrackets(int x1, int y1, int x2, int y2, int nb, Color color){
        this.canvasWidth = Math.max(this.canvasWidth, Math.max(x1, x2));
        this.canvasHeight = Math.max(this.canvasHeight, Math.max(y1, y2));
        drawActions.add(new DrawAction(this::drawCurlyBrackets, new Object[]{x1, y1, x2, y2, nb, color}));
    }

    public BufferedImage toImage(){
        this.autoExpandCanvas();

        for(DrawAction action : drawActions){
            action.callFunction();
        }
        this.drawActions.clear();

        return this.canvas;
    }

    private void addImage(Object[] objects){
        try{
            this.addImageInternal((BufferedImage)objects[0], (int)objects[1], (int)objects[2]);
        }catch(Exception e){
            System.out.println("Error executing addImage() " + e.getMessage());
        }
    }

    private void addImageInternal(BufferedImage image, int x, int y){
        this.g2d.drawImage(image, x + this.margin, y + this.margin, null);
    }

    private void drawArrow(Object[] objects){
        try{
            this.drawArrowInternal((int)objects[0], (int)objects[1], (int)objects[2], (int)objects[3], (Color)objects[4], (boolean)objects[5]);
        }catch(Exception e){
            System.out.println("Error executing drawArrow() " + e.getMessage());
        }
    }

    private void drawArrowInternal(int x1, int y1, int x2, int y2, Color color, boolean priorityX){
        Path2D path = new Path2D.Double();
        boolean invertX = x1 > x2;
        boolean invertY = y1 > y2;
        int middleWidth = (x2 + x1)/2;
        int middleHeight = (y2 + y1)/2;
        int radius = Math.max(Math.min(Math.abs(y2 - y1)/2, Math.abs((x2 - x1)/2)), 0);
        int[][] points = new int[][]{
            {(priorityX ? middleWidth : x1) + (invertX == priorityX ? 0 : -2*radius) + this.margin, (priorityX ? y1 : middleHeight) + (invertY == priorityX ? -2*radius : 0) + this.margin, priorityX ? (invertY ? 270 : 90) : (invertX ? 0 : 180), (invertX ^ invertY) ^ !priorityX ? 90 : -90},
            {(priorityX ? middleWidth : x2) + (invertX == priorityX ? -2*radius : 0) + this.margin, (priorityX ? y2 : middleHeight) + (invertY == priorityX ? 0 : -2*radius) + this.margin, priorityX ? (invertX ? 0 : 180) : (invertY ? 270 : 90), (invertX ^ invertY) ^ !priorityX ? -90 : 90}
        };

        this.g2d.setColor(color);
        path.moveTo(x1 + this.margin, y1 + this.margin);
        for(int i=0; i < points.length; i++){
            path.append(new Arc2D.Double(points[i][0], points[i][1], 2*radius, 2*radius, points[i][2], points[i][3], Arc2D.OPEN), true);
        }
        path.lineTo(x2 + this.margin, y2 + this.margin);

        this.g2d.draw(path);
        this.drawArrowhead(x2 + (priorityX ? (invertX ? -1 : 1) : 0), y2, priorityX ? invertX : invertY, priorityX);
    }

    private void drawArrowhead(int x, int y, boolean invertX, boolean invertY){
        int arrowLength = 8;
        int arrowWidth = 4;
        int x1 = x + (invertY ? (invertX ? 1 : -1)*arrowLength : arrowWidth);
        int y1 = y + (invertY ? arrowWidth : (invertX ? 1 : -1)*arrowLength);
        int x2 = x - (invertY ? (invertX ? -1 : 1)*arrowLength : arrowWidth);
        int y2 = y - (invertY ? arrowWidth : (invertX ? -1 : 1)*arrowLength);

        Path2D arrowhead = new Path2D.Double();
        arrowhead.moveTo(x + this.margin, y + this.margin);
        arrowhead.lineTo(x1 + this.margin + (invertY ? 0 : 0.1), y1 + this.margin);
        arrowhead.lineTo(x2 + this.margin, y2 + this.margin);
        arrowhead.closePath();
        this.g2d.fill(arrowhead);
    }

    private void drawCurlyBrackets(Object[] objects){
        try{
            this.drawCurlyBracketsInternal((int)objects[0], (int)objects[1], (int)objects[2], (int)objects[3], (int)objects[4], (Color)objects[5]);
        }catch(Exception e){
            System.out.println("Error executing drawCurlyBrackets() " + e.getMessage());
        }
    }

    private void drawCurlyBracketsInternal(int x1, int y1, int x2, int y2, int nb, Color color){
        Path2D path = new Path2D.Double();
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

        int middleWidth = (x2 + x1)/2;
        int middleHeight = (y2 + y1)/2;
        int radius = Math.max(Math.min((y2 - y1)/2, (x2 - x1)/4), 0);
        int[][] points = new int[][]{
            {x1 + this.margin, middleHeight - 2*radius + this.margin, 180, 90},
            {middleWidth - 2*radius + this.margin, middleHeight + this.margin, 90, -90}
        };

        this.g2d.setColor(color);
        path.moveTo(x1 + this.margin, y1 + this.margin);
        for(int i=0; i < points.length; i++){
            path.append(new Arc2D.Double(points[i][0], points[i][1], 2*radius, 2*radius, points[i][2], points[i][3], Arc2D.OPEN), true);
        }
        path.lineTo(middleWidth + this.margin, y2 + this.margin);

        AffineTransform transform = new AffineTransform();
        transform.translate(path.getCurrentPoint().getX(), 0);
        transform.scale(-1, 1);
        transform.translate(-path.getCurrentPoint().getX(), 0);
        path.append(path.createTransformedShape(transform), false);

        this.g2d.drawString("x" + nb, (x2 + x1)/2 - this.g2d.getFontMetrics().stringWidth("x" + nb)/2 + this.margin, y2 + this.g2d.getFontMetrics().getHeight() + this.margin);
        this.g2d.draw(path);
    }

    public boolean saveToFile(String filePath){
        try{
            ImageIO.write(this.toImage(), "png", new File(filePath));
            return true;
        }catch(IOException e){
            return false;
        }
    }
}