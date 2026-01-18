package org.example.spells;

import org.example.main.*;

public class LIGHTNING_RAY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Lightning thrower";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "lightning_ray.png";
        //this.emote = "";
        this.description = "Makes a projectile cast lightning in random directions";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 110;
        this.hasCharges = true;
        this.maxCharges = 16;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIGHTNING_RAY",
		name 		= "$action_lightning_ray",
		description = "$actiondesc_lightning_ray",
		sprite 		= "data/ui_gfx/gun_actions/lightning_ray.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/lightning_ray.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5", -- LIGHTNING_RAY
		spawn_probability                 = "0,0.2,0.4,0.4,0.4", -- LIGHTNING_RAY
		price = 180,
		mana = 110,
		max_uses = 16,
		custom_xml_file = "data/entities/misc/custom_cards/electric_charge.xml",
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/lightning_ray.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
