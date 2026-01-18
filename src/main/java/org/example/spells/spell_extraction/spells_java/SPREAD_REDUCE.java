package org.example.spells;

import org.example.main.*;

public class SPREAD_REDUCE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Reduce spread";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spread_reduce.png";
        //this.emote = "";
        this.description = "Reduces the spread of a spell";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.8, 0.8, 0.8, 0.8, 0.7, 0.6, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SPREAD_REDUCE",
		name 		= "$action_spread_reduce",
		description = "$actiondesc_spread_reduce",
		sprite 		= "data/ui_gfx/gun_actions/spread_reduce.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5,6", -- SPREAD_REDUCE
		spawn_probability                 = "0.8,0.8,0.8,0.8,0.7,0.6", -- SPREAD_REDUCE
		price = 100,
		mana = 1,
		--max_uses = 150,
		action 		= function()
			c.spread_degrees = c.spread_degrees - 60.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
