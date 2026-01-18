package org.example.spells;

import org.example.main.*;

public class BOUNCE_SMALL_EXPLOSION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Sparkly bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_small_explosion.png";
        //this.emote = "";
        this.description = "Makes a projectile release damaging sparks as it bounces";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0.3, 0.3, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_SMALL_EXPLOSION",
		name 		= "$action_bounce_small_explosion",
		description = "$actiondesc_bounce_small_explosion",
		sprite 		= "data/ui_gfx/gun_actions/bounce_small_explosion.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_small_explosion.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "0,1,2", -- BOUNCE_EXPLOSION
		spawn_probability                 = "0.5,0.3,0.3", -- BOUNCE_EXPLOSION
		price = 100,
		mana = 10,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_small_explosion.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 9
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
