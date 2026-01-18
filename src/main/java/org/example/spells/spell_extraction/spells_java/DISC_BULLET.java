package org.example.spells;

import org.example.main.*;

public class DISC_BULLET extends Spell{
    @Override
    protected void initialization(){
        this.name = "Disc projectile";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "disc_bullet.png";
        //this.emote = "";
        this.description = "Summons a sharp disc projectile";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 0, 1, 0, 0.6, 0, 0, 0, 0, 0, 0);
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
		id          = "DISC_BULLET",
		name 		= "$action_disc_bullet",
		description = "$actiondesc_disc_bullet",
		sprite 		= "data/ui_gfx/gun_actions/disc_bullet.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/disc_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/disc_bullet.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,2,4", -- DISC_BULLET
		spawn_probability                 = "1,1,0.6", -- DISC_BULLET
		price = 120,
		mana = 20,
		--max_uses = 40,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/disc_bullet.xml")
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 10
			c.spread_degrees = c.spread_degrees + 2.0
			shot_effects.recoil_knockback = 20.0
		end,
	},
	},
*/
}
