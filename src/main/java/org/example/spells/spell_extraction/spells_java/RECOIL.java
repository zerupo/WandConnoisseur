package org.example.spells;

import org.example.main.*;

public class RECOIL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Recoil";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "recoil.png";
        //this.emote = "";
        this.description = "Increases the recoil when casting spells";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.6, 0, 0.7, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "RECOIL",
		name 		= "$action_recoil",
		description = "$actiondesc_recoil",
		sprite 		= "data/ui_gfx/gun_actions/recoil.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/recoil_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,4", -- RECOIL
		spawn_probability                 = "0.6,0.7", -- RECOIL
		price = 100,
		mana = 5,
		--max_uses = 150,
		action 		= function()
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 200.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
