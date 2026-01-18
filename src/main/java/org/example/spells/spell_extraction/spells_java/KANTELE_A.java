package org.example.spells;

import org.example.main.*;

public class KANTELE_A extends Spell{
    @Override
    protected void initialization(){
        this.name = "Kantele - note A";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "kantele_a.png";
        //this.emote = "";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 10;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "KANTELE_A",
		name 		= "$action_kantele_a",
		description = "$actiondesc_kantele_a",
		spawn_requires_flag = "card_unlocked_kantele",
		sprite 		= "data/ui_gfx/gun_actions/kantele_a.png",
		related_projectiles	= {"data/entities/projectiles/deck/kantele/kantele_a.xml"},
		type 		= ACTION_TYPE_OTHER,
		spawn_level                       = "10", -- OCARINA_A
		spawn_probability                 = "0", -- OCARINA_A
		price = 10,
		mana = 1,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/kantele/kantele_a.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
