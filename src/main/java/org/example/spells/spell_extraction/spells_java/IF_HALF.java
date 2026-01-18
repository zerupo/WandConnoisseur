package org.example.spells;

import org.example.main.*;

public class IF_HALF extends Spell{
    @Override
    protected void initialization(){
        this.name = "Requirement - Every Other";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "if_half.png";
        //this.emote = "";
        this.description = "The next spell is skipped every other time this spell is cast";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
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
		id          = "IF_HALF",
		name 		= "$action_if_half",
		description = "$actiondesc_if_half",
		sprite 		= "data/ui_gfx/gun_actions/if_half.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_maths",
		type 		= ACTION_TYPE_OTHER,
		spawn_level                       = "10", -- MANA_REDUCE
		spawn_probability                 = "1", -- MANA_REDUCE
		price = 100,
		mana = 0,
		action 		= function( recursion_level, iteration )
			local endpoint = -1
			local elsepoint = -1
			local doskip = false
			
			if ( reflecting == false ) then
				local status = tonumber( GlobalsGetValue( "GUN_ACTION_IF_HALF_STATUS", "0" ) ) or 0
				
				if ( status == 1 ) then
					doskip = true
				end
				
				status = 1 - status
				GlobalsSetValue( "GUN_ACTION_IF_HALF_STATUS", tostring( status ) )
			end
			
			if ( #deck > 0 ) then
				for i,v in ipairs( deck ) do
					if ( v ~= nil ) then
						if ( string.sub( v.id, 1, 3 ) == "IF_" ) and ( v.id ~= "IF_END" ) and ( v.id ~= "IF_ELSE" ) then
							endpoint = -1
							break
						end
						
						if ( v.id == "IF_ELSE" ) then
							endpoint = i
							elsepoint = i
						end
						
						if ( v.id == "IF_END" ) then
							endpoint = i
							break
						end
					end
				end
				
				local envelope_min = 1
				local envelope_max = 1
				
				if doskip then
					if ( elsepoint > 0 ) then
						envelope_max = elsepoint
					elseif ( endpoint > 0 ) then
						envelope_max = endpoint
					end
					
					for i=envelope_min,envelope_max do
						local v = deck[envelope_min]
					
						if ( v ~= nil ) then
							table.insert( discarded, v )
							table.remove( deck, envelope_min )
						end
					end
				else
					if ( elsepoint > 0 ) then
						envelope_min = elsepoint
						
						if ( endpoint > 0 ) then
							envelope_max = endpoint
						else
							envelope_max = #deck
						end
						
						for i=envelope_min,envelope_max do
							local v = deck[envelope_min]
							
							if ( v ~= nil ) then
								table.insert( discarded, v )
								table.remove( deck, envelope_min )
							end
						end
					end
				end
			end
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
