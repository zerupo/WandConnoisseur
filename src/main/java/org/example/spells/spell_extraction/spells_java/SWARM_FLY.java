package org.example.spells;

import org.example.main.*;

public class SWARM_FLY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon fly swarm";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "swarm_fly.png";
        //this.emote = "";
        this.description = "Summons five flies to aid you in battle";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0, 0.4, 0.5, 0, 0, 0, 0, 0);
        this.price = 90;
        this.manaCost = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SWARM_FLY",
		name 		= "$action_swarm_fly",
		description = "$actiondesc_swarm_fly",
		sprite 		= "data/ui_gfx/gun_actions/swarm_fly.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spiral_shot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/swarm_fly.xml",5},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,4,5", -- SPIRAL_SHOT
		spawn_probability                 = "0.3,0.4,0.5", -- SPIRAL_SHOT
		price = 90,
		mana = 60,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/swarm_fly.xml")
			add_projectile("data/entities/projectiles/deck/swarm_fly.xml")
			add_projectile("data/entities/projectiles/deck/swarm_fly.xml")
			add_projectile("data/entities/projectiles/deck/swarm_fly.xml")
			c.spread_degrees = c.spread_degrees + 6.0
			c.fire_rate_wait = c.fire_rate_wait + 60
			current_reload_time = current_reload_time + 20
		end,
	},
	},
*/
}
