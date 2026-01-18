package org.example.spells;

import org.example.main.*;

public class SEA_SWAMP extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Swamp";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "sea_swamp.png";
        //this.emote = "";
        this.description = "Summons a large swamp below the caster";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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
		id          = "SEA_SWAMP",
		name 		= "$action_sea_swamp",
		description = "$actiondesc_sea_swamp",
		sprite 		= "data/ui_gfx/gun_actions/sea_swamp.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sea_swamp_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/sea_swamp.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "0", -- SEA_SWAMP
		spawn_probability                 = "0", -- SEA_SWAMP
		price = 350,
		mana = 140,
		max_uses = 3,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/sea_swamp.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
