package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_FIREBOMB;

import java.lang.invoke.MethodHandles;

public class FIREBOMB extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Firebomb";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "firebomb.png";
        this.emote = staticEmote;
        this.description = "Slow, fiery bolt";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_FIREBOMB();
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 0.9, 0.7, 0, 0, 0, 0, 0, 0, 0);
        this.price = 90;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "FIREBOMB",
	name 		= "$action_firebomb",
	description = "$actiondesc_firebomb",
	sprite 		= "data/ui_gfx/gun_actions/firebomb.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/firebomb_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/firebomb.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3", -- FIREBOMB
	spawn_probability                 = "1,0.9,0.7", -- FIREBOMB
	price = 90,
	mana = 10,
	--max_uses    = 70,
	custom_xml_file = "data/entities/misc/custom_cards/firebomb.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/firebomb.xml")
	end,
}*/