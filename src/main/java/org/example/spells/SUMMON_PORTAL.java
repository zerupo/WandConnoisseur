package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_SUMMON_PORTAL;

import java.lang.invoke.MethodHandles;

public class SUMMON_PORTAL extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Summon portal";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "portal"};
        this.imageFile = "summon_portal.png";
        this.emote = staticEmote;
        this.description = "Summons a strange portal";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_SUMMON_PORTAL();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 7;
        this.castDelay = 80;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "SUMMON_PORTAL",
	name 		= "$action_summon_portal",
	description = "$actiondesc_summon_portal",
	sprite 		= "data/ui_gfx/gun_actions/summon_portal.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- MANA_REDUCE
	spawn_probability                 = "0", -- MANA_REDUCE
	price = 100,
	mana = 50,
	max_uses = 7,
	custom_xml_file = "data/entities/misc/custom_cards/summon_portal.xml",
	action = function()
		add_projectile("data/entities/projectiles/deck/summon_portal.xml")
		c.fire_rate_wait = c.fire_rate_wait + 80
	end,
}*/