package org.example.spells;

import org.example.main.*;

public class SPITTER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spitter bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spitter.png";
        //this.emote = "";
        this.description = "A short-lived magical bolt";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 1, 1, 0.5, 0, 0, 0, 0, 0, 0, 0);
        this.price = 110;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DECOY_TRIGGER",
		name 		= "$action_decoy_trigger",
		description = "$actiondesc_decoy_trigger",
		sprite 		= "data/ui_gfx/gun_actions/decoy_trigger.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/decoy_trigger_unidentified.png",
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "", -- DECOY_TRIGGER
		spawn_probability                 = "", -- DECOY_TRIGGER
		price = 150,
		mana = 80,
		max_uses    = 10, 
		custom_xml_file = "data/entities/misc/custom_cards/decoy_trigger.xml",
		action 		= function()
			add_projectile_trigger_death("data/entities/projectiles/deck/decoy_trigger.xml", 1)
			c.fire_rate_wait = c.fire_rate_wait + 40
		end,
	},]]--
		id          = "SPITTER",
		name 		= "$action_spitter",
		description = "$actiondesc_spitter",
		sprite 		= "data/ui_gfx/gun_actions/spitter.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spitter_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/spitter.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3", -- SPITTER
		spawn_probability                 = "1,1,1,0.5", -- SPITTER
		price = 110,
		mana = 5,
		--max_uses = 120,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/spitter.xml")
			-- damage = 0.1
			c.fire_rate_wait = c.fire_rate_wait - 1
			c.screenshake = c.screenshake + 0.1
			c.dampening = 0.1
			c.spread_degrees = c.spread_degrees + 6.0
		end,
	},
	},
*/
}
