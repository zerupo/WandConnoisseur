package org.example.spells;

import org.example.main.*;

public class BOUNCE_LASER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Laser bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_laser.png";
        //this.emote = "";
        this.description = "Makes a projectile release a bundle of concentrated light as it bounces";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.4, 0.8, 0.4, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_LASER",
		name 		= "$action_bounce_laser",
		description = "$actiondesc_bounce_laser",
		sprite 		= "data/ui_gfx/gun_actions/bounce_laser.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_laser.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,4,5", -- BOUNCE_SPARK
		spawn_probability                 = "0.4,0.8,0.4", -- BOUNCE_SPARK
		price = 180,
		mana = 30,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_laser.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 12
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 5.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
