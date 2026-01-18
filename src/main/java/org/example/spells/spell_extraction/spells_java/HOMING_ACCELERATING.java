package org.example.spells;

import org.example.main.*;

public class HOMING_ACCELERATING extends Spell{
    @Override
    protected void initialization(){
        this.name = "Accelerative Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_accelerating.png";
        //this.emote = "";
        this.description = "A projectile homes towards enemies at an increasing pace";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.3, 0.3, 0.5, 0, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_ACCELERATING",
		name 		= "$action_homing_accelerating",
		description = "$actiondesc_homing_accelerating",
		sprite 		= "data/ui_gfx/gun_actions/homing_accelerating.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing_accelerating.xml", "data/entities/particles/tinyspark_white_small.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4", -- HOMING
		spawn_probability                 = "0.1,0.3,0.3,0.5", -- HOMING
		price = 180,
		mana = 60,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_accelerating.xml,data/entities/particles/tinyspark_white_small.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
