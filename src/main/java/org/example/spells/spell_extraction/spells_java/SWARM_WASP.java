package org.example.spells;

import org.example.main.*;

public class SWARM_WASP extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Wasp swarm";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "swarm_wasp.png";
        //this.emote = "";
        this.description = "Summon six wasps to aid you in battle";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.2, 0.5, 0.6, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 80;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SWARM_WASP",
		name 		= "$action_swarm_wasp",
		description = "$actiondesc_swarm_wasp",
		sprite 		= "data/ui_gfx/gun_actions/swarm_wasp.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spiral_shot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/swarm_wasp.xml",6},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "4,5,6", -- SPIRAL_SHOT
		spawn_probability                 = "0.2,0.5,0.6", -- SPIRAL_SHOT
		price = 120,
		mana = 80,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/swarm_wasp.xml")
			add_projectile("data/entities/projectiles/deck/swarm_wasp.xml")
			add_projectile("data/entities/projectiles/deck/swarm_wasp.xml")
			add_projectile("data/entities/projectiles/deck/swarm_wasp.xml")
			add_projectile("data/entities/projectiles/deck/swarm_wasp.xml")
			c.spread_degrees = c.spread_degrees + 24.0
			c.fire_rate_wait = c.fire_rate_wait + 60
			current_reload_time = current_reload_time + 20
		end,
	},
	},
*/
}
