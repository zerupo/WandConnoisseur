package org.example.spells;

import org.example.main.*;

public class LIFETIME extends Spell{
    @Override
    protected void initialization(){
        this.name = "Increase lifetime";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "lifetime.png";
        //this.emote = "";
        this.description = "Increases the lifetime of a spell";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.5, 0.5, 0.5, 0.75, 0, 0, 0, 0.1);
        this.price = 250;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIFETIME",
		name 		= "$action_lifetime",
		description = "$actiondesc_lifetime",
		sprite 		= "data/ui_gfx/gun_actions/lifetime.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,4,5,6,10", -- LIFETIME
		spawn_probability                 = "0.5,0.5,0.5,0.75,0.1", -- LIFETIME
		price = 250,
		mana = 40,
		--max_uses = 150,
		custom_xml_file = "data/entities/misc/custom_cards/lifetime.xml",
		action 		= function()
			c.lifetime_add 		= c.lifetime_add + 75
			c.fire_rate_wait = c.fire_rate_wait + 13
			draw_actions( 1, true )
		end,
	},
	},
*/
}
