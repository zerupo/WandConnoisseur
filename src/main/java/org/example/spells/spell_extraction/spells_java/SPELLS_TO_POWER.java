package org.example.spells;

import org.example.main.*;

public class SPELLS_TO_POWER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spells to Power";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spells_to_power.png";
        //this.emote = "";
        this.description = "Converts any nearby projectiles cast by you into extra damage";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.5, 0.5, 0.5, 0, 0, 0, 0.1);
        this.price = 140;
        this.manaCost = 110;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SPELLS_TO_POWER",
		name 		= "$action_spells_to_power",
		description = "$actiondesc_spells_to_power",
		sprite 		= "data/ui_gfx/gun_actions/spells_to_power.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/spells_to_power.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6,10", -- AREA_DAMAGE
		spawn_probability                 = "0.3,0.3,0.5,0.5,0.5,0.1", -- AREA_DAMAGE
		price = 140,
		mana = 110,
		-- max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/spells_to_power.xml,"
			c.fire_rate_wait    = c.fire_rate_wait + 40
			draw_actions( 1, true )
		end,
	},
	},
*/
}
