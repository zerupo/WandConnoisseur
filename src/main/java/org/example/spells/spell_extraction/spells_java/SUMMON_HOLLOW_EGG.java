package org.example.spells;

import org.example.main.*;

public class SUMMON_HOLLOW_EGG extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon hollow egg";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "summon_hollow_egg.png";
        //this.emote = "";
        this.description = "Summons an otherwise empty egg that casts a spell upon cracking open";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.6, 0.8, 0.7, 0, 0, 0.8, 0.3, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SUMMON_HOLLOW_EGG",
		name 		= "$action_summon_hollow_egg",
		description = "$actiondesc_summon_hollow_egg",
		sprite 		= "data/ui_gfx/gun_actions/summon_hollow_egg.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= {"data/entities/items/pickup/egg_hollow.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,5,6", -- SUMMON_HOLLOW_EGG
		spawn_probability                 = "0.6,0.8,0.7,0.8,0.3", -- SUMMON_HOLLOW_EGG
		price = 140,
		mana = 30, 
		action 		= function()
			add_projectile_trigger_death("data/entities/items/pickup/egg_hollow.xml", 1)
			c.fire_rate_wait = c.fire_rate_wait - 12
		end,
	},
	},
*/
}
