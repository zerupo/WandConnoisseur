package org.example.spells;

import org.example.main.*;

public class MIST_RADIOACTIVE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Toxic mist";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "mist_radioactive.png";
        //this.emote = "";
        this.description = "A cloud of toxic mist";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "MIST_RADIOACTIVE",
		name 		= "$action_mist_radioactive",
		description = "$actiondesc_mist_radioactive",
		sprite 		= "data/ui_gfx/gun_actions/mist_radioactive.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/mist_radioactive.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4", -- MIST_RADIOACTIVE
		spawn_probability                 = "0.4,0.4,0.4,0.4", -- MIST_RADIOACTIVE
		price = 80,
		mana = 40,
		--max_uses = 10,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/mist_radioactive.xml")
			c.fire_rate_wait = c.fire_rate_wait + 10
		end,
	},
	},
*/
}
