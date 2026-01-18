package org.example.spells;

import org.example.main.*;

public class PIPE_BOMB extends Spell{
    @Override
    protected void initialization(){
        this.name = "Dormant crystal";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "pipe_bomb.png";
        //this.emote = "";
        this.description = "A crystal that explodes when caught in an explosion";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 1, 1, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 20;
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
		id 			= "PIPE_BOMB",
		name 		= "$action_pipe_bomb",
		description = "$actiondesc_pipe_bomb",
		sprite 		= "data/ui_gfx/gun_actions/pipe_bomb.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/pipe_bomb_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/pipe_bomb.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level	           = "2,3,4", -- PIPE_BOMB
		spawn_probability	   = "1,1,0.6", -- PIPE_BOMB
		price = 200,
		mana = 20,
		max_uses	= 20, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/pipe_bomb.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
			c.child_speed_multiplier = c.child_speed_multiplier * 0.75
			c.speed_multiplier = c.speed_multiplier * 0.75
			
			if ( c.speed_multiplier >= 20 ) then
				c.speed_multiplier = math.min( c.speed_multiplier, 20 )
			elseif ( c.speed_multiplier < 0 ) then
				c.speed_multiplier = 0
			end
		end,
	},
	},
*/
}
