package org.example.spells;

import org.example.main.*;

public class SCATTER_2 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Double scatter spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "scatter_2.png";
        //this.emote = "";
        this.description = "Simultaneously casts 2 spells with low accuracy";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.8, 0.7, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{

		id          = "SCATTER_2",
		name 		= "$action_scatter_2",
		description = "$actiondesc_scatter_2",
		sprite 		= "data/ui_gfx/gun_actions/scatter_2.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/scatter_2_unidentified.png",
		type 		= ACTION_TYPE_DRAW_MANY,
		spawn_level                       = "0,1,2", -- SCATTER_2
		spawn_probability                 = "0.8,0.8,0.7", -- SCATTER_2
		price = 100,
		mana = 0,
		--max_uses = 100,
		action 		= function()
			draw_actions( 2, true )
			c.spread_degrees = c.spread_degrees + 10.0
		end,
	},
	},
*/
}
