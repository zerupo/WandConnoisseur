package org.example.spells;

import org.example.main.*;

public class ROCKET_TIER_3 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giant magic missile";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "rocket_tier_3.png";
        //this.emote = "";
        this.description = "The most powerful version of Magic missile";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.25, 0.5, 1, 1, 1, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 120;
        this.hasCharges = true;
        this.maxCharges = 6;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ROCKET_TIER_3",
		name 		= "$action_rocket_tier_3",
		description = "$actiondesc_rocket_tier_3",
		sprite 		= "data/ui_gfx/gun_actions/rocket_tier_3.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/rocket_tier_3.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- ROCKET_TIER_3
		spawn_probability                 = "0.25,0.5,1,1,1", -- ROCKET_TIER_3
		price = 250,
		mana = 120,
		max_uses    = 6, 
		custom_xml_file = "data/entities/misc/custom_cards/rocket_tier_3.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/rocket_tier_3.xml")
			c.fire_rate_wait = c.fire_rate_wait + 120
			--current_reload_time = current_reload_time + 40
			c.ragdoll_fx = 2
			shot_effects.recoil_knockback = 180.0
		end,
	},
	},
*/
}
