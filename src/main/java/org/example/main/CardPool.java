package org.example.main;

import org.example.spells.*;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.awt.image.BufferedImage;;

public class CardPool{
    private List<Spell> startingDeck;
    private List<Spell> deck;
    private List<Spell> hand;
    private List<Spell> discard;
    private boolean drawEnabled = true;
    private boolean autoFlowchart = false;
    private boolean autoCardHistory = false;
    private Flowchart flowchart = new Flowchart();
    private CardHistory cardHistory;
    private List<Flowchart> callStack = new ArrayList<>();
    private boolean wrappedThisCast = false;
    private int maxMana = 0;
    private int manaUsage = 0;
    private int rechargeTime = 0;
    private double recoil = 0;
    private double screenshake = 0.0;

    public CardPool(){
        this.startingDeck = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.cardHistory = new CardHistory(new Spell[0]);
    }

    public CardPool(Spell[] spells){
        this.startingDeck = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        for(int i=0; i < spells.length; i++){
            if(spells[i] != null){
                this.deck.add(spells[i]);
                this.startingDeck.add(spells[i]);
            }
        }
        this.cardHistory = new CardHistory(this.deck.toArray(new Spell[0]));
    }

    // setters
    public void setAutoFlowchart(boolean autoFlowchart){
        this.autoFlowchart = autoFlowchart;
    }

    public void setAutoCardHistory(boolean autoCardHistory){
        this.autoCardHistory = autoCardHistory;
    }

    public void addCallStack(Flowchart node){
        Flowchart[] callStackFlowchart;
        Spell[] callStackSpell;

        this.callStack.add(node);
        if(this.autoCardHistory){
            callStackFlowchart = this.callStack.toArray(new Flowchart[0]);
            callStackSpell = new Spell[callStackFlowchart.length];
            for(int j=0; j < callStackSpell.length; j++){
                callStackSpell[j] = callStackFlowchart[j].getSelf();
            }
            this.cardHistory.addCallStack(callStackSpell);
        }
    }

    public void removeCallStack(){
        Flowchart[] callStackFlowchart;
        Spell[] callStackSpell;

        if(this.callStack.size() > 0){
            this.callStack.remove(this.callStack.size() - 1);
            if(this.autoCardHistory){
                callStackFlowchart = this.callStack.toArray(new Flowchart[0]);
                callStackSpell = new Spell[callStackFlowchart.length];
                for(int j=0; j < callStackSpell.length; j++){
                    callStackSpell[j] = callStackFlowchart[j].getSelf();
                }
                this.cardHistory.addCallStack(callStackSpell);
            }
        }
    }

    public Flowchart getCallStack(){
        Flowchart[] callStackArray = this.callStack.toArray(new Flowchart[0]);

        if(callStackArray.length <= 0){
            return this.flowchart;
        }
        return callStackArray[callStackArray.length - 1];
    }

    public CardHistory getCardHistory(){
        return this.cardHistory;
    }

    public void setStats(int maxMana, int rechargeTime){
        this.maxMana = maxMana;
        this.rechargeTime = rechargeTime;
        this.manaUsage = 0;
    }

    public void addManaUsage(int manaUsage){
        this.manaUsage += manaUsage;
    }

    public void setRechargeTime(int rechargeTime){
        this.rechargeTime = rechargeTime;
    }

    public void addRechargeTime(int rechargeTime){
        this.rechargeTime += rechargeTime;
    }

    public void addRecoil(double recoil){
        this.recoil += recoil;
    }

    public void setRecoil(double recoil){
        this.recoil = recoil;
    }

    public void addScreenshake(double screenshake){
        this.screenshake += screenshake;
    }

    // getters
    public boolean getAutoFlowchart(){
        return this.autoFlowchart;
    }

    public boolean getAutoCardHistory(){
        return this.autoCardHistory;
    }

    public String getFlowchartString(boolean formating){
        this.flowchart.refactor();
        return this.flowchart.toString(formating);
    }

    public String getCardHistoryString(){
        return this.cardHistory.toString();
    }

    /*public String getCallStackString(){
        return this.cardHistory.callStackToString();
    }*/

    /*public String getHistoryString(){
        return this.cardHistory.historyToString();
    }*/

    public BufferedImage getFlowchartImage(){
        this.flowchart.refactor();
        return this.flowchart.toImage();
    }

    public void saveFlowchartImage(String filename){
        this.flowchart.refactor();
        this.flowchart.saveToImage(filename);
    }

    public int computeFlowchartImage(ImageBuilder image, int x, int y){
        this.flowchart.refactor();
        return this.flowchart.toImage(image, x, y);
    }

