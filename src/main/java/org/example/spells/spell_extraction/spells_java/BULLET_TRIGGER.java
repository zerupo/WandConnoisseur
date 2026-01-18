package org.example.spells;

import org.example.main.*;

public class BULLET_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Magic arrow with trigger";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bullet_trigger.png";
        //this.emote = "";
        this.description = "A magical arrow that casts another spell upon collision";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.5, 0.5, 0.6, 0.5, 0, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 35;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BULLET_TRIGGER",
		name 		= "$action_bullet_trigger",
		description = "$actiondesc_bullet_trigger",
		sprite 		= "data/ui_gfx/gun_actions/bullet_trigger.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bullet_trigger_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bullet.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                         = "1,2,3,4,5", -- BULLET_TRIGGER
		spawn_probability                   = "0.5,0.5,0.5,0.6,0.5", -- BULLET_TRIGGER
		price = 190,
		mana = 35,
		--max_uses = 80,
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 4
			c.screenshake = c.screenshake + 2
			c.spread_degrees = c.spread_degrees + 2.0
			c.damage_critical_chance = c.damage_critical_chance + 5
			add_projectile_trigger_hit_world("data/entities/projectiles/deck/bullet.xml", 1)
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 23.0
		end,
	},
	},
*/
}
