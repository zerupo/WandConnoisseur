package org.example.spells;

import org.example.main.*;

public class CASTER_CAST extends Spell{
    @Override
    protected void initialization(){
        this.name = "Inner spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "caster_cast.png";
        //this.emote = "";
        this.description = "Causes a projectile to be cast from where the caster is standing";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.2, 0.4, 0.4, 0, 0, 0, 0.2);
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
		id          = "CASTER_CAST",
		name 		= "$action_caster_cast",
		description = "$actiondesc_caster_cast",
		sprite 		= "data/ui_gfx/gun_actions/caster_cast.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/caster_cast.xml"},
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "2,4,5,6,10", -- CASTER_CAST
		spawn_probability                 = "0.2,0.2,0.4,0.4,0.2", -- CASTER_CAST
		price = 70,
		mana = 10,
		action 		= function()
			c.spread_degrees = c.spread_degrees - 24
			c.extra_entities = c.extra_entities .. "data/entities/misc/caster_cast.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
