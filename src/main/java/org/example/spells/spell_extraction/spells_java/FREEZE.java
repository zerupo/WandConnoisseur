package org.example.spells;

import org.example.main.*;

public class FREEZE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Freeze charge";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "freeze.png";
        //this.emote = "";
        this.description = "Gives a projectile a frozen charge";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 0, 1, 0.9, 0.8, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FREEZE",
		name 		= "$action_freeze",
		description = "$actiondesc_freeze",
		sprite 		= "data/ui_gfx/gun_actions/freeze.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/particles/freeze_charge.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- FREEZE
		spawn_probability                 = "1,1,0.9,0.8", -- FREEZE
		price = 140,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/freeze.xml",
		action 		= function()
			c.damage_ice_add = c.damage_ice_add + 0.2
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_frozen.xml,"
			c.extra_entities = c.extra_entities .. "data/entities/particles/freeze_charge.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
