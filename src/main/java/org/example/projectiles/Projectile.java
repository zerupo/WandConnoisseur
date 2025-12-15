package org.example.projectiles;

import org.example.main.CastState;
import org.example.main.DamageComponent;

public abstract class Projectile{
    public enum TriggerType {none, trigger, timer, expiration};
    protected TriggerType triggerType = TriggerType.none;
    protected int timer = 0;
    protected CastState triggerCastState = null;

    // velocity component
    protected double gravityX = 0.0;
    protected double gravityY = 400.0;
    protected double mass = 0.05;
    protected double airFriction = 0.55;
    protected double terminalVelocity = 1000;
    protected boolean applyTerminalVelocity = true;
    protected boolean updateVelocity = true;
    protected boolean displaceLiquid = true;
    protected boolean affectPhysicsBodies = false;
    protected boolean limitToMaxVelocity = true;
    protected int liquidDeathThreshold = 0;
    protected double liquidDrag = 1.0;

    // projectile component
    protected int lifetime = -1;
    protected int lifetimeRandomness = 0;
    protected double initialSpeed = 0;
    protected double speedMin = 60;
    protected double speedMax = 60;
    protected double spreadRad = 0;
    protected double friction = 0;
    protected DamageComponent damageComponent = new DamageComponent();
    protected boolean explodeOnDeath = false;
    protected boolean collideWithWorld = true;
    protected boolean onLifetimeOutExplode = false;
    protected boolean explosionSelfDamage = true;
    protected boolean onCollisionDie = true;
    protected double knockbackForce = 0.0;

    public Projectile(){
        this.initialization();
    }

    public Projectile clone(){
        Class<? extends Projectile> projectileClass = this.getClass();
        Projectile newProjectile;

        try{
            newProjectile = projectileClass.newInstance();
        }catch(Exception e){
            return null;
        }

        newProjectile.triggerType = this.triggerType;
        newProjectile.timer = this.timer;
        newProjectile.triggerCastState = this.triggerCastState;

        // velocity component
        newProjectile.gravityX = this.gravityX;
        newProjectile.gravityY = this.gravityY;
        newProjectile.mass = this.mass;
        newProjectile.airFriction = this.airFriction;
        newProjectile.terminalVelocity = this.terminalVelocity;
        newProjectile.applyTerminalVelocity = this.applyTerminalVelocity;
        newProjectile.updateVelocity = this.updateVelocity;
        newProjectile.displaceLiquid = this.displaceLiquid;
        newProjectile.affectPhysicsBodies = this.affectPhysicsBodies;
        newProjectile.limitToMaxVelocity = this.limitToMaxVelocity;
        newProjectile.liquidDeathThreshold = this.liquidDeathThreshold;
        newProjectile.liquidDrag = this.liquidDrag;

        // projectile component
        newProjectile.lifetime = this.lifetime;
        newProjectile.lifetimeRandomness = this.lifetimeRandomness;
        newProjectile.initialSpeed = this.initialSpeed;
        newProjectile.speedMin = this.speedMin;
        newProjectile.speedMax = this.speedMax;
        newProjectile.spreadRad = this.spreadRad;
        newProjectile.friction = this.friction;
        newProjectile.damageComponent = new DamageComponent(this.damageComponent);
        newProjectile.explodeOnDeath = this.explodeOnDeath;
        newProjectile.collideWithWorld = this.collideWithWorld;
        newProjectile.onLifetimeOutExplode = this.onLifetimeOutExplode;
        newProjectile.explosionSelfDamage = this.explosionSelfDamage;
        newProjectile.onCollisionDie = this.onCollisionDie;
        newProjectile.knockbackForce = this.knockbackForce;

        return newProjectile;
    }

    public boolean addTrigger(TriggerType triggerType, int timer, CastState castState){
        if(triggerType == TriggerType.none){
            this.triggerCastState = null;
        }else{
            if(castState == null){
                return false;
            }
            this.triggerCastState = castState;
        }
        this.triggerType = triggerType;
        if(triggerType == TriggerType.timer){
            this.timer = timer;
        }else{
            this.timer = 0;
        }

        return true;
    }

    public boolean addTrigger(TriggerType triggerType, CastState castState){
        return addTrigger(triggerType, 0, castState);
    }

    public TriggerType getTriggerType(){
        return this.triggerType;
    }

    public int getTimer(){
        return this.timer;
    }

    public CastState getTriggerCastState(){
        return this.triggerCastState;
    }

    public int getLifetimeMin(){
        return this.lifetime - this.lifetimeRandomness;
    }

    public int getLifetimeMax(){
        return this.lifetime + this.lifetimeRandomness;
    }


    protected abstract void initialization();
}