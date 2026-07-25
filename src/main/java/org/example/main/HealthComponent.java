package org.example.main;

import org.example.main.Global.DamageType;
import org.example.main.Global.DamageTypeBooleanPair;
import org.example.main.Global.DamageTypeDoublePair;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HealthComponent{
    private double hp;
    private double maxHp;
    private final double[] resistance = new double[DamageType.values().length];
    private final boolean[] immunity = new boolean[DamageType.values().length];
    private boolean critImmune = false;
    private boolean immuneToAll = false;

    public HealthComponent(double maxHp, DamageTypeDoublePair[] resistances, DamageTypeBooleanPair[] immunities, boolean critImmune, boolean immuneToAll){
        Arrays.fill(this.resistance, 1.0);
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.setResistance(resistances);
        this.setImmunity(immunities);
        this.critImmune = critImmune;
        this.immuneToAll = immuneToAll;
    }

    public HealthComponent(double maxHp, DamageTypeDoublePair[] resistances, DamageTypeBooleanPair[] immunities){
        Arrays.fill(this.resistance, 1.0);
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.setResistance(resistances);
        this.setImmunity(immunities);
    }

    public HealthComponent(double maxHp){
        Arrays.fill(this.resistance, 1.0);
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    // getters
    public double getHp(){
        return hp;
    }

    public double getMaxHp(){
        return maxHp;
    }

    public double[] getResistance(boolean clone){
        return clone ? Arrays.copyOf(this.resistance, this.resistance.length) : this.resistance;
    }

    public boolean[] getImmunity(boolean clone){
        return clone ? Arrays.copyOf(this.immunity, this.immunity.length) : this.immunity;
    }

    public boolean getCritImmune(){
        return this.critImmune;
    }

    public boolean getImmuneToAll(){
        return immuneToAll;
    }

    // setters
    public void setHp(double hp){
        this.hp = Math.max(0.0, hp);
    }

    public void setMaxHp(double maxHp){
        this.maxHp = Math.max(0.0, maxHp);
        this.hp = Math.min(this.hp, this.maxHp);
    }

    public void setResistance(double resistance, DamageType damageType){
        this.resistance[damageType.ordinal()] = resistance;
    }

    public void setResistance(DamageTypeDoublePair damageType){
        this.resistance[damageType.damageType().ordinal()] = damageType.value();
    }

    public void setResistance(DamageTypeDoublePair[] damageTypes){
        for(DamageTypeDoublePair damageType : damageTypes){
            this.resistance[damageType.damageType().ordinal()] = damageType.value();
        }
    }

    public void setImmunity(boolean immunity, DamageType damageType){
        this.immunity[damageType.ordinal()] = immunity;
    }

    public void setImmunity(DamageTypeBooleanPair damageType){
        this.immunity[damageType.damageType().ordinal()] = damageType.value();
    }

    public void setImmunity(DamageTypeBooleanPair[] damageTypes){
        for(DamageTypeBooleanPair damageType : damageTypes){
            this.immunity[damageType.damageType().ordinal()] = damageType.value();
        }
    }

    public void setCritImmune(boolean critImmune){
        this.critImmune = critImmune;
    }

    public void setImmuneToAll(boolean immuneToAll){
        this.immuneToAll = immuneToAll;
    }

    public boolean damage(DamageComponent damageComponent, double critRate){
        if(this.immuneToAll && this.hp == Double.MAX_VALUE){
            return false;
        }

        double multiplier = this.critImmune ? 1.0 : (critRate >= 100.0 ? 5.0*critRate/100.0 : (ThreadLocalRandom.current().nextDouble() < critRate/100.0 ? 5.0 : 1.0));

        int i = 0;
        double damage = 0.0;
        for(DamageType damageType : DamageType.values()){
            if(!this.immunity[i] && (damageComponent.getDamage(damageType) > 0.0 || damageType == DamageType.HEALING)){
                damage += multiplier*this.resistance[i]*damageComponent.getDamage(damageType);
            }
        }

        this.hp -= damage;
        if(this.hp <= 0.0){
            this.hp = 0.0;
            return true;
        }
        this.hp = Math.min(this.hp, this.maxHp);

        return false;
    }

    public boolean damage(DamageComponent damageComponent){
        return this.damage(damageComponent, 0.0);
    }

    public boolean trueDamage(double damage){
        if(this.hp == Double.MAX_VALUE){
            return false;
        }

        this.hp -= damage;
        if(this.hp <= 0.0){
            this.hp = 0.0;
            return true;
        }
        this.hp = Math.min(this.hp, this.maxHp);

        return false;
    }

    public void heal(){
        this.hp = this.maxHp;
    }
}