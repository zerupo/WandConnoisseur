package org.example.spells;

import org.example.main.*;

public class TEMPORARY_PLATFORM extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Platform";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "temporary_platform.png";
        //this.emote = "";
        this.description = "Summons a shortlived bit of ground";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0.1, 0.1, 0.3, 0, 0.4, 0.2, 0, 0, 0, 0, 0);
        this.price = 90;
        this.manaCost = 30;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TEMPORARY_PLATFORM",
		name 		= "$action_temporary_platform",
		description = "$actiondesc_temporary_platform",
		sprite 		= "data/ui_gfx/gun_actions/temporary_platform.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/temporary_platform.xml"},
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "0,1,2,4,5", -- WALL_SQUARE
		spawn_probability                 = "0.1,0.1,0.3,0.4,0.2", -- WALL_SQUARE
		price = 90,
		mana = 30,
		max_uses = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/temporary_platform.xml")
			c.fire_rate_wait = c.fire_rate_wait + 40
		end,
	},
	},
*/
}
