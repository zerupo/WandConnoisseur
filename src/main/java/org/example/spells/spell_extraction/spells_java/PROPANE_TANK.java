package org.example.spells;

import org.example.main.*;

public class PROPANE_TANK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Propane tank";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "propane_tank.png";
        //this.emote = "";
        this.description = "Summons a propane tank. Be careful what you wish for.";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 1, 1, 0.8, 0.8, 0.7, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 75;
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
		id          = "PROPANE_TANK",
		name 		= "$action_propane_tank",
		description = "$actiondesc_propane_tank",
		sprite 		= "data/ui_gfx/gun_actions/propane_tank.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/propane_tank.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- PROPANE_TANK
		spawn_probability                 = "1,1,0.8,0.8,0.7", -- PROPANE_TANK
		price = 200,
		mana = 75, 
		max_uses    = 10, 
		custom_xml_file = "data/entities/misc/custom_cards/propane_tank.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/propane_tank.xml")
			c.fire_rate_wait = c.fire_rate_wait + 100
		end,
	},
	},
*/
}
