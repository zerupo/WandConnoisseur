package org.example.main;

import org.example.projectiles.Projectile;

import java.util.ArrayList;

class CastStateProjectile{
    private Projectile projectile;
    private int count;

    public CastStateProjectile(Projectile projectile){
        this.projectile = projectile;
        this.count = 1;
    }

    public CastStateProjectile(Projectile projectile, int count){
        this.projectile = projectile;
        this.count = count;
    }

    // getters
    public Projectile getProjectile(){
        return this.projectile;
    }

    public int getCount(){
        return this.count;
    }

    // setters
    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void addCount(){
        this.count++;
    }
}

public class CastState{
    private ArrayList<CastStateProjectile> castStateProjectiles = new ArrayList<CastStateProjectile>();
    private int castDelay = 0;
    private DamageComponent damageComponent = new DamageComponent();
    private int lifetime = 0;
    private int critRate = 0;
    private int patern = 0;
    private double spread = 0;

    public CastState(){
        // nothing
    }

    // getters
    public CastStateProjectile[] getCastStateProjectile(){
        return this.castStateProjectiles.toArray(new CastStateProjectile[0]);
    }

    public int getCastDelay(){
        return this.castDelay;
    }

    public DamageComponent getDamageComponent(){
        return this.damageComponent;
    }

    public int getLifetime(){
        return this.lifetime;
    }

    public int getCritRate(){
        return this.critRate;
    }

    public int getPatern(){
        return this.patern;
    }

    public double getSpread(){
        return this.spread;
    }

    // setters
    public void setCastDelay(int castDelay){
        this.castDelay = castDelay;
    }

    public void setDamageComponent(DamageComponent damageComponent){
        this.damageComponent = damageComponent;
    }

    public void setLifetime(int lifetime){
        this.lifetime = lifetime;
    }

    public void setCritRate(int critRate){
        this.critRate = critRate;
    }

    public void setPatern(int patern){
        this.patern = patern;
    }

    public void setSpread(double spread){
        this.spread = spread;
    }

    // adders
    public void addCastDelay(int castDelay){
        this.castDelay += castDelay;
    }

    public void addDamageComponent(DamageComponent damageComponent){
        this.damageComponent.add(damageComponent);
    }

    public void addLifetime(int lifetime){
        this.lifetime += lifetime;
    }

    public void addCritRate(int critRate){
        this.critRate += critRate;
    }

    public void addPatern(int patern){
        this.patern += patern;
    }

    public void addSpread(double spread){
        this.spread += spread;
    }

    public void addProjectile(Projectile projectile){
        if(projectile.getTriggerType() != Projectile.TriggerType.none){
            for(int i=0; i < this.castStateProjectiles.size(); i++){
                if(this.castStateProjectiles.get(i).getProjectile().getClass() == projectile.getClass()){
                    this.castStateProjectiles.get(i).addCount();
                    return;
                }
            }
        }
        this.castStateProjectiles.add(new CastStateProjectile(projectile));
    }
}