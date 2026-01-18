package org.example.spells;

import org.example.main.*;

public class PROJECTILE_TRANSMUTATION_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Projectile transmutation field";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "projectile_transmutation_field.png";
        //this.emote = "";
        this.description = "Projectiles caught within the field transform into harmless critters";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.4, 0.4, 0.3, 0.3, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 120;
        this.hasCharges = true;
        this.maxCharges = 6;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "PROJECTILE_TRANSMUTATION_FIELD",
		name 		= "$action_projectile_transmutation_field",
		description = "$actiondesc_projectile_transmutation_field",
		sprite 		= "data/ui_gfx/gun_actions/projectile_transmutation_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/chaos_polymorph_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/projectile_transmutation_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,3,4,5,6", -- PROJECTILE_TRANSMUTATION_FIELD
		spawn_probability                 = "0.3,0.4,0.4,0.3,0.3", -- PROJECTILE_TRANSMUTATION_FIELD
		price = 250,
		mana = 120,
		max_uses = 6,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/projectile_transmutation_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
