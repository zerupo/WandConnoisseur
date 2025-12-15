package org.example.main;

import org.example.spells.*;

import java.util.ArrayList;
import java.lang.StringBuilder;

// used to be for deck evaluation but since it became a bot... maybe one day
class StructHistory{
    private int[][] history;
    private ArrayList<int[]> callStackHistory;

    public StructHistory(int[] discard, int[] hand, int[] deck, int[] callStack){
        int[] newCallStack = new int[callStack.length];
        for(int i=0; i < callStack.length; i++){
            newCallStack[i] = callStack[i];
        }
        this.callStackHistory = new ArrayList<int[]>();
        this.callStackHistory.add(newCallStack);

        this.history = new int[3][];
        this.history[0] = new int[discard.length];
        for(int i=0; i < discard.length; i++){
            this.history[0][i] = discard[i];
        }
        this.history[1] = new int[hand.length];
        for(int i=0; i < hand.length; i++){
            this.history[1][i] = hand[i];
        }
        this.history[2] = new int[deck.length];
        for(int i=0; i < deck.length; i++){
            this.history[2][i] = deck[i];
        }
    }

    public StructHistory(int[] discard, int[] hand, int[] deck){
        this.callStackHistory = new ArrayList<int[]>();
        this.history = new int[3][];
        this.history[0] = new int[discard.length];
        for(int i=0; i < discard.length; i++){
            this.history[0][i] = discard[i];
        }
        this.history[1] = new int[hand.length];
        for(int i=0; i < hand.length; i++){
            this.history[1][i] = hand[i];
        }
        this.history[2] = new int[deck.length];
        for(int i=0; i < deck.length; i++){
            this.history[2][i] = deck[i];
        }
    }

    public int getCallStackSize(){
        return this.callStackHistory.size();
    }

    public int[][] getCallStack(){
        int[][] result = new int[this.callStackHistory.size()][];
        int[] currentCallStack;
        int id;

        for(int i=0; i < result.length; i++){
            currentCallStack = this.callStackHistory.get(i);
            if(currentCallStack.length == 1 && currentCallStack[0] < 0){
                //id = -(currentCallStack[0] + 1);
                currentCallStack = this.callStackHistory.get(-(currentCallStack[0] + 1));
            }/*else{
                id = i;
            }*/
            result[i] = new int[currentCallStack.length];
            for(int j=0; j < result[i].length; j++){
                result[i][j] = currentCallStack[j];
            }
        }
        return result;
        //return this.callStackHistory.toArray(new int[this.callStackHistory.size()][]);
    }

    public int[][] getHistory(){
        return this.history;
    }

    public int[] getCallStackHistory(int id){
        int[] result;

        if(id < 0 || id >= this.callStackHistory.size()){
            return new int[0];
        }else if(this.callStackHistory.get(id).length == 1 && this.callStackHistory.get(id)[0] < 0){
            return this.callStackHistory.get(-(this.callStackHistory.get(id)[0] + 1));
        }else{
            return this.callStackHistory.get(id);
        }
    }

    public void addCallStack(int[] callStack){
        int[] newCallStack;
        int[] currentCallStack;
        boolean same = true;

        for(int i=0; i < callStackHistory.size(); i++){
            currentCallStack = callStackHistory.get(i);
            if(callStack.length == currentCallStack.length && callStack.length > 1){
                same = true;
                for(int j=0; j < currentCallStack.length && same; j++){
                    if(callStack[j] != currentCallStack[j]){
                        same = false;
                    }
                }
                if(same){
                    newCallStack = new int[]{-(i + 1)};
                    return;
                }
            }
        }

        newCallStack = new int[callStack.length];
        for(int i=0; i < callStack.length; i++){
            newCallStack[i] = callStack[i];
        }
        this.callStackHistory.add(newCallStack);
    }

