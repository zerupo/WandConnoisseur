package org.example.spells;

import org.example.main.*;

public class TRUE_ORBIT extends Spell{
    @Override
    protected void initialization(){
        this.name = "True Orbit";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "true_orbit.png";
        //this.emote = "";
        this.description = "Makes a projectile rotate around the caster like an orbiting planet";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 40;
        this.manaCost = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TRUE_ORBIT",
		name 		= "$action_true_orbit",
		description = "$actiondesc_true_orbit",
		sprite 		= "data/ui_gfx/gun_actions/true_orbit.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/true_orbit.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- HORIZONTAL_ARC
		spawn_probability                 = "0.2,0.3,0.4", -- HORIZONTAL_ARC
		price = 40,
		mana = 2,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/true_orbit.xml,"
			draw_actions( 1, true )
			c.damage_projectile_add = c.damage_projectile_add + 0.1
			c.fire_rate_wait    = c.fire_rate_wait - 20
			c.lifetime_add 		= c.lifetime_add + 80
		end,
	},
	},
*/
}
