package org.example.spells;

import org.example.main.*;

public class BOUNCY_ORB_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Energy sphere with timer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bouncy_orb_timer.png";
        //this.emote = "";
        this.description = "A fast";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0, 0.5, 0, 0.5, 0, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOUNCY_ORB_TIMER",
		name 		= "$action_bouncy_orb_timer",
		description = "$actiondesc_bouncy_orb_timer",
		sprite 		= "data/ui_gfx/gun_actions/bouncy_orb_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/disc_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bouncy_orb.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,2,4", -- BOUNCY_ORB_TIMER
		spawn_probability                 = "0.5,0.5,0.5", -- BOUNCY_ORB_TIMER
		price = 150,
		mana = 50,
		--max_uses = 40,
		action 		= function()
			add_projectile_trigger_timer("data/entities/projectiles/deck/bouncy_orb.xml",200,1)
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 10
			shot_effects.recoil_knockback = 20.0
		end,
	},
	},
*/
}
