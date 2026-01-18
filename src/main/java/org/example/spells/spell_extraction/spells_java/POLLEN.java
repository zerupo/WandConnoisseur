package org.example.spells;

import org.example.main.*;

public class POLLEN extends Spell{
    @Override
    protected void initialization(){
        this.name = "Pollen";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "pollen.png";
        //this.emote = "";
        this.description = "A small";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.6, 1, 0, 1, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 110;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "POLLEN",
		name 		= "$action_pollen",
		description = "$actiondesc_pollen",
		sprite 		= "data/ui_gfx/gun_actions/pollen.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/arrow_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/pollen.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,3,4", -- ARROW
		spawn_probability                 = "0.6,1,1,0.6", -- ARROW
		price = 110,
		mana = 10,
		--max_uses = 40,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/pollen.xml")
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 2
			c.spread_degrees = c.spread_degrees + 20
		end,
	},
	},
*/
}
