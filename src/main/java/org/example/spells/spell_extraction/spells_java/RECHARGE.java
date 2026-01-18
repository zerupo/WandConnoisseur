package org.example.spells;

import org.example.main.*;

public class RECHARGE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Reduce recharge time";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "recharge.png";
        //this.emote = "";
        this.description = "Reduces the time between spellcasts";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.8, 0.9, 1, 0.8, 0.9, 1, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 12;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "RECHARGE",
		name 		= "$action_recharge",
		description = "$actiondesc_recharge",
		sprite 		= "data/ui_gfx/gun_actions/recharge.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5,6", -- RECHARGE
		spawn_probability                 = "0.8,0.9,1,0.8,0.9,1", -- RECHARGE
		price = 200,
		mana = 12,
		--max_uses = 150,
		action 		= function()
			c.fire_rate_wait    = c.fire_rate_wait - 10
			current_reload_time = current_reload_time - 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
