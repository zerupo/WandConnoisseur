package org.example.spells;

import org.example.main.*;

public class BOUNCY_ORB extends Spell{
    @Override
    protected void initialization(){
        this.name = "Energy sphere";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bouncy_orb.png";
        //this.emote = "";
        this.description = "A fast";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCY_ORB",
		name 		= "$action_bouncy_orb",
		description = "$actiondesc_bouncy_orb",
		sprite 		= "data/ui_gfx/gun_actions/bouncy_orb.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/disc_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bouncy_orb.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,2,4", -- BOUNCY_ORB
		spawn_probability                 = "1,1,1", -- BOUNCY_ORB
		price = 120,
		mana = 20,
		--max_uses = 40,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/bouncy_orb.xml")
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 10
			shot_effects.recoil_knockback = 20.0
		end,
	},
	},
*/
}
