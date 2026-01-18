package org.example.spells;

import org.example.main.*;

public class ENERGY_SHIELD_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Projectile energy shield";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "energy_shield_shot.png";
        //this.emote = "";
        this.description = "Gives a projectile a shield that deflects other projectiles";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.5, 0.4, 0.3, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ENERGY_SHIELD_SHOT",
		name 		= "$action_energy_shield_shot",
		description = "$actiondesc_energy_shield_shot",
		sprite 		= "data/ui_gfx/gun_actions/energy_shield_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/energy_shield_shot_unidentified.png",
		related_extra_entities = { "data/entities/misc/energy_shield_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- ENERGY_SHIELD_SHOT
		spawn_probability                 = "0.3,0.3,0.5,0.4,0.3", -- ENERGY_SHIELD_SHOT
		price = 180,
		mana = 5,
		action 		= function()
			c.speed_multiplier = c.speed_multiplier * 0.4
			c.extra_entities = c.extra_entities .. "data/entities/misc/energy_shield_shot.xml,"
			
			if ( c.speed_multiplier >= 20 ) then
				c.speed_multiplier = math.min( c.speed_multiplier, 20 )
			elseif ( c.speed_multiplier < 0 ) then
				c.speed_multiplier = 0
			end
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
