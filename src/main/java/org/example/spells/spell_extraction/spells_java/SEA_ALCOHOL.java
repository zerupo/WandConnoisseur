package org.example.spells;

import org.example.main.*;

public class SEA_ALCOHOL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Sea of alcohol";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "sea_alcohol.png";
        //this.emote = "";
        this.description = "Summons a large body of tasty alcohol below the caster";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0, 0, 0, 0.5, 0.6, 0.3, 0, 0, 0, 0);
        this.price = 350;
        this.manaCost = 140;
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
		id          = "SEA_ALCOHOL",
		name 		= "$action_sea_alcohol",
		description = "$actiondesc_sea_alcohol",
		sprite 		= "data/ui_gfx/gun_actions/sea_alcohol.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sea_lava_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/sea_alcohol.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "0,4,5,6", -- SEA_ALCOHOL
		spawn_probability                 = "0.3,0.5,0.6,0.3", -- SEA_ALCOHOL
		price = 350,
		mana = 140,
		max_uses = 3,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/sea_alcohol.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
