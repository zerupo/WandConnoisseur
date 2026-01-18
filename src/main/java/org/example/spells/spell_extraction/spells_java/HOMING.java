package org.example.spells;

import org.example.main.*;

public class HOMING extends Spell{
    @Override
    protected void initialization(){
        this.name = "Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing.png";
        //this.emote = "";
        this.description = "Makes a projectile accelerate towards your foes";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING",
		name 		= "$action_homing",
		description = "$actiondesc_homing",
		sprite 		= "data/ui_gfx/gun_actions/homing.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5,6", -- HOMING
		spawn_probability                 = "0.1,0.4,0.4,0.4,0.4,0.4", -- HOMING
		price = 220,
		mana = 70,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing.xml,data/entities/particles/tinyspark_white.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
