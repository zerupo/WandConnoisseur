package org.example.spells;

import org.example.main.*;

public class LARPA_UPWARDS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Upwards larpa";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "larpa_upwards.png";
        //this.emote = "";
        this.description = "Makes a projectile cast copies of itself with an upwards trajectory";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.1, 0.2, 0.4, 0, 0, 0, 0, 0.2);
        this.price = 290;
        this.manaCost = 120;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LARPA_UPWARDS",
		name 		= "$action_larpa_upwards",
		description = "$actiondesc_larpa_upwards",
		sprite 		= "data/ui_gfx/gun_actions/larpa_upwards.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/larpa_upwards.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,10", -- FIREBALL_RAY
		spawn_probability                 = "0.1,0.1,0.2,0.4,0.2", -- FIREBALL_RAY
		price = 290,
		mana = 120,
		--max_uses = 20,
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 15
			c.extra_entities = c.extra_entities .. "data/entities/misc/larpa_upwards.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
