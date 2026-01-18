package org.example.spells;

import org.example.main.*;

public class ESSENCE_TO_POWER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Essence to Power";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "essence_to_power.png";
        //this.emote = "";
        this.description = "Increases a projectile's damage based on the number of creatures nearby";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.5, 0.5, 0, 0, 0.5, 0, 0, 0, 0.1);
        this.price = 120;
        this.manaCost = 110;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ESSENCE_TO_POWER",
		name 		= "$action_enemies_to_power",
		description = "$actiondesc_enemies_to_power",
		sprite 		= "data/ui_gfx/gun_actions/essence_to_power.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/essence_to_power.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,6,10", -- AREA_DAMAGE
		spawn_probability                 = "0.2,0.5,0.5,0.5,0.1", -- AREA_DAMAGE
		price = 120,
		mana = 110,
		-- max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/essence_to_power.xml,"
			c.fire_rate_wait    = c.fire_rate_wait + 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
