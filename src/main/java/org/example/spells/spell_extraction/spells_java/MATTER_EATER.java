package org.example.spells;

import org.example.main.*;

public class MATTER_EATER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Matter eater";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "matter_eater.png";
        //this.emote = "";
        this.description = "Makes a projectile eat the environment as it flies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.9, 0, 0.1, 0.2, 0, 0, 0, 0, 0.2);
        this.price = 280;
        this.manaCost = 120;
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
		id          = "MATTER_EATER",
		name 		= "$action_matter_eater",
		description = "$actiondesc_matter_eater",
		sprite 		= "data/ui_gfx/gun_actions/matter_eater.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
		related_extra_entities = { "data/entities/misc/matter_eater.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,4,5,10", -- MATTER_EATER
		spawn_probability                 = "0.1,0.9,0.1,0.2,0.2", -- MATTER_EATER
		price = 280,
		mana = 120,
		max_uses = 10,
		never_unlimited = true,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/matter_eater.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
