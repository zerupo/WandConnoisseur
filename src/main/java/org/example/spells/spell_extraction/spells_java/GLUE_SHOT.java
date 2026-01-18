package org.example.spells;

import org.example.main.*;

public class GLUE_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Glue Ball";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "glue_shot.png";
        //this.emote = "";
        this.description = "A projectile that explodes into a sticky mess";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.7, 0.4, 0.2, 0.5, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 25;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "GLUE_SHOT",
		name 		= "$action_glue_shot",
		description = "$actiondesc_glue_shot",
		sprite 		= "data/ui_gfx/gun_actions/glue_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/dynamite_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/glue_shot.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5", -- GLUE_SHOT
		spawn_probability                 = "0.7,0.4,0.2,0.5", -- GLUE_SHOT
		price = 140,
		mana = 25,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/glue_shot.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
			c.spread_degrees = c.spread_degrees + 5.0
		end,
	},
	},
*/
}
