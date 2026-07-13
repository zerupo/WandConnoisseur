package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_LASER_EMITTER_WIDER;

import java.lang.invoke.MethodHandles;

public class LASER_EMITTER_WIDER extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Plasma Beam Enhancer";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "plasma enhancer"};
        this.imageFile = "laser_emitter_wider.png";
        this.emote = staticEmote;
        this.description = "Makes plasma beam spell's beam wider";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_LASER_EMITTER_WIDER()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.3, 0.3, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 40;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "LASER_EMITTER_WIDER",
	name 		= "$action_laser_emitter_wider",
	description = "$actiondesc_laser_emitter_wider",
	sprite 		= "data/ui_gfx/gun_actions/laser_emitter_wider.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/burn_trail_unidentified.png",
	related_extra_entities = { "data/entities/misc/laser_emitter_wider.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,4", -- BURN_TRAIL
	spawn_probability                 = "0.3,0.3,0.4", -- BURN_TRAIL
	price = 40,
	mana = 10,
	--max_uses = 120,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/laser_emitter_wider.xml,"
		draw_actions( 1, true )
	end,
}*/