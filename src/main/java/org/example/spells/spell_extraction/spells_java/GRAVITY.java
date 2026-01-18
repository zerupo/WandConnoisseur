package org.example.spells;

import org.example.main.*;

public class GRAVITY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Gravity";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "gravity.png";
        //this.emote = "";
        this.description = "Increases the effect gravity has on a projectile";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.4, 0.4, 0.3, 0.3, 0, 0, 0, 0);
        this.price = 50;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "GRAVITY",
		name 		= "$action_gravity",
		description = "$actiondesc_gravity",
		sprite 		= "data/ui_gfx/gun_actions/gravity.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/w_shape_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- GRAVITY
		spawn_probability                 = "0.5,0.4,0.4,0.3,0.3", -- GRAVITY
		price = 50,
		mana = 1,
		--max_uses = 100,
		action 		= function()
			c.gravity = c.gravity + 600.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
