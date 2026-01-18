package org.example.spells;

import org.example.main.*;

public class ARC_ELECTRIC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Electric Arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "arc_electric.png";
        //this.emote = "";
        this.description = "Creates arcs of lightning between projectiles (requires 2 projectile spells)";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.4, 0.4, 0.4, 0.8, 0, 0, 0, 0);
        this.price = 170;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ARC_ELECTRIC",
		name 		= "$action_arc_electric",
		description = "$actiondesc_arc_electric",
		sprite 		= "data/ui_gfx/gun_actions/arc_electric.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/arc_electric_unidentified.png",
		related_extra_entities = { "data/entities/misc/arc_electric.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6", -- ARC_ELECTRIC
		spawn_probability                 = "0.4,0.4,0.4,0.4,0.8", -- ARC_ELECTRIC
		price = 170,
		--max_uses 	= 15,
		mana = 15,
		custom_xml_file = "data/entities/misc/custom_cards/arc_electric.xml",
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/arc_electric.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
