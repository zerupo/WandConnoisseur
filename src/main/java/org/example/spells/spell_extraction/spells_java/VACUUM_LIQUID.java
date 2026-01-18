package org.example.spells;

import org.example.main.*;

public class VACUUM_LIQUID extends Spell{
    @Override
    protected void initialization(){
        this.name = "Liquid Vacuum Field";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "vacuum_liquid.png";
        //this.emote = "";
        this.description = "Sucks liquid materials nearby and releases them upon expiring";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0, 0.7, 0.4, 0.3, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 40;
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
		id          = "VACUUM_LIQUID",
		name 		= "$action_vacuum_liquid",
		description = "$actiondesc_vacuum_liquid",
		sprite 		= "data/ui_gfx/gun_actions/vacuum_liquid.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/chaos_polymorph_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/vacuum_liquid.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,4,5,6", -- PROJECTILE_GRAVITY_FIELD
		spawn_probability                 = "0.3,0.7,0.4,0.3", -- PROJECTILE_GRAVITY_FIELD
		price = 150,
		mana = 40,
		max_uses = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/vacuum_liquid.xml")
			c.fire_rate_wait = c.fire_rate_wait + 10
		end,
	},
	},
*/
}
