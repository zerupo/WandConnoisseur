package org.example.spells;

import org.example.main.*;

public class AVOIDING_ARC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Avoiding arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "avoiding_arc.png";
        //this.emote = "";
        this.description = "Makes a projectile shy away from obstacles";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0, 0.4, 0, 0.4, 0, 0, 0, 0);
        this.price = 30;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "AVOIDING_ARC",
		name 		= "$action_avoiding_arc",
		description = "$actiondesc_avoiding_arc",
		sprite 		= "data/ui_gfx/gun_actions/avoiding_arc.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/avoiding_arc.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4,6", -- AVOIDING_ARC
		spawn_probability                 = "0.5,0.4,0.4", -- AVOIDING_ARC
		price = 30,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/avoiding_arc.xml,"
			c.fire_rate_wait    = c.fire_rate_wait + 10
			draw_actions( 1, true )
		end,
	},
	},
*/
}
