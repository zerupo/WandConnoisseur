package org.example.spells;

import org.example.main.*;

public class TOUCH_ALCOHOL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Touch of Spirits";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "touch_alcohol.png";
        //this.emote = "";
        this.description = "Transmutes everything in a short radius into alcohol, including walls, creatures... and you";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0, 0.4);
        this.price = 360;
        this.manaCost = 240;
        this.hasCharges = true;
        this.maxCharges = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TOUCH_ALCOHOL",
		name 		= "$action_touch_alcohol",
		description = "$actiondesc_touch_alcohol",
		sprite 		= "data/ui_gfx/gun_actions/touch_alcohol.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/touch_alcohol.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4,5,6,7,10", -- TOUCH_ALCOHOL
		spawn_probability                 = "0,0,0,0,0.1,0.1,0.1,0.4", -- TOUCH_ALCOHOL
		price = 360,
		mana = 240,
		max_uses    = 5, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/touch_alcohol.xml")
		end,
	},
	},
*/
}
