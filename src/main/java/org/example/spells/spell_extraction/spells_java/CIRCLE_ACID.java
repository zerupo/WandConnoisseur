package org.example.spells;

import org.example.main.*;

public class CIRCLE_ACID extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of acid";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "circle_acid.png";
        //this.emote = "";
        this.description = "An expanding circle of dripping acid";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 40;
        this.hasCharges = true;
        this.maxCharges = 4;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CIRCLE_ACID",
		name 		= "$action_circle_acid",
		description = "$actiondesc_circle_acid",
		sprite 		= "data/ui_gfx/gun_actions/circle_acid.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/circle_acid.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4", -- CIRCLE_ACID
		spawn_probability                 = "0.4,0.4,0.4,0.4", -- CIRCLE_ACID
		price = 180,
		mana = 40,
		max_uses = 4,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/circle_acid.xml")
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