    /*public void historyToString(StringBuilder sb){
        int[] currentCallStackHistory;

        for(int i=0; i < this.history.length; i++){
            switch(i){
                case 0:
                    sb.append("\033[0;31mDiscard\u001B[0m: ");
                    break;
                case 1:
                    sb.append(" \033[0;31mHand\u001B[0m: ");
                    break;
                case 2:
                    sb.append(" \033[0;31mDeck\u001B[0m: ");
                    break;
                default:
                    sb.append(" ");
            }
            sb.append("[");
            for(int j=0; j < this.history[i].length; j++){
                sb.append(this.history[i][j].getName());
                if(j + 1 < this.history[i].length){
                    sb.append(", ");
                }
            }
            sb.append("]");
        }
        sb.append("\n");
    }

    public void callStackToString(StringBuilder sb){
        int[] currentCallStackHistory;

        for(int i=0; i < this.callStackHistory.size(); i++){
            currentCallStackHistory = this.callStackHistory.get(i);
            if(currentCallStackHistory.length == 0){
                sb.append("<empty>");
            }else{
                for(int j=0; j < currentCallStackHistory.length; j++){
                    sb.append(currentCallStackHistory[j].getName());
                    if(j + 1 < currentCallStackHistory.length){
                        sb.append(" -> ");
                    }
                }
            }
            sb.append("\n");
        }
    }

    public void toString(StringBuilder sb){
        historyToString(sb);
        callStackToString(sb);
    }

    public String historyToString(){
        int[] currentCallStackHistory;
        String result = "";

        for(int i=0; i < this.history.length; i++){
            switch(i){
                case 0:
                    result += "\033[0;31mDiscard\u001B[0m: ";
                    break;
                case 1:
                    result += "\n\033[0;31mHand\u001B[0m: ";
                    break;
                case 2:
                    result += "\n\033[0;31mDeck\u001B[0m: ";
                    break;
                default:
                    result += " ";
            }
            result += "[";
            for(int j=0; j < this.history[i].length; j++){
                result += this.history[i][j].getName();
                if(j + 1 < this.history[i].length){
                    result += ", ";
                }
            }
            result += "]";
        }
        result += "\n";
        return result;
    }

    public String callStackToString(int id){
        String result = "";
        int[] currentCallStackHistory;

        if(id >= callStackHistory.size()){
            return "err";//result;
        }
        currentCallStackHistory = this.callStackHistory.get(id);
        if(currentCallStackHistory.length == 0){
            result = "<empty>";
        }else{
            for(int j=0; j < currentCallStackHistory.length; j++){
                result += currentCallStackHistory[j].getName();
                if(j + 1 < currentCallStackHistory.length){
                    result += " -> ";
                }
            }
        }
        return result;
    }*/
}

public class CardHistory{
    private Spell[] spellID = new Spell[0];
    private Spell[] startingSpells;
    private ArrayList<StructHistory> history;
    private int totalCallStackSize = 1;
    private int currentCardPoolStep = 0;
    private int currentCardPoolStep2 = 0;
    private int currentCallStackStep = 0;

    public CardHistory(Spell[] startingSpells){
        this.startingSpells = new Spell[startingSpells.length];
        for(int i=0; i < startingSpells.length; i++){
            this.startingSpells[i] = startingSpells[i];
        }
        this.history = new ArrayList<StructHistory>();
        this.history.add(new StructHistory(spellToID(new Spell[0]), spellToID(new Spell[0]), spellToID(startingSpells), spellToID(new Spell[0])));
    }

    public int getCardOnlySize(){
        return this.history.size();
    }

    public int getSize(){
        return this.totalCallStackSize;
    }

    public int getStep(){
        int result = 0;

        for(int i=0; i < Math.min(currentCardPoolStep + 1, this.history.size()); i++){
            if(i < this.currentCardPoolStep){
                result += Math.max(this.history.get(i).getCallStackSize(), 1);
                //System.out.println("+" + Math.max(this.history.get(i).getCallStackSize(), 1));
            }else{
                if(this.currentCardPoolStep2 == this.currentCardPoolStep){
                    result += this.currentCallStackStep;
                }
                //System.out.println(this.currentCardPoolStep + " "  + this.currentCardPoolStep2 + " " + this.currentCallStackStep + " = " + result);
                return result;
            }
        }
        //System.out.println(this.currentCardPoolStep + " "  + this.currentCardPoolStep2 + " " + this.currentCallStackStep + " = " + result);
        return result;
    }

