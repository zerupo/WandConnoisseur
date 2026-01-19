package org.example.spells;

import org.example.main.*;

public class HORIZONTAL_ARC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Horizontal path";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "horizontal_arc.png";
        //this.emote = "";
        this.description = "Forces a projectile on a horizontal path, but increases its damage";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0, 0.4, 0, 0.4, 0, 0, 0, 0, 0);
        this.price = 20;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HORIZONTAL_ARC",
		name 		= "$action_horizontal_arc",
		description = "$actiondesc_horizontal_arc",
		sprite 		= "data/ui_gfx/gun_actions/horizontal_arc.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
		related_extra_entities = { "data/entities/misc/horizontal_arc.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,5", -- HORIZONTAL_ARC
		spawn_probability                 = "0.4,0.4,0.4", -- HORIZONTAL_ARC
		price = 20,
		mana = 0,
		--max_uses = 150,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/horizontal_arc.xml,"
			draw_actions( 1, true )
			c.damage_projectile_add = c.damage_projectile_add + 0.3
			c.fire_rate_wait    = c.fire_rate_wait - 6
		end,
	},
	},
*/
}
