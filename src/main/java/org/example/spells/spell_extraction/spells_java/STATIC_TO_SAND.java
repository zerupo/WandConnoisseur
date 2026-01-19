package org.example.spells;

import org.example.main.*;

public class STATIC_TO_SAND extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ground to sand";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "static_to_sand.png";
        //this.emote = "";
        this.description = "Makes any hard, solid materials within a projectile's range turn into sand";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.3, 0, 0, 0, 0, 0, 0.2);
        this.price = 140;
        this.manaCost = 70;
        this.hasCharges = true;
        this.maxCharges = 8;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "STATIC_TO_SAND",
		name 		= "$action_static_to_sand",
		description = "$actiondesc_static_to_sand",
		sprite 		= "data/ui_gfx/gun_actions/static_to_sand.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		related_extra_entities = { "data/entities/misc/static_to_sand.xml", "data/entities/particles/tinyspark_yellow.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,10", -- STATIC_TO_SAND
		spawn_probability                 = "0.3,0.3,0.3,0.2", -- STATIC_TO_SAND
		price = 140,
		mana = 70,
		max_uses = 8,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/static_to_sand.xml,data/entities/particles/tinyspark_yellow.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 60
			draw_actions( 1, true )
		end,
	},
	},
*/
}
