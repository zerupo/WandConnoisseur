package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_HITFX_PETRIFY;

import java.lang.invoke.MethodHandles;

public class HITFX_PETRIFY extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Petrify";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "petrify.png";
        this.emote = staticEmote;
        this.description = "Turns a wounded enemy into stone";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_HITFX_PETRIFY()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0, 0.2, 0.3, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "HITFX_PETRIFY",
	name 		= "$action_petrify",
	description = "$actiondesc_petrify_a",
	sprite 		= "data/ui_gfx/gun_actions/petrify.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,5,6", -- PETRIFY
	spawn_probability                 = "0.2,0.3,0.2,0.3", -- PETRIFY
	price = 140,
	mana = 10,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_petrify.xml,"
		draw_actions( 1, true )
	end,
}*/