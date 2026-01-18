package org.example.spells;

import org.example.main.*;

public class PENTA_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Peplicate Spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "penta_shot.png";
        //this.emote = "";
        this.description = "Casts 5 copies of a projectile in a pentagonal pattern";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.1, 0.2, 0.5, 0.5, 0, 0, 0, 0.2);
        this.price = 250;
        this.manaCost = 110;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "PENTA_SHOT",
		name 		= "$action_penta_shot",
		description = "$actiondesc_penta_shot",
		sprite 		= "data/ui_gfx/gun_actions/penta_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/i_shape_unidentified.png",
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "3,4,5,6,10", -- I_SHAPE
		spawn_probability                 = "0.1,0.2,0.5,0.5,0.2", -- I_SHAPE
		price = 250,
		mana = 110,
		max_uses = 20,
		action 		= function()
			local data
			
			if ( #deck > 0 ) then
				data = deck[1]
			end
			
			if ( data ~= nil ) and ( ( data.type == ACTION_TYPE_PROJECTILE ) or ( data.type == ACTION_TYPE_STATIC_PROJECTILE ) ) and ( data.related_projectiles ~= nil ) and ( ( data.uses_remaining == nil ) or ( data.uses_remaining ~= 0 ) ) then
				local count = 5
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
