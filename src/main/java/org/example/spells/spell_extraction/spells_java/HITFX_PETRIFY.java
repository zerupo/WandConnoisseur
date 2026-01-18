package org.example.spells;

import org.example.main.*;

public class HITFX_PETRIFY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Petrify";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "petrify.png";
        //this.emote = "";
        this.description = "Turns a wounded enemy into stone";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0, 0.2, 0.3, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HITFX_PETRIFY",
		name 		= "$action_petrify",
		description = "$actiondesc_petrify_a",
		sprite 		= "data/ui_gfx/gun_actions/petrify.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,5,6", -- PETRIFY
		spawn_probability                 = "0.2,0.3,0.2,0.3", -- PETRIFY
		price = 140,
		mana = 10,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_petrify.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
