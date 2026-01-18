package org.example.spells;

import org.example.main.*;

public class WHITE_HOLE_BIG extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giga white hole";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "white_hole_big.png";
        //this.emote = "";
        this.description = "A growing orb of positive energy that destroys everything in its reach";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.05, 0, 0.05, 0, 0.1, 0.4, 0, 0, 0, 0.2);
        this.price = 320;
        this.manaCost = 240;
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
		id          = "WHITE_HOLE_BIG",
		name 		= "$action_white_hole_big",
		description = "$actiondesc_white_hole_big",
		sprite 		= "data/ui_gfx/gun_actions/white_hole_big.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_big_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/white_hole_big.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "1,3,5,6,10", -- BLACK_HOLE_BIG
		spawn_probability                 = "0.05,0.05,0.1,0.4,0.2", -- BLACK_HOLE_BIG
		price = 320,
		mana = 240,
		max_uses    = 6, 
		custom_xml_file = "data/entities/misc/custom_cards/white_hole_big.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/white_hole_big.xml")
			c.fire_rate_wait = c.fire_rate_wait + 80
			c.screenshake = c.screenshake + 10
		end,
	},
	},
*/
}
