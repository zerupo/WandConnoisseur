package org.example.spells;

import org.example.main.*;

public class CURSE_WITHER_EXPLOSION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Weakening Curse - Explosives";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "curse_wither_explosion.png";
        //this.emote = "";
        this.description = "Target hit by a projectile takes 25% extra explosion damage for a time";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.4, 0.7, 0.7, 0, 0, 0, 0, 0.1);
        this.price = 100;
        this.manaCost = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CURSE_WITHER_EXPLOSION",
		name 		= "$action_curse_wither_explosion",
		description = "$actiondesc_curse_wither_explosion",
		sprite 		= "data/ui_gfx/gun_actions/curse_wither_explosion.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_curse_wither_explosion.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,10", -- FIREBALL_RAY_ENEMY
		spawn_probability                 = "0.2,0.4,0.7,0.7,0.1", -- FIREBALL_RAY_ENEMY
		price = 100,
		mana = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_curse_wither_explosion.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
