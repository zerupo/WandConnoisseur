package org.example.spells;

import org.example.main.*;

public class FIREBALL_RAY_LINE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Two-way fireball thrower";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fireball_ray_line.png";
        //this.emote = "";
        this.description = "Makes a projectile fire small fireballs perpendicular to its trajectory";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.6, 0.4, 0.4, 0.4, 1, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 130;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FIREBALL_RAY_LINE",
		name 		= "$action_fireball_ray_line",
		description = "$actiondesc_fireball_ray_line",
		sprite 		= "data/ui_gfx/gun_actions/fireball_ray_line.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/fireball_ray_line.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- FIREBALL_RAY_LINE
		spawn_probability                 = "0.6,0.4,0.4,0.4,1", -- FIREBALL_RAY_LINE
		price = 120,
		mana = 130,
		max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/fireball_ray_line.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
