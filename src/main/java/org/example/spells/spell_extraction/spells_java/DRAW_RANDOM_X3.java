package org.example.spells;

import org.example.main.*;

public class DRAW_RANDOM_X3 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Copy random spell thrice";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "draw_random_x3.png";
        //this.emote = "";
        this.description = "Casts a random spell among the spells in your wand three times!";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.1, 0.3, 0.1, 0.1, 0, 0, 0, 1);
        this.price = 250;
        this.manaCost = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DRAW_RANDOM_X3",
		name 		= "$action_draw_random_x3",
		description = "$actiondesc_draw_random_x3",
		sprite 		= "data/ui_gfx/gun_actions/draw_random_x3.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_pyramid",
		type 		= ACTION_TYPE_OTHER,
		recursive	= true,
		spawn_level                       = "3,4,5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.1,0.3,0.1,0.1,1", -- MANA_REDUCE
		price = 250,
		mana = 50,
		action 		= function( recursion_level, iteration )
			SetRandomSeed( GameGetFrameNum() + #deck, GameGetFrameNum() - 325 + #discarded )
			local datasize = #deck + #discarded
			local rnd = Random( 1, datasize )
			
			local data = {}
				
			if ( rnd <= #deck ) then
				data = deck[rnd]
			else
				data = discarded[rnd - #deck]
			end
			
			local checks = 0
			local rec = check_recursion( data, recursion_level )
			
			while ( data ~= nil ) and ( ( rec == -1 ) or ( ( data.uses_remaining ~= nil ) and ( data.uses_remaining == 0 ) ) ) and ( checks < datasize ) do
				rnd = ( rnd % datasize ) + 1
				checks = checks + 1
				
				if ( rnd <= #deck ) then
					data = deck[rnd]
				else
					data = discarded[rnd - #deck]
				end
				
				rec = check_recursion( data, recursion_level )
			end
			
			if ( data ~= nil ) and ( rec > -1 ) and ( ( data.uses_remaining == nil ) or ( data.uses_remaining ~= 0 ) ) then
				for i=1,3 do
					data.action( rec )
				end
				
				if ( data.uses_remaining ~= nil ) and ( data.uses_remaining > 0 ) then
					data.uses_remaining = data.uses_remaining - 1
					
					local reduce_uses = ActionUsesRemainingChanged( data.inventoryitem_id, data.uses_remaining )
					if not reduce_uses then
						data.uses_remaining = data.uses_remaining + 1 -- cancel the reduction
					end
				end
			end
		end,
	},
	},
*/
}
