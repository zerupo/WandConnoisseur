package org.example.spells;

import org.example.main.*;

public class BUBBLESHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Bubble spark";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bubbleshot.png";
        //this.emote = "";
        this.description = "A bouncy, inaccurate spell";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 0.6, 1, 0.5, 0, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BUBBLESHOT",
		name 		= "$action_bubbleshot",
		description = "$actiondesc_bubbleshot",
		sprite 		= "data/ui_gfx/gun_actions/bubbleshot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bubbleshot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bubbleshot.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3", -- BUBBLESHOT
		spawn_probability                 = "1,0.6,1,0.5", -- BUBBLESHOT
		price = 100,
		mana = 5,
		--max_uses = 120,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/bubbleshot.xml")
			-- damage = 0.1
			c.fire_rate_wait = c.fire_rate_wait - 5
			c.dampening = 0.1
		end,
	},
	},
*/
}
