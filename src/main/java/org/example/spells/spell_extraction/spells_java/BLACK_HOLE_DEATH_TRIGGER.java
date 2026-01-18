package org.example.spells;

import org.example.main.*;

public class BLACK_HOLE_DEATH_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Black Hole with Death Trigger";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "black_hole_timer.png";
        //this.emote = "";
        this.description = "A slow orb of void that eats through all obstacles and casts another spell as it expires";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0, 0.5, 0.5, 0.5, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 200;
        this.hasCharges = true;
        this.maxCharges = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BLACK_HOLE_DEATH_TRIGGER",
		name 		= "$action_black_hole_death_trigger",
		description = "$actiondesc_black_hole_death_trigger",
		sprite 		= "data/ui_gfx/gun_actions/black_hole_timer.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/black_hole.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,4,5,6", -- BLACK_HOLE
		spawn_probability                 = "0.5,0.5,0.5,0.5", -- BLACK_HOLE
		price = 220,
		mana = 200,
		max_uses    = 3, 
		never_unlimited = true,
		custom_xml_file = "data/entities/misc/custom_cards/black_hole.xml",
		action 		= function()
			add_projectile_trigger_death("data/entities/projectiles/deck/black_hole.xml", 1)
			c.fire_rate_wait = c.fire_rate_wait + 90
			c.screenshake = c.screenshake + 20
		end,
	},
	},
*/
}
