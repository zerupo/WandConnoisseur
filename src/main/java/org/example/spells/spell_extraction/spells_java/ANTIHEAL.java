package org.example.spells;

import org.example.main.*;

public class ANTIHEAL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Deadly heal";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "antiheal.png";
        //this.emote = "";
        this.description = "A projectile that at first deals damage, then heals over time";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.3, 0.3, 0.3, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 20;
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
		id          = "ANTIHEAL",
		name 		= "$action_antiheal",
		description = "$actiondesc_antiheal",
		sprite 		= "data/ui_gfx/gun_actions/antiheal.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/healhurt.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5", -- BULLET
		spawn_probability                 = "0.4,0.3,0.3,0.3", -- BULLET
		price = 200,
		mana = 20,
		max_uses = 20,
		never_unlimited = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/healhurt.xml")
			c.fire_rate_wait = c.fire_rate_wait + 8
			c.screenshake = c.screenshake + 2
			c.spread_degrees = c.spread_degrees + 3.0
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 12.0
		end,
	},
	},
*/
}
