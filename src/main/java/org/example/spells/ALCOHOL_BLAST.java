package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_ALCOHOL_BLAST;

import java.lang.invoke.MethodHandles;

public class ALCOHOL_BLAST extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Explosion of spirits";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "alcohol_blast.png";
        this.emote = staticEmote;
        this.description = "An inebriating explosion";
        this.type = SpellType.static_projectile;
        this.relatedProjectile = new PROJECTILE_ALCOHOL_BLAST();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.6, 0, 0.65, 0, 0.3, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 30;
        this.castDelay = 3;
        this.screenshake = 0.5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "ALCOHOL_BLAST",
	name 		= "$action_alcohol_blast",
	description = "$actiondesc_alcohol_blast",
	sprite 		= "data/ui_gfx/gun_actions/alcohol_blast.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/poison_blast_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/alcohol_blast.xml"},
	type 		= ACTION_TYPE_STATIC_PROJECTILE,
	spawn_level                       = "1,2,4,6", -- ALCOHOL_BLAST
	spawn_probability                 = "0.5,0.6,0.65,0.3", -- ALCOHOL_BLAST
	price = 140,
	mana = 30,
	--max_uses = 30,
	custom_xml_file = "data/entities/misc/custom_cards/alcohol_blast.xml",
	is_dangerous_blast = true,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/alcohol_blast.xml")
		c.fire_rate_wait = c.fire_rate_wait + 3
		c.screenshake = c.screenshake + 0.5
	end,
}*/