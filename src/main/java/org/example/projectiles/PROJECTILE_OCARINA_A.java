package org.example.projectiles;

public class PROJECTILE_OCARINA_A extends Projectile{
    @Override
    protected void initialization(){
        this.gravityY = 0;
        this.airFriction = 8;
        this.mass = 0.01;

        this.speedMin = 250;
        this.speedMax = 450;
        this.lifetime = 2;
        this.lifetimeRandomness = 0;
    }
}