    public int getManaUsage(){
        return this.manaUsage;
    }

    public void setManaUsage(int manaUsage){
        this.manaUsage = manaUsage;
    }

    public int getRechargeTime(){
        return this.rechargeTime;
    }

    public boolean getWrappedThisCast(){
        return this.wrappedThisCast;
    }

    public Spell getDiscardSpell(int id){
        if(id >= 0 && id < this.discard.size()){
            return this.discard.get(id);
        }else{
            return null;
        }
    }

    public Spell getHandSpell(int id){
        if(id >= 0 && id < this.hand.size()){
            return this.hand.get(id);
        }else{
            return null;
        }
    }

    public Spell getDeckSpell(int id){
        if(id >= 0 && id < this.deck.size()){
            return this.deck.get(id);
        }else{
            return null;
        }
    }

    public Spell[] getDiscard(){
        return this.discard.toArray(new Spell[0]);
    }

    public Spell[] getHand(){
        return this.hand.toArray(new Spell[0]);
    }

    public Spell[] getDeck(){
        return this.deck.toArray(new Spell[0]);
    }

    public int getDiscardSize(){
        return this.discard.size();
    }

    public int getHandSize(){
        return this.hand.size();
    }

    public int getDeckSize(){
        return this.deck.size();
    }

    public void addDeck(Spell spell){
        this.deck.add(spell);
    }

    public void addHand(Spell spell){
        this.hand.add(spell);
    }

    public void addDiscard(Spell spell){
        this.discard.add(spell);
    }

    public void addDeck(Spell[] spells){
        for(int i=0; i < spells.length; i++){
            this.deck.add(spells[i]);
        }
    }

    public void addHand(Spell[] spells){
        for(int i=0; i < spells.length; i++){
            this.hand.add(spells[i]);
        }
    }

    public void addDiscard(Spell[] spells){
        for(int i=0; i < spells.length; i++){
            this.discard.add(spells[i]);
        }
    }

    public void clear(){
        this.startingDeck.clear();
        this.deck.clear();
        this.hand.clear();
        this.discard.clear();
        this.drawEnabled = true;
        this.flowchart.reset();
        this.cardHistory.reset();
        this.wrappedThisCast = false;
    }

    public void reset(){
        this.deck = new ArrayList<Spell>(this.startingDeck);
        this.hand.clear();
        this.discard.clear();
        this.drawEnabled = true;
        this.flowchart.reset();
        this.cardHistory.reset();
        this.wrappedThisCast = false;
    }

    public void reset(Spell[] spells){
        this.startingDeck.clear();
        this.deck.clear();
        for(int i=0; i < spells.length; i++){
            if(spells[i] != null){
                this.deck.add(spells[i]);
                this.startingDeck.add(spells[i]);
            }
        }
        this.hand.clear();
        this.discard.clear();
        this.drawEnabled = true;
        this.flowchart.reset();
        this.cardHistory = new CardHistory(this.startingDeck.toArray(new Spell[0]));
        this.wrappedThisCast = false;
    }

    public void resetFlowchart(){
        this.flowchart.reset();
    }

    public void resetCardHistory(){
        this.cardHistory.reset(this.discard.toArray(new Spell[0]), this.hand.toArray(new Spell[0]), this.deck.toArray(new Spell[0]));
    }

    private void sortDeck(){
        List<Spell> newDeck = new ArrayList<>();
        ListIterator<Spell> sorter = this.startingDeck.listIterator();
        Spell currentSpell;

        String oldCP = this.toString(); // test

        while(sorter.hasNext()){
            currentSpell = sorter.next();
            if(this.deck.contains(currentSpell)){
                newDeck.add(currentSpell);
            }
        }

        this.deck = newDeck;
        /*String newCP = this.toString(); // test
        if(!oldCP.equals(newCP)){
            System.out.println("reorder : " + oldCP + " -> " + newCP);
        }*/
    }

    public void endCast(){
        while(!this.hand.isEmpty()){
            this.discard.add(this.hand.remove(0));
        }
        if(this.wrappedThisCast || this.deck.isEmpty()){
            this.deck = new ArrayList<Spell>(this.startingDeck);
            this.hand.clear();
            this.discard.clear();
            this.drawEnabled = true;
        }
        this.wrappedThisCast = false;
    }

    public void clearDeck(){
        this.deck.clear();
    }

    public void clearHand(){
        this.hand.clear();
    }

    public void clearDiscard(){
        this.discard.clear();
    }

