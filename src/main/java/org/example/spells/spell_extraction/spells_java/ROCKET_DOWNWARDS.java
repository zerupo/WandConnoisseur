package org.example.spells;

import org.example.main.*;

public class ROCKET_DOWNWARDS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Downwards bolt bundle";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "rocket_downwards.png";
        //this.emote = "";
        this.description = "Makes a projectile separate into a bundle of 5 explosive bolts as soon as it moves downwards";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.5, 0.7, 0.7, 0, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 90;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ROCKET_DOWNWARDS",
		name 		= "$action_rocket_downwards",
		description = "$actiondesc_rocket_downwards",
		sprite 		= "data/ui_gfx/gun_actions/rocket_downwards.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/rocket_downwards.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4", -- ROCKET_DOWNWARDS
		spawn_probability                 = "0.2,0.5,0.7,0.7", -- ROCKET_DOWNWARDS
		price = 200,
		mana = 90,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/rocket_downwards.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 25
			draw_actions( 1, true )
		end,
	},
	},
*/
}
