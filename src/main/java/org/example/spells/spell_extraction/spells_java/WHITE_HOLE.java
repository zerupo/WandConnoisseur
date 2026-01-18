package org.example.spells;

import org.example.main.*;

public class WHITE_HOLE extends Spell{
    @Override
    protected void initialization(){
        this.name = "White hole";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "white_hole.png";
        //this.emote = "";
        this.description = "An orb of positive energy that destroys everything in its path";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.05, 0, 0.05, 0, 0.1, 0, 0.2, 0, 0, 0, 0.5);
        this.price = 200;
        this.manaCost = 180;
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
		id          = "WHITE_HOLE",
		name 		= "$action_white_hole",
		description = "$actiondesc_white_hole",
		sprite 		= "data/ui_gfx/gun_actions/white_hole.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/white_hole.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,2,4,6,10", -- BLACK_HOLE
		spawn_probability                 = "0.05,0.05,0.1,0.2,0.5", -- BLACK_HOLE
		price = 200,
		mana = 180,
		max_uses    = 3, 
		never_unlimited = true,
		custom_xml_file = "data/entities/misc/custom_cards/white_hole.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/white_hole.xml")
			c.fire_rate_wait = c.fire_rate_wait + 80
			c.screenshake = c.screenshake + 20
		end,
	},
	},
*/
}
