package org.example.spells;

import org.example.main.*;

public class OCARINA_B extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - note B";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "ocarina_b.png";
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
		id          = "OCARINA_B",
		name 		= "$action_ocarina_b",
		description = "$actiondesc_ocarina_b",
		spawn_requires_flag = "card_unlocked_ocarina",
		sprite 		= "data/ui_gfx/gun_actions/ocarina_b.png",
		related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_b.xml"},
		type 		= ACTION_TYPE_OTHER,
		spawn_level                       = "10", -- OCARINA_B
		spawn_probability                 = "0", -- OCARINA_B
		price = 10,
		mana = 1,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/ocarina/ocarina_b.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
