package org.example.spells;

import org.example.main.*;

public class EXPLODING_DUCKS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Flock of Ducks";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "duck_2.png";
        //this.emote = "";
        this.description = "Summons a chaotic flock of spicy ducks";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.8, 0.5, 0.6, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 100;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "EXPLODING_DUCKS",
		name 		= "$action_exploding_ducks",
		description = "$actiondesc_exploding_ducks",
		spawn_requires_flag = "card_unlocked_exploding_deer",
		sprite 		= "data/ui_gfx/gun_actions/duck_2.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/exploding_deer_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/duck.xml", 3},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "3,4,5", -- EXPLODING_DEER
		spawn_probability                 = "0.8,0.5,0.6", -- EXPLODING_DEER
		price = 200,
		mana = 100,
		max_uses    = 20, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/duck.xml")
			add_projectile("data/entities/projectiles/deck/duck.xml")
			add_projectile("data/entities/projectiles/deck/duck.xml")
			c.fire_rate_wait = c.fire_rate_wait + 60
			current_reload_time = current_reload_time + 20
		end,
	},
	},
*/
}
