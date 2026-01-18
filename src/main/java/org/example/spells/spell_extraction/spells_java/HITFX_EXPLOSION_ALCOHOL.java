package org.example.spells;

import org.example.main.*;

public class HITFX_EXPLOSION_ALCOHOL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Explosion on drunk enemies";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "explode_on_alcohol.png";
        //this.emote = "";
        this.description = "Makes a projectile explode upon collision with creatures covered in alcohol";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0, 0.2, 0.2, 0.2, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HITFX_EXPLOSION_ALCOHOL",
		name 		= "$action_hitfx_explosion_alcohol",
		description = "$actiondesc_hitfx_explosion_alcohol",
		sprite 		= "data/ui_gfx/gun_actions/explode_on_alcohol.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_explode_alcohol.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_EXPLOSION_ALCOHOL
		spawn_probability                 = "0.3,0.2,0.2,0.2", -- HITFX_EXPLOSION_ALCOHOL
		price = 140,
		mana = 20,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_explode_alcohol.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
