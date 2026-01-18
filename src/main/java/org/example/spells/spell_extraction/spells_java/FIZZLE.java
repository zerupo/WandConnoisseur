package org.example.spells;

import org.example.main.*;

public class FIZZLE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Fizzle";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fizzle.png";
        //this.emote = "";
        this.description = "Gives a spell a small probability of short-circuiting";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.4, 0.3, 0.1, 0, 0, 0, 0, 0);
        this.price = 0;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FIZZLE",
		name 		= "$action_fizzle",
		description = "$actiondesc_fizzle",
		sprite 		= "data/ui_gfx/gun_actions/fizzle.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/fizzle.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,4,5", -- CHAOTIC_ARC
		spawn_probability                 = "0.4,0.3,0.1", -- CHAOTIC_ARC
		price = 0,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/fizzle.xml,"
			c.speed_multiplier = c.speed_multiplier * 1.2
			c.fire_rate_wait = c.fire_rate_wait - 10
			
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
