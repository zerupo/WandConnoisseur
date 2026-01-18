package org.example.spells;

import org.example.main.*;

public class RAINBOW_TRAIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Rainbow trail";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "rainbow_trail.png";
        //this.emote = "";
        this.description = "Gives a projectile a trail of rainbow";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "RAINBOW_TRAIL",
		name 		= "$action_rainbow_trail",
		description = "$actiondesc_rainbow_trail",
		sprite 		= "data/ui_gfx/gun_actions/rainbow_trail.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/oil_trail_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "10", -- rainbow_trail
		spawn_probability                 = "0", -- rainbow_trail
		spawn_requires_flag = "card_unlocked_rainbow_trail",
		price = 100,
		mana = 0,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/rainbow_trail.xml",
		action 		= function()
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_rainbow_farts.xml,"
			c.trail_material = c.trail_material .. "material_rainbow,"
			c.trail_material_amount = c.trail_material_amount + 20
			draw_actions( 1, true )
		end,

	},
	},
*/
}
