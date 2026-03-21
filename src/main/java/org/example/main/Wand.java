package org.example.main;

import org.example.spells.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

class InfoResult{
    public CastState[] castStates;
    public ImageBuilder flowchartImage;
    public StringBuilder flowchartString;

    public InfoResult(CastState[] castStates, ImageBuilder flowchartImage, StringBuilder flowchartString){
        this.castStates = castStates;
        this.flowchartImage = flowchartImage;
        this.flowchartString = flowchartString;
    }
}

public class Wand{
    private boolean shuffle;
    private int nbDraw;
    private int castDelay;
    private int rechargeTime;
    private int currentCastDelay = 0;
    private int currentRechargeTime = 0;
    private int waitingTime = 0;
    private int maxMana;
    private int regenMana;
    private int currentMana;
    private int nbSlot;
    private double spread;
    private double speed;
    private Spell[] spells;
    private WandJPanel wandJPanel;
    private CardPool cardPool = new CardPool();

    public Wand(){
        this.shuffle = false;
        this.nbDraw = 1;
        this.castDelay = 0;
        this.rechargeTime = 0;
        this.maxMana = 100;
        this.regenMana = 10;
        this.currentMana = this.maxMana*60;
        this.nbSlot = 1;
        this.spread = 0.0;
        this.speed = 1.0;
        this.spells = new Spell[this.nbSlot];
    }

    public Wand(int nbDraw, int castDelay, int rechargeTime, int maxMana, int regenMana, int nbSlot, double spread, double speed){
        this.shuffle = false;
        this.nbDraw = nbDraw;
        this.castDelay = castDelay;
        this.rechargeTime = rechargeTime;
        this.maxMana = maxMana;
        this.regenMana = regenMana;
        this.currentMana = this.maxMana*60;
        this.nbSlot = nbSlot;
        this.spread = spread;
        this.speed = speed;
        this.spells = new Spell[nbSlot];
    }

    // setters
    public void setAutoFlowchart(boolean autoFlowchart){
        this.cardPool.setAutoFlowchart(autoFlowchart);
    }

    public void setAutoCardHistory(boolean autoCardHistory){
        this.cardPool.setAutoCardHistory(autoCardHistory);
    }

    // getters
    public boolean getShuffle(){
        return this.shuffle;
    }

    public int getNbDraw(){
        return this.nbDraw;
    }

    public int getCastDelay(){
        return this.castDelay;
    }

    public int getRechargeTime(){
        return this.rechargeTime;
    }

    public int getMaxMana(){
        return this.maxMana;
    }

    public int getRegenMana(){
        return this.regenMana;
    }

    public int getNbSlot(){
        return this.nbSlot;
    }

    public double getSpread(){
        return this.spread;
    }

    public double getSpeed(){
        return this.speed;
    }

    public Spell[] getSpells(){
        return this.spells;
    }

    public JPanel getWandJPanel(){
        if(this.wandJPanel == null){
            //this.createLayout();
            this.wandJPanel = new WandJPanel(this);
        }
        return this.wandJPanel.getBasicJPanel();
    }

    public JPanel getStatJPanel(){
        if(this.wandJPanel == null){
            //this.createLayout();
            this.wandJPanel = new WandJPanel(this);
        }
        return this.wandJPanel.getStatJPanel();
    }

    public CardPool getCardPool(){
        return this.cardPool;
    }

    public String getCurrentFlowchartString(boolean formatting){
        return this.cardPool.getFlowchartString(formatting);
    }

    public BufferedImage getCurrentFlowchartImage(){
        return this.cardPool.getFlowchartImage();
    }

    public void saveCurrentFlowchartImage(String filename){
        this.cardPool.saveFlowchartImage(filename);
    }

    //

