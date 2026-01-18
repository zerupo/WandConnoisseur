package org.example.spells;

import org.example.main.*;

public class CHAOTIC_ARC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Chaotic path";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "chaotic_arc.png";
        //this.emote = "";
        this.description = "Causes a projectile to chaotically fly wherever it wishes";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0, 0.55, 0, 0.4, 0, 0, 0, 0, 0);
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
		id          = "CHAOTIC_ARC",
		name 		= "$action_chaotic_arc",
		description = "$actiondesc_chaotic_arc",
		sprite 		= "data/ui_gfx/gun_actions/chaotic_arc.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/chaotic_arc.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,5", -- CHAOTIC_ARC
		spawn_probability                 = "0.4,0.55,0.4", -- CHAOTIC_ARC
		price = 10,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/chaotic_arc.xml,"
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
