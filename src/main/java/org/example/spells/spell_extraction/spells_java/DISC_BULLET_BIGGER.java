package org.example.spells;

import org.example.main.*;

public class DISC_BULLET_BIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Omega Sawblade";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "omega_disc_bullet.png";
        //this.emote = "";
        this.description = "That's a lot of sawblade";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.6, 0, 1, 0, 0, 0, 0, 0.4);
        this.price = 270;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DISC_BULLET_BIGGER",
		name 		= "$action_omega_disc_bullet",
		description = "$actiondesc_omega_disc_bullet",
		sprite 		= "data/ui_gfx/gun_actions/omega_disc_bullet.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/disc_bullet_unidentified.png",
		spawn_requires_flag = "card_unlocked_everything",
		related_projectiles	= {"data/entities/projectiles/deck/disc_bullet_bigger.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,5,10", -- DISC_BULLET_BIG
		spawn_probability                 = "0.1,0.6,1,0.4", -- DISC_BULLET_BIG
		price = 270,
		mana = 70,
		--max_uses = 40,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/disc_bullet_bigger.xml")
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 40
			c.spread_degrees = c.spread_degrees + 6.4
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
			c.damage_projectile_add = c.damage_projectile_add + 0.2
		end,
	},
	},
*/
}
