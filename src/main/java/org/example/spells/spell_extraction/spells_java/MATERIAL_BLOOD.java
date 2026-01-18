package org.example.spells;

import org.example.main.*;

public class MATERIAL_BLOOD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Blood";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "material_blood.png";
        //this.emote = "";
        this.description = "Blood blood blood";
        this.type = SpellType.material;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 0;
        this.hasCharges = true;
        this.maxCharges = 250;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "MATERIAL_BLOOD",
		name 		= "$action_material_blood",
		description = "$actiondesc_material_blood",
		sprite 		= "data/ui_gfx/gun_actions/material_blood.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/material_blood_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/material_blood.xml"},
		type 		= ACTION_TYPE_MATERIAL,
		spawn_level                       = "1,2,3,4,5", -- MATERIAL_BLOOD
		spawn_probability                 = "0.4,0.4,0.4,0.4,0.4", -- MATERIAL_BLOOD
		price = 130,
		max_uses = 250,
		mana = 0,
		sound_loop_tag = "sound_spray",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/material_blood.xml")
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_apply_bloody.xml,"
			c.fire_rate_wait = c.fire_rate_wait - 15
			current_reload_time = current_reload_time - ACTION_DRAW_RELOAD_TIME_INCREASE - 10 -- this is a hack to get the cement reload time back to 0
		end,
	},
	},
*/
}
