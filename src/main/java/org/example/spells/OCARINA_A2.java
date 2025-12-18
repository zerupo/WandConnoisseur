package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_OCARINA_A2;

public class OCARINA_A2 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - Note A2";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ocarina a2", "a2"};
        this.imageFile = "ocarina_a2.png";
        this.emote = "<:ocarina_a2:1451342039294935040>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_OCARINA_A2();
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
	id          = "OCARINA_A2",
	name 		= "$action_ocarina_a2",
	description = "$actiondesc_ocarina_a2",
	spawn_requires_flag = "card_unlocked_ocarina",
	sprite 		= "data/ui_gfx/gun_actions/ocarina_a2.png",
	related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_a2.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_A2
	spawn_probability                 = "0", -- OCARINA_A2
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/ocarina/ocarina_a2.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/