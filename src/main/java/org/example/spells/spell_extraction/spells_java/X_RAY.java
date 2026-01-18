package org.example.spells;

import org.example.main.*;

public class X_RAY extends Spell{
    @Override
    protected void initialization(){
        this.name = "All-seeing eye";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "x_ray.png";
        //this.emote = "";
        this.description = "See into the unexplored. But not everywhere...";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 1, 1, 0.7, 0.5, 0.3, 0.2, 0, 0, 0, 0);
        this.price = 230;
        this.manaCost = 100;
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
		id          = "X_RAY",
		name 		= "$action_x_ray",
		description = "$actiondesc_x_ray",
		sprite 		= "data/ui_gfx/gun_actions/x_ray.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/x_ray_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/xray.xml"},
		type 		= ACTION_TYPE_UTILITY,
		spawn_level       = "0,1,2,3,4,5,6", -- X_RAY
		spawn_probability = "0.8,1,1,0.7,0.5,0.3,0.2", -- X_RAY
		price = 230,
		max_uses    = 10,
		mana = 100,
		custom_xml_file = "data/entities/misc/custom_cards/xray.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/xray.xml")
		end,
	},
	},
*/
}
