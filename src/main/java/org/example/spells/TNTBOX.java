package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_TNTBOX;

import java.lang.invoke.MethodHandles;

public class TNTBOX extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Summon Explosive Box";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "tntbox.png";
        this.emote = staticEmote;
        this.description = "Summons a box of explosive matter";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_TNTBOX();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.8, 0.9, 0.5, 0, 0.4, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 40;
        this.hasCharges = true;
        this.maxCharges = 15;
        this.castDelay = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "TNTBOX",
	name 		= "$action_tntbox",
	description = "$actiondesc_tntbox",
	sprite 		= "data/ui_gfx/gun_actions/tntbox.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/tntbox.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3,5", -- SUMMON_ROCK
	spawn_probability                 = "0.8,0.9,0.5,0.4", -- SUMMON_ROCK
	price = 150,
	mana = 40,
	max_uses    = 15,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/tntbox.xml")
		c.fire_rate_wait = c.fire_rate_wait + 30
	end,
}*/