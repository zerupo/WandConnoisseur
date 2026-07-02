package org.example.projectiles;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class PROJECTILE_CASTER_CAST extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public PROJECTILE_CASTER_CAST(){
        this.name = "Inner spell";
        this.imageFile = "caster_cast.png";
        this.emote = staticEmote;
    }
}

// no xml