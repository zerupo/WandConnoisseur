package org.example.spells;

import org.example.main.*;

public class SHIELD_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of shielding";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "shield_field.png";
        //this.emote = "";
        this.description = "A field of protective magic";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.4, 0.5, 0.3, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 20;
        this.hasCharges = true;
        this.maxCharges = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SHIELD_FIELD",
		name 		= "$action_shield_field",
		description = "$actiondesc_shield_field",
		sprite 		= "data/ui_gfx/gun_actions/shield_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/shield_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/shield_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- SHIELD_FIELD
		spawn_probability                 = "0.3,0.3,0.4,0.5,0.3", -- SHIELD_FIELD
		price = 160,
		mana = 20,
		max_uses = 10,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/shield_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
