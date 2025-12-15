package org.example.main;

import org.example.spells.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class WandJPanel{
    private Wand wand;
    private BufferedImage slotImage;
    private BufferedImage wandSprite;
    private JPanel basicJPanel;
    private JPanel statJPanel;
    private int baseImageSize;
    private int baseIconSize;
    private final int imageScaleFactor;
    private final int margin;
    private int slotSize;
    private int nbSlot;
    private Color backgroundColor = new Color(0, 0, 0);
    private Color textColor = new Color(255, 255, 255);
    private BufferedImage statJPanelImage;

    public WandJPanel(Wand wand){
        this.wand = wand;
        this.baseImageSize = Global.getBaseImageSize();
        this.baseIconSize = Global.getBaseIconSize();
        this.imageScaleFactor = Global.getImageScaleFactor();
        this.margin = Global.getMargin();
        this.nbSlot = wand.getNbSlot();

        try{
            this.slotImage = ImageIO.read(new File("./src/main/java/org/example/image/wand/full_inventory_box.png"));
            this.slotSize = this.slotImage.getWidth();
            this.slotImage = Global.scaleImage(this.slotImage, this.imageScaleFactor);
        }catch(IOException e){
            System.out.println("unable to load slot image");
        }
    }

    // getter
    public JPanel getBasicJPanel(){
        if(this.basicJPanel == null){
            this.createBasicJPanel();
        }
        return this.basicJPanel;
    }

    public JPanel getStatJPanel(){
        if(this.statJPanel == null){
            this.createStatJPanel();
        }
        return this.statJPanel;
    }

    private void createBasicJPanel(){
        int JPanelHeight;
        int JPanelWidth;

        if(this.slotImage == null){
            try{
                this.slotImage = ImageIO.read(new File("./src/main/java/org/example/image/wand/full_inventory_box.png"));
                this.slotSize = this.slotImage.getWidth();
                this.slotImage = Global.scaleImage(this.slotImage, this.imageScaleFactor);
            }catch(IOException e){
                System.out.println("unable to load slot image");
            }
        }

        if(this.slotImage != null){
            this.basicJPanel = new JPanel(null){
                @Override
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);

                    if(WandJPanel.this.slotImage != null){
                        for(int i=0; i < WandJPanel.this.nbSlot; i++){
                            g.drawImage(WandJPanel.this.slotImage, i*WandJPanel.this.slotSize*WandJPanel.this.imageScaleFactor + (i + 1)*WandJPanel.this.margin, WandJPanel.this.margin, this);
                        }
                    }
                }
            };
        }

        this.basicJPanel.setBackground(this.backgroundColor);
        JPanelWidth = this.nbSlot*this.slotSize*this.imageScaleFactor + (this.nbSlot + 1)*this.margin;
        JPanelHeight = this.slotSize*this.imageScaleFactor + this.margin*2;
        this.basicJPanel.setPreferredSize(new Dimension(JPanelWidth, JPanelHeight));
        this.basicJPanel.setBounds(0, 0, JPanelWidth, JPanelHeight);
        updateSpells();
    }

    private void createStatJPanel(){
        int JPanelWidth;
        int JPanelHeight;
        String path = "./src/main/java/org/example/image/other/";
        String[] stats = new String[]{"Shuffle", "Spells/Cast", "Cast delay", "Rechrg. Time", "Mana max", "Mana chg. Spd", "Capacity", "Spread", "Speed"};
        String[] statImages = new String[]{"shuffle.png", "spell_cast.png", "cast_delay.png", "recharge_time.png", "max_mana.png", "mana_regen.png", "slot.png", "spread.png", "speed.png"};
        String[] statValues = new String[9];
        BufferedImage currentImage;
        Font font;
        FontMetrics fm;
        int textHeight;
        //int textWidth;//
        int maxWidth = 0;
        int maxWidth2 = 0;
        Graphics2D g2d;

        if(this.wandSprite == null){
            this.wandSprite = Global.rotateImageByDegrees(Global.scaleImage(Global.loadImage(Global.getWandList().getSprite(this.wand)), 2*this.imageScaleFactor), -90);
            if(this.wandSprite == null){
                System.out.println("unable to load wand sprite");
            }
        }

        if(this.wand.getShuffle()) {
            statValues[0] = "Yes";
        }else{
            statValues[0] = "No";
        }
        statValues[1] = String.valueOf(this.wand.getNbDraw());
        statValues[2] = String.format("%1$3.2f s (%2$df)", this.wand.getCastDelay()/60.0, this.wand.getCastDelay());
        statValues[3] = String.format("%1$3.2f s (%2$df)", this.wand.getRechargeTime()/60.0, this.wand.getRechargeTime());
        statValues[4] = String.valueOf(this.wand.getMaxMana());
        statValues[5] = String.valueOf(this.wand.getRegenMana());
        statValues[6] = String.valueOf(this.wand.getNbSlot());
        statValues[7] = String.format("%1$2.1f DEG", this.wand.getSpread());
        statValues[8] = String.format("x%1$2.1f", this.wand.getSpeed());

        try{
            InputStream myStream = new BufferedInputStream(new FileInputStream("./src/main/java/org/example/PixelFont.ttf"));
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            font = baseFont.deriveFont(Font.PLAIN, this.baseIconSize*this.imageScaleFactor);
        }catch(Exception e){
            System.out.println("Pixel font not loaded.");
            font = new Font("Arial", Font.PLAIN, this.baseIconSize*this.imageScaleFactor);
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

        JPanelWidth = this.baseIconSize*this.imageScaleFactor + maxWidth + maxWidth2 + this.wandSprite.getWidth() + 5*this.margin;
        JPanelHeight = Math.max(stats.length*this.baseIconSize*this.imageScaleFactor + (stats.length + 1)*this.margin, this.wandSprite.getHeight() + 2*this.margin);
        this.statJPanelImage = new BufferedImage(JPanelWidth, JPanelHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = this.statJPanelImage.createGraphics();

        g2d.setColor(this.textColor);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();

        for(int i=0; i < Math.min(stats.length, statImages.length); i++){
            textHeight = fm.getHeight();
            //textWidth = fm.stringWidth(statValues[i]);//
            currentImage = Global.loadImage(path + statImages[i]);

            if(currentImage != null){
                g2d.drawImage(Global.scaleImage(currentImage, this.imageScaleFactor), this.margin, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin, null);
            }
            //g2d.setColor(this.textColor);//
            g2d.drawString(stats[i], this.baseIconSize*this.imageScaleFactor + 2*this.margin, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin + (textHeight + this.baseIconSize*this.imageScaleFactor)/2 - fm.getMaxDescent());
            g2d.drawString(statValues[i], maxWidth + this.baseIconSize*this.imageScaleFactor + 3*this.margin, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin + (textHeight + this.baseIconSize*this.imageScaleFactor)/2 - fm.getMaxDescent());

            /*g2d.setColor(Color.RED);
            g2d.drawRect(0, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin + (textHeight + this.baseIconSize*this.imageScaleFactor)/2 - fm.getMaxDescent() - textHeight,  this.baseIconSize*this.imageScaleFactor + textWidth + maxWidth + 3*this.margin, textHeight + fm.getMaxDescent()*2);
            g2d.setColor(Color.GREEN);
            g2d.drawRect(0, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin + (textHeight + this.baseIconSize*this.imageScaleFactor)/2 - fm.getMaxDescent(), this.baseIconSize*this.imageScaleFactor + textWidth + maxWidth + 3*this.margin, 0);
            g2d.setColor(Color.BLUE);
            g2d.drawRect(0, i*this.baseIconSize*this.imageScaleFactor + (i + 1)*this.margin + (textHeight + this.baseIconSize*this.imageScaleFactor)/2 - fm.getMaxDescent() - textHeight/2 + fm.getMaxDescent(), this.baseIconSize*this.imageScaleFactor + textWidth + maxWidth + 3*this.margin, 0);*/
        }

        if(this.wandSprite != null){
            g2d.drawImage(this.wandSprite,  this.baseIconSize*this.imageScaleFactor + maxWidth + maxWidth2 + 4*this.margin, Math.max((JPanelHeight - this.wandSprite.getHeight())/2, 0), null);
            //g2d.setColor(Color.RED);//
            //g2d.drawRect(this.baseIconSize*this.imageScaleFactor + maxWidth + maxWidth2 + 4*this.margin, Math.max((JPanelHeight - this.wandSprite.getHeight())/2, 0), this.wandSprite.getWidth(), this.wandSprite.getHeight());//
        }

        this.statJPanel = new JPanel(null){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(WandJPanel.this.statJPanelImage, 0, 0, this);
            }
        };

        this.statJPanel.setBackground(this.backgroundColor);
        this.statJPanel.setPreferredSize(new Dimension(JPanelWidth, JPanelHeight));
        this.statJPanel.setBounds(0, 0, JPanelWidth, JPanelHeight);
    }

    public void updateSpells(){
        Spell[] spells = this.wand.getSpells();

        this.basicJPanel.removeAll();
        for(int i=0; i < spells.length; i++){
            if(spells[i] != null){
                if(this.imageScaleFactor != spells[i].getImageScaleFactor()){
                    spells[i].setImageScaleFactor(this.imageScaleFactor);
                }
                this.basicJPanel.add(spells[i].getImageLabel());
                spells[i].setLocation(i*this.slotSize*this.imageScaleFactor + (i + 1)*this.margin + (this.slotSize*this.imageScaleFactor - spells[i].getImageLabel().getWidth())/2, this.margin + (this.slotSize*this.imageScaleFactor - spells[i].getImageLabel().getHeight())/2);
            }
        }
    }
}