    public boolean putSpell(Spell spell, int slot){
        if(slot >= 0 && slot < this.nbSlot){
            /*if(this.spells[slot] != null && this.wandJPanel != null){
                this.wandJPanel.remove(this.spells[slot].getImageLabel());
            }*/
            if(spell == null){
                return true;
            }
            /*if(this.imageScaleFactor != spell.getImageScaleFactor()){
                spell.setImageScaleFactor(this.imageScaleFactor);
            }*/
            //this.updateSpellLocation(slot, spell);
            this.spells[slot] = spell;
            /*if(this.wandJPanel != null){
                this.wandJPanel.add(spell.getImageLabel());
            }*/
            this.setCardPool();
            return true;
        }else{
            return false;
        }
    }

    /*private void updateSpellLocation(int slot){
        if(slot >= 0 && slot < this.nbSlot && this.spells[slot] != null){
            Spell currentSpell = this.spells[slot];
            if(currentSpell != null){
                currentSpell.setLocation(slot*this.slotSize*this.imageScaleFactor + (slot + 1)*this.margin + (this.slotSize*this.imageScaleFactor - currentSpell.getImageLabel().getWidth())/2, this.margin + (this.slotSize*this.imageScaleFactor - currentSpell.getImageLabel().getHeight())/2);
            }
        }
    }*/

    /*private void updateSpellLocation(int slot, Spell currentSpell){
        if(slot >= 0 && slot < this.nbSlot && currentSpell != null && this.wandJPanel != null){
            currentSpell.setLocation(slot*this.slotSize*this.imageScaleFactor + (slot + 1)*this.margin + (this.slotSize*this.imageScaleFactor - currentSpell.getImageLabel().getWidth())/2, this.margin + (this.slotSize*this.imageScaleFactor - currentSpell.getImageLabel().getHeight())/2);
        }
    }*/

    public boolean putSpellEnd(Spell spell){
        int slot = -1;

        for(int i=this.nbSlot - 1; i >= 0; i--){
            if(this.spells[i] != null){
                break;
            }else{
                slot = i;
            }
        }

        if(slot == -1){
            return false;
        }else{
            return putSpell(spell, slot);
        }
    }

    public boolean removeSpell(int slot){
        if(slot < 0 || slot >= this.nbSlot){
            return false;
        }
        if(this.spells[slot] == null){
            return false;
        }

        /*if(this.wandJPanel != null){
            this.wandJPanel.remove(this.spells[slot].getImageLabel());
        }*/
        this.spells[slot] = null;
        this.setCardPool();
        return true;
    }

    public void removeAllSpell(){
        for(int i=0; i < this.spells.length; i++){
            if(this.spells[i] != null){
                /*if(this.wandJPanel != null){
                    this.wandJPanel.remove(this.spells[i].getImageLabel());
                }*/
                this.spells[i] = null;
            }
        }
        this.setCardPool();
    }

    public boolean swapSpell(int slot1, int slot2){
        if(slot1 < 0 || slot1 >= this.nbSlot){
            return false;
        }
        if(slot2 < 0 || slot2 >= this.nbSlot){
            return false;
        }

        Spell currentSpell1 = this.spells[slot1];
        Spell currentSpell2 = this.spells[slot2];

        this.spells[slot2] = currentSpell1;
        this.spells[slot1] = currentSpell2;
        //this.updateSpellLocation(slot2, currentSpell1);
        //this.updateSpellLocation(slot1, currentSpell2);

        this.setCardPool();
        return true;
    }

    private void setCardPool(){
        this.cardPool.reset(this.spells);
    }

