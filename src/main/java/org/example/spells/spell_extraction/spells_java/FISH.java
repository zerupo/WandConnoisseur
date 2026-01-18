package org.example.spells;

import org.example.main.*;

public class FISH extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon fish";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fish.png";
        //this.emote = "";
        this.description = "FISH!";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.01, 0.01, 0.01, 0, 0, 0, 0, 0);
        this.price = 250;
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
		id          = "FISH",
		name 		= "$action_fish",
		description = "$actiondesc_fish",
		spawn_requires_flag = "card_unlocked_fish",
		sprite 		= "data/ui_gfx/gun_actions/fish.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/fish_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/fish.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "3,4,5", -- FISH
		spawn_probability                 = "0.01,0.01,0.01", -- FISH
		price = 250,
		mana = 90,
		max_uses    = 20, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/fish.xml")
			c.fire_rate_wait = c.fire_rate_wait + 80
		end,
	},
	},
*/
}
