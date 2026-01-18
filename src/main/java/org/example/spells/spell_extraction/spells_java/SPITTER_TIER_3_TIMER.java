package org.example.spells;

import org.example.main.*;

public class SPITTER_TIER_3_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giant spitter bolt with timer";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spitter_purple_timer.png";
        //this.emote = "";
        this.description = "The most powerful version of Spitter Bolt that casts another spell after a timer runs out";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.5, 0.65, 0.5, 0, 0, 0, 0);
        this.price = 260;
        this.manaCost = 45;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SPITTER_TIER_3_TIMER",
		name 		= "$action_spitter_tier_3_timer",
		description = "$actiondesc_spitter_tier_3_timer",
		sprite 		= "data/ui_gfx/gun_actions/spitter_purple_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spitter_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/spitter_tier_3.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "4,5,6", -- SPITTER_TIER_3_TIMER
		spawn_probability                 = "0.5,0.65,0.5", -- SPITTER_TIER_3_TIMER
		price = 260,
		mana = 45,
		--max_uses = 120,
		action 		= function()
			add_projectile_trigger_timer("data/entities/projectiles/deck/spitter_tier_3.xml", 40, 1)
			-- damage = 0.1
			c.fire_rate_wait = c.fire_rate_wait - 4
			c.screenshake = c.screenshake + 3.1
			c.dampening = 0.3
			c.spread_degrees = c.spread_degrees + 9.0
		end,
	},
	},
*/
}
