package org.example.spells;

import org.example.main.*;

public class MATERIAL_CEMENT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Cement";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "material_cement.png";
        //this.emote = "";
        this.description = "Transmute drops of wet cement from nothing";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "MATERIAL_CEMENT",
		name 		= "$action_material_cement",
		description = "$actiondesc_material_cement",
		spawn_requires_flag = "card_unlocked_material_cement",
		sprite 		= "data/ui_gfx/gun_actions/material_cement.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/material_cement_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/material_cement.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "2,3,4,5,6", -- MATERIAL_CEMENT
		spawn_probability                 = "0.4,0.4,0.4,0.4,0.4", -- MATERIAL_CEMENT
		price = 100,
		-- Note( Petri ): 10.7.2019 - removed uses. We have acid trail already
		-- max_uses = 250,
		mana = 0,
		sound_loop_tag = "sound_spray",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/material_cement.xml")
			c.fire_rate_wait = c.fire_rate_wait - 15
			current_reload_time = current_reload_time - ACTION_DRAW_RELOAD_TIME_INCREASE - 10 -- this is a hack to get the cement reload time back to 0
		end,
	},
	},
*/
}
