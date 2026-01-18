package org.example.spells;

import org.example.main.*;

public class SEA_MIMIC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Sea of Mimicium";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "sea_mimic.png";
        //this.emote = "";
        this.description = "Summons a large body of mimicium under the caster";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0.05, 0, 0, 0, 0.05, 0.1, 0.1, 0, 0, 0, 0.2);
        this.price = 350;
        this.manaCost = 140;
        this.hasCharges = true;
        this.maxCharges = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SEA_MIMIC",
		name 		= "$action_sea_mimic",
		description = "$actiondesc_sea_mimic",
		sprite 		= "data/ui_gfx/gun_actions/sea_mimic.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sea_acid_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/sea_mimic.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "0,4,5,6,10", -- SEA_MIMIC
		spawn_probability                 = "0.05,0.05,0.1,0.1,0.2", -- SEA_MIMIC
		spawn_requires_flag = "card_unlocked_sea_mimic",
		price = 350,
		mana = 140,
		max_uses = 2,
		never_unlimited = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/sea_mimic.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