    /*public CardHistory getTruc(boolean allCasts){
        Spell[] spellsCopy = new Spell[this.spells.length];
        CardPool cardPoolCopy;
        CardHistory cardHistory = null;
        int nbCast = 0;
        boolean recharged = false;

        for(int i=0; i < spellsCopy.length; i++){
            if(this.spells[i] != null){
                spellsCopy[i] = this.spells[i].clone();
            }
        }
        cardPoolCopy = new CardPool(spellsCopy);
        cardPoolCopy.setAutoCardHistory(true);
        cardPoolCopy.setAutoFlowchart(true);

        while(!recharged && nbCast < this.nbSlot){
            nbCast++;
            cardPoolCopy.setStats(this.maxMana, this.rechargeTime);
            cardPoolCopy.resetCardHistory();
            cardPoolCopy.draw(this.nbDraw, false, new CastState());
            if(cardPoolCopy.getWrappedThisCast() || cardPoolCopy.getDeckSize() <= 0){
                recharged = true;
            }
            cardPoolCopy.endCast();
            if(!allCasts){
                recharged = true;
            }
            // TODO fix this
            if(cardHistory == null){
                cardHistory = cardPoolCopy.getCardHistory();
            }else{
                cardHistory.add(cardPoolCopy.getCardHistory(), true);
            }
        }
        return cardHistory;
    }*/

    /*public String getHistoryString(boolean allCasts){
        String result = "";
        Spell[] spellsCopy = new Spell[this.spells.length];
        CardPool cardPoolCopy;
        int nbCast = 0;
        boolean recharged = false;

        for(int i=0; i < spellsCopy.length; i++){
            if(this.spells[i] != null){
                spellsCopy[i] = this.spells[i].clone();
            }
        }
        cardPoolCopy = new CardPool(spellsCopy);
        cardPoolCopy.setAutoCardHistory(true);

        while(!recharged && nbCast < this.nbSlot){
            nbCast++;
            cardPoolCopy.setStats(this.maxMana, this.rechargeTime);
            cardPoolCopy.resetCardHistory();
            cardPoolCopy.draw(this.nbDraw, false, new CastState());
            if(cardPoolCopy.getWrappedThisCast() || cardPoolCopy.getDeckSize() <= 0){
                recharged = true;
            }
            cardPoolCopy.endCast();
            if(!allCasts){
                recharged = true;
            }
            //if(!recharged && nbCast < this.nbSlot){
            //    result += nbCast + ")\n" + cardPoolCopy.getHistoryString() + "\n";
            //}else{
            //    result += nbCast + ")\n" + cardPoolCopy.getHistoryString();
            //}
        }
        return result;
    }*/

    /*public String getCallStackString(boolean allCasts){
        String result = "";
        Spell[] spellsCopy = new Spell[this.spells.length];
        CardPool cardPoolCopy;
        int nbCast = 0;
        boolean recharged = false;

        for(int i=0; i < spellsCopy.length; i++){
            if(this.spells[i] != null){
                spellsCopy[i] = this.spells[i].clone();
            }
        }

        cardPoolCopy = new CardPool(spellsCopy);
        cardPoolCopy.setAutoCardHistory(true);

        while(!recharged && nbCast < this.nbSlot){
            nbCast++;
            cardPoolCopy.setStats(this.maxMana, this.rechargeTime);
            cardPoolCopy.resetCardHistory();
            cardPoolCopy.draw(this.nbDraw, false, new CastState());
            if(cardPoolCopy.getWrappedThisCast() || cardPoolCopy.getDeckSize() <= 0){
                recharged = true;
            }
            cardPoolCopy.endCast();
            if(!allCasts){
                recharged = true;
            }
            //if(!recharged && nbCast < this.nbSlot){
            //    result += nbCast + ")\n" + cardPoolCopy.getCallStackString() + "\n";
            //}else{
            //    result += nbCast + ")\n" + cardPoolCopy.getCallStackString();
            //}
        }
        return result;
    }*/

