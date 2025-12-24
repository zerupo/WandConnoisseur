package org.example.spells;

import org.example.main.*;
import org.example.projectiles.Projectile;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import java.awt.Graphics;
import java.io.InputStream;

public abstract class Spell{
    public enum SpellType {projectile, static_projectile, passif, utility, modifier, material, multicast, other};
    protected final int recursionLimit = 2;
    protected String name = "spell_name";
    protected String[] alias = new String[0];
    protected String imagePath = "./src/main/java/org/example/image/spell/";
    protected String imageFile = "_unidentified.png";
    protected String typeFile = "item_bg_other.png";
    protected String emote = "<:_unidentified:1413201253672157304>";
    protected String defaultImage = "_unidentified.png";
    protected String description = "this spell does things";
    protected JLabel imageLabel = null;
    protected JLabel chargeLabel = null;
    protected BufferedImage image = null;
    protected int imageScaleFactor = 1;
    protected SpellType type = SpellType.other;
    protected Projectile relatedProjectile = null;
    protected int relatedProjectileCount = 1;
    protected SpawnProbabilities spawnProbabilities = new SpawnProbabilities();
    protected boolean recursive = false;
    protected int price = 0;
    protected int manaCost = 0;
    protected boolean hasCharges = false;
    protected int maxCharges = 0;
    protected int chargesLeft = 0;
    protected boolean neverUnlimited = false;
    protected boolean autoStat = true;
    protected int castDelay = 0;
    protected int rechargeTime = 0;
    protected DamageComponent damageComponent = new DamageComponent();
    protected int lifetime = 0;
    protected int critRate = 0;
    protected int patern = 0;
    protected double spread = 0;
    protected double recoil = 0.0;
    protected double screenshake = 0.0;

    public Spell(){
        this.initialization();
        if(this.alias.length == 0){
            this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        }
        for(int i=0; i < this.alias.length; i++){
            this.alias[i] = this.alias[i].trim().toLowerCase();
        }
        this.alias = removeDuplicates(this.alias);
        if(this.hasCharges){
            this.chargesLeft = this.maxCharges;
        }
    }

    public Spell clone(){
        Class<? extends Spell> spellClass = this.getClass();
        Spell newSpell;

        try{
            newSpell = spellClass.newInstance();
        }catch(Exception e){
            return null;
        }

        newSpell.name = this.name;
        newSpell.alias = new String[this.alias.length];
        for(int i=0; i < newSpell.alias.length; i++){
            newSpell.alias[i] = this.alias[i];
        }
        newSpell.imagePath = this.imagePath;
        newSpell.imageFile = this.imageFile;
        newSpell.typeFile = this.typeFile;
        newSpell.defaultImage = this.defaultImage;
        newSpell.description = this.description;
        if(this.image == null){
            newSpell.image = null;
        }else{
            newSpell.image = cloneBufferedImage(this.image);
        }
        newSpell.imageScaleFactor = this.imageScaleFactor;
        newSpell.type = this.type;
        newSpell.spawnProbabilities = new SpawnProbabilities(this.spawnProbabilities); // clone
        newSpell.recursive = this.recursive;
        newSpell.price = this.price;
        newSpell.manaCost = this.manaCost;
        newSpell.hasCharges = this.hasCharges;
        newSpell.maxCharges = this.maxCharges;
        newSpell.chargesLeft = this.chargesLeft;
        newSpell.neverUnlimited = this.neverUnlimited;
        newSpell.castDelay = this.castDelay;
        newSpell.rechargeTime = this.rechargeTime;
        newSpell.damageComponent = new DamageComponent(this.damageComponent); // clone
        newSpell.lifetime = this.lifetime;
        newSpell.critRate = this.critRate;
        newSpell.patern = this.patern;
        newSpell.spread = this.spread;
        newSpell.recoil = this.recoil;
        newSpell.screenshake = this.screenshake;
        if(this.imageLabel != null){
            newSpell.imageInJLabel();
        }

        return newSpell;
    }

