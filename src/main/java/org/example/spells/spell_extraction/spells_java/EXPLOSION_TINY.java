package org.example.spells;

import org.example.main.*;

public class EXPLOSION_TINY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Concentrated Explosion";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "explosion_tiny.png";
        //this.emote = "";
        this.description = "Limits the radius of a projectile's explosion heavily";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.6, 0.7, 0.2, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "EXPLOSION_TINY",
		name 		= "$action_explosion_tiny",
		description = "$actiondesc_explosion_tiny",
		sprite 		= "data/ui_gfx/gun_actions/explosion_tiny.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		related_extra_entities = { "data/entities/misc/explosion_tiny.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4,5,6", -- LIFETIME_DOWN
		spawn_probability                 = "0.2,0.6,0.7,0.2", -- LIFETIME_DOWN
		price = 160,
		mana = 40,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/explosion_tiny.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 15
			c.explosion_radius = c.explosion_radius - 30.0
			c.damage_explosion_add = c.damage_explosion_add + 0.8
			draw_actions( 1, true )
		end,
	},
	},
*/
}
