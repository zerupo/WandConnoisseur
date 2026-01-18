package org.example.spells;

import org.example.main.*;

public class HITFX_CRITICAL_OIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Critical on oiled enemies";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "critical_oil.png";
        //this.emote = "";
        this.description = "Makes a projectile always do a critical hit on oiled enemies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.4, 0.2, 0.2, 0, 0, 0, 0, 0);
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
		id          = "HITFX_CRITICAL_OIL",
		name 		= "$action_hitfx_critical_oil",
		description = "$actiondesc_hitfx_critical_oil",
		sprite 		= "data/ui_gfx/gun_actions/critical_oil.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_critical_oil.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_CRITICAL_OIL
		spawn_probability                 = "0.2,0.4,0.2,0.2", -- HITFX_CRITICAL_OIL
		price = 70,
		mana = 10,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_critical_oil.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
