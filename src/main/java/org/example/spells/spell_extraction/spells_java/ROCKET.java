package org.example.spells;

import org.example.main.*;

public class ROCKET extends Spell{
    @Override
    protected void initialization(){
        this.name = "Magic missile";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "rocket.png";
        //this.emote = "";
        this.description = "A fiery";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 1, 1, 0.5, 0.3, 0, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 70;
        this.hasCharges = true;
        this.maxCharges = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ROCKET",
		name 		= "$action_rocket",
		description = "$actiondesc_rocket",
		sprite 		= "data/ui_gfx/gun_actions/rocket.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/rocket.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5", -- ROCKET
		spawn_probability                 = "1,1,1,0.5,0.3", -- ROCKET
		price = 220,
		mana = 70,
		max_uses    = 10, 
		custom_xml_file = "data/entities/misc/custom_cards/rocket.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/rocket.xml")
			c.fire_rate_wait = c.fire_rate_wait + 60
			--current_reload_time = current_reload_time + 40
			c.ragdoll_fx = 2
			shot_effects.recoil_knockback = 120.0
		end,
	},
	},
*/
}
