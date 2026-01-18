package org.example.spells;

import org.example.main.*;

public class KNOCKBACK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Knockback";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "knockback.png";
        //this.emote = "";
        this.description = "Gives a projectile the power to knock back the foes it hits";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.7, 0, 0.6, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "KNOCKBACK",
		name 		= "$action_knockback",
		description = "$actiondesc_knockback",
		sprite 		= "data/ui_gfx/gun_actions/knockback.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/knockback_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,5", -- KNOCKBACK
		spawn_probability                 = "0.7,0.6", -- KNOCKBACK
		price = 100,
		mana = 5,
		--max_uses = 150,
		action 		= function()
			c.knockback_force = c.knockback_force + 5
			draw_actions( 1, true )
		end,
	},
	},
*/
}
