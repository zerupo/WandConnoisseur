package org.example.spells;

import org.example.main.*;

public class ALPHA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Alpha";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "alpha.png";
        //this.emote = "";
        this.description = "Casts a copy of the first spell in your wand";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.2, 0, 0, 0, 1);
        this.price = 200;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ALPHA",
		name 		= "$action_alpha",
		description = "$actiondesc_alpha",
		sprite 		= "data/ui_gfx/gun_actions/alpha.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_duplicate",
		type 		= ACTION_TYPE_OTHER,
		recursive	= true,
		spawn_level                       = "5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.1,0.2,1", -- MANA_REDUCE
		price = 200,
		mana = 40,
		action 		= function( recursion_level, iteration )
			c.fire_rate_wait = c.fire_rate_wait + 15
			
			local data = {}
			
			if ( #discarded > 0 ) then
				data = discarded[1]
			elseif ( #hand > 0 ) then
				data = hand[1]
			elseif ( #deck > 0 ) then
				data = deck[1]
			else
				data = nil
			end
			
			local rec = check_recursion( data, recursion_level )
			
			if ( data ~= nil ) and ( rec > -1 ) then
				data.action( rec )
			end
			
			--draw_actions( 1, true )
		end,
	},
	},
*/
}
