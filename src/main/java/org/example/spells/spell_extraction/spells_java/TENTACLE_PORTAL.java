package org.example.spells;

import org.example.main.*;

public class TENTACLE_PORTAL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Eldritch portal";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "tentacle_portal.png";
        //this.emote = "";
        this.description = "Summons a one-way portal to a sinister realm";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.5, 0, 0, 0, 0, 0, 0.2);
        this.price = 220;
        this.manaCost = 140;
        this.hasCharges = true;
        this.maxCharges = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "TENTACLE_PORTAL",
		name 		= "$action_tentacle_portal",
		description = "$actiondesc_tentacle_portal",
		sprite 		= "data/ui_gfx/gun_actions/tentacle_portal.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/tentacle_portal.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,10", -- TENTACLE_PORTAL
		spawn_probability                 = "0.4,0.4,0.4,0.5,0.2", -- TENTACLE_PORTAL
		price = 220,
		mana = 140,
		max_uses = 5,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/tentacle_portal.xml")
			c.fire_rate_wait = c.fire_rate_wait + 30
		end,
	},
	},
*/
}