    /*public String getCardHistoryString(boolean allCasts){
        String result = "";
        Spell[] spellsCopy = new Spell[this.spells.length];
        CardPool cardPoolCopy;
        int nbCast = 0;
        boolean recharged = false;

        for(int i=0; i < spellsCopy.length; i++){
            if(this.spells[i] != null){
                spellsCopy[i] = this.spells[i].clone();
            }
        }
        cardPoolCopy = new CardPool(spellsCopy);
        cardPoolCopy.setAutoCardHistory(true);

        while(!recharged && nbCast < this.nbSlot){
            nbCast++;
            cardPoolCopy.setStats(this.maxMana, this.rechargeTime);
            cardPoolCopy.resetCardHistory();
            cardPoolCopy.draw(this.nbDraw, false, new CastState());
            if(cardPoolCopy.getWrappedThisCast() || cardPoolCopy.getDeckSize() <= 0){
                recharged = true;
            }
            cardPoolCopy.endCast();
            if(!allCasts){
                recharged = true;
            }
            //if(!recharged && nbCast < this.nbSlot){
            //    result += nbCast + ")\n" + cardPoolCopy.getCardHistoryString() + "\n";
            //}else{
            //    result += nbCast + ")\n" + cardPoolCopy.getCardHistoryString();
            //}
        }
        return result;
    }*/

    public void getAllInfo(boolean allCasts, InfoResult infoResult, boolean formatting){
        ArrayList<CastState> castStateList = infoResult.castStates == null ? null : new ArrayList<>();
        Spell[] spellsCopy = new Spell[this.spells.length];
        CardPool cardPoolCopy = new CardPool();
        CastState primaryCastState;
        int currentMana = this.maxMana*60;
        int waitingTime = 0;
        int currentCastDelay = 0;
        int currentRechargeTime = 0;
        int nbCast = 0;
        boolean recharged = false;
        int y = 0;

        for(int i=0; i < spellsCopy.length; i++){
            if(this.spells[i] != null){
                spellsCopy[i] = this.spells[i].clone();
            }
        }
        cardPoolCopy.reset(spellsCopy);
        if(infoResult.flowchartImage != null || infoResult.flowchartString != null){
            cardPoolCopy.setAutoFlowchart(true);
        }

        while(!recharged && nbCast < this.nbSlot){
            currentMana = Math.min(currentMana + this.regenMana*waitingTime, this.maxMana*60);
            waitingTime = 0;
            nbCast++;
            cardPoolCopy.setStats((int)Math.floor(currentMana/60.0), this.rechargeTime);
            cardPoolCopy.resetFlowchart();
            primaryCastState = new CastState();
            primaryCastState.setCastDelay(this.castDelay);
            cardPoolCopy.draw(this.nbDraw, false, primaryCastState);
            currentRechargeTime += cardPoolCopy.getRechargeTime();
            currentCastDelay += primaryCastState.getCastDelay();
            currentMana -= cardPoolCopy.getManaUsage()*60;
            if(cardPoolCopy.getWrappedThisCast() || cardPoolCopy.getDeckSize() <= 0){
                if(infoResult.flowchartString != null){
                    infoResult.flowchartString.append("Mana cost: ").append(cardPoolCopy.getManaUsage()).append("\n").append("Recharge time").append(currentCastDelay > currentRechargeTime ? " (cast delay): " : ": ").append(String.format("%1$df (%2$3.2fs)", currentRechargeTime, currentRechargeTime / 60.0)).append("\n").append(nbCast).append(")\n").append(cardPoolCopy.getFlowchartString(formatting));
                }
                waitingTime += Math.max(Math.max(currentCastDelay, currentRechargeTime), 0);
                currentCastDelay = 0;
                currentRechargeTime = 0;
                recharged = true;
            }else{
                if(infoResult.flowchartString != null){
                    infoResult.flowchartString.append("Mana cost: ").append(cardPoolCopy.getManaUsage()).append("\n").append("Cast delay: ").append(String.format("%1$df (%2$3.2fs)", currentCastDelay, currentCastDelay / 60.0)).append("\n").append(nbCast).append(")\n").append(cardPoolCopy.getFlowchartString(formatting)).append(allCasts ? "\n" : "");
                }
                waitingTime += Math.max(currentCastDelay, 0);
                currentCastDelay = 0;
            }
            Global.nextFrame(waitingTime);
            cardPoolCopy.endCast();
            if(!allCasts){
                recharged = true;
            }
            if(castStateList != null){
                castStateList.add(primaryCastState);
            }
            if(infoResult.flowchartImage != null){
                y = cardPoolCopy.computeFlowchartImage(infoResult.flowchartImage, 0, y);
            }
        }

        if(castStateList != null){
            infoResult.castStates = castStateList.toArray(new CastState[0]);
        }
    }

