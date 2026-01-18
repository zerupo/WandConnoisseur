package org.example.spells;

import org.example.main.*;

public class CLOUD_WATER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Rain cloud";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "cloud_water.png";
        //this.emote = "";
        this.description = "Creates a watery weather phenomenon";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.2, 0.3, 0.4, 0.4, 0.3, 0.2, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 30;
        this.hasCharges = true;
        this.maxCharges = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CLOUD_WATER",
		name 		= "$action_cloud_water",
		description = "$actiondesc_cloud_water",
		sprite 		= "data/ui_gfx/gun_actions/cloud_water.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/cloud_water_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/cloud_water.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5", -- CLOUD_WATER
		spawn_probability                 = "0.2,0.3,0.4,0.4,0.3,0.2", -- CLOUD_WATER
		price = 140,
		mana = 30,
		max_uses = 10,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/cloud_water.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
