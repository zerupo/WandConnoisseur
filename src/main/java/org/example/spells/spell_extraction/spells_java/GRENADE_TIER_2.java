package org.example.spells;

import org.example.main.*;

public class GRENADE_TIER_2 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Large firebolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade_tier_2.png";
        //this.emote = "";
        this.description = "A more powerful version of Firebolt";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 1, 1, 1, 0.5, 0, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 90;
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
		id          = "GRENADE_TIER_2",
		name 		= "$action_grenade_tier_2",
		description = "$actiondesc_grenade_tier_2",
		sprite 		= "data/ui_gfx/gun_actions/grenade_tier_2.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/grenade_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/grenade_tier_2.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5", -- GRENADE_TIER_2
		spawn_probability                 = "0.5,1,1,1,0.5", -- GRENADE_TIER_2
		price = 220,
		mana = 90,
		max_uses    = 20, 
		custom_xml_file = "data/entities/misc/custom_cards/grenade_tier_2.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/grenade_tier_2.xml")
			c.fire_rate_wait = c.fire_rate_wait + 50
			c.screenshake = c.screenshake + 8.0
			c.child_speed_multiplier = c.child_speed_multiplier * 0.75
			--current_reload_time = current_reload_time + 40
			shot_effects.recoil_knockback = 120.0
		end,
	},
	},
*/
}
