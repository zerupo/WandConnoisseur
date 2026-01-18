package org.example.spells;

import org.example.main.*;

public class RANDOM_PROJECTILE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Random projectile spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "random_projectile.png";
        //this.emote = "";
        this.description = "Casts one random projectile spell";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.4, 0.1, 0.2, 0, 0, 0, 0.5);
        this.price = 150;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "RANDOM_PROJECTILE",
		name 		= "$action_random_projectile",
		description = "$actiondesc_random_projectile",
		sprite 		= "data/ui_gfx/gun_actions/random_projectile.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_pyramid",
		type 		= ACTION_TYPE_PROJECTILE,
		recursive	= true,
		spawn_level                       = "2,4,5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.2,0.4,0.1,0.2,0.5", -- MANA_REDUCE
		price = 150,
		mana = 20,
		action 		= function( recursion_level, iteration )
			SetRandomSeed( GameGetFrameNum() + #deck, GameGetFrameNum() + 203 )
			local rnd = Random( 1, #actions )
			local data = actions[rnd]
			
			local safety = 0
			local rec = check_recursion( data, recursion_level )
			local flag = data.spawn_requires_flag
			local usable = true
			if ( flag ~= nil ) and ( HasFlagPersistent( flag ) == false ) then
				usable = false
			end
			
			while ( safety < 100 ) and ( ( data.type ~= 0 ) or ( rec == -1 ) or ( usable == false ) ) do
				rnd = Random( 1, #actions )
				data = actions[rnd]
				rec = check_recursion( data, recursion_level )
				flag = data.spawn_requires_flag
				usable = true
				if ( flag ~= nil ) and ( HasFlagPersistent( flag ) == false ) then
					usable = false
				end
				
				safety = safety + 1
			end
			
			data.action( rec )
		end,
	},
	},
*/
}
