package org.example.spells;

import org.example.main.*;

public class METEOR_RAIN extends Spell{
    @Override
    protected void initialization(){
        this.name = "Meteorisade";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "meteor_rain.png";
        //this.emote = "";
        this.description = "Alea iacta est";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0.1, 0, 0, 0, 1);
        this.price = 300;
        this.manaCost = 225;
        this.hasCharges = true;
        this.maxCharges = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "METEOR_RAIN",
		name 		= "$action_meteor_rain",
		description = "$actiondesc_meteor_rain",
		sprite 		= "data/ui_gfx/gun_actions/meteor_rain.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= { "data/entities/projectiles/deck/meteor_rain_meteor.xml" },
		related_extra_entities = { "data/entities/misc/effect_meteor_rain.xml" },
		spawn_requires_flag = "card_unlocked_rain",
		never_unlimited		= true,
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "6,10", -- BOMB
		spawn_probability                 = "0.1,1", -- BOMB
		price = 300,
		mana = 225, 
		max_uses    = 2, 
		custom_xml_file = "data/entities/misc/custom_cards/meteor_rain.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/meteor_rain.xml")
			c.extra_entities = c.extra_entities .. "data/entities/misc/effect_meteor_rain.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 100
			current_reload_time = current_reload_time + 60
		end,
	},
	},
*/
}
