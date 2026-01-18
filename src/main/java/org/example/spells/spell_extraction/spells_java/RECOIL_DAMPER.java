package org.example.spells;

import org.example.main.*;

public class RECOIL_DAMPER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Recoil Damper";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "recoil_damper.png";
        //this.emote = "";
        this.description = "Reduces the recoil when casting spells";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.6, 0, 0, 0.7, 0, 0, 0, 0);
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
		id          = "RECOIL_DAMPER",
		name 		= "$action_recoil_damper",
		description = "$actiondesc_recoil_damper",
		sprite 		= "data/ui_gfx/gun_actions/recoil_damper.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/recoil_damper_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,6", -- RECOIL_DAMPER
		spawn_probability                 = "0.6,0.7", -- RECOIL_DAMPER
		price = 100,
		mana = 5,
		--max_uses = 150,
		action 		= function()
			shot_effects.recoil_knockback = shot_effects.recoil_knockback - 200
			draw_actions( 1, true )
		end,
	},
	},
*/
}
