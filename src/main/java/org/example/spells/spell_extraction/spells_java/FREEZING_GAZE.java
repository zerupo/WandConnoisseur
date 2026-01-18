package org.example.spells;

import org.example.main.*;

public class FREEZING_GAZE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Freezing gaze";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "freezing_gaze.png";
        //this.emote = "";
        this.description = "A heart-freezingly sinister aura";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.9, 1, 1, 0, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 45;
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
		id          = "FREEZING_GAZE",
		name 		= "$action_freezing_gaze",
		description = "$actiondesc_freezing_gaze",
		sprite 		= "data/ui_gfx/gun_actions/freezing_gaze.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/dynamite_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/freezing_gaze_beam.xml",12},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4", -- FREEZING_GAZE
		spawn_probability                 = "0.9,1,1", -- FREEZING_GAZE
		price = 180,
		mana = 45,
		max_uses	= 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			add_projectile("data/entities/projectiles/deck/freezing_gaze_beam.xml")
			c.pattern_degrees = 30
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
