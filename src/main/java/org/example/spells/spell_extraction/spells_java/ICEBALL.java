package org.example.spells;

import org.example.main.*;

public class ICEBALL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Iceball";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "iceball.png";
        //this.emote = "";
        this.description = "A magical ball of frozen fire";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.8, 0.9, 0.9, 0, 0.6, 0, 0, 0, 0);
        this.price = 260;
        this.manaCost = 90;
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
		id          = "ICEBALL",
		name 		= "$action_iceball",
		description = "$actiondesc_iceball",
		sprite 		= "data/ui_gfx/gun_actions/iceball.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/fireball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/iceball.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,6", -- FIREBALL
		spawn_probability                 = "0.8,0.9,0.9,0.6", -- FIREBALL
		price = 260,
		mana = 90,
		max_uses = 15,
		custom_xml_file = "data/entities/misc/custom_cards/iceball.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/iceball.xml")
			c.spread_degrees = c.spread_degrees + 8.0
			c.fire_rate_wait = c.fire_rate_wait + 80
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 20.0
		end,
	},
	},
*/
}
