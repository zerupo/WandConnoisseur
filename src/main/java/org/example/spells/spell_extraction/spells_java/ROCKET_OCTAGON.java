package org.example.spells;

import org.example.main.*;

public class ROCKET_OCTAGON extends Spell{
    @Override
    protected void initialization(){
        this.name = "Octagonal bolt bundle";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "rocket_octagon.png";
        //this.emote = "";
        this.description = "Makes a projectile launch 8 magical bolts if it moves slowly enough";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.6, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 100;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ROCKET_OCTAGON",
		name 		= "$action_rocket_octagon",
		description = "$actiondesc_rocket_octagon",
		sprite 		= "data/ui_gfx/gun_actions/rocket_octagon.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/rocket_octagon.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- ROCKET_DOWNWARDS
		spawn_probability                 = "0.5,0.6,0.3", -- ROCKET_DOWNWARDS
		price = 200,
		mana = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/rocket_octagon.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
