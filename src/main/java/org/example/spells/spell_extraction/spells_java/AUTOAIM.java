package org.example.spells;

import org.example.main.*;

public class AUTOAIM extends Spell{
    @Override
    protected void initialization(){
        this.name = "Auto-Aim";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "autoaim.png";
        //this.emote = "";
        this.description = "Makes a projectile turns towards the nearest visible enemy";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 25;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "AUTOAIM",
		name 		= "$action_autoaim",
		description = "$actiondesc_autoaim",
		sprite 		= "data/ui_gfx/gun_actions/autoaim.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/autoaim_unidentified.png",
		related_extra_entities = { "data/entities/misc/autoaim.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- AUTOAIM
		spawn_probability                 = "0.4,0.4,0.4,0.4,0.4", -- AUTOAIM
		price = 150,
		mana = 25,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/autoaim.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
