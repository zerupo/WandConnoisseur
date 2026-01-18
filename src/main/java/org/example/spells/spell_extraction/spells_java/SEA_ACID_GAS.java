package org.example.spells;

import org.example.main.*;

public class SEA_ACID_GAS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Sea of flammable gas";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "sea_acid_gas.png";
        //this.emote = "";
        this.description = "Summons a large body of flammable gas below the caster";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0, 0, 0, 0.4, 0.4, 0.3, 0, 0, 0, 0);
        this.price = 200;
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
		id          = "SEA_ACID_GAS",
		name 		= "$action_sea_acid_gas",
		description = "$actiondesc_sea_acid_gas",
		sprite 		= "data/ui_gfx/gun_actions/sea_acid_gas.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sea_acid_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/sea_acid_gas.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "0,4,5,6", -- SEA_ACID_GAS
		spawn_probability                 = "0.3,0.4,0.4,0.3", -- SEA_ACID_GAS
		price = 200,
		mana = 140,
		max_uses = 3,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/sea_acid_gas.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
