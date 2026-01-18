package org.example.spells;

import org.example.main.*;

public class LIQUID_TO_EXPLOSION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Liquid Detonation";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "liquid_to_explosion.png";
        //this.emote = "";
        this.description = "Converts nearby nonmagical liquids into explosive gunpowder";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIQUID_TO_EXPLOSION",
		name 		= "$action_liquid_to_explosion",
		description = "$actiondesc_liquid_to_explosion",
		sprite 		= "data/ui_gfx/gun_actions/liquid_to_explosion.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		related_extra_entities = { "data/entities/misc/liquid_to_explosion.xml", "data/entities/particles/tinyspark_red.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- BLOOD_TO_ACID
		spawn_probability                 = "0.3,0.3,0.3", -- BLOOD_TO_ACID
		price = 120,
		mana = 40,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/liquid_to_explosion.xml,data/entities/particles/tinyspark_red.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