    public int getCardOnlyStep(){
        return this.currentCardPoolStep;
    }

    private int spellToID(Spell spell){
        for(int i=0; i < this.spellID.length; i++){
            if(spell.getClass() == this.spellID[i].getClass()){
                return i;
            }
        }
        Spell[] newSpellID = new Spell[this.spellID.length + 1];
        for(int i=0; i < this.spellID.length; i++){
            newSpellID[i] = this.spellID[i];
        }
        newSpellID[newSpellID.length - 1] = spell;
        this.spellID = newSpellID;
        return this.spellID.length - 1;
    }

    private int[] spellToID(Spell[] spell){
        int[] result = new int[spell.length];

        for(int i=0; i < result.length; i++){
            result[i] = spellToID(spell[i]);
        }
        return result;
    }

    private Spell[] IDToSpell(int[] IDs){
        Spell[] result = new Spell[IDs.length];

        for(int i=0; i < result.length; i++){
            result[i] = this.spellID[IDs[i]];
        }
        return result;
    }

    private Spell[][] IDToSpell(int[][] IDs){
        Spell[][] result = new Spell[IDs.length][];

        for(int i=0; i < result.length; i++){
            result[i] = IDToSpell(IDs[i]);
        }
        return result;
    }

    public void add(CardHistory cardHistory, boolean destroy){
        StructHistory structHistory;
        Spell[][] history;
        Spell[][] callStack;

        // avoid infinite loop
        if(this == cardHistory){
            System.out.println("Same CardHistory, skipping");
            return;
        }

        System.out.println("start");
        for(int i=0; i < cardHistory.history.size(); i++){
            System.out.println(i + "/" + cardHistory.history.size() + " -> /" + this.history.size());
            structHistory = cardHistory.history.get(i);
            history = cardHistory.IDToSpell(structHistory.getHistory());
            this.addStep(history[0], history[1], history[2]);
            callStack = cardHistory.IDToSpell(structHistory.getCallStack());
            for(int j=0; j < callStack.length; j++){
                this.addCallStack(callStack[j]);
            }
            if(destroy){
                cardHistory.history.set(i, null);
            }
        }
        this.totalCallStackSize += cardHistory.getSize();
        System.out.println("end");
    }

    public void addStep(Spell[] discard, Spell[] hand, Spell[] deck, Spell[] callStack){
        this.history.add(new StructHistory(spellToID(discard), spellToID(hand), spellToID(deck), spellToID(callStack)));
        this.totalCallStackSize += 1;
    }

    public void addStep(Spell[] discard, Spell[] hand, Spell[] deck){
        this.history.add(new StructHistory(spellToID(discard), spellToID(hand), spellToID(deck)));
        this.totalCallStackSize += 1;
    }

    public void reset(){
        this.history = new ArrayList<StructHistory>();
        this.history.add(new StructHistory(spellToID(new Spell[0]), spellToID(new Spell[0]), spellToID(this.startingSpells), spellToID(new Spell[0])));
        this.totalCallStackSize = 1;
    }

    public void reset(Spell[] discard, Spell[] hand, Spell[] deck){
        this.history = new ArrayList<StructHistory>();
        this.history.add(new StructHistory(spellToID(discard), spellToID(hand), spellToID(deck)));
        this.totalCallStackSize = 1;
    }

    public void addCallStack(Spell[] callStack){
        if(this.history.get(this.history.size() - 1).getCallStackSize() != 0){
            this.totalCallStackSize += 1;
        }
        this.history.get(this.history.size() - 1).addCallStack(spellToID(callStack));
    }

