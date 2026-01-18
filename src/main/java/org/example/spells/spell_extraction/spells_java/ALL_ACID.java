package org.example.spells;

import org.example.main.*;

public class ALL_ACID extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spells to acid";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "all_acid.png";
        //this.emote = "";
        this.description = "Transforms every projectile currently in the air into a pool of acid";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.1, 0, 0.05, 0, 0, 0, 1);
        this.price = 600;
        this.manaCost = 200;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ALL_ACID",
		name 		= "$action_all_acid",
		description = "$actiondesc_all_acid",
		sprite 		= "data/ui_gfx/gun_actions/all_acid.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		spawn_requires_flag = "card_unlocked_alchemy",
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "4,6,10", -- DESTRUCTION
		spawn_probability                 = "0.1,0.05,1", -- DESTRUCTION
		price = 600,
		mana = 200,
		--max_uses    = 15, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/all_acid.xml")
			c.fire_rate_wait = c.fire_rate_wait + 100
			current_reload_time = current_reload_time + 100
		end,
	},
	},
*/
}
