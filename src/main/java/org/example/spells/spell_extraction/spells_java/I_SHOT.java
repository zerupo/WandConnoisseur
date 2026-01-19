package org.example.spells;

import org.example.main.*;

public class I_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Iplicate Spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "i_shot.png";
        //this.emote = "";
        this.description = "Casts a copy of a projectile behind you, but the copy still cost mana";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.2, 0.5, 0, 0, 0, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 40;
        this.hasCharges = true;
        this.maxCharges = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "I_SHOT",
		name 		= "$action_i_shot",
		description = "$actiondesc_i_shot",
		sprite 		= "data/ui_gfx/gun_actions/i_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/i_shape_unidentified.png",
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "1,2,3", -- I_SHAPE
		spawn_probability                 = "0.1,0.2,0.5", -- I_SHAPE
		price = 130,
		mana = 40,
		max_uses = 30,
		action 		= function()
			local data
			
			if ( #deck > 0 ) then
				data = deck[1]
			end
			
			if ( data ~= nil ) and ( ( data.type == ACTION_TYPE_PROJECTILE ) or ( data.type == ACTION_TYPE_STATIC_PROJECTILE ) ) and ( data.related_projectiles ~= nil ) and ( ( data.uses_remaining == nil ) or ( data.uses_remaining ~= 0 ) ) then
				local count = 2
				for i=1,count-1 do
					if ( mana >= data.mana ) then
						local proj = data.related_projectiles[1]
						local proj_count = data.related_projectiles[2] or 1
						
						for a=1,proj_count do
							add_projectile(proj)
						end
						
						mana = mana - data.mana
					else
						OnNotEnoughManaForAction()
						break
					end
				end
			end
			
			c.pattern_degrees = 180
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