    public String getFlowchartString(boolean allCasts, boolean formatting){
        InfoResult result = new InfoResult(null, null, new StringBuilder());
        this.getAllInfo(allCasts, result, formatting);
        return result.flowchartString.toString();
    }

    public CastState[] getCastState(boolean allCasts){
        InfoResult result = new InfoResult(new CastState[0], null, null);
        this.getAllInfo(allCasts, result, false);
        return result.castStates;
    }

    public BufferedImage getFlowchartImage(boolean allCasts){
        InfoResult result = new InfoResult(null, new ImageBuilder(new Color(0, 0, 0)), null);
        this.getAllInfo(allCasts, result, false);
        return result.flowchartImage.toImage();
    }

    public void saveFlowchartImage(String filename, boolean allCasts){
        InfoResult result = new InfoResult(null, new ImageBuilder(new Color(0, 0, 0)), null);
        this.getAllInfo(allCasts, result, false);
        result.flowchartImage.saveToFile(filename);
    }

    public boolean cast(){
        CastState castState = new CastState();

        if(this.waitingTime <= 0){
            this.cardPool.setStats((int)Math.floor(currentMana/60.0), this.rechargeTime);
            this.cardPool.resetFlowchart();
            castState.setCastDelay(this.castDelay);
            this.cardPool.draw(this.nbDraw, false, castState);
            this.currentRechargeTime += this.cardPool.getRechargeTime();
            this.currentCastDelay += castState.getCastDelay();
            this.currentMana -= this.cardPool.getManaUsage()*60;
            if(this.cardPool.getWrappedThisCast() || this.cardPool.getDeckSize() <= 0){
                this.waitingTime += Math.max(Math.max(this.currentCastDelay, this.currentRechargeTime), 0);
                this.currentCastDelay = 0;
                this.currentRechargeTime = 0;
            }else{
                this.waitingTime += Math.max(this.currentCastDelay, 0);
                this.currentCastDelay = 0;
            }
            this.cardPool.endCast();
            return true;
        }else{
            System.out.println("WAITING " + this.waitingTime);
            return false;
        }
    }

    public void nextFrame(){
        if(this.waitingTime > 0){
            this.waitingTime--;
        }
        Global.nextFrame();

        this.currentMana += this.regenMana;
        if(this.currentMana > this.maxMana*60){
            this.currentMana = this.maxMana*60;
        }
    }

    public void nextFrame(int nbFrame){
        if(nbFrame < 1){
            return;
        }
        Global.nextFrame(nbFrame);
        this.waitingTime = Math.max(this.waitingTime - nbFrame, 0);

        this.currentMana += this.regenMana*nbFrame;
        if(this.currentMana > this.maxMana*60){
            this.currentMana = this.maxMana*60;
        }
    }

    /*public void update(){
        int JPanelHeight;
        int JPanelWidth;

        if(this.wandJPanel == null){
            this.createLayout();
        }
        this.wandJPanel.repaint();
        this.wandJPanel.setBackground(this.backgroundColor);
        JPanelWidth = this.nbSlot*this.slotSize*this.imageScaleFactor + (this.nbSlot + 1)*this.margin;
        JPanelHeight = this.slotSize*this.imageScaleFactor + this.margin*2;
        this.wandJPanel.setPreferredSize(new Dimension(JPanelWidth, JPanelHeight));
        this.wandJPanel.setBounds(0, 0, JPanelWidth, JPanelHeight);

    }*/
}