    public void draw(int nbDraw, boolean wrapAllowed, CastState castState){
        draw(nbDraw, castState, wrapAllowed, this.getCallStack());
    }

    private void wrap(){
        //System.out.println(this.toString());
        //System.out.println("WRAP !");
        while(!this.discard.isEmpty()){
            this.deck.add(this.discard.remove(0));
        }
        this.sortDeck();
        this.wrappedThisCast = true;
        if(this.autoCardHistory){
            this.cardHistory.addStep(this.discard.toArray(new Spell[0]), this.hand.toArray(new Spell[0]), this.deck.toArray(new Spell[0]));
        }
        //System.out.println(this.toString());
    }

    public void draw(int nbDraw, CastState castState, boolean wrapAllowed, Flowchart currentNode){
        Spell currentSpell = null;
        boolean spellSkipped = false;
        boolean removeCharges = false;
        boolean drawConsumed = true;

        for(int i=0; i < nbDraw; i++){
            if(this.drawEnabled && this.deck.isEmpty()){
                if(wrapAllowed && !this.discard.isEmpty()){
                    this.wrap();
                }
            }
            if(this.drawEnabled && !this.deck.isEmpty()){
                removeCharges = false;
                drawConsumed = true;
                currentSpell = this.deck.remove(0);
                if(this.manaUsage + currentSpell.getManaCost() <= this.maxMana && !currentSpell.getHasCharges()){
                    this.hand.add(currentSpell);
                    spellSkipped = false;
                }else if(this.manaUsage + currentSpell.getManaCost() <= this.maxMana && currentSpell.getHasCharges() && currentSpell.getChargesLeft() > 0){
                    this.hand.add(currentSpell);
                    removeCharges = true;
                    spellSkipped = false;
                }else{
                    this.discard.add(currentSpell);
                    spellSkipped = true;
                    if(!this.deck.isEmpty()){
                        // draw not consumed
                        drawConsumed = false;
                        i--;
                    }
                }

                if(!spellSkipped){
                    if(this.autoCardHistory){
                        this.cardHistory.addStep(this.discard.toArray(new Spell[0]), this.hand.toArray(new Spell[0]), this.deck.toArray(new Spell[0]));
                    }
                    if(this.autoFlowchart || this.autoCardHistory){
                        this.addCallStack(currentNode.add(currentSpell));
                    }
                    this.manaUsage += currentSpell.getManaCost();
                    if(removeCharges){
                        currentSpell.removeCharge();
                    }
                    currentSpell.doAction(this, castState);
                    if(this.autoFlowchart || this.autoCardHistory){
                        this.removeCallStack();
                    }
                }else if(this.autoFlowchart && drawConsumed && i + 1 == nbDraw){
                    currentNode.add(null);
                }
            }else{
                if(this.autoFlowchart){
                    currentNode.add(null);
                }
                break;
            }
            currentSpell = null;
        }
    }

    public void discardNext(){
        if(!this.deck.isEmpty()){
            this.discard.add(this.deck.remove(0));
            if(this.autoCardHistory){
                this.cardHistory.addStep(this.discard.toArray(new Spell[0]), this.hand.toArray(new Spell[0]), this.deck.toArray(new Spell[0]));
            }
        }
    }

    public void discardNext(int nbDiscard){
        for(int i=0; i < nbDiscard; i++){
            if(!this.deck.isEmpty()){
                this.discard.add(this.deck.remove(0));
            }else{
                return;
            }
        }
        if(this.autoCardHistory){
            this.cardHistory.addStep(this.discard.toArray(new Spell[0]), this.hand.toArray(new Spell[0]), this.deck.toArray(new Spell[0]));
        }
    }

    public void enableDraw(){
        this.drawEnabled = true;
    }

    public void disableDraw(){
        this.drawEnabled = false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        ListIterator<Spell> deckIterator = this.deck.listIterator();
        ListIterator<Spell> handIterator = this.hand.listIterator();
        ListIterator<Spell> discardIterator = this.discard.listIterator();

        str.append("[");
        while(discardIterator.hasNext()){
            str.append(discardIterator.next().getName());
            if(discardIterator.hasNext()){
                str.append(", ");
            }
        }
        str.append("] [");
        while(handIterator.hasNext()){
            str.append(handIterator.next().getName());
            if(handIterator.hasNext()){
                str.append(", ");
            }
        }
        str.append("] [");
        while(deckIterator.hasNext()){
            str.append(deckIterator.next().getName());
            if(deckIterator.hasNext()){
                str.append(", ");
            }
        }
        str.append("]");

        return str.toString();
    }
}