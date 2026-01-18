package org.example.spells;

import org.example.main.*;

public class BOUNCE_LASER_EMITTER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Plasma Beam Bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_laser_emitter.png";
        //this.emote = "";
        this.description = "A projectile launches a plasma beam upon bouncing";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.4, 0.8, 0.4, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_LASER_EMITTER",
		name 		= "$action_bounce_laser_emitter",
		description = "$actiondesc_bounce_laser_emitter",
		sprite 		= "data/ui_gfx/gun_actions/bounce_laser_emitter.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_laser_emitter.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,4,5", -- BOUNCE_SPARK
		spawn_probability                 = "0.4,0.8,0.4", -- BOUNCE_SPARK
		price = 180,
		mana = 40,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_laser_emitter.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 12
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 5.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
