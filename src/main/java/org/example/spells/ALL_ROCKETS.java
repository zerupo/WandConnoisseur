package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_ALL_ROCKETS;

import java.lang.invoke.MethodHandles;

public class ALL_ROCKETS extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Spells to magic missiles";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "all_rockets.png";
        this.emote = staticEmote;
        this.description = "Transforms every projectile currently in the air into a magic missile";
        this.type = SpellType.utility;
        this.relatedProjectile = new PROJECTILE_ALL_ROCKETS();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0, 0, 0, 0, 0.05, 0, 0, 0, 1);
        this.price = 400;
        this.manaCost = 100;
        this.hasCharges = true;
        this.maxCharges = 10;
        this.neverUnlimited = true;
        this.castDelay = 50;
        this.rechargeTime = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "ALL_ROCKETS",
	name 		= "$action_all_rockets",
	description = "$actiondesc_all_rockets",
	sprite 		= "data/ui_gfx/gun_actions/all_rockets.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
	spawn_requires_flag = "card_unlocked_alchemy",
	never_unlimited		= true,
	type 		= ACTION_TYPE_UTILITY,
	spawn_level                       = "1,6,10", -- DESTRUCTION
	spawn_probability                 = "0.1,0.05,1", -- DESTRUCTION
	price = 400,
	mana = 100,
	max_uses    = 10,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/all_rockets.xml")
		c.fire_rate_wait = c.fire_rate_wait + 50
		current_reload_time = current_reload_time + 50
	end,
}*/