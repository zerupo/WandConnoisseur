package org.example.spells;

import org.example.main.*;

public class LIGHT_BULLET_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spark bolt with timer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "light_bullet_timer.png";
        //this.emote = "";
        this.description = "A spark bolt that casts another spell after a timer runs out";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.5, 0.75, 0, 0, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIGHT_BULLET_TIMER",
		name 		= "$action_light_bullet_timer",
		description = "$actiondesc_light_bullet_timer",
		sprite 		= "data/ui_gfx/gun_actions/light_bullet_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/light_bullet_timer_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/light_bullet.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                         = "1,2,3", -- LIGHT_BULLET_TIMER
		spawn_probability                   = "0.5,0.5,0.75", -- LIGHT_BULLET_TIMER
		price = 140,
		mana = 10,
		--max_uses = 100,
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 3
			c.screenshake = c.screenshake + 0.5
			c.damage_critical_chance = c.damage_critical_chance + 5
			add_projectile_trigger_timer("data/entities/projectiles/deck/light_bullet.xml", 10, 1)
		end,
	},
	},
*/
}
