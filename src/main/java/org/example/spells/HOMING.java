package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_HOMING;

import java.lang.invoke.MethodHandles;

public class HOMING extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Homing";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile accelerate towards your foes";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_HOMING()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.4, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 70;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/particles/tinyspark_white.xml,"
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "HOMING",
	name 		= "$action_homing",
	description = "$actiondesc_homing",
	sprite 		= "data/ui_gfx/gun_actions/homing.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
	related_extra_entities = { "data/entities/misc/homing.xml", "data/entities/particles/tinyspark_white.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,2,3,4,5,6", -- HOMING
	spawn_probability                 = "0.1,0.4,0.4,0.4,0.4,0.4", -- HOMING
	price = 220,
	mana = 70,
	--max_uses = 100,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/homing.xml,data/entities/particles/tinyspark_white.xml,"
		draw_actions( 1, true )
	end,
}*/