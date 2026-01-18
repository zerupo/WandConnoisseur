package org.example.spells;

import org.example.main.*;

public class HITFX_EXPLOSION_ALCOHOL_GIGA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giant explosion on drunk enemies";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "explode_on_alcohol_giga.png";
        //this.emote = "";
        this.description = "Makes a projectile explode powerfully upon collision with creatures covered in alcohol";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0, 0.1, 0.1, 0.3, 0, 0, 0, 0, 0);
        this.price = 300;
        this.manaCost = 200;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HITFX_EXPLOSION_ALCOHOL_GIGA",
		name 		= "$action_hitfx_explosion_alcohol_giga",
		description = "$actiondesc_hitfx_explosion_alcohol_giga",
		sprite 		= "data/ui_gfx/gun_actions/explode_on_alcohol_giga.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_explode_alcohol_giga.xml", "data/entities/particles/tinyspark_orange.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_EXPLOSION_ALCOHOL_GIGA
		spawn_probability                 = "0.1,0.1,0.1,0.3", -- HITFX_EXPLOSION_ALCOHOL_GIGA
		price = 300,
		mana = 200,
		max_uses = 20,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_explode_alcohol_giga.xml,data/entities/particles/tinyspark_orange.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
