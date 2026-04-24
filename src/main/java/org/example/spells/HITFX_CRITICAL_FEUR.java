package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;

import java.lang.invoke.MethodHandles;

public class HITFX_CRITICAL_FEUR extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Critical on feur enemies";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "crit on quoi", "crit on feur"};
        this.imageFile = "critical_feur.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile always do a critical hit on feur enemies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.2, 0.4, 0.2, 0, 0, 0, 0, 0);
        this.price = 70;
        this.manaCost = 10;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_critical_feur.xml,"
        cardPool.draw(1, true, castState);
    }
}