package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_PIPE_BOMB;

public class PIPE_BOMB extends Spell{
    @Override
    protected void initialization(){
        this.name = "Dormant Crystal";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "pipe_bomb.png";
        this.emote = "<:pipe_bomb:1451342048270483456>";
        this.description = "A crystal that explodes when caught in an explosion";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_PIPE_BOMB();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 1, 1, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 20;
        this.castDelay = 30;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());

        /*c.child_speed_multiplier = c.child_speed_multiplier * 0.75
		c.speed_multiplier = c.speed_multiplier * 0.75

		if ( c.speed_multiplier >= 20 ) then
			c.speed_multiplier = math.min( c.speed_multiplier, 20 )
		elseif ( c.speed_multiplier < 0 ) then
			c.speed_multiplier = 0
		end*/
    }
}

/*{
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
}*/