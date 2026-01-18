package org.example.spells;

import org.example.main.*;

public class BOUNCE_HOLE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Vacuum bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_hole.png";
        //this.emote = "";
        this.description = "Makes a projectile remove earth as it bounces";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0, 0.4, 0, 0.4, 0, 0, 0, 0.1);
        this.price = 220;
        this.manaCost = 60;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_HOLE",
		name 		= "$action_bounce_hole",
		description = "$actiondesc_bounce_hole",
		sprite 		= "data/ui_gfx/gun_actions/bounce_hole.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_hole.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4,6,10", -- BOUNCE_EXPLOSION
		spawn_probability                 = "0.1,0.4,0.4,0.1", -- BOUNCE_EXPLOSION
		price = 220,
		mana = 60,
		max_uses = 20,
		never_unlimited = true,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_hole.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 40
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
