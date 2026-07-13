package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_LARPA_DOWNWARDS;

import java.lang.invoke.MethodHandles;

public class LARPA_DOWNWARDS extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Downwards larpa";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "larpa_downwards.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile cast copies of itself with a downwards trajectory";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_LARPA_DOWNWARDS()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.1, 0.3, 0.4, 0.2, 0, 0, 0, 0, 0.2);
        this.price = 290;
        this.manaCost = 120;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "LARPA_DOWNWARDS",
	name 		= "$action_larpa_downwards",
	description = "$actiondesc_larpa_downwards",
	sprite 		= "data/ui_gfx/gun_actions/larpa_downwards.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
	related_extra_entities = { "data/entities/misc/larpa_downwards.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,4,5,10", -- FIREBALL_RAY
	spawn_probability                 = "0.1,0.3,0.4,0.2,0.2", -- FIREBALL_RAY
	price = 290,
	mana = 120,
	--max_uses = 20,
	action 		= function()
		c.fire_rate_wait = c.fire_rate_wait + 15
		c.extra_entities = c.extra_entities .. "data/entities/misc/larpa_downwards.xml,"
		draw_actions( 1, true )
	end,
}*/