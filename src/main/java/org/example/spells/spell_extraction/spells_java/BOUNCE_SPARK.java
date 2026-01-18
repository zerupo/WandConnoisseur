package org.example.spells;

import org.example.main.*;

public class BOUNCE_SPARK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Bubbly bounce";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bounce_spark.png";
        //this.emote = "";
        this.description = "Makes a projectile shoot bubble sparks as it bounces";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.6, 0.6, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCE_SPARK",
		name 		= "$action_bounce_spark",
		description = "$actiondesc_bounce_spark",
		sprite 		= "data/ui_gfx/gun_actions/bounce_spark.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/bounce_spark.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4", -- BOUNCE_SPARK
		spawn_probability                 = "0.2,0.6,0.6,0.6", -- BOUNCE_SPARK
		price = 120,
		mana = 20,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/bounce_spark.xml,"
			c.bounces = c.bounces + 1
			c.fire_rate_wait = c.fire_rate_wait + 8
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 5.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
