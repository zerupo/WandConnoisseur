package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_CASTER_CAST;
import org.example.script.Script;
import org.example.script.SCRIPT_CASTER_CAST;

import java.lang.invoke.MethodHandles;

public class CASTER_CAST extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Inner spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "caster_cast.png";
        this.emote = staticEmote;
        this.description = "Causes a projectile to be cast from where the caster is standing";
        this.type = SpellType.utility;
        this.relatedProjectile = new PROJECTILE_CASTER_CAST();
        this.relatedScripts = new Script[]{new SCRIPT_CASTER_CAST()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0.2, 0.4, 0.4, 0, 0, 0, 0.2);
        this.price = 70;
        this.manaCost = 10;
        this.spread = -24;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "CASTER_CAST",
	name 		= "$action_caster_cast",
	description = "$actiondesc_caster_cast",
	sprite 		= "data/ui_gfx/gun_actions/caster_cast.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/caster_cast.xml"},
	type 		= ACTION_TYPE_UTILITY,
	spawn_level                       = "2,4,5,6,10", -- CASTER_CAST
	spawn_probability                 = "0.2,0.2,0.4,0.4,0.2", -- CASTER_CAST
	price = 70,
	mana = 10,
	action 		= function()
		c.spread_degrees = c.spread_degrees - 24
		c.extra_entities = c.extra_entities .. "data/entities/misc/caster_cast.xml,"
		draw_actions( 1, true )
	end,
}*/