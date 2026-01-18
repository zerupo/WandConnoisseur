package org.example.spells;

import org.example.main.*;

public class LASER_EMITTER_RAY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Plasma Beam Thrower";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "laser_emitter_ray.png";
        //this.emote = "";
        this.description = "A projectile fires plasma beams in all directions!";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 110;
        this.hasCharges = true;
        this.maxCharges = 16;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LASER_EMITTER_RAY",
		name 		= "$action_laser_emitter_ray",
		description = "$actiondesc_laser_emitter_ray",
		sprite 		= "data/ui_gfx/gun_actions/laser_emitter_ray.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/laser_emitter_ray.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5", -- TENTACLE_RAY
		spawn_probability                 = "0,0.1,0.4,0.4,0.4", -- TENTACLE_RAY
		price = 150,
		mana = 110,
		max_uses = 16,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/laser_emitter_ray.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
