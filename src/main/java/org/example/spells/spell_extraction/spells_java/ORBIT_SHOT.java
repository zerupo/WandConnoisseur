package org.example.spells;

import org.example.main.*;

public class ORBIT_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Orbiting Arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "orbit_shot.png";
        //this.emote = "";
        this.description = "A projectile orbits the point of its origin";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0.4, 0.4, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 30;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ORBIT_SHOT",
		name 		= "$action_orbit_shot",
		description = "$actiondesc_orbit_shot",
		sprite 		= "data/ui_gfx/gun_actions/orbit_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/spiraling_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4", -- HORIZONTAL_ARC
		spawn_probability                 = "0.2,0.4,0.4,0.3", -- HORIZONTAL_ARC
		price = 30,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/spiraling_shot.xml,"
			draw_actions( 1, true )
			c.damage_projectile_add = c.damage_projectile_add + 0.1
			c.fire_rate_wait    = c.fire_rate_wait - 6
			c.lifetime_add 		= c.lifetime_add + 25
		end,
	},
	},
*/
}
