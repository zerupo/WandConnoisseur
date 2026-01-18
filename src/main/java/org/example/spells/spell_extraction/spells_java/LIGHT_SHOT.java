package org.example.spells;

import org.example.main.*;

public class LIGHT_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Light shot";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "light_shot.png";
        //this.emote = "";
        this.description = "Makes a projectile move considerably faster";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.5, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 60;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIGHT_SHOT",
		name 		= "$action_light_shot",
		description = "$actiondesc_light_shot",
		sprite 		= "data/ui_gfx/gun_actions/light_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/heavy_shot_unidentified.png",
		related_extra_entities = { "data/entities/particles/light_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- LIGHT_SHOT
		spawn_probability                 = "0.3,0.5,0.4", -- LIGHT_SHOT
		price = 60,
		mana = 5,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/light_shot.xml",
		action 		= function()
			c.damage_projectile_add = c.damage_projectile_add - 1.0
			c.explosion_radius = c.explosion_radius - 10.0
			if (c.explosion_radius < 0) then
				c.explosion_radius = 0
			end
			c.fire_rate_wait    = c.fire_rate_wait - 3
			c.speed_multiplier = c.speed_multiplier * 7.5
			c.spread_degrees = c.spread_degrees - 6
			shot_effects.recoil_knockback = shot_effects.recoil_knockback - 10.0
			c.extra_entities = c.extra_entities .. "data/entities/particles/light_shot.xml,"
			
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
