package org.example.spells;

import org.example.main.*;

public class ARC_FIRE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Fire Arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "arc_fire.png";
        //this.emote = "";
        this.description = "Creates arcs of fire between projectiles (requires 2 projectile spells)";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.2, 0.2, 0.5, 0.2, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "ARC_FIRE",
		name 		= "$action_arc_fire",
		description = "$actiondesc_arc_fire",
		sprite 		= "data/ui_gfx/gun_actions/arc_fire.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/arc_fire_unidentified.png",
		related_extra_entities = { "data/entities/misc/arc_fire.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3,4,5", -- ARC_FIRE
		spawn_probability                 = "0.4,0.2,0.2,0.5,0.2", -- ARC_FIRE
		price = 160,
		--max_uses 	= 15,
		mana = 15,
		custom_xml_file = "data/entities/misc/custom_cards/arc_fire.xml",
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/arc_fire.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
