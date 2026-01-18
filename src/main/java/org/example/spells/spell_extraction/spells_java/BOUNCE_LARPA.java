package org.example.spells;

import org.example.main.*;

public class BOUNCE_LARPA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Larpa Bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_larpa.png";
        //this.emote = "";
        this.description = "A projectile will launch a copy of itself when it bounces";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.4, 0.6, 0.4, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 80;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_LARPA",
		name 		= "$action_bounce_larpa",
		description = "$actiondesc_bounce_larpa",
		sprite 		= "data/ui_gfx/gun_actions/bounce_larpa.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_larpa.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "4,5,6", -- BOUNCE_SPARK
		spawn_probability                 = "0.4,0.6,0.4", -- BOUNCE_SPARK
		price = 250,
		mana = 80,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_larpa.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 32
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
