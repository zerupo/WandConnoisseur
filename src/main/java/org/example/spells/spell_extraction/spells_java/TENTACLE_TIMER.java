package org.example.spells;

import org.example.main.*;

public class TENTACLE_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Tentacle with timer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "tentacle_timer.png";
        //this.emote = "";
        this.description = "Calls a terrifying appendage from another dimension! Comes with a timer";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.6, 0.8, 0.6, 0.7, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TENTACLE_TIMER",
		name 		= "$action_tentacle_timer",
		description = "$actiondesc_tentacle_timer",
		spawn_requires_flag = "card_unlocked_tentacle",
		sprite 		= "data/ui_gfx/gun_actions/tentacle_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/tentacle_timer_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/tentacle.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "3,4,5,6", -- TENTACLE_TIMER
		spawn_probability                 = "0.6,0.8,0.6,0.7", -- TENTACLE_TIMER
		price = 250,
		mana = 20,
		--max_uses = 40,
		custom_xml_file = "data/entities/misc/custom_cards/tentacle_timer.xml",
		action 		= function()
			add_projectile_trigger_timer("data/entities/projectiles/deck/tentacle.xml",20,1)
			c.fire_rate_wait = c.fire_rate_wait + 40
		end,
	},
	},
*/
}
