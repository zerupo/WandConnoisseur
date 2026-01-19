package org.example.spells;

import org.example.main.*;

public class SINEWAVE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Slithering path";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "sinewave.png";
        //this.emote = "";
        this.description = "Makes a projectile move rapidly in a slithering manner, like a snake";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0, 0.55, 0, 0.4, 0, 0, 0, 0);
        this.price = 10;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "PENETRATE_WALLS",
		name 		= "$action_penetrate_walls",
		description = "$actiondesc_penetrate_walls",
		sprite 		= "data/ui_gfx/gun_actions/penetrate_walls.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "", -- PENETRATE_WALLS
		spawn_probability                        = "", -- PENETRATE_WALLS
		price = 100,
		action 		= function()
			penetration_power = penetration_power + 1
		end,
	},]]--
		id          = "SINEWAVE",
		name 		= "$action_sinewave",
		description = "$actiondesc_sinewave",
		sprite 		= "data/ui_gfx/gun_actions/sinewave.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/sinewave.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4,6", -- SINEWAVE
		spawn_probability                 = "0.4,0.55,0.4", -- SINEWAVE
		price = 10,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/sinewave.xml,"
			c.speed_multiplier = c.speed_multiplier * 2
			
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
