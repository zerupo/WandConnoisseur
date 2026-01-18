package org.example.spells;

import org.example.main.*;

public class TELEPORT_CAST extends Spell{
    @Override
    protected void initialization(){
        this.name = "Teleporting cast";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "teleport_cast.png";
        //this.emote = "";
        this.description = "Casts a spell from the closest enemy";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.6, 0.6, 0, 0.6, 0.8, 1, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 100;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TELEPORT_CAST",
		name 		= "$action_teleport_cast",
		description = "$actiondesc_teleport_cast",
		sprite 		= "data/ui_gfx/gun_actions/teleport_cast.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/teleport_cast.xml"},
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "1,2,4,5,6", -- TELEPORT_CAST
		spawn_probability                 = "0.6,0.6,0.6,0.8,1", -- TELEPORT_CAST
		price = 190,
		mana = 100,
		action 		= function()
			add_projectile_trigger_death("data/entities/projectiles/deck/teleport_cast.xml", 1)
			c.fire_rate_wait = c.fire_rate_wait + 20
			c.spread_degrees = c.spread_degrees + 24
		end,
	},
	},
*/
}
