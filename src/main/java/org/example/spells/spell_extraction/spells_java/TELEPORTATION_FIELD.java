package org.example.spells;

import org.example.main.*;

public class TELEPORTATION_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of displacement";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "teleportation_field.png";
        //this.emote = "";
        this.description = "A field of teleportative magic";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0.6, 0.3, 0.3, 0.6, 0.3, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 30;
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
		id          = "TELEPORTATION_FIELD",
		name 		= "$action_teleportation_field",
		description = "$actiondesc_teleportation_field",
		sprite 		= "data/ui_gfx/gun_actions/teleportation_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleportation_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/teleportation_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5", -- TELEPORTATION_FIELD
		spawn_probability                 = "0.3,0.6,0.3,0.3,0.6,0.3", -- TELEPORTATION_FIELD
		price = 150,
		mana = 30,
		max_uses = 15,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/teleportation_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
