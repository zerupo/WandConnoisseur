package org.example.spells;

import org.example.main.*;

public class SUPER_TELEPORT_CAST extends Spell{
    @Override
    protected void initialization(){
        this.name = "Warp cast";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "super_teleport_cast.png";
        //this.emote = "";
        this.description = "Makes a spell immediately jump a long distance";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.6, 0.8, 0.8, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SUPER_TELEPORT_CAST",
		name 		= "$action_super_teleport_cast",
		description = "$actiondesc_super_teleport_cast",
		sprite 		= "data/ui_gfx/gun_actions/super_teleport_cast.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/super_teleport_cast.xml"},
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "2,4,5,6", -- SUPER_TELEPORT_CAST
		spawn_probability                 = "0.2,0.6,0.8,0.8", -- SUPER_TELEPORT_CAST
		price = 160,
		mana = 20,
		action 		= function()
			add_projectile_trigger_death("data/entities/projectiles/deck/super_teleport_cast.xml", 1)
			c.fire_rate_wait = c.fire_rate_wait + 10
			c.spread_degrees = c.spread_degrees - 6
		end,
	},
	},
*/
}
