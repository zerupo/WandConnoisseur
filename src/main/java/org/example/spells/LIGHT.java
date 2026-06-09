package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_LIGHT;

import java.lang.invoke.MethodHandles;

public class LIGHT extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Light";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "light.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile illuminate its surroundings";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_LIGHT()};
        this.spawnProbabilities = new SpawnProbabilities(1, 0.8, 0.6, 0.4, 0.2, 0, 0, 0, 0, 0, 0);
        this.price = 20;
        this.manaCost = 1;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "LIGHT",
	name 		= "$action_light",
	description = "$actiondesc_light",
	sprite 		= "data/ui_gfx/gun_actions/light.png",
	related_extra_entities = { "data/entities/misc/light.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "0,1,2,3,4", -- LIGHT
	spawn_probability                 = "1,0.8,0.6,0.4,0.2", -- LIGHT
	price = 20,
	mana = 1,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/light.xml,"
		draw_actions( 1, true )
	end,
}*/