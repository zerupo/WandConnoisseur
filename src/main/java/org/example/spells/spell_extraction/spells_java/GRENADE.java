package org.example.spells;

import org.example.main.*;

public class GRENADE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Firebolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade.png";
        //this.emote = "";
        this.description = "A bouncy";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 1, 0.5, 0.25, 0.2, 0, 0, 0, 0, 0, 0);
        this.price = 170;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 25;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "GRENADE",
		name 		= "$action_grenade",
		description = "$actiondesc_grenade",
		sprite 		= "data/ui_gfx/gun_actions/grenade.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/grenade_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/grenade.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4", -- GRENADE
		spawn_probability                 = "1,1,0.5,0.25,0.2", -- GRENADE
		price = 170,
		mana = 50,
		max_uses    = 25, 
		custom_xml_file = "data/entities/misc/custom_cards/grenade.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/grenade.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
			c.screenshake = c.screenshake + 4.0
			c.child_speed_multiplier = c.child_speed_multiplier * 0.75
			--current_reload_time = current_reload_time + 40
			shot_effects.recoil_knockback = 80.0
		end,
	},
	},
*/
}
