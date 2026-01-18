package org.example.spells;

import org.example.main.*;

public class SPITTER_TIER_2 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Large spitter bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spitter_green.png";
        //this.emote = "";
        this.description = "A more powerful version of Spitter Bolt";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 1, 1, 1, 0.5, 0, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 25;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SPITTER_TIER_2",
		name 		= "$action_spitter_tier_2",
		description = "$actiondesc_spitter_tier_2",
		sprite 		= "data/ui_gfx/gun_actions/spitter_green.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spitter_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/spitter_tier_2.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5", -- SPITTER_TIER_2
		spawn_probability                 = "1,1,1,0.5", -- SPITTER_TIER_2
		price = 190,
		mana = 25,
		--max_uses = 120,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/spitter_tier_2.xml")
			-- damage = 0.1
			c.fire_rate_wait = c.fire_rate_wait - 2
			c.screenshake = c.screenshake + 1.1
			c.dampening = 0.2
			c.spread_degrees = c.spread_degrees + 7.5
		end,
	},
	},
*/
}
