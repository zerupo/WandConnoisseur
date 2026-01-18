package org.example.spells;

import org.example.main.*;

public class UNSTABLE_GUNPOWDER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Firecrackers";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "unstable_gunpowder.png";
        //this.emote = "";
        this.description = "Makes a projectile release firecrackers when it disappears";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ACID",
		name 		= "$action_acid",
		description = "$actiondesc_acid",
		sprite 		= "data/ui_gfx/gun_actions/acid.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "", -- ACID
		spawn_probability                        = "", -- ACID
		price = 100,
		action 		= function()
			material = "acid"
			material_amount = material_amount + 20
		end,
	},]]--
		id          = "UNSTABLE_GUNPOWDER",
		name 		= "$action_unstable_gunpowder",
		description = "$actiondesc_unstable_gunpowder",
		sprite 		= "data/ui_gfx/gun_actions/unstable_gunpowder.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/unstable_gunpowder_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                      = "2,3,4", -- UNSTABLE_GUNPOWDER
		spawn_probability                = "0.3,0.4,0.4", -- UNSTABLE_GUNPOWDER
		price = 140,
		mana = 15,
		--max_uses    = 20, 
		custom_xml_file = "data/entities/misc/custom_cards/unstable_gunpowder.xml",
		action 		= function()
			c.material = "gunpowder_unstable"
			c.material_amount = c.material_amount + 10
			--shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
