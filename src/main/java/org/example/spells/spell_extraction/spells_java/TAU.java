package org.example.spells;

import org.example.main.*;

public class TAU extends Spell{
    @Override
    protected void initialization(){
        this.name = "Tau";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "tau.png";
        //this.emote = "";
        this.description = "Copies the two following spells in the wand when cast";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.2, 0, 0, 0, 1);
        this.price = 200;
        this.manaCost = 90;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TAU",
		name 		= "$action_tau",
		description = "$actiondesc_tau",
		sprite 		= "data/ui_gfx/gun_actions/tau.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_duplicate",
		type 		= ACTION_TYPE_OTHER,
		recursive	= true,
		spawn_level                       = "5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.1,0.2,1", -- MANA_REDUCE
		price = 200,
		mana = 90,
		action 		= function( recursion_level, iteration )
			c.fire_rate_wait = c.fire_rate_wait + 35
			
			local data1 = {}
			local data2 = {}
			
			local s1 = ""
			local s2 = ""
			
			if ( #deck > 0 ) then
				s1 = "deck"
				data1 = deck[1]
			else
				data1 = nil
			end
			
			if ( #deck > 0 ) then
				s2 = "deck 2"
				data2 = deck[2]
			else
				data2 = nil
			end
			
			local rec1 = check_recursion( data1, recursion_level )
			local rec2 = check_recursion( data2, recursion_level )
			
			if ( data1 ~= nil ) and ( rec1 > -1 ) then
				-- print("1: " .. tostring(data1.id) .. ", " .. s1)
				data1.action( rec1 )
			end
			
			if ( data2 ~= nil ) and ( rec2 > -1 ) then
				-- print("2: " .. tostring(data2.id) .. ", " .. s2)
				data2.action( rec2 )
			end
			
			--draw_actions( 1, true )
		end,
	},
	},
*/
}
