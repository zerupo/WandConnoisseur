package org.example.spells;

import org.example.main.*;

public class ANTI_HOMING extends Spell{
    @Override
    protected void initialization(){
        this.name = "Anti Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "anti_homing.png";
        //this.emote = "";
        this.description = "Makes a projectile accelerate away from your foes";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.05, 0.3, 0.3, 0.1, 0.1, 0.01, 0, 0, 0, 0);
        this.price = 110;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ANTI_HOMING",
		name 		= "$action_anti_homing",
		description = "$actiondesc_anti_homing",
		sprite 		= "data/ui_gfx/gun_actions/anti_homing.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/anti_homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/anti_homing.xml", "data/entities/particles/tinyspark_white.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5,6", -- ANTI_HOMING
		spawn_probability                 = "0.05,0.3,0.3,0.1,0.1,0.01", -- ANTI_HOMING
		price = 110,
		mana = 1,
		--max_uses = 100,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/anti_homing.xml,data/entities/particles/tinyspark_white.xml,"
			c.fire_rate_wait    = c.fire_rate_wait - 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
