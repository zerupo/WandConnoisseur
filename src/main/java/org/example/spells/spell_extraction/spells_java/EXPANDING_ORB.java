package org.example.spells;

import org.example.main.*;

public class EXPANDING_ORB extends Spell{
    @Override
    protected void initialization(){
        this.name = "Expanding Sphere";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "expanding_orb.png";
        //this.emote = "";
        this.description = "A slow projectile that increases its damage over time";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.5, 1, 1, 0.5, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "EXPANDING_ORB",
		name 		= "$action_expanding_orb",
		description = "$actiondesc_expanding_orb",
		sprite 		= "data/ui_gfx/gun_actions/expanding_orb.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/disc_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/orb_expanding.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- CURSED_ORB
		spawn_probability                 = "0.5,0.5,1,1,0.5", -- CURSED_ORB
		price = 200,
		mana = 70,
		action 		= function()
			add_projectile("data/entities/projectiles/orb_expanding.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
			shot_effects.recoil_knockback = 20.0
		end,
	},
	},
*/
}
