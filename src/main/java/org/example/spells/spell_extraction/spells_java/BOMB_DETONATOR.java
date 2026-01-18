package org.example.spells;

import org.example.main.*;

public class BOMB_DETONATOR extends Spell{
    @Override
    protected void initialization(){
        this.name = "Explosive Detonator";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "pipe_bomb_detonator.png";
        //this.emote = "";
        this.description = "All nearby explosive spells cast by you instantly detonate";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 1, 0.4, 0.5, 1, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BOMB_DETONATOR",
		name 		= "$action_bomb_detonator",
		description = "$actiondesc_bomb_detonator",
		sprite 		= "data/ui_gfx/gun_actions/pipe_bomb_detonator.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/meteor_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bomb_detonator.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- PIPE_BOMB_DETONATOR
		spawn_probability                 = "0.5,1,0.4,0.5,1", -- PIPE_BOMB_DETONATOR
		price = 120,
		mana = 50,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/bomb_detonator.xml")
		end,
	},
	},
*/
}
