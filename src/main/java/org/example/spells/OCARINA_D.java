package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_OCARINA_D;

public class OCARINA_D extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - Note D";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ocarina d"};
        this.imageFile = "ocarina_d.png";
        this.emote = "<:ocarina_d:1451342043359215790>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_OCARINA_D();
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
	id          = "OCARINA_D",
	name 		= "$action_ocarina_d",
	description = "$actiondesc_ocarina_d",
	spawn_requires_flag = "card_unlocked_ocarina",
	sprite 		= "data/ui_gfx/gun_actions/ocarina_d.png",
	related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_d.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_D
	spawn_probability                 = "0", -- OCARINA_D
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/ocarina/ocarina_d.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/