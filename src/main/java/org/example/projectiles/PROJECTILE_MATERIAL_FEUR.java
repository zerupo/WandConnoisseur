package org.example.projectiles;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class PROJECTILE_MATERIAL_FEUR extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Feur";
        this.imageFile = "material_feur.png";
        this.emote = staticEmote;
        this.airFriction = 3.0;

        this.speedMin = 123;
        this.speedMax = 135;
        this.lifetime = 360;
        this.lifetimeRandomness = 7;
    }
}