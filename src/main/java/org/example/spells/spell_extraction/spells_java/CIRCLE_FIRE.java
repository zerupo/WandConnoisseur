package org.example.spells;

import org.example.main.*;

public class CIRCLE_FIRE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of fire";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "circle_fire.png";
        //this.emote = "";
        this.description = "An expanding circle of burning air";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 170;
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
		id          = "CIRCLE_FIRE",
		name 		= "$action_circle_fire",
		description = "$actiondesc_circle_fire",
		sprite 		= "data/ui_gfx/gun_actions/circle_fire.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/circle_fire.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4", -- CIRCLE_FIRE
		spawn_probability                 = "0.4,0.4,0.4,0.4", -- CIRCLE_FIRE
		price = 170,
		mana = 20,
		max_uses = 15,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/circle_fire.xml")
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
