package org.example.spells;

import org.example.main.*;

public class MISSILE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon missile";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "missile.png";
        //this.emote = "";
        this.description = "A missile!!!";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.5, 1, 0, 1, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 60;
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
		id          = "MISSILE",
		name 		= "$action_missile",
		description = "$actiondesc_missile",
		sprite 		= "data/ui_gfx/gun_actions/missile.png",
		related_projectiles	= {"data/entities/projectiles/deck/rocket_player.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,5", -- MISSILE
		spawn_probability                        = "0.5,0.5,1,1", -- MISSILE
		price = 200,
		mana = 60,
		max_uses    = 20, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/rocket_player.xml")
			current_reload_time = current_reload_time + 30
			c.spread_degrees = c.spread_degrees + 3.0
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 60.0
		end,
	},
	},
*/
}
