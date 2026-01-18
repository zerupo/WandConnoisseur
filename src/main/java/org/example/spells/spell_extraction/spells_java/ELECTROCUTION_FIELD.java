package org.example.spells;

import org.example.main.*;

public class ELECTROCUTION_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of thunder";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "electrocution_field.png";
        //this.emote = "";
        this.description = "A field of electrifying magic";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0, 0.6, 0, 0.8, 0.3, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 60;
        this.hasCharges = true;
        this.maxCharges = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ELECTROCUTION_FIELD",
		name 		= "$action_electrocution_field",
		description = "$actiondesc_electrocution_field",
		sprite 		= "data/ui_gfx/gun_actions/electrocution_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electrocution_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/electrocution_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "1,3,5,6", -- ELECTROCUTION_FIELD
		spawn_probability                 = "0.3,0.6,0.8,0.3", -- ELECTROCUTION_FIELD
		price = 200,
		mana = 60,
		max_uses = 15,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/electrocution_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
