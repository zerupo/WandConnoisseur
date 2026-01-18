package org.example.spells;

import org.example.main.*;

public class ACCELERATING_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Accelerating shot";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "accelerating_shot.png";
        //this.emote = "";
        this.description = "Causes a projectile to accelerate as it flies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.4, 1, 0, 0, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ACCELERATING_SHOT",
		name 		= "$action_accelerating_shot",
		description = "$actiondesc_accelerating_shot",
		sprite 		= "data/ui_gfx/gun_actions/accelerating_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/heavy_shot_unidentified.png",
		related_extra_entities = { "data/entities/misc/accelerating_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- ACCELERATING_SHOT
		spawn_probability                 = "0.5,0.4,1", -- ACCELERATING_SHOT
		price = 190,
		mana = 20,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/accelerating_shot.xml",
		action 		= function()
			c.fire_rate_wait    = c.fire_rate_wait + 8
			c.speed_multiplier = c.speed_multiplier * 0.32
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			c.extra_entities = c.extra_entities .. "data/entities/misc/accelerating_shot.xml,"
			
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
