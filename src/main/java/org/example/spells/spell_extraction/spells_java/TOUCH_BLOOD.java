package org.example.spells;

import org.example.main.*;

public class TOUCH_BLOOD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Touch of Blood";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "touch_blood.png";
        //this.emote = "";
        this.description = "Transmutes everything in a short radius into blood";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0, 0.5);
        this.price = 390;
        this.manaCost = 270;
        this.hasCharges = true;
        this.maxCharges = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TOUCH_BLOOD",
		name 		= "$action_touch_blood",
		description = "$actiondesc_touch_blood",
		sprite 		= "data/ui_gfx/gun_actions/touch_blood.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/touch_blood.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4,5,6,7,10", -- TOUCH_BLOOD
		spawn_probability                 = "0,0,0,0,0.1,0.1,0.1,0.5", -- TOUCH_BLOOD
		price = 390,
		mana = 270,
		max_uses    = 3, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/touch_blood.xml")
		end,
	},
	},
*/
}
