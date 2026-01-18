package org.example.spells;

import org.example.main.*;

public class GLOWING_BOLT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Pinpoint of light";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "glowing_bolt.png";
        //this.emote = "";
        this.description = "An extremely concentrated point of light that explodes after a moment";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.8, 0.9, 1, 0, 0, 0, 0, 0.1);
        this.price = 220;
        this.manaCost = 65;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "GLOWING_BOLT",
		name 		= "$action_glowing_bolt",
		description = "$actiondesc_glowing_bolt",
		sprite 		= "data/ui_gfx/gun_actions/glowing_bolt.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/dynamite_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/glowing_bolt.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "3,4,5,10", -- FREEZING_GAZE
		spawn_probability                 = "0.8,0.9,1,0.1", -- FREEZING_GAZE
		price = 220,
		mana = 65,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/glowing_bolt.xml")
			c.fire_rate_wait = c.fire_rate_wait + 40
			c.spread_degrees = c.spread_degrees + 6.0
		end,
	},
	},
*/
}
