package org.example.spells;

import org.example.main.*;

public class WALL_SQUARE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Square barrier";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "wall_square.png";
        //this.emote = "";
        this.description = "A thin, square-shaped barrier that harms passing creatures, including you";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0.2, 0.6, 0, 0.5, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 70;
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
		id          = "WALL_SQUARE",
		name 		= "$action_wall_square",
		description = "$actiondesc_wall_square",
		sprite 		= "data/ui_gfx/gun_actions/wall_square.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/wall_square.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- WALL_SQUARE
		spawn_probability                 = "0.3,0.2,0.6,0.5,0.4,0.4", -- WALL_SQUARE
		price = 160,
		mana = 70,
		max_uses = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/wall_square.xml")
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
