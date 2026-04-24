package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_MATERIAL_FEUR;

import java.lang.invoke.MethodHandles;

public class MATERIAL_FEUR extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Feur";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "material_feur.png";
        this.emote = staticEmote;
        this.description = "Transmute drops of feur from nothing";
        this.type = SpellType.material;
        this.relatedProjectile = new PROJECTILE_MATERIAL_FEUR();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 110;
        this.manaCost = 0;
        this.castDelay = -15;
        this.rechargeTime = -10;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_apply_feur.xml,"
        castState.addProjectile(this.relatedProjectile.clone());
    }
}