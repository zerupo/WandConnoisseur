package org.example.spells;

import org.example.main.*;

public class ARROW extends Spell{
    @Override
    protected void initialization(){
        this.name = "Arrow";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "arrow.png";
        //this.emote = "";
        this.description = "Summons an arrow";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 1, 0, 0.6, 0.3, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ARROW",
		name 		= "$action_arrow",
		description = "$actiondesc_arrow",
		sprite 		= "data/ui_gfx/gun_actions/arrow.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/arrow_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/arrow.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,4,5", -- ARROW
		spawn_probability                 = "1,1,0.6,0.3", -- ARROW
		price = 140,
		mana = 15,
		--max_uses = 40,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/arrow.xml")
			-- damage = 0.3
			c.fire_rate_wait = c.fire_rate_wait + 10
			c.spread_degrees = c.spread_degrees - 20
			shot_effects.recoil_knockback = 30.0
		end,
	},
	},
*/
}
