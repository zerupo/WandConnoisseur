package org.example.spells;

import org.example.main.*;

public class FIRE_TRAIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Fire trail";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fire_trail.png";
        //this.emote = "";
        this.description = "Gives a projectile a trail of fiery particles";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0.4, 0.5, 0.3, 0.3, 0.2, 0, 0, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FIRE_TRAIL",
		name 		= "$action_fire_trail",
		description = "$actiondesc_fire_trail",
		sprite 		= "data/ui_gfx/gun_actions/fire_trail.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/fire_trail_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "0,1,2,3,4", -- FIRE_TRAIL
		spawn_probability                 = "0.4,0.5,0.3,0.3,0.2", -- FIRE_TRAIL
		price = 130,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/fire_trail.xml",
		action 		= function()
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_apply_on_fire.xml,"
			c.trail_material = c.trail_material .. "fire,"
			c.trail_material_amount = c.trail_material_amount + 10
			draw_actions( 1, true )
		end,
	},
	},
*/
}
