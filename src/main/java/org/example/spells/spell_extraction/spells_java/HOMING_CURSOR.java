package org.example.spells;

import org.example.main.*;

public class HOMING_CURSOR extends Spell{
    @Override
    protected void initialization(){
        this.name = "Aiming Arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_cursor.png";
        //this.emote = "";
        this.description = "A projectile rotates towards the direction you're aiming";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.7, 0.7, 0.4, 0.4, 1, 0, 0, 0, 0);
        this.price = 175;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_CURSOR",
		name 		= "$action_homing_cursor",
		description = "$actiondesc_homing_cursor",
		sprite 		= "data/ui_gfx/gun_actions/homing_cursor.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing_cursor.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- HOMING_ROTATE
		spawn_probability                 = "0.7,0.7,0.4,0.4,1", -- HOMING_ROTATE
		price = 175,
		mana = 30,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_cursor.xml,data/entities/particles/tinyspark_white.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
