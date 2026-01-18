package org.example.spells;

import org.example.main.*;

public class FRIEND_FLY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon Friendly fly";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "friend_fly.png";
        //this.emote = "";
        this.description = "Summons a friendly fly that attacks your enemies!";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.2, 0.6, 0.5, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 120;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FRIEND_FLY",
		name 		= "$action_friend_fly",
		description = "$actiondesc_friend_fly",
		sprite 		= "data/ui_gfx/gun_actions/friend_fly.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spiral_shot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/friend_fly.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "4,5,6", -- SPIRAL_SHOT
		spawn_probability                 = "0.2,0.6,0.5", -- SPIRAL_SHOT
		price = 160,
		mana = 120,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/friend_fly.xml")
			c.spread_degrees = c.spread_degrees + 24.0
			c.fire_rate_wait = c.fire_rate_wait + 80
			current_reload_time = current_reload_time + 40
		end,
	},
	},
*/
}