    private BufferedImage cloneBufferedImage(BufferedImage original){
        BufferedImage clone = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        Graphics g = clone.getGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();

        return clone;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public String[] getAlias(){
        return this.alias;
    }

    public String getFullImagePath(){
        return this.imagePath + this.imageFile;
    }

    public String getImagePath(){
        return this.imagePath;
    }

    public String getImageFile(){
        return this.imageFile;
    }

    public String getEmote(){
        return this.emote;
    }

    public SpellType getType(){
        return this.type;
    }

    public Projectile getRelatedProjectile(){
        return this.relatedProjectile;
    }

    public int getRelatedProjectileCount(){
        return this.relatedProjectileCount;
    }

    public int getImageScaleFactor(){
        return this.imageScaleFactor;
    }

    public JLabel getImageLabel(){
        if(this.image == null){
            this.loadImage();
            this.imageInJLabel();
        }
        return this.imageLabel;
    }

    public JLabel getImageLabelCopy(){
        JLabel newLabel = new JLabel();
        if(this.image == null){
            this.loadImage();
        }
        if(this.image != null){
            newLabel.setIcon(new ImageIcon(scaleImage(this.image, this.imageScaleFactor)));
            newLabel.setSize(this.image.getWidth()*this.imageScaleFactor, this.image.getHeight()*this.imageScaleFactor);
            this.chargeLabel.setSize(Global.getBaseImageSize()*this.imageScaleFactor, Global.getBaseImageSize()*this.imageScaleFactor);
            this.chargeLabel.setLocation((this.imageLabel.getWidth() - this.chargeLabel.getWidth())/2, (this.imageLabel.getHeight() - this.chargeLabel.getHeight())/2);
        }
        return newLabel;
    }

    public JLabel getImageLabelCopy(int imageScaleFactor){
        JLabel newLabel = new JLabel();
        if(this.image == null){
            this.loadImage();
        }
        if(this.image != null){
            newLabel.setIcon(new ImageIcon(scaleImage(this.image, imageScaleFactor)));
            newLabel.setSize(this.image.getWidth()*imageScaleFactor, this.image.getHeight()*imageScaleFactor);
            this.chargeLabel.setSize(Global.getBaseImageSize()*this.imageScaleFactor, Global.getBaseImageSize()*this.imageScaleFactor);
            this.chargeLabel.setLocation((this.imageLabel.getWidth() - this.chargeLabel.getWidth())/2, (this.imageLabel.getHeight() - this.chargeLabel.getHeight())/2);
        }
        return newLabel;
    }

    public boolean getHasCharges(){
        return this.hasCharges;
    }

    public int getChargesLeft(){
        return this.chargesLeft;
    }

    public int getManaCost(){
        return this.manaCost;
    }

    public BufferedImage getImage(){
        if(this.image == null){
            this.loadImage();
            this.imageInJLabel();
        }
        return this.image;
    }

    public int getLifetime(){
        return this.lifetime;
    }

    public int getLastIteration(){
        return 0;
    }

    public String getInfoString(){
        StringBuilder result = new StringBuilder();
        boolean space = false;
        double[] spawnProbabilities;

        result.append("Name: \"").append(this.name).append("\"\n");
        result.append("Description: \"").append(this.description).append("\"\n");
        result.append("Type: ").append(this.type).append("\n");
        if(this.hasCharges){
            result.append("Charges: ").append(this.maxCharges);
            if(this.neverUnlimited) {
                result.append(" (never infinite)");
            }else{
                result.append(" (can be infinite)");
            }
            result.append("\n");
        }else{
            result.append("Charges: infinite\n");
        }
        result.append("Mana cost: ").append(this.manaCost).append("\n");
        result.append(String.format("Cast delay: %1$3.2f s (%2$df)\n", this.castDelay/60.0, this.castDelay));
        result.append(String.format("Recharge time: %1$3.2f s (%2$df)\n", this.rechargeTime/60.0, this.rechargeTime));
        result.append("\n");
        if(this.damageComponent.getProjectile() != 0){
            result.append("Damage: ").append(this.damageComponent.getProjectile()).append("\n");
            space = true;
        }
        if(this.damageComponent.getMelee() != 0){
            result.append("Melee damage: ").append(this.damageComponent.getMelee()).append("\n");
            space = true;
        }
        if(this.damageComponent.getExplosion() != 0){
            result.append("Explosion damage: ").append(this.damageComponent.getExplosion()).append("\n");
            space = true;
        }
        if(this.damageComponent.getElectricity() != 0){
            result.append("Electricity damage: ").append(this.damageComponent.getElectricity()).append("\n");
            space = true;
        }
        if(this.damageComponent.getFire() != 0){
            result.append("Fire damage: ").append(this.damageComponent.getFire()).append("\n");
            space = true;
        }
        if(this.damageComponent.getDrill() != 0){
            result.append("Drill damage: ").append(this.damageComponent.getDrill()).append("\n");
            space = true;
        }
        if(this.damageComponent.getSlice() != 0){
            result.append("Slice damage: ").append(this.damageComponent.getSlice()).append("\n");
            space = true;
        }
        if(this.damageComponent.getIce() != 0){
            result.append("Ice damage: ").append(this.damageComponent.getIce()).append("\n");
            space = true;
        }
        if(this.damageComponent.getHealing() != 0){
            result.append("Healing damage: ").append(this.damageComponent.getHealing()).append("\n");
            space = true;
        }
        if(this.damageComponent.getPhysics_hit() != 0){
            result.append("Physics_hit damage: ").append(this.damageComponent.getPhysics_hit()).append("\n");
            space = true;
        }
        if(this.damageComponent.getRadioactive() != 0){
            result.append("Radioactive damage: ").append(this.damageComponent.getRadioactive()).append("\n");
            space = true;
        }
        if(this.damageComponent.getPoison() != 0){
            result.append("Poison damage: ").append(this.damageComponent.getPoison()).append("\n");
            space = true;
        }
        if(this.damageComponent.getOvereating() != 0){
            result.append("Overeating damage: ").append(this.damageComponent.getOvereating()).append("\n");
            space = true;
        }
        if(this.damageComponent.getCurse() != 0){
            result.append("Curse damage: ").append(this.damageComponent.getCurse()).append("\n");
            space = true;
        }
        if(this.damageComponent.getHealing() != 0){
            result.append("Holy damage: ").append(this.damageComponent.getHealing()).append("\n");
            space = true;
        }
        if(this.lifetime != 0){
            result.append("lifetime: ").append(this.lifetime).append("\n");
            space = true;
        }
        if(this.critRate != 0){
            result.append("Crit rate: ").append(this.critRate).append("%\n");
            space = true;
        }
        if(this.patern != 0){
            result.append("Patern: ").append(this.patern).append("\n");
            space = true;
        }
        if(this.spread != 0){
            result.append("Spread: ").append(this.spread).append("\n");
            space = true;
        }
        if(this.recoil != 0){
            result.append("Recoil: ").append(this.recoil).append("\n");
            space = true;
        }
        if(this.screenshake != 0){
            result.append("Screen shake: ").append(this.screenshake).append("\n");
            space = true;
        }
        if(space){
            result.append("\n");
        }

        spawnProbabilities = this.spawnProbabilities.getSpawnProbability();
        result.append("Spawn probabilities: [");
        for(int i=0; i < spawnProbabilities.length; i++){
            if(i != 0){
                result.append(", ");
            }
            result.append("T").append(i).append(": ").append(spawnProbabilities[i]);
        }
        result.append("]\n");
        if(this.recursive){
            result.append("Recursive: Yes\n");
        }else{
            result.append("Recursive: No\n");
        }
        result.append("Price: ").append(this.price);

        return result.toString();
    }

    // setters
    public void setName(String name){
        this.name = name;
    }

    public void setType(SpellType type){
        this.type = type;
    }

    public void setImageScaleFactor(int imageScaleFactor){
        this.imageScaleFactor = imageScaleFactor;
    }

    public void setLocation(int x, int y){
        if(this.imageLabel != null){
            this.imageLabel.setLocation(x, y);
        }
    }

    public void setCharges(int charges){
        this.chargesLeft = Math.max(Math.min(charges, this.maxCharges), 0);
        this.updateChargeLabel();
    }

    public void refillCharges(){
        if(this.hasCharges){
            this.chargesLeft = this.maxCharges;
            this.updateChargeLabel();
        }
    }

    public void removeCharge(){
        if(this.hasCharges && this.chargesLeft > 0){
            this.chargesLeft--;
            this.updateChargeLabel();
        }
    }

    public void removeAllCharges(){
        if(this.hasCharges){
            this.chargesLeft = 0;
            this.updateChargeLabel();
        }
    }

    public void makeInfinite(){
        if(this.hasCharges && !this.neverUnlimited){
            this.refillCharges();
            this.hasCharges = false;
            this.updateChargeLabel();
        }
    }

    private void updateChargeLabel(){
        if(this.chargeLabel != null){
            if(this.hasCharges){
                this.chargeLabel.setText(String.valueOf(this.chargesLeft));
            }else{
                this.chargeLabel.setText("");
            }
        }
    }

    protected boolean loadImage(){
        boolean result = true;
        BufferedImage imageType;

        getTypeImage();
        try{
            this.image = ImageIO.read(new File(this.imagePath + this.imageFile));
        }catch(IOException e1){
            try{
                this.image = ImageIO.read(new File(this.imagePath + this.defaultImage));
                System.out.println(this.imagePath + this.imageFile + " not found, using default image");
            }catch(IOException e2){
                // no image
                System.out.println("no image found for " + this.name);
                result = false;
            }
        }

        if(result){
            try{
                imageType = ImageIO.read(new File(this.imagePath + this.typeFile));
                this.image = combineImages(imageType, this.image, null);
            }catch(IOException e1){
                System.out.println(this.imagePath + this.typeFile + " not found");
            }
        }
        return result;
    }

    public void createEmote(){
        try{
            BufferedImage type = null;
            switch(this.type){
                case projectile -> type = ImageIO.read(new File(this.imagePath + "item_bg_projectile_emote.png"));
                case static_projectile -> type = ImageIO.read(new File(this.imagePath + "item_bg_static_projectile_emote.png"));
                case passif -> type = ImageIO.read(new File(this.imagePath + "item_bg_passive_emote.png"));
                case utility -> type = ImageIO.read(new File(this.imagePath + "item_bg_utility_emote.png"));
                case modifier -> type = ImageIO.read(new File(this.imagePath + "item_bg_modifier_emote.png"));
                case material -> type = ImageIO.read(new File(this.imagePath + "item_bg_material_emote.png"));
                case multicast -> type = ImageIO.read(new File(this.imagePath + "item_bg_draw_many_emote.png"));
                case other -> type = ImageIO.read(new File(this.imagePath + "item_bg_other_emote.png"));
                default -> type = ImageIO.read(new File(this.imagePath + "item_bg_other_emote.png"));
            }
            ImageIO.write(scaleImage(combineImages(type, ImageIO.read(new File(this.imagePath + this.imageFile)), new Color(54, 43, 39)), 8),"png",new File(Global.getPathOutput() + this.getClass().getSimpleName().toLowerCase() + ".png"));
        }catch(IOException e){
            System.out.println("Error trying to create emote for spell \"" + this.name + "\" : " + e.getMessage());
        }
    }

    protected void getTypeImage(){
        switch(this.type){
            case projectile:
                this.typeFile = "item_bg_projectile.png";
                break;
            case static_projectile:
                this.typeFile = "item_bg_static_projectile.png";
                break;
            case passif:
                this.typeFile = "item_bg_passive.png";
                break;
            case utility:
                this.typeFile = "item_bg_utility.png";
                break;
            case modifier:
                this.typeFile = "item_bg_modifier.png";
                break;
            case material:
                this.typeFile = "item_bg_material.png";
                break;
            case multicast:
                this.typeFile = "item_bg_draw_many.png";
                break;
            case other:
                this.typeFile = "item_bg_other.png";
                break;
            default:
                this.typeFile = "item_bg_other.png";
                break;
        }
    }

    public boolean imageInJLabel(){
        if(this.imageLabel == null){
            // à refaire
            this.chargeLabel = new JLabel("0") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
                    );
                    g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_OFF
                    );
                    g2d.setRenderingHint(
                        RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_OFF
                    );
                    super.paintComponent(g);
                }
            };
            this.chargeLabel.setForeground(new Color(255, 255, 255));
            this.chargeLabel.setHorizontalAlignment(JLabel.LEFT);
            this.chargeLabel.setVerticalAlignment(JLabel.TOP);
            this.chargeLabel.setOpaque(false);
            //this.chargeLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            Font font = null;
            try{
                InputStream myStream = new FileInputStream("./src/main/java/org/example/PixelFont.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, this.imageScaleFactor*7);
            }catch(Exception e){
                font = new Font("Arial", Font.PLAIN, this.imageScaleFactor*7);
            }
            if(font != null){
                this.chargeLabel.setFont(font);
                //this.chargeLabel.setFont(new Font("Arial", Font.PLAIN, this.imageScaleFactor*10));
            }

            this.updateChargeLabel();
            //this.chargeLabel.setText("8");

            this.imageLabel = new JLabel();
            this.imageLabel.add(this.chargeLabel);
        }
        if(this.image != null){
            this.imageLabel.setIcon(new ImageIcon(scaleImage(this.image, this.imageScaleFactor)));
            this.imageLabel.setSize(this.image.getWidth()*this.imageScaleFactor, this.image.getHeight()*this.imageScaleFactor);
            this.chargeLabel.setSize(Global.getBaseImageSize()*this.imageScaleFactor, Global.getBaseImageSize()*this.imageScaleFactor);
            this.chargeLabel.setLocation((this.imageLabel.getWidth() - this.chargeLabel.getWidth())/2, (this.imageLabel.getHeight() - this.chargeLabel.getHeight())/2);
            return true;
        }else{
            return false;
        }
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int scaleFactor){
        int newWidth = originalImage.getWidth() * scaleFactor;
        int newHeight = originalImage.getHeight() * scaleFactor;
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    private static BufferedImage combineImages(BufferedImage img1, BufferedImage img2, Color backgroundColor){
        int width = Math.max(img1.getWidth(), img2.getWidth());
        int height = Math.max(img1.getHeight(), img2.getHeight());
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        if(backgroundColor != null){
            g2d.setColor(backgroundColor);
            g2d.fillRect(1, 1, width - 2, height - 2);
        }

        g2d.setComposite(AlphaComposite.SrcOver);
        int x1 = (width - img1.getWidth())/2;
        int y1 = (height - img1.getHeight())/2;
        int x2 = (width - img2.getWidth())/2;
        int y2 = (height - img2.getHeight())/2;

        g2d.drawImage(img1, x1, y1, null);
        g2d.drawImage(img2, x2, y2, null);
        g2d.dispose();

        return combinedImage;
    }

    public void copy(CardPool cardPool, CastState castState, Spell spell, int recursionLevel){
        copy(cardPool, castState, spell, recursionLevel, 1);
    }

    public void copy(CardPool cardPool, CastState castState, Spell spell, int recursionLevel, int iterationLevel){
        Flowchart currentNode = null;
        Flowchart newNode = null;
        int nextRecursionLevel = recursionLevel;
        boolean makeFlowchart = cardPool.getAutoFlowchart() || cardPool.getAutoCardHistory();

        if(makeFlowchart){
            currentNode = cardPool.getCallStack();
            newNode = currentNode.add(spell);
            newNode.setIsCopy(true);
        }

        if(spell != null){
            if(spell.recursive){
                nextRecursionLevel = recursionLevel + 1;
            }else{
                nextRecursionLevel = recursionLevel;
            }

            if(nextRecursionLevel <= recursionLimit){
                if(makeFlowchart){
                    cardPool.addCallStack(newNode);
                }
                spell.doAction(cardPool, castState, nextRecursionLevel, iterationLevel);
                if(makeFlowchart){
                    cardPool.removeCallStack();
                }
            }else if(makeFlowchart){
                newNode.setCopyFailed(true);
            }
        }
    }

    public void applyStats(CardPool cardPool, CastState castState){
        // cardPool
        if(this.rechargeTime != 0){
            cardPool.addRechargeTime(this.rechargeTime);
        }
        if(this.recoil != 0){
            cardPool.addRecoil(this.recoil);
        }
        if(this.screenshake != 0){
            cardPool.addScreenshake(this.screenshake);
        }

        // castState
        if(this.castDelay != 0){
            castState.addCastDelay(this.castDelay);
        }
        if(this.damageComponent.zero()){
            castState.addDamageComponent(this.damageComponent);
        }
        if(this.lifetime != 0){
            castState.addLifetime(this.lifetime);
        }
        if(this.critRate != 0){
            castState.addCritRate(this.critRate);
        }
        if(this.patern != 0){
            castState.addPatern(this.patern);
        }
        if(this.spread != 0){
            castState.addSpread(this.spread);
        }
    }

    public void doAction(CardPool cardPool, CastState castState){
        this.doAction(cardPool, castState, 0, 1);
    }

    public void doAction(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        if(this.autoStat){
            this.applyStats(cardPool, castState);
        }
        this.action(cardPool, castState, recursionLevel, iterationLevel);
    }

    protected void action(CardPool cardPool, CastState castState){
        this.action(cardPool, castState, 0, 1);
    }

    public boolean containsAlias(String alias){
        for(int i=0; i < this.alias.length; i++){
            if(this.alias[i].equals(alias)){
                return true;
            }
        }
        return false;
    }

    private String[] removeDuplicates(String[] wordList){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordList.length; i++){
            boolean found = false;
            for(int j=i + 1; j < wordList.length; j++){
                if(wordList[j].equals(wordList[i])){
                    found = true;
                    break;
                }
            }
            if(!found){
                if (sb.length() > 0){
                    sb.append(';');
                }
                sb.append(wordList[i]);
            }
        }
        return sb.toString().split(";");
    }

    protected abstract void initialization();
    protected abstract void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel);
}