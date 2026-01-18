package org.example.spells;

import org.example.main.*;

public class CURSE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Venomous Curse";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "curse.png";
        //this.emote = "";
        this.description = "Imbues a projectile with a curse that makes the target hit by the projectile to waste away";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.6, 0.7, 0, 0.4, 0, 0, 0, 0, 0.1);
        this.price = 140;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CURSE",
		name 		= "$action_curse",
		description = "$actiondesc_curse",
		sprite 		= "data/ui_gfx/gun_actions/curse.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_curse.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,5,10", -- FIREBALL_RAY_ENEMY
		spawn_probability                 = "0.6,0.7,0.4,0.1", -- FIREBALL_RAY_ENEMY
		price = 140,
		mana = 30,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_curse.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
