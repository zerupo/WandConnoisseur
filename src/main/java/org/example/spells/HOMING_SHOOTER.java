package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_HOMING_SHOOTER;

import java.lang.invoke.MethodHandles;

public class HOMING_SHOOTER extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Boomerang";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "homing_shooter.png";
        this.emote = staticEmote;
        this.description = "Gives a projectile a path that curves towards you";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_HOMING_SHOOTER()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0.2, 0, 0.2, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 10;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/particles/tinyspark_white.xml,"
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "HOMING_SHOOTER",
	name 		= "$action_homing_shooter",
	description = "$actiondesc_homing_shooter",
	sprite 		= "data/ui_gfx/gun_actions/homing_shooter.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
	related_extra_entities = { "data/entities/misc/homing_shooter.xml", "data/entities/particles/tinyspark_white.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,4,6", -- HOMING_SHOOTER
	spawn_probability                 = "0.2,0.3,0.2,0.2", -- HOMING_SHOOTER
	price = 100,
	mana = 10,
	--max_uses = 100,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/homing_shooter.xml,data/entities/particles/tinyspark_white.xml,"
		draw_actions( 1, true )
	end,
}*/