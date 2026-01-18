package org.example.spells;

import org.example.main.*;

public class FIREBALL_RAY_ENEMY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Personal fireball thrower";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fireball_ray_enemy.png";
        //this.emote = "";
        this.description = "Makes a projectile turn the creatures it hits into living fireball throwers";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.6, 0, 0.4, 0.3, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 90;
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
		id          = "FIREBALL_RAY_ENEMY",
		name 		= "$action_fireball_ray_enemy",
		description = "$actiondesc_fireball_ray_enemy",
		sprite 		= "data/ui_gfx/gun_actions/fireball_ray_enemy.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_fireball_ray_enemy.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,4,5", -- FIREBALL_RAY_ENEMY
		spawn_probability                 = "0.5,0.6,0.4,0.3", -- FIREBALL_RAY_ENEMY
		price = 100,
		mana = 90,
		max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_fireball_ray_enemy.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
