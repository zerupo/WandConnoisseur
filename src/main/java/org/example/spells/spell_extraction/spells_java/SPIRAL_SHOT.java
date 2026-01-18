package org.example.spells;

import org.example.main.*;

public class SPIRAL_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spiral shot";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "spiral_shot.png";
        //this.emote = "";
        this.description = "A mystical whirlwind of magic sparks";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.7, 0.8, 0.7, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SPIRAL_SHOT",
		name 		= "$action_spiral_shot",
		description = "$actiondesc_spiral_shot",
		spawn_requires_flag = "card_unlocked_spiral_shot",
		sprite 		= "data/ui_gfx/gun_actions/spiral_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spiral_shot_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/spiral_shot.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "4,5,6", -- SPIRAL_SHOT
		spawn_probability                 = "0.7,0.8,0.7", -- SPIRAL_SHOT
		price = 190,
		mana = 50,
		max_uses    = 15, 
		custom_xml_file = "data/entities/misc/custom_cards/spiral_shot.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/spiral_shot.xml")
			c.fire_rate_wait = c.fire_rate_wait + 20
		end,
	},
	},
*/
}
