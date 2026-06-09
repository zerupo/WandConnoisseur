package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.script.Script;
import org.example.script.SCRIPT_PIERCING_SHOT;

import java.lang.invoke.MethodHandles;

public class PIERCING_SHOT extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Piercing shot";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "piercing", "pierc"};
        this.imageFile = "piercing_shot.png";
        this.emote = staticEmote;
        this.description = "Makes a projectile fly through enemies, but harmful to the caster";
        this.type = SpellType.modifier;
        this.relatedScripts = new Script[]{new SCRIPT_PIERCING_SHOT()};
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.5, 0.6, 0.6, 0.4, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 140;
        this.damageComponent.setProjectile(-15.0);
        this.setFriendlyFire = true;
        this.friendlyFire = true;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "PIERCING_SHOT",
	name 		= "$action_piercing_shot",
	description = "$actiondesc_piercing_shot",
	sprite 		= "data/ui_gfx/gun_actions/piercing_shot.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
	related_extra_entities = { "data/entities/misc/piercing_shot.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,4,5,6", -- PIERCING_SHOT
	spawn_probability                 = "0.4,0.5,0.6,0.6,0.4", -- PIERCING_SHOT
	price = 190,
	mana = 140,
	--max_uses = 100,
	action 		= function()
		c.damage_projectile_add = c.damage_projectile_add - 0.6
		c.extra_entities = c.extra_entities .. "data/entities/misc/piercing_shot.xml,"
		c.friendly_fire		= true
		draw_actions( 1, true )
	end,
}*/