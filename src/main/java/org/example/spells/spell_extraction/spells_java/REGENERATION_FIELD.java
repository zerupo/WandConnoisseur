package org.example.spells;

import org.example.main.*;

public class REGENERATION_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of vigour";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "regeneration_field.png";
        //this.emote = "";
        this.description = "A field of regenerative magic";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0.3, 0.4, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 80;
        this.hasCharges = true;
        this.maxCharges = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "REGENERATION_FIELD",
		name 		= "$action_regeneration_field",
		description = "$actiondesc_regeneration_field",
		sprite 		= "data/ui_gfx/gun_actions/regeneration_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/regeneration_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/regeneration_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "1,2,3,4", -- REGENERATION_FIELD
		spawn_probability                 = "0.3,0.3,0.4,0.3", -- REGENERATION_FIELD
		price = 250,
		mana = 80,
		max_uses = 2,
		never_unlimited = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/regeneration_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
