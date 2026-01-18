package org.example.spells;

import org.example.main.*;

public class HITFX_CRITICAL_WATER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Critical on wet (water) enemies";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "critical_water.png";
        //this.emote = "";
        this.description = "Makes a projectile always do a critical hit on wet (water) enemies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.2, 0.4, 0.2, 0, 0, 0, 0, 0);
        this.price = 70;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HITFX_CRITICAL_WATER",
		name 		= "$action_hitfx_critical_water",
		description = "$actiondesc_hitfx_critical_water",
		sprite 		= "data/ui_gfx/gun_actions/critical_water.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_critical_water.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_CRITICAL_WATER
		spawn_probability                 = "0.2,0.2,0.4,0.2", -- HITFX_CRITICAL_WATER
		price = 70,
		mana = 10,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_critical_water.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
