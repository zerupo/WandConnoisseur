package org.example.spells;

import org.example.main.*;

public class HITFX_BURNING_CRITICAL_HIT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Critical on burning";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "burning_critical.png";
        //this.emote = "";
        this.description = "Makes a projectile always do a critical hit on burning enemies";
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
		id          = "HITFX_BURNING_CRITICAL_HIT",
		name 		= "$action_hitfx_burning_critical_hit",
		description = "$actiondesc_hitfx_burning_critical_hit",
		sprite 		= "data/ui_gfx/gun_actions/burning_critical.png",
		sprite_unidentified = "data/entities/misc/hitfx_burning_critical_hit.xml",
		related_extra_entities = { "data/entities/particles/freeze_charge.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_BURNING_CRITICAL_HIT
		spawn_probability                 = "0.2,0.4,0.2,0.2", -- HITFX_BURNING_CRITICAL_HIT
		price = 70,
		mana = 10,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_burning_critical_hit.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