    /*public String historyToString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < this.history.size(); i++){
            this.history.get(i).historyToString(sb);
        }
        return sb.toString();
    }

    public String callStackToString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < this.history.size(); i++){
            this.history.get(i).callStackToString(sb);
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < this.history.size(); i++){
            this.history.get(i).toString(sb);
        }
        //for(int i=0; i < this.totalCallStackSize; i++){
        //    System.out.println(this.toString(i));
        //}
        //System.out.println();
        return sb.toString();
    }*/

    /*public String toString(int callStackStep){
        int currentStep = 0;
        StructHistory currentHistory;
        int previousId;

        if(callStackStep < 0 || callStackStep >= totalCallStackSize){
            return "OOB (" + callStackStep + " >= " + totalCallStackSize + ")";
        }
        for(int i=0; i < this.history.size(); i++){
            currentHistory = this.history.get(i);
            if(currentStep + Math.max(currentHistory.getCallStackSize(), 1) <= callStackStep){
                currentStep += Math.max(currentHistory.getCallStackSize(), 1);
            }else{
                if(currentHistory.getCallStackSize() == 0){
                    previousId = i - 1;
                    while(previousId >= 0 && this.history.get(previousId).getCallStackSize() == 0){
                        previousId -= 1;
                    }
                    if(previousId < 0){
                        return currentHistory.historyToString() + "<empty>";
                    }else{
                        return currentHistory.historyToString() + this.history.get(previousId).callStackToString(this.history.get(previousId).getCallStackSize() - 1);
                    }
                }else{
                    return currentHistory.historyToString() + currentHistory.callStackToString(callStackStep - currentStep);
                }
            }
        }
        return "OOB (" + currentStep + " >= " + totalCallStackSize + ")";
    }*/

    public Spell[][] getStep(int callStackStep){
        Spell[][] result = new Spell[4][];
        int currentStep = 0;
        StructHistory currentHistory;
        Spell[][] currentCardPool;
        Spell[] currentCallStack;
        int previousId;

        if(callStackStep < 0 || callStackStep >= totalCallStackSize){
            for(int i=0; i < result.length; i++){
                result[i] = new Spell[0];
            }
            return result;
        }
        for(int i=0; i < this.history.size(); i++){
            currentHistory = this.history.get(i);
            if(currentStep + Math.max(currentHistory.getCallStackSize(), 1) <= callStackStep){
                currentStep += Math.max(currentHistory.getCallStackSize(), 1);
            }else{
                currentCardPool = IDToSpell(currentHistory.getHistory());
                for(int j=0; j < currentCardPool.length && j < 3; j++){
                    result[j] = new Spell[currentCardPool[j].length];
                    for(int k=0; k < result[j].length; k++){
                        result[j][k] = currentCardPool[j][k];
                    }
                }
                if(currentHistory.getCallStackSize() == 0){
                    previousId = i - 1;
                    while(previousId >= 0 && this.history.get(previousId).getCallStackSize() == 0){
                        previousId -= 1;
                    }
                    if(previousId < 0){
                        result[3] = new Spell[0];
                    }else{
                        currentCallStack = IDToSpell(this.history.get(previousId).getCallStackHistory(this.history.get(previousId).getCallStackSize() - 1));
                        result[3] = new Spell[currentCallStack.length];
                        for(int j=0; j < result.length; j++){
                            result[3][j] = currentCallStack[j];
                        }
                    }
                    return result;
                }else{
                    currentCallStack = IDToSpell(currentHistory.getCallStackHistory(callStackStep - currentStep));
                    result[3] = new Spell[currentCallStack.length];
                    for(int j=0; j < result.length; j++){
                        result[3][j] = currentCallStack[j];
                    }
                    return result;
                }
            }
        }
        return result;
    }

