package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_ARC_GUNPOWDER;

import java.lang.invoke.MethodHandles;

public class ARC_GUNPOWDER extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Gunpowder Arc";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "arc_gunpowder.png";
        this.emote = staticEmote;
        this.description = "Creates arcs of gunpowder between projectiles (requires 2 projectile spells)";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_ARC_GUNPOWDER()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.2, 0.4, 0.2, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "ARC_GUNPOWDER",
	name 		= "$action_arc_gunpowder",
	description = "$actiondesc_arc_gunpowder",
	sprite 		= "data/ui_gfx/gun_actions/arc_gunpowder.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/arc_fire_unidentified.png",
	related_extra_entities = { "data/entities/misc/arc_gunpowder.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,2,3,4,5", -- ARC_GUNPOWDER
	spawn_probability                 = "0.4,0.4,0.2,0.4,0.2", -- ARC_GUNPOWDER
	price = 160,
	--max_uses 	= 15,
	mana = 15,
	-- custom_xml_file = "data/entities/misc/custom_cards/arc_gunpowder.xml",
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/arc_gunpowder.xml,"
		draw_actions( 1, true )
	end,
}*/