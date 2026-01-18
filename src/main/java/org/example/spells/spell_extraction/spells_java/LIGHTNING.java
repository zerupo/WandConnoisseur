package org.example.spells;

import org.example.main.*;

public class LIGHTNING extends Spell{
    @Override
    protected void initialization(){
        this.name = "Lightning bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "lightning.png";
        //this.emote = "";
        this.description = "The primordial force of nature";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 0.9, 0, 0, 0.7, 1, 0, 0, 0, 0);
        this.price = 250;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LIGHTNING",
		name 		= "$action_lightning",
		description = "$actiondesc_lightning",
		sprite 		= "data/ui_gfx/gun_actions/lightning.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/lightning_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/lightning.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,5,6", -- LIGHTNING
		spawn_probability                 = "1,0.9,0.7,1", -- LIGHTNING
		price = 250,
		mana = 70,
		--max_uses = 30,
		custom_xml_file = "data/entities/misc/custom_cards/electric_charge.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/lightning.xml")
			c.fire_rate_wait = c.fire_rate_wait + 50
			shot_effects.recoil_knockback = 180.0
		end,
	},
	},
*/
}
