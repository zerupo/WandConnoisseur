package org.example.spells;

import org.example.main.*;

public class FLY_DOWNWARDS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Fly downwards";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fly_downwards.png";
        //this.emote = "";
        this.description = "Causes a projectile to aim straight downwards a short time after casting";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0, 0.45, 0, 0.3, 0, 0, 0, 0, 0);
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
		id          = "FLY_DOWNWARDS",
		name 		= "$action_fly_downwards",
		description = "$actiondesc_fly_downwards",
		sprite 		= "data/ui_gfx/gun_actions/fly_downwards.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/fly_downwards.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,5", -- FLY_DOWNWARDS
		spawn_probability                 = "0.3,0.45,0.3", -- FLY_DOWNWARDS
		price = 30,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/fly_downwards.xml,"
			draw_actions( 1, true )
			c.fire_rate_wait    = c.fire_rate_wait - 8
			c.speed_multiplier	= c.speed_multiplier * 1.2
		end,
	},
	},
*/
}
