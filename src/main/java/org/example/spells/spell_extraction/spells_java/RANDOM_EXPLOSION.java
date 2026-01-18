package org.example.spells;

import org.example.main.*;

public class RANDOM_EXPLOSION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Chaos magic";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "random_explosion.png";
        //this.emote = "";
        this.description = "Makes a projectile launch a random spell (out of a limited selection) when it hits something";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.3, 0, 0.6, 1, 0, 0, 0, 0);
        this.price = 240;
        this.manaCost = 120;
        this.hasCharges = true;
        this.maxCharges = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "RANDOM_EXPLOSION",
		name 		= "$action_random_explosion",
		description = "$actiondesc_random_explosion",
		sprite 		= "data/ui_gfx/gun_actions/random_explosion.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		related_extra_entities = { "data/entities/misc/random_explosion.xml", "data/entities/particles/tinyspark_purple_bright.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,5,6", -- TRANSMUTATION
		spawn_probability                 = "0.3,0.6,1", -- TRANSMUTATION
		price = 240,
		mana = 120,
		max_uses = 30,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/random_explosion.xml,data/entities/particles/tinyspark_purple_bright.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 40
			draw_actions( 1, true )
		end,
	},
	},
*/
}
