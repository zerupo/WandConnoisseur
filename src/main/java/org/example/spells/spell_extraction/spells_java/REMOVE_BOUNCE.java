package org.example.spells;

import org.example.main.*;

public class REMOVE_BOUNCE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Remove Bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "remove_bounce.png";
        //this.emote = "";
        this.description = "A normally bouncy projectile stops doing so";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.2, 1, 1, 0, 0, 0, 0, 0);
        this.price = 50;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "REMOVE_BOUNCE",
		name 		= "$action_remove_bounce",
		description = "$actiondesc_remove_bounce",
		sprite 		= "data/ui_gfx/gun_actions/remove_bounce.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bounce_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5", -- BOUNCE
		spawn_probability                 = "0.2,0.2,1,1", -- BOUNCE
		price = 50,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/remove_bounce.xml,"
			c.bounces = 0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
