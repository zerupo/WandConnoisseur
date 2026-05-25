package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_HITFX_CURSE;

import java.lang.invoke.MethodHandles;

public class CURSE extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Venomous Curse";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "venom curse"};
        this.imageFile = "curse.png";
        this.emote = staticEmote;
        this.description = "Imbues a projectile with a curse that makes the target hit by the projectile to waste away";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_HITFX_CURSE()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.6, 0.7, 0, 0.4, 0, 0, 0, 0, 0.1);
        this.price = 140;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "CURSE",
	name 		= "$action_curse",
	description = "$actiondesc_curse",
	sprite 		= "data/ui_gfx/gun_actions/curse.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/electric_charge_unidentified.png",
	related_extra_entities = { "data/entities/misc/hitfx_curse.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,5,10", -- FIREBALL_RAY_ENEMY
	spawn_probability                 = "0.6,0.7,0.4,0.1", -- FIREBALL_RAY_ENEMY
	price = 140,
	mana = 30,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_curse.xml,"
		draw_actions( 1, true )
	end,
}*/