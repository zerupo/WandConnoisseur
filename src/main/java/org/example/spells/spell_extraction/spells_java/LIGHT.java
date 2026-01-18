package org.example.spells;

import org.example.main.*;

public class LIGHT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Light";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "light.png";
        //this.emote = "";
        this.description = "Makes a projectile illuminate its surroundings";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(1, 0.8, 0.6, 0.4, 0.2, 0, 0, 0, 0, 0, 0);
        this.price = 20;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIGHT",
		name 		= "$action_light",
		description = "$actiondesc_light",
		sprite 		= "data/ui_gfx/gun_actions/light.png",
		related_extra_entities = { "data/entities/misc/light.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "0,1,2,3,4", -- LIGHT
		spawn_probability                 = "1,0.8,0.6,0.4,0.2", -- LIGHT
		price = 20,
		mana = 1,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/light.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
