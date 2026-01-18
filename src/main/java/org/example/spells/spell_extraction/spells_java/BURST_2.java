package org.example.spells;

import org.example.main.*;

public class BURST_2 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Double spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "burst_2.png";
        //this.emote = "";
        this.description = "Simultaneously casts 2 spells";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BURST_2",
		name 		= "$action_burst_2",
		description = "$actiondesc_burst_2",
		sprite 		= "data/ui_gfx/gun_actions/burst_2.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/burst_2_unidentified.png",
		type 		= ACTION_TYPE_DRAW_MANY,
		spawn_level                       = "0,1,2,3,4,5,6", -- BURST_2
		spawn_probability                 = "0.8,0.8,0.8,0.8,0.8,0.8,0.8", -- BURST_2
		price = 140,
		mana = 0,
		--max_uses = 100,
		action 		= function()
			draw_actions( 2, true )
		end,
	},
	},
*/
}
