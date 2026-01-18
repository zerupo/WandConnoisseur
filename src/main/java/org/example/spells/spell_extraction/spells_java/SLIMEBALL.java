package org.example.spells;

import org.example.main.*;

public class SLIMEBALL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Slimeball";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "slimeball.png";
        //this.emote = "";
        this.description = "A dripping ball of poisonous slime";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 0, 0, 1, 0.7, 0, 0, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SLIMEBALL",
		name 		= "$action_slimeball",
		description = "$actiondesc_slimeball",
		sprite 		= "data/ui_gfx/gun_actions/slimeball.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/slime.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,3,4", -- SLIMEBALL
		spawn_probability                 = "1,1,0.7", -- SLIMEBALL
		price = 130,
		mana = 20,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/slimeball.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/slime.xml")
			c.spread_degrees = c.spread_degrees + 4.0
			c.fire_rate_wait = c.fire_rate_wait + 10
			c.speed_multiplier = c.speed_multiplier * 1.1
			
			if ( c.speed_multiplier >= 20 ) then
				c.speed_multiplier = math.min( c.speed_multiplier, 20 )
			elseif ( c.speed_multiplier < 0 ) then
				c.speed_multiplier = 0
			end
		end,
	},
	},
*/
}
