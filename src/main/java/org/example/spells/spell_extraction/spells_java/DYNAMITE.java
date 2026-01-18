package org.example.spells;

import org.example.main.*;

public class DYNAMITE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Dynamite";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "dynamite.png";
        //this.emote = "";
        this.description = "Summons a small explosive";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 0.9, 0.8, 0.7, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 16;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DYNAMITE",
		name 		= "$action_dynamite",
		description = "$actiondesc_dynamite",
		sprite 		= "data/ui_gfx/gun_actions/dynamite.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/dynamite_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/tnt.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4", -- DYNAMITE
		spawn_probability                 = "1,0.9,0.8,0.7,0.6", -- DYNAMITE
		price = 160,
		mana = 50,
		max_uses    = 16,
		custom_xml_file = "data/entities/misc/custom_cards/tnt.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/tnt.xml")
			c.fire_rate_wait = c.fire_rate_wait + 50
			c.spread_degrees = c.spread_degrees + 6.0
		end,
	},
	},
*/
}
