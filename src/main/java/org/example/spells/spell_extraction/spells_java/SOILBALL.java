package org.example.spells;

import org.example.main.*;

public class SOILBALL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Chunk of soil";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "soil.png";
        //this.emote = "";
        this.description = "Don't soil yourself";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.8, 0.8, 1, 0, 0.75, 0, 0, 0, 0, 0);
        this.price = 10;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SOILBALL",
		name 		= "$action_soilball",
		description = "$actiondesc_soilball",
		sprite 		= "data/ui_gfx/gun_actions/soil.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/firebomb_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/chunk_of_soil.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,5", -- SOILBALL
		spawn_probability                 = "0.8,0.8,1,0.75", -- SOILBALL
		price = 10,
		mana = 5,
		action 		= function()
			add_projectile("data/entities/projectiles/chunk_of_soil.xml")
		end,
	},
	},
*/
}
