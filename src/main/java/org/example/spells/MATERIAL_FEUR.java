package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_MATERIAL_FEUR;
import org.example.script.Script;
import org.example.script.SCRIPT_EFFECT_APPLY_FEURED;

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
        this.relatedScripts = new Script[]{new SCRIPT_EFFECT_APPLY_FEURED()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 110;
        this.manaCost = 0;
        this.castDelay = -15;
        this.rechargeTime = -10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}