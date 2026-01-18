package org.example.spells;

import org.example.main.*;

public class FLAMETHROWER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Flamethrower";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "flamethrower.png";
        //this.emote = "";
        this.description = "A stream of fire!!";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.8, 0.9, 0.9, 0, 0.6, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 20;
        this.hasCharges = true;
        this.maxCharges = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FLAMETHROWER",
		name 		= "$action_flamethrower",
		description = "$actiondesc_flamethrower",
		sprite 		= "data/ui_gfx/gun_actions/flamethrower.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/flamethrower_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/flamethrower.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,6", -- FLAMETHROWER
		spawn_probability                 = "0.8,0.9,0.9,0.6", -- FLAMETHROWER
		price = 220,
		mana = 20,
		max_uses = 60,
		custom_xml_file = "data/entities/misc/custom_cards/flamethrower.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/flamethrower.xml")
			c.spread_degrees = c.spread_degrees + 4.0
		end,
	},
	},
*/
}
