package org.example.spells;

import org.example.main.*;

public class TELEPORT_PROJECTILE_CLOSER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Homebringer Teleport Bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "teleport_projectile_closer.png";
        //this.emote = "";
        this.description = "Brings the target hit closer to you";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.4, 0.6, 0.6, 0, 0.7, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TELEPORT_PROJECTILE_CLOSER",
		name 		= "$action_teleport_closer",
		description = "$actiondesc_teleport_closer",
		sprite 		= "data/ui_gfx/gun_actions/teleport_projectile_closer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/teleport_projectile_closer.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- TELEPORT_PROJECTILE
		spawn_probability                 = "0.4,0.6,0.6,0.7,0.4,0.4", -- TELEPORT_PROJECTILE
		price = 130,
		mana = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/teleport_projectile_closer.xml")
			c.spread_degrees = c.spread_degrees - 2.0
		end,
	},
	},
*/
}
