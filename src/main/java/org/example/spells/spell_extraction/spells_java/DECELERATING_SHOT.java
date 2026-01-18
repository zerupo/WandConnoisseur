package org.example.spells;

import org.example.main.*;

public class DECELERATING_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Decelerating shot";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "decelerating_shot.png";
        //this.emote = "";
        this.description = "Makes a projectile decelerate as it flies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.1, 0.7, 0, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DECELERATING_SHOT",
		name 		= "$action_decelerating_shot",
		description = "$actiondesc_decelerating_shot",
		sprite 		= "data/ui_gfx/gun_actions/decelerating_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/heavy_shot_unidentified.png",
		related_extra_entities = { "data/entities/misc/decelerating_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- ACCELERATING_SHOT
		spawn_probability                 = "0.4,0.1,0.7", -- ACCELERATING_SHOT
		price = 80,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/decelerating_shot.xml",
		action 		= function()
			c.fire_rate_wait    = c.fire_rate_wait - 8
			c.speed_multiplier = c.speed_multiplier * 1.68
			shot_effects.recoil_knockback = shot_effects.recoil_knockback - 10.0
			c.extra_entities = c.extra_entities .. "data/entities/misc/decelerating_shot.xml,"
			
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
