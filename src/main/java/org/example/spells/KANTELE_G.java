package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_KANTELE_G;

public class KANTELE_G extends Spell{
    @Override
    protected void initialization(){
        this.name = "Kantele - Note G";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "kantele g", "g"};
        this.imageFile = "kantele_g.png";
        this.emote = "<:kantele_g:1451342036786614282>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_KANTELE_G();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 10;
        this.manaCost = 1;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "KANTELE_G",
	name 		= "$action_kantele_g",
	description = "$actiondesc_kantele_g",
	spawn_requires_flag = "card_unlocked_kantele",
	sprite 		= "data/ui_gfx/gun_actions/kantele_g.png",
	related_projectiles	= {"data/entities/projectiles/deck/kantele/kantele_g.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_GSHARP
	spawn_probability                 = "0", -- OCARINA_GSHARP
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/kantele/kantele_g.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/