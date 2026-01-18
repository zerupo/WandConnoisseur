package org.example.spells;

import org.example.main.*;

public class GUNPOWDER_TRAIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Gunpowder trail";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "gunpowder_trail.png";
        //this.emote = "";
        this.description = "Gives a projectile a trail of gunpowder";
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
		id          = "BLOOD_TRAIL",
		name 		= "$action_blood_trail",
		description = "$actiondesc_blood_trail",
		sprite 		= "data/ui_gfx/gun_actions/blood_trail.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/oil_trail_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "", -- BLOOD_TRAIL
		spawn_probability                 = "", -- BLOOD_TRAIL
		price = 160,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/blood_trail.xml",
		action 		= function()
			c.trail_material = c.trail_material .. "blood,"
			c.trail_material_amount = c.trail_material_amount + 20
			draw_actions( 1, true )
		end,
	},]]--
		id          = "GUNPOWDER_TRAIL",
		name 		= "$action_gunpowder_trail",
		description = "$actiondesc_gunpowder_trail",
		sprite 		= "data/ui_gfx/gun_actions/gunpowder_trail.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/oil_trail_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4", -- GUNPOWDER_TRAIL
		spawn_probability                 = "0.3,0.3,0.3", -- GUNPOWDER_TRAIL
		price = 160,
		mana = 10,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/gunpowder_trail.xml",
		action 		= function()
			c.trail_material = c.trail_material .. "gunpowder,"
			c.trail_material_amount = c.trail_material_amount + 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
