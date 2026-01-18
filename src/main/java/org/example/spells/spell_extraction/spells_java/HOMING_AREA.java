package org.example.spells;

import org.example.main.*;

public class HOMING_AREA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Projectile Area Teleport";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_area.png";
        //this.emote = "";
        this.description = "If a valid target appears somewhere in the proximity of a projectile";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.4, 0.6, 0.7, 0.4, 0, 0, 0, 0);
        this.price = 175;
        this.manaCost = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_AREA",
		name 		= "$action_homing_area",
		description = "$actiondesc_homing_area",
		sprite 		= "data/ui_gfx/gun_actions/homing_area.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing_area.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- HOMING_ROTATE
		spawn_probability                 = "0.2,0.4,0.6,0.7,0.4", -- HOMING_ROTATE
		price = 175,
		mana = 60,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_area.xml,data/entities/particles/tinyspark_white.xml,"
			c.fire_rate_wait    = c.fire_rate_wait + 8
			c.spread_degrees = c.spread_degrees + 6
			c.speed_multiplier	= c.speed_multiplier * 0.75
			
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
