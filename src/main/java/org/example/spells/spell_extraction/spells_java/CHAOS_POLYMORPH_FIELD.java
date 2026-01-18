package org.example.spells;

import org.example.main.*;

public class CHAOS_POLYMORPH_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Circle of unstable metamorphosis";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "chaos_polymorph_field.png";
        //this.emote = "";
        this.description = "A field of transformative magic";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0.3, 0.5, 0.6, 0.3, 0.3, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 20;
        this.hasCharges = true;
        this.maxCharges = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CHAOS_POLYMORPH_FIELD",
		name 		= "$action_chaos_polymorph_field",
		description = "$actiondesc_chaos_polymorph_field",
		sprite 		= "data/ui_gfx/gun_actions/chaos_polymorph_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/chaos_polymorph_field_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/chaos_polymorph_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "1,2,3,4,5,6", -- CHAOS_POLYMORPH_FIELD
		spawn_probability                 = "0.3,0.3,0.5,0.6,0.3,0.3", -- CHAOS_POLYMORPH_FIELD
		price = 200,
		mana = 20,
		max_uses = 10,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/chaos_polymorph_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
		end,
	},
	},
*/
}
