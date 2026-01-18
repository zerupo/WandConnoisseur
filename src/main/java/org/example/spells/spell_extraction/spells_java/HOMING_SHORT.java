package org.example.spells;

import org.example.main.*;

public class HOMING_SHORT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Short-range Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_short.png";
        //this.emote = "";
        this.description = "A projectile flies towards targets when near them";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.8, 1, 0.4, 0.3, 0.1, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_SHORT",
		name 		= "$action_homing_short",
		description = "$actiondesc_homing_short",
		sprite 		= "data/ui_gfx/gun_actions/homing_short.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing_short.xml", "data/entities/particles/tinyspark_white_weak.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5,6", -- HOMING
		spawn_probability                 = "0.4,0.8,1,0.4,0.3,0.1", -- HOMING
		price = 160,
		mana = 40,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_short.xml,data/entities/particles/tinyspark_white_weak.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
