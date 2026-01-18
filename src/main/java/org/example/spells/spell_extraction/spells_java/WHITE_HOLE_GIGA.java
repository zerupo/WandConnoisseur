package org.example.spells;

import org.example.main.*;

public class WHITE_HOLE_GIGA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Omega white hole";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "white_hole_giga.png";
        //this.emote = "";
        this.description = "A massive orb of positive energy that destroys everything in its reach";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        this.price = 600;
        this.manaCost = 500;
        this.hasCharges = true;
        this.maxCharges = 6;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "WHITE_HOLE_GIGA",
		name 		= "$action_white_hole_giga",
		description = "$actiondesc_white_hole_giga",
		sprite 		= "data/ui_gfx/gun_actions/white_hole_giga.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_big_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/white_hole_giga.xml"},
		spawn_requires_flag = "card_unlocked_black_hole",
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "10", -- BLACK_HOLE_BIG
		spawn_probability                 = "1", -- BLACK_HOLE_BIG
		price = 600,
		mana = 500,
		max_uses    = 6,
		never_unlimited = true,
		custom_xml_file = "data/entities/misc/custom_cards/white_hole_giga.xml",
		action 		= function()
			local black_holes = EntityGetWithTag( "black_hole_giga" )
			
			if ( #black_holes < 3 ) then
				add_projectile("data/entities/projectiles/deck/white_hole_giga.xml")
				c.fire_rate_wait = c.fire_rate_wait + 120
				current_reload_time = current_reload_time + 100
				c.screenshake = c.screenshake + 40
			end
		end,
	},
	},
*/
}
