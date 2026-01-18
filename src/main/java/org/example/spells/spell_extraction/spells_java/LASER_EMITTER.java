package org.example.spells;

import org.example.main.*;

public class LASER_EMITTER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Plasma beam";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "laser_emitter.png";
        //this.emote = "";
        this.description = "An instantaneous";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.8, 1, 0.5, 0, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 60;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LASER_EMITTER",
		name 		= "$action_laser_emitter",
		description = "$actiondesc_laser_emitter",
		sprite 		= "data/ui_gfx/gun_actions/laser_emitter.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/laser_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/orb_laseremitter.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4", -- LASER
		spawn_probability                 = "0.2,0.8,1,0.5", -- LASER
		price = 180,
		mana = 60,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/orb_laseremitter.xml")
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 20.0
			c.fire_rate_wait = c.fire_rate_wait + 6
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
		end,
	},
	},
*/
}
