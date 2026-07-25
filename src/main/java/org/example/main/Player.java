package org.example.main;

import org.example.main.Global.DamageType;
import org.example.main.Global.DamageTypeBooleanPair;
import org.example.main.Global.DamageTypeDoublePair;
import static org.example.main.Global.DamageTypeDoublePair.of;

public class Player{
    HealthComponent healthComponent;
    int gold;
    int x = 0;
    int y = 0;

    public Player(double maxHp, int gold, boolean immuneToAll){
        this.healthComponent = new HealthComponent(
            maxHp,
            new DamageTypeDoublePair[]{
                of(0.35, DamageType.EXPLOSION),
                of(1.5, DamageType.HOLY)
            },
            new DamageTypeBooleanPair[]{},
            true,
            immuneToAll
        );
        this.gold = gold;
    }

    // getters
    public HealthComponent getHealthComponent(){
        return this.healthComponent;
    }

    public int getGold(){
        return this.gold;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    // setters
    public void setGold(int amount){
        this.gold = amount;
    }

    public void addGold(int amount){
        if(amount < 0 || this.gold == Integer.MAX_VALUE){
            return;
        }
        this.gold = (int)Math.min((long)this.gold + amount, Integer.MAX_VALUE);
    }

    public void addGold(long amount){
        if(amount < 0 || this.gold == Integer.MAX_VALUE){
            return;
        }
        this.gold = (int)Math.min((long)this.gold + amount, Integer.MAX_VALUE);
    }

    public boolean payGold(int amount){
        if(amount < 0 || this.gold == Integer.MAX_VALUE){
            return true;
        }
        if(amount <= this.gold){
            this.gold -= amount;
            return true;
        }else{
            return false;
        }
    }

    public boolean payGold(long amount){
        if(amount < 0 || (this.gold == Integer.MAX_VALUE && amount <= Integer.MAX_VALUE)){
            return true;
        }
        if(amount <= this.gold){
            this.gold -= amount;
            return true;
        }else{
            return false;
        }
    }
}