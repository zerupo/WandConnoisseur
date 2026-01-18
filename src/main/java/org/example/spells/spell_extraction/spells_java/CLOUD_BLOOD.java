package org.example.spells;

import org.example.main.*;

public class CLOUD_BLOOD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Blood cloud";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "cloud_blood.png";
        //this.emote = "";
        this.description = "Creates a rain of blood";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.2, 0.3, 0.3, 0.4, 0.3, 0.2, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 60;
        this.hasCharges = true;
        this.maxCharges = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CLOUD_BLOOD",
		name 		= "$action_cloud_blood",
		description = "$actiondesc_cloud_blood",
		sprite 		= "data/ui_gfx/gun_actions/cloud_blood.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/cloud_water_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/cloud_blood.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5", -- CLOUD_BLOOD
		spawn_probability                 = "0.2,0.3,0.3,0.4,0.3,0.2", -- CLOUD_BLOOD
		price = 200,
		mana = 60,
		max_uses = 3,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/cloud_blood.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
		end,
	},
	},
*/
}
