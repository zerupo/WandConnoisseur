package org.example.spells;

import org.example.main.*;

public class COLOUR_RAINBOW extends Spell{
    @Override
    protected void initialization(){
        this.name = "Rainbow Glimmer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "colour_rainbow.png";
        //this.emote = "";
        this.description = "Gives a projectile a randomly colored sparkly trail";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.1, 0.1, 0, 0, 0, 0, 0, 0.2);
        this.price = 40;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "COLOUR_RAINBOW",
		name 		= "$action_colour_rainbow",
		description = "$actiondesc_colour_rainbow",
		sprite 		= "data/ui_gfx/gun_actions/colour_rainbow.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/particles/tinyspark_red.xml", "data/entities/misc/colour_rainbow.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,10", -- HOMING
		spawn_probability                 = "0.1,0.1,0.1,0.2", -- HOMING
		spawn_requires_flag = "card_unlocked_paint",
		price = 40,
		mana = 0,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/particles/tinyspark_red.xml,data/entities/misc/colour_rainbow.xml,"
			c.fire_rate_wait = c.fire_rate_wait - 8
			c.screenshake = c.screenshake - 2.5
			if ( c.screenshake < 0 ) then
				c.screenshake = 0
			end
			draw_actions( 1, true )
		end,
	},
	},
*/
}
