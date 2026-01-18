package org.example.spells;

import org.example.main.*;

public class GRAVITY_FIELD_ENEMY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Personal gravity field";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "gravity_field_enemy.png";
        //this.emote = "";
        this.description = "Makes creatures hit by a projectile gain a temporary gravity well that draws projectiles in";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.6, 0, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 110;
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
		id          = "GRAVITY_FIELD_ENEMY",
		name 		= "$action_gravity_field_enemy",
		description = "$actiondesc_gravity_field_enemy",
		sprite 		= "data/ui_gfx/gun_actions/gravity_field_enemy.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_gravity_field_enemy.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,4,5", -- GRAVITY_FIELD_ENEMY
		spawn_probability                 = "0.5,0.6,0.4,0.4", -- GRAVITY_FIELD_ENEMY
		price = 250,
		mana = 110,
		max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_gravity_field_enemy.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
