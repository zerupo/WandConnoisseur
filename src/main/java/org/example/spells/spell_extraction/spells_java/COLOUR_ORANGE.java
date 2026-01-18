package org.example.spells;

import org.example.main.*;

public class COLOUR_ORANGE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Orange Glimmer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "colour_orange.png";
        //this.emote = "";
        this.description = "Gives a projectile a orange sparkly trail";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.1, 0.4, 0, 0, 0, 0, 0, 0);
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
		id          = "COLOUR_ORANGE",
		name 		= "$action_colour_orange",
		description = "$actiondesc_colour_orange",
		sprite 		= "data/ui_gfx/gun_actions/colour_orange.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/particles/tinyspark_red.xml", "data/entities/misc/colour_orange.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- HOMING
		spawn_probability                 = "0.1,0.1,0.4", -- HOMING
		spawn_requires_flag = "card_unlocked_paint",
		price = 40,
		mana = 0,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/particles/tinyspark_red.xml,data/entities/misc/colour_orange.xml,"
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
