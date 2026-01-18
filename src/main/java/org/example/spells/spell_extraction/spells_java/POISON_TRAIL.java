package org.example.spells;

import org.example.main.*;

public class POISON_TRAIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Poison trail";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "poison_trail.png";
        //this.emote = "";
        this.description = "Gives a projectile a trail of poison";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.3, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "POISON_TRAIL",
		name 		= "$action_poison_trail",
		description = "$actiondesc_poison_trail",
		sprite 		= "data/ui_gfx/gun_actions/poison_trail.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/poison_trail_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- POISON_TRAIL
		spawn_probability                 = "0.3,0.3,0.3", -- POISON_TRAIL
		price = 160,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/poison_trail.xml",
		action 		= function()
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_apply_poison.xml,"
			c.trail_material = c.trail_material .. "poison,"
			c.trail_material_amount = c.trail_material_amount + 9
			draw_actions( 1, true )
		end,
	},
	},
*/
}
