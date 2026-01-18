package org.example.spells;

import org.example.main.*;

public class W_SHAPE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Formation - trifurcated";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "w_shape.png";
        //this.emote = "";
        this.description = "Casts 3 spells in a trifurcated pattern";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.3, 0.5, 0.3, 0.3, 0, 0, 0, 0);
        this.price = 50;
        this.manaCost = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "W_SHAPE",
		name 		= "$action_w_shape",
		description = "$actiondesc_w_shape",
		sprite 		= "data/ui_gfx/gun_actions/w_shape.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/w_shape_unidentified.png",
		type 		= ACTION_TYPE_DRAW_MANY,
		spawn_level                       = "2,3,4,5,6", -- W_SHAPE
		spawn_probability                 = "0.4,0.3,0.5,0.3,0.3", -- W_SHAPE
		price = 50,
		mana = 3,
		--max_uses = 100,
		action 		= function()
			draw_actions(3, true)
			c.pattern_degrees = 20
			c.spread_degrees = c.spread_degrees - 5.0
		end,
	},
	},
*/
}
