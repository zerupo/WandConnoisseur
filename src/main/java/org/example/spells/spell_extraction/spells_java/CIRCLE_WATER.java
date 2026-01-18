package org.example.spells;

import org.example.main.*;

public class CIRCLE_WATER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of water";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "circle_water.png";
        //this.emote = "";
        this.description = "An expanding circle of water";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 20;
        this.hasCharges = true;
        this.maxCharges = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CIRCLE_WATER",
		name 		= "$action_circle_water",
		description = "$actiondesc_circle_water",
		sprite 		= "data/ui_gfx/gun_actions/circle_water.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/circle_water.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4", -- CIRCLE_WATER
		spawn_probability                 = "0.4,0.4,0.4,0.4", -- CIRCLE_WATER
		price = 160,
		mana = 20,
		max_uses = 15,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/circle_water.xml")
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
