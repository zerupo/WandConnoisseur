package org.example.spells;

import org.example.main.*;

public class GRAVITY_ANTI extends Spell{
    @Override
    protected void initialization(){
        this.name = "Anti-gravity";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "gravity_anti.png";
        //this.emote = "";
        this.description = "Applies a lifting force to a projectile";
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
		id          = "GRAVITY_ANTI",
		name 		= "$action_gravity_anti",
		description = "$actiondesc_gravity_anti",
		sprite 		= "data/ui_gfx/gun_actions/gravity_anti.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/w_shape_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- GRAVITY_ANTI
		spawn_probability                 = "0.5,0.4,0.4,0.3,0.3", -- GRAVITY_ANTI
		price = 50,
		mana = 1,
		--max_uses = 100,
		action 		= function()
			c.gravity = c.gravity - 600.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
