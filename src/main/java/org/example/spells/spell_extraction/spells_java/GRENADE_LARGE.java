package org.example.spells;

import org.example.main.*;

public class GRENADE_LARGE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Dropper bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade_large.png";
        //this.emote = "";
        this.description = "A very heavy explosive bolt";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 80;
        this.hasCharges = true;
        this.maxCharges = 35;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "GRENADE_LARGE",
		name 		= "$action_grenade_large",
		description = "$actiondesc_grenade_large",
		sprite 		= "data/ui_gfx/gun_actions/grenade_large.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/grenade_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/grenade_large.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5", -- GRENADE_LARGE
		spawn_probability                 = "0.4,0.4,0.4,0.4,0.4,0.4", -- GRENADE_LARGE
		price = 150,
		mana = 80,
		max_uses    = 35, 
		custom_xml_file = "data/entities/misc/custom_cards/grenade.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/grenade_large.xml")
			c.fire_rate_wait = c.fire_rate_wait + 40
			c.screenshake = c.screenshake + 5.0
			c.child_speed_multiplier = c.child_speed_multiplier * 0.75
			--current_reload_time = current_reload_time + 40
			shot_effects.recoil_knockback = 80.0
		end,
	},
	},
*/
}
