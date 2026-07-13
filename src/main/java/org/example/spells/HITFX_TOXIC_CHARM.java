package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_HITFX_TOXIC_CHARM;

import java.lang.invoke.MethodHandles;

public class HITFX_TOXIC_CHARM extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Charm on toxic sludge";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "charm on toxic"};
        this.imageFile = "charm_on_toxic.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile charm creatures covered in toxic sludge";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_HITFX_TOXIC_CHARM()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.2, 0.3, 0.2, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "HITFX_TOXIC_CHARM",
	name 		= "$action_hitfx_toxic_charm",
	description = "$actiondesc_hitfx_toxic_charm",
	sprite 		= "data/ui_gfx/gun_actions/charm_on_toxic.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
	related_extra_entities = { "data/entities/misc/hitfx_toxic_charm.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,3,4,5", -- HITFX_TOXIC_CHARM
	spawn_probability                 = "0.2,0.2,0.3,0.2", -- HITFX_TOXIC_CHARM
	price = 150,
	mana = 70,
	--max_uses = 50,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_toxic_charm.xml,"
		draw_actions( 1, true )
	end,
}*/