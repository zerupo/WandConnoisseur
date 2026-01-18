package org.example.spells;

import org.example.main.*;

public class T_SHAPE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Formation - above and below";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "t_shape.png";
        //this.emote = "";
        this.description = "Casts 3 spells - ahead";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.5, 0.4, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 30;
        this.manaCost = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "T_SHAPE",
		name 		= "$action_t_shape",
		description = "$actiondesc_t_shape",
		sprite 		= "data/ui_gfx/gun_actions/t_shape.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/t_shape_unidentified.png",
		type 		= ACTION_TYPE_DRAW_MANY,
		spawn_level                       = "1,2,3,4", -- T_SHAPE
		spawn_probability                 = "0.4,0.5,0.4,0.3", -- T_SHAPE
		price = 30,
		mana = 3,
		--max_uses = 100,
		action 		= function()
			draw_actions(3, true)
			c.pattern_degrees = 90
			c.spread_degrees = c.spread_degrees - 8.0
		end,
	},
	},
*/
}
