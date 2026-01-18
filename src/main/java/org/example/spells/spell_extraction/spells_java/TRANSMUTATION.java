package org.example.spells;

import org.example.main.*;

public class TRANSMUTATION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Chaotic transmutation";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "transmutation.png";
        //this.emote = "";
        this.description = "Transmutes various liquids and powdery substances within a projectile's range into something else";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.3, 0.3, 0.3, 0, 0, 0, 0.2);
        this.price = 180;
        this.manaCost = 80;
        this.hasCharges = true;
        this.maxCharges = 8;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TRANSMUTATION",
		name 		= "$action_transmutation",
		description = "$actiondesc_transmutation",
		sprite 		= "data/ui_gfx/gun_actions/transmutation.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		related_extra_entities = { "data/entities/misc/transmutation.xml", "data/entities/particles/tinyspark_purple_bright.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6,10", -- TRANSMUTATION
		spawn_probability                 = "0.3,0.3,0.3,0.3,0.3,0.2", -- TRANSMUTATION
		price = 180,
		mana = 80,
		max_uses = 8,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/transmutation.xml,data/entities/particles/tinyspark_purple_bright.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 20
			draw_actions( 1, true )
		end,
	},
	},
*/
}
