package org.example.spells;

import org.example.main.*;

public class CLOUD_THUNDER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Thundercloud";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "cloud_thunder.png";
        //this.emote = "";
        this.description = "Creates a stormy cloud";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0.3, 0.2, 0.3, 0.4, 0.5, 0, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 90;
        this.hasCharges = true;
        this.maxCharges = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CLOUD_THUNDER",
		name 		= "$action_cloud_thunder",
		description = "$actiondesc_cloud_thunder",
		sprite 		= "data/ui_gfx/gun_actions/cloud_thunder.png",
		spawn_requires_flag = "card_unlocked_cloud_thunder",
		sprite_unidentified = "data/ui_gfx/gun_actions/cloud_water_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/cloud_thunder.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5", -- CLOUD_THUNDER
		spawn_probability                 = "0.3,0.3,0.2,0.3,0.4,0.5", -- CLOUD_THUNDER
		price = 190,
		mana = 90,
		max_uses = 5,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/cloud_thunder.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
		end,
	},
	},
*/
}
