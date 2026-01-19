package org.example.spells;

import org.example.main.*;

public class TOUCH_GRASS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Touch of Grass";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "touch_grass.png";
        //this.emote = "";
        this.description = "Transmutes everything in a short radius into Earth, including walls, creatures... and you. Unless…";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0, 0.2);
        this.price = 360;
        this.manaCost = 190;
        this.hasCharges = true;
        this.maxCharges = 4;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TOUCH_GRASS",
		name 		= "$action_touch_grass",
		description = "$actiondesc_touch_grass",
		sprite 		= "data/ui_gfx/gun_actions/touch_grass.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/touch_grass.xml"},
		spawn_requires_flag = "card_unlocked_touch_grass",
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4,5,6,7,10", -- TOUCH_GRASS
		spawn_probability                 = "0,0,0,0,0.1,0.1,0.1,0.2", -- TOUCH_GRASS
		spawn_requires_flag = "card_unlocked_touch_grass",
		price = 360,
		mana = 190,
		max_uses    = 4, 
		never_unlimited = true,
		custom_xml_file = "data/entities/misc/custom_cards/touch_grass.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/touch_grass.xml")
		end,
	},
	},
*/
}