    public Spell[][] getCurrentStep(){
        Spell[][] currentCardPool;
        Spell[] currentCallStack;
        Spell[][] result = new Spell[4][];
        for(int i=0; i < result.length; i++){
            result[i] = new Spell[0];
        }

        if(this.currentCardPoolStep >= 0 && this.currentCardPoolStep < this.history.size()){
            currentCardPool = IDToSpell(this.history.get(this.currentCardPoolStep).getHistory());
            for(int i=0; i < currentCardPool.length && i < 3; i++){
                result[i] = new Spell[currentCardPool[i].length];
                for(int j=0; j < result[i].length; j++){
                    result[i][j] = currentCardPool[i][j];
                }
            }
        }
        if(this.currentCardPoolStep2 >= 0 && this.currentCardPoolStep2 < this.history.size() && this.currentCallStackStep >= 0 && this.currentCallStackStep < this.history.get(this.currentCardPoolStep2).getCallStackSize()){
            currentCallStack = IDToSpell(this.history.get(this.currentCardPoolStep2).getCallStackHistory(this.currentCallStackStep));
            result[3] = new Spell[currentCallStack.length];
            for(int i=0; i < currentCallStack.length; i++){
                result[3][i] = currentCallStack[i];
            }
        }
        return result;
    }

    /*public String getCurrentStepString(){
        String result = "";

        if(this.currentCardPoolStep >= 0 && this.currentCardPoolStep < this.history.size()){
            result += this.history.get(this.currentCardPoolStep).historyToString();
        }
        if(this.currentCardPoolStep2 >= 0 && this.currentCardPoolStep2 < this.history.size() && this.currentCallStackStep >= 0 && this.currentCallStackStep < this.history.get(this.currentCardPoolStep2).getCallStackSize()){
            result += this.history.get(this.currentCardPoolStep2).callStackToString(this.currentCallStackStep);
        }
        return result;
    }*/

    public void nextStep(){
        if(this.history.size() == 0 || this.currentCardPoolStep < 0 || this.currentCardPoolStep >= this.history.size()){
            this.currentCardPoolStep = 0;
            this.currentCardPoolStep2 = 0;
            this.currentCallStackStep = 0;
            return;
        }
        //System.out.println("(1) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        if(currentCardPoolStep2 < currentCardPoolStep){
            this.currentCallStackStep = 0;
        }
        currentCardPoolStep2 = currentCardPoolStep;
        this.currentCallStackStep += 1;
        //System.out.println("(2) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);

