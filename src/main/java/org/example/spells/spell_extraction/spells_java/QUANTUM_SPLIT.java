package org.example.spells;

import org.example.main.*;

public class QUANTUM_SPLIT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Quantum Split";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "quantum_split.png";
        //this.emote = "";
        this.description = "Makes a projectile split into three projectiles whose existences are entangled";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.6, 0.5, 0.5, 1, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "QUANTUM_SPLIT",
		name 		= "$action_quantum_split",
		description = "$actiondesc_quantum_split",
		sprite 		= "data/ui_gfx/gun_actions/quantum_split.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		related_extra_entities = { "data/entities/misc/quantum_split.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- MANA_REDUCE
		spawn_probability                 = "0.5,0.6,0.5,0.5,1", -- MANA_REDUCE
		price = 200,
		mana = 10,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/quantum_split.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 5
			draw_actions( 1, true )
		end,
	},
	},
*/
}
