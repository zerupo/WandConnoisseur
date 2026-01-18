package org.example.spells;

import org.example.main.*;

public class WATER_TO_POISON extends Spell{
    @Override
    protected void initialization(){
        this.name = "Water to poison";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "water_to_poison.png";
        //this.emote = "";
        this.description = "Makes any water within a projectile's range turns into poison";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "WATER_TO_POISON",
		name 		= "$action_water_to_poison",
		description = "$actiondesc_water_to_poison",
		sprite 		= "data/ui_gfx/gun_actions/water_to_poison.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		related_extra_entities = { "data/entities/misc/water_to_poison.xml", "data/entities/particles/tinyspark_purple.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- WATER_TO_POISON
		spawn_probability                 = "0.3,0.3,0.3", -- WATER_TO_POISON
		price = 80,
		mana = 30,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/water_to_poison.xml,data/entities/particles/tinyspark_purple.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 10
			draw_actions( 1, true )
		end,
	},
	},
*/
}
