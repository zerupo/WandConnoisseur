package org.example.spells;

import org.example.main.*;

public class ORBIT_DISCS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Sawblade Orbit";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "orbit_discs.png";
        //this.emote = "";
        this.description = "Makes four sawblades rotate around a projectile";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0.65, 0, 0.4, 0.3, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ORBIT_DISCS",
		name 		= "$action_orbit_discs",
		description = "$actiondesc_orbit_discs",
		sprite 		= "data/ui_gfx/gun_actions/orbit_discs.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/orbit_discs.xml" },
		spawn_requires_flag = "card_unlocked_dragon",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,4,5", -- GRAVITY_FIELD_ENEMY
		spawn_probability                 = "0.3,0.65,0.4,0.3", -- GRAVITY_FIELD_ENEMY
		price = 200,
		mana = 70,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/orbit_discs.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
