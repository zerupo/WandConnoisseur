package org.example.spells;

import org.example.main.*;

public class LARPA_DEATH extends Spell{
    @Override
    protected void initialization(){
        this.name = "Larpa Explosion";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "larpa_death.png";
        //this.emote = "";
        this.description = "A projectile will shoot out 8 copies of itself when it expires or hits the ground";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.1, 0.3, 0.4, 0, 0, 0, 0, 0.2);
        this.price = 150;
        this.manaCost = 90;
        this.hasCharges = true;
        this.maxCharges = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LARPA_DEATH",
		name 		= "$action_larpa_death",
		description = "$actiondesc_larpa_death",
		sprite 		= "data/ui_gfx/gun_actions/larpa_death.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/larpa_death.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,10", -- FIREBALL_RAY
		spawn_probability                 = "0.1,0.1,0.3,0.4,0.2", -- FIREBALL_RAY
		price = 150,
		mana = 90,
		max_uses = 30,
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 15
			c.extra_entities = c.extra_entities .. "data/entities/misc/larpa_death.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
