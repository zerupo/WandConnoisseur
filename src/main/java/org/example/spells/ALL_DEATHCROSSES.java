package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_ALL_DEATHCROSSES;

import java.lang.invoke.MethodHandles;

public class ALL_DEATHCROSSES extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Spells to death crosses";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "all_deathcrosses.png";
        this.emote = staticEmote;
        this.description = "Transforms every projectile currently in the air into a death cross";
        this.type = SpellType.utility;
        this.relatedProjectile = new PROJECTILE_ALL_DEATHCROSSES();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0, 0, 0, 0.05, 0, 0, 0, 1);
        this.price = 350;
        this.manaCost = 80;
        this.hasCharges = true;
        this.maxCharges = 15;
        this.neverUnlimited = true;
        this.castDelay = 40;
        this.rechargeTime = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "ALL_DEATHCROSSES",
	name 		= "$action_all_deathcrosses",
	description = "$actiondesc_all_deathcrosses",
	sprite 		= "data/ui_gfx/gun_actions/all_deathcrosses.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
	spawn_requires_flag = "card_unlocked_alchemy",
	never_unlimited		= true,
	type 		= ACTION_TYPE_UTILITY,
	spawn_level                       = "2,6,10", -- DESTRUCTION
	spawn_probability                 = "0.1,0.05,1", -- DESTRUCTION
	price = 350,
	mana = 80,
	max_uses    = 15,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/all_deathcrosses.xml")
		c.fire_rate_wait = c.fire_rate_wait + 40
		current_reload_time = current_reload_time + 40
	end,
}*/