        if(this.currentCallStackStep >= this.history.get(this.currentCardPoolStep).getCallStackSize()){
            this.currentCardPoolStep += 1;
            this.currentCardPoolStep2 += 1;
            this.currentCallStackStep = 0;
            //System.out.println("(3) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
            if(this.currentCardPoolStep >= this.history.size()){
                this.currentCardPoolStep = 0;
                this.currentCardPoolStep2 = 0;
                //System.out.println("(4) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
                return;
            }
            if(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                while(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                    this.currentCardPoolStep2 -= 1;
                    //System.out.println("(5) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
                }
                if(this.currentCardPoolStep2 >= 0){
                    this.currentCallStackStep = this.history.get(this.currentCardPoolStep2).getCallStackSize() - 1;
                }else{
                    this.currentCardPoolStep2 = 0;
                    this.currentCallStackStep = 0;
                }
            }
            //System.out.println("(6) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
        }
    }

    public void previousStep(){
        if(this.history.size() == 0 || this.currentCardPoolStep < 0 || this.currentCardPoolStep >= this.history.size()){
            this.currentCardPoolStep = 0;
            this.currentCardPoolStep2 = 0;
            this.currentCallStackStep = 0;
            return;
        }
        //System.out.println("(1) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        if(currentCardPoolStep2 < currentCardPoolStep){
            this.currentCallStackStep = 0;
        }
        currentCardPoolStep2 = currentCardPoolStep;
        this.currentCallStackStep -= 1;
        //System.out.println("(2) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");

        if(this.currentCallStackStep < 0){
            this.currentCardPoolStep -= 1;
            this.currentCardPoolStep2 -= 1;
            if(this.currentCardPoolStep < 0){
                this.currentCardPoolStep = this.history.size() - 1;
                this.currentCardPoolStep2 = this.history.size() - 1;
            }
            this.currentCallStackStep = this.history.get(this.currentCardPoolStep).getCallStackSize() - 1;
            /*if(this.currentCardPoolStep > 0){
                System.out.println("(3) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
            }else{
                System.out.println("(3) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
            }*/
            if(this.currentCardPoolStep < 0){
                this.currentCardPoolStep = this.history.size() - 1;
                this.currentCardPoolStep2 = this.history.size() - 1;
                this.currentCallStackStep = this.history.get(this.currentCardPoolStep).getCallStackSize() - 1;
                //System.out.println("(4) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
            }
            if(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                while(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                    this.currentCardPoolStep2 -= 1;
                    //System.out.println("(5) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
                }
                if(this.currentCardPoolStep2 >= 0){
                    this.currentCallStackStep = this.history.get(this.currentCardPoolStep2).getCallStackSize() - 1;
                }else{
                    this.currentCardPoolStep2 = 0;
                    this.currentCallStackStep = 0;
                }
            }
            //System.out.println("(6) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        }
    }

    public void nextCardPoolStep(){
        if(this.history.size() == 0 || this.currentCardPoolStep < 0 || this.currentCardPoolStep >= this.history.size()){
            this.currentCardPoolStep = 0;
            this.currentCardPoolStep2 = 0;
            this.currentCallStackStep = 0;
            return;
        }
        //System.out.println("(1) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        this.currentCardPoolStep += 1;
        this.currentCardPoolStep2 = this.currentCardPoolStep;
        this.currentCallStackStep = 0;
        /*if(this.currentCardPoolStep < this.history.size()){
            System.out.println("(2) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        }else{
            System.out.println("(2) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep);
        }*/
        if(this.currentCardPoolStep >= this.history.size()){
            this.currentCardPoolStep = 0;
            this.currentCardPoolStep2 = 0;
            //System.out.println("(3) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
            return;
        }
        if(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
            while(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                this.currentCardPoolStep2 -= 1;
                //System.out.println("(4) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
            }
            if(this.currentCardPoolStep2 >= 0){
                this.currentCallStackStep = this.history.get(this.currentCardPoolStep2).getCallStackSize() - 1;
            }else{
                this.currentCardPoolStep2 = 0;
                this.currentCallStackStep = 0;
            }
        }
        //System.out.println("(5) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
    }

    public void previousCardPoolStep(){
        if(this.history.size() == 0 || this.currentCardPoolStep < 0 || this.currentCardPoolStep >= this.history.size()){
            this.currentCardPoolStep = 0;
            this.currentCardPoolStep2 = 0;
            this.currentCallStackStep = 0;
            return;
        }
        //System.out.println("(1) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        if(this.currentCardPoolStep == this.currentCardPoolStep2 && this.currentCallStackStep > 0){
            this.currentCallStackStep = 0;
        }else{
            this.currentCardPoolStep -= 1;
            if(this.currentCardPoolStep < 0){
                this.currentCardPoolStep = this.history.size() - 1;
            }
            this.currentCardPoolStep2 = this.currentCardPoolStep;
            this.currentCallStackStep = 0;
        }
        //System.out.println("(2) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
        if(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
            while(this.currentCardPoolStep2 >= 0 && this.history.get(this.currentCardPoolStep2).getCallStackSize() == 0){
                this.currentCardPoolStep2 -= 1;
                //System.out.println("(3) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
            }
            if(this.currentCardPoolStep2 >= 0){
                this.currentCallStackStep = this.history.get(this.currentCardPoolStep2).getCallStackSize() - 1;
            }else{
                this.currentCardPoolStep2 = 0;
                this.currentCallStackStep = 0;
            }
        }
        //System.out.println("(4) " + this.currentCardPoolStep + " " + this.currentCardPoolStep2 + " " + this.currentCallStackStep + "/[" + this.history.get(this.currentCardPoolStep).getCallStackSize() + ", " + this.totalCallStackSize + "]");
    }
}