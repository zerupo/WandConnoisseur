package org.example.spells;

import org.example.main.*;

public class HOMING_WAND extends Spell{
    @Override
    protected void initialization(){
        this.name = "Wand Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_wand.png";
        //this.emote = "";
        this.description = "Makes a projectile accelerate towards wands";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.00001, 0.08, 0, 0.08, 0.25, 0.25, 0, 0, 0, 0.2);
        this.price = 500;
        this.manaCost = 200;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_WAND",
		name 		= "$action_homing_wand",
		description = "$actiondesc_homing_wand",
		sprite 		= "data/ui_gfx/gun_actions/homing_wand.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		spawn_requires_flag = "card_unlocked_homing_wand",
		related_extra_entities = { "data/entities/misc/homing_wand.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,4,5,6,10", -- HOMING_WAND
		spawn_probability                 = "0.00001,0.08,0.08,0.25,0.25,0.2", -- SUMMON_WANDGHOST
		price = 500,
		mana = 200,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_wand.xml,data/entities/particles/tinyspark_white.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
