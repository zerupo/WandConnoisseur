package org.example.spells;

import org.example.main.*;

public class GRENADE_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Firebolt with trigger";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade_trigger.png";
        //this.emote = "";
        this.description = "A bouncy";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0.5, 0.2, 0.5, 0.5, 1, 0, 0, 0, 0, 0);
        this.price = 210;
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
		id          = "GRENADE_TRIGGER",
		name 		= "$action_grenade_trigger",
		description = "$actiondesc_grenade_trigger",
		sprite 		= "data/ui_gfx/gun_actions/grenade_trigger.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/grenade_trigger_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/grenade.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                         = "0,1,2,3,4,5", -- GRENADE_TRIGGER
		spawn_probability                   = "0.5,0.5,0.2,0.5,0.5,1", -- GRENADE_TRIGGER
		price = 210,
		max_uses    = 25, 
		custom_xml_file = "data/entities/misc/custom_cards/grenade_trigger.xml",
		mana = 50,
		action 		= function()
			c.fire_rate_wait = c.fire_rate_wait + 30
			c.screenshake = c.screenshake + 4.0
			--current_reload_time = current_reload_time + 60
			c.child_speed_multiplier = c.child_speed_multiplier * 0.75
			add_projectile_trigger_hit_world("data/entities/projectiles/deck/grenade.xml", 1)
			shot_effects.recoil_knockback = 80.0
		end,
	},
	},
*/
}
