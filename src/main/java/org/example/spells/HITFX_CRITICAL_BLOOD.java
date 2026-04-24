package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;

import java.lang.invoke.MethodHandles;

public class HITFX_CRITICAL_BLOOD extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Critical on bloody enemies";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "crit on blood"};
        this.imageFile = "critical_blood.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile always do a critical hit on bloody enemies";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.2, 0.2, 0.4, 0, 0, 0, 0, 0);
        this.price = 70;
        this.manaCost = 10;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_critical_blood.xml,"
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "HITFX_CRITICAL_BLOOD",
	name 		= "$action_hitfx_critical_blood",
	description = "$actiondesc_hitfx_critical_blood",
	sprite 		= "data/ui_gfx/gun_actions/critical_blood.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
	related_extra_entities = { "data/entities/misc/hitfx_critical_blood.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,3,4,5", -- HITFX_CRITICAL_BLOOD
	spawn_probability                 = "0.2,0.2,0.2,0.4", -- HITFX_CRITICAL_BLOOD
	price = 70,
	mana = 10,
	--max_uses = 50,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_critical_blood.xml,"
		draw_actions( 1, true )
	end,
}*/