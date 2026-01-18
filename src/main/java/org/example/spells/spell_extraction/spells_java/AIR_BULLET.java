package org.example.spells;

import org.example.main.*;

public class AIR_BULLET extends Spell{
    @Override
    protected void initialization(){
        this.name = "Burst of air";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "air_bullet.png";
        //this.emote = "";
        this.description = "A brittle burst of air capable of greatly pushing objects";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "AIR_BULLET",
		name 		= "$action_air_bullet",
		description = "$actiondesc_air_bullet",
		sprite 		= "data/ui_gfx/gun_actions/air_bullet.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/air_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/light_bullet_air.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2", -- AIR_BULLET
		spawn_probability                 = "1,1", -- AIR_BULLET
		price = 80,
		mana = 5,
		--max_uses = 120,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/light_bullet_air.xml")
			c.fire_rate_wait = c.fire_rate_wait + 3
			--c.screenshake = c.screenshake + 0.1
			c.spread_degrees = c.spread_degrees - 2.0
			--c.knockback_force = c.knockback_force + 2
		end,
	},
	},
*/
}
