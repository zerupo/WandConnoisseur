package org.example.spells;

import org.example.main.*;

public class SLOW_BULLET_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Energy orb with a timer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "slow_bullet_timer.png";
        //this.emote = "";
        this.description = "A slow but powerful orb of energy that casts another spell after a timer runs out";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.5, 0.5, 0.5, 1, 1, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SLOW_BULLET_TIMER",
		name 		= "$action_slow_bullet_timer",
		description = "$actiondesc_slow_bullet_timer",
		sprite 		= "data/ui_gfx/gun_actions/slow_bullet_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slow_bullet_timer_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bullet_slow.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5,6", -- SLOW_BULLET_TIMER
		spawn_probability                 = "0.5,0.5,0.5,0.5,1,1", -- SLOW_BULLET_TIMER
		price = 200,
		mana = 50,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/bullet_slow.xml",
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 6
			c.screenshake = c.screenshake + 2
			c.spread_degrees = c.spread_degrees + 3.6
			add_projectile_trigger_timer("data/entities/projectiles/deck/bullet_slow.xml", 100, 1)
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 20.0
		end,
	},
	},
*/
}
