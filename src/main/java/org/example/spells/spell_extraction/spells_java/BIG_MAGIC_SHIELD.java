package org.example.spells;

import org.example.main.*;

public class BIG_MAGIC_SHIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Big magic guard";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "big_magic_shield.png";
        //this.emote = "";
        this.description = "Eight guarding lights rotate around you for a time";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.4, 0.5, 0.6, 0, 0, 0, 0.2);
        this.price = 120;
        this.manaCost = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BIG_MAGIC_SHIELD",
		name 		= "$action_big_magic_shield",
		description = "$actiondesc_big_magic_shield",
		sprite 		= "data/ui_gfx/gun_actions/big_magic_shield.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spiral_shot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/big_magic_shield_start.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,4,5,6,10", -- SPIRAL_SHOT
		spawn_probability                 = "0.2,0.4,0.5,0.6,0.2", -- SPIRAL_SHOT
		price = 120,
		mana = 60,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/big_magic_shield_start.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
		end,
	},
	},
*/
}
