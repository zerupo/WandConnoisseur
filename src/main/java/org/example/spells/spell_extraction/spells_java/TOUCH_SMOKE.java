package org.example.spells;

import org.example.main.*;

public class TOUCH_SMOKE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Touch of Smoke";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "touch_smoke.png";
        //this.emote = "";
        this.description = "Transmutes everything in a short radius into smoke";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0, 0, 0.4);
        this.price = 350;
        this.manaCost = 230;
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
		id          = "TOUCH_SMOKE",
		name 		= "$action_touch_smoke",
		description = "$actiondesc_touch_smoke",
		sprite 		= "data/ui_gfx/gun_actions/touch_smoke.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/touch_smoke.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4,5,6,7,10", -- TOUCH_SMOKE
		spawn_probability                 = "0,0,0,0,0.1,0.1,0.1,0.4", -- TOUCH_SMOKE
		price = 350,
		mana = 230,
		max_uses    = 5, 
		action 		= function()
			add_projectile("data/entities/projectiles/deck/touch_smoke.xml")
		end,
	},
	},
*/
}
