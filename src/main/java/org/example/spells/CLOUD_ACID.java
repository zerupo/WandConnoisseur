package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_CLOUD_ACID;

import java.lang.invoke.MethodHandles;

public class CLOUD_ACID extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Acid cloud";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "cloud_acid.png";
        this.emote = staticEmote;
        this.description = "Creates an rain of acid";
        this.type = SpellType.static_projectile;
        this.relatedProjectile = new PROJECTILE_CLOUD_ACID();
        this.spawnProbabilities = new SpawnProbabilities(0.2, 0.2, 0.4, 0.2, 0.2, 0.5, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 90;
        this.hasCharges = true;
        this.maxCharges = 8;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "CLOUD_ACID",
	name 		= "$action_cloud_acid",
	description = "$actiondesc_cloud_acid",
	sprite 		= "data/ui_gfx/gun_actions/cloud_acid.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/cloud_water_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/cloud_acid.xml"},
	type 		= ACTION_TYPE_STATIC_PROJECTILE,
	spawn_level                       = "0,1,2,3,4,5", -- CLOUD_ACID
	spawn_probability                 = "0.2,0.2,0.4,0.2,0.2,0.5", -- CLOUD_ACID
	price = 180,
	mana = 90,
	max_uses = 8,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/cloud_acid.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/