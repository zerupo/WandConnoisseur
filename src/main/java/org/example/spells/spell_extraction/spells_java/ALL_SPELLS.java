package org.example.spells;

import org.example.main.*;

public class ALL_SPELLS extends Spell{
    @Override
    protected void initialization(){
        this.name = "The end of everything";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "all_spells.png";
        //this.emote = "";
        this.description = "You're heavily advised not to cast this spell.";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        this.price = 1000;
        this.manaCost = 600;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ALL_SPELLS",
		name 		= "$action_all_spells",
		description = "$actiondesc_all_spells",
		sprite 		= "data/ui_gfx/gun_actions/all_spells.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		spawn_requires_flag = "card_unlocked_everything",
		spawn_manual_unlock = true,
		never_unlimited		= true,
		type 		= ACTION_TYPE_OTHER,
		recursive	= true,
		ai_never_uses = true,
		spawn_level                       = "10", -- MANA_REDUCE
		spawn_probability                 = "1", -- MANA_REDUCE
		price = 1000,
		mana = 600,
		max_uses    = 1,
		action 		= function()
			local players = EntityGetWithTag( "player_unit" )
			for i,v in ipairs( players ) do
				local x,y = EntityGetTransform( v )
				local eid = EntityLoad("data/entities/projectiles/deck/all_spells_loader.xml", x, y)
			end
			c.fire_rate_wait = c.fire_rate_wait + 100
			current_reload_time = current_reload_time + 100
		end,
	},
	},
*/
}
