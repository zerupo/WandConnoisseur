package org.example.spells;

import org.example.main.*;

public class NOLLA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Nolla";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "nolla.png";
        //this.emote = "";
        this.description = "The duration of a projectile is set to zero";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.2, 0.5, 0.5, 0, 0, 0, 1);
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
		id          = "NOLLA",
		name 		= "$action_nolla",
		description = "$actiondesc_nolla",
		sprite 		= "data/ui_gfx/gun_actions/nolla.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_pyramid",
		related_extra_entities = { "data/entities/misc/nolla.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4,5,6,10", -- LIFETIME_DOWN
		spawn_probability                 = "0.2,0.2,0.5,0.5,1", -- LIFETIME_DOWN
		price = 50,
		mana = 1,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/nolla.xml,"
			c.fire_rate_wait = c.fire_rate_wait - 15
			draw_actions( 1, true )
		end,
	},
	},
*/
}
