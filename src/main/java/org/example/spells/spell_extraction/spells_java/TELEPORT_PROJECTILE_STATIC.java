package org.example.spells;

import org.example.main.*;

public class TELEPORT_PROJECTILE_STATIC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Return";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "teleport_projectile_static.png";
        //this.emote = "";
        this.description = "After a period of time";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.6, 0.6, 0.6, 0, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 90;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TELEPORT_PROJECTILE_STATIC",
		name 		= "$action_teleport_projectile_static",
		description = "$actiondesc_teleport_projectile_static",
		sprite 		= "data/ui_gfx/gun_actions/teleport_projectile_static.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/teleport_projectile_static.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- TELEPORT_PROJECTILE_STATIC
		spawn_probability                 = "0.6,0.6,0.6,0.4,0.4,0.4", -- TELEPORT_PROJECTILE_STATIC
		price = 90,
		mana = 40,
		--max_uses = 80,
		custom_xml_file = "data/entities/misc/custom_cards/teleport_projectile_static.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/teleport_projectile_static.xml")
			c.fire_rate_wait = c.fire_rate_wait + 3
			c.spread_degrees = c.spread_degrees - 2.0
		end,
	},
	},
*/
}
