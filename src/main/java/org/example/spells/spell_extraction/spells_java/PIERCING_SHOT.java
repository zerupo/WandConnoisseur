package org.example.spells;

import org.example.main.*;

public class PIERCING_SHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Piercing shot";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "piercing_shot.png";
        //this.emote = "";
        this.description = "Makes a projectile fly through enemies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.5, 0.6, 0.6, 0.4, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 140;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "PIERCING_SHOT",
		name 		= "$action_piercing_shot",
		description = "$actiondesc_piercing_shot",
		sprite 		= "data/ui_gfx/gun_actions/piercing_shot.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
		related_extra_entities = { "data/entities/misc/piercing_shot.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- PIERCING_SHOT
		spawn_probability                 = "0.4,0.5,0.6,0.6,0.4", -- PIERCING_SHOT
		price = 190,
		mana = 140,
		--max_uses = 100,
		action 		= function()
			c.damage_projectile_add = c.damage_projectile_add - 0.6
			c.extra_entities = c.extra_entities .. "data/entities/misc/piercing_shot.xml,"
			c.friendly_fire		= true
			draw_actions( 1, true )
		end,
	},
	},
*/
}
