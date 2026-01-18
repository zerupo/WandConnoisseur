package org.example.spells;

import org.example.main.*;

public class TELEPORT_PROJECTILE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Teleport bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "teleport_projectile.png";
        //this.emote = "";
        this.description = "A magical bolt that moves you wherever it ends up flying";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.6, 0.6, 0.6, 0, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 130;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TELEPORT_PROJECTILE",
		name 		= "$action_teleport_projectile",
		description = "$actiondesc_teleport_projectile",
		sprite 		= "data/ui_gfx/gun_actions/teleport_projectile.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/teleport_projectile.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- TELEPORT_PROJECTILE
		spawn_probability                 = "0.6,0.6,0.6,0.4,0.4,0.4", -- TELEPORT_PROJECTILE
		price = 130,
		mana = 40,
		--max_uses = 80,
		custom_xml_file = "data/entities/misc/custom_cards/teleport_projectile.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/teleport_projectile.xml")
			c.fire_rate_wait = c.fire_rate_wait + 3
			c.spread_degrees = c.spread_degrees - 2.0
		end,
	},
	},
*/
}
