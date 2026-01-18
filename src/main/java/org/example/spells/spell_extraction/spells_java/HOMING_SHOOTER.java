package org.example.spells;

import org.example.main.*;

public class HOMING_SHOOTER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Boomerang";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_shooter.png";
        //this.emote = "";
        this.description = "Gives a projectile a path that curves towards you";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0.2, 0, 0.2, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOMING_SHOOTER",
		name 		= "$action_homing_shooter",
		description = "$actiondesc_homing_shooter",
		sprite 		= "data/ui_gfx/gun_actions/homing_shooter.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/homing_shooter.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,6", -- HOMING_SHOOTER
		spawn_probability                 = "0.2,0.3,0.2,0.2", -- HOMING_SHOOTER
		price = 100,
		mana = 10,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/homing_shooter.xml,data/entities/particles/tinyspark_white.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
