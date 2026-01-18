package org.example.spells;

import org.example.main.*;

public class VACUUM_POWDER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Powder Vacuum Field";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "vacuum_powder.png";
        //this.emote = "";
        this.description = "Sucks powder-like materials nearby and releases them upon expiring";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.7, 0, 0.3, 0.4, 0, 0, 0, 0);
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
		id          = "VACUUM_POWDER",
		name 		= "$action_vacuum_powder",
		description = "$actiondesc_vacuum_powder",
		sprite 		= "data/ui_gfx/gun_actions/vacuum_powder.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/chaos_polymorph_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/vacuum_powder.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "2,3,5,6", -- PROJECTILE_GRAVITY_FIELD
		spawn_probability                 = "0.3,0.7,0.3,0.4", -- PROJECTILE_GRAVITY_FIELD
		price = 150,
		mana = 40,
		max_uses = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/vacuum_powder.xml")
			c.fire_rate_wait = c.fire_rate_wait + 10
		end,
	},
	},
*/
}
