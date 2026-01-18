package org.example.spells;

import org.example.main.*;

public class LASER_EMITTER_FOUR extends Spell{
    @Override
    protected void initialization(){
        this.name = "Plasma Beam Cross";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "laser_emitter_four.png";
        //this.emote = "";
        this.description = "Four deadly plasma beams in a cross-shape. Look out";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.9, 0.3, 0.5, 1, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 80;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LASER_EMITTER_FOUR",
		name 		= "$action_laser_emitter_four",
		description = "$actiondesc_laser_emitter_four",
		sprite 		= "data/ui_gfx/gun_actions/laser_emitter_four.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/laser_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/orb_laseremitter.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5", -- LASER
		spawn_probability                 = "0.2,0.9,0.3,0.5,1", -- LASER
		price = 200,
		mana = 80,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/orb_laseremitter_four.xml")
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
			c.fire_rate_wait = c.fire_rate_wait + 15
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
		end,
	},
	},
*/
}
