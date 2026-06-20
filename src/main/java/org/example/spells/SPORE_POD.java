package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_SPORE_POD;

import java.lang.invoke.MethodHandles;

public class SPORE_POD extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Prickly Spore Pod";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "mushroom", "shroom"};
        this.imageFile = "spore_pod.png";
        this.emote = staticEmote;
        this.description = "Summons a spore pod that attaches to a surface and then grows and explodes into spikes";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_SPORE_POD();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.7, 0.8, 0.9, 0.8, 0.6, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 20;
        this.castDelay = 40;
        this.recoil = 30.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "SPORE_POD",
	name 		= "$action_spore_pod",
	description = "$actiondesc_spore_pod",
	sprite 		= "data/ui_gfx/gun_actions/spore_pod.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spore_pod_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/spore_pod.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3,4,5", -- SPORE_POD
	spawn_probability                 = "0.7,0.8,0.9,0.8,0.6", -- SPORE_POD
	price = 200,
	mana = 20,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/spore_pod.xml")
		c.fire_rate_wait = c.fire_rate_wait + 40
		shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
	end,
}*/