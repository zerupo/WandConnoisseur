package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_OCARINA_B;

public class OCARINA_B extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - Note B";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ocarina b", "b"};
        this.imageFile = "ocarina_b.png";
        this.emote = "<:ocarina_b:1451342040515346717>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_OCARINA_B();
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
	id          = "OCARINA_B",
	name 		= "$action_ocarina_b",
	description = "$actiondesc_ocarina_b",
	spawn_requires_flag = "card_unlocked_ocarina",
	sprite 		= "data/ui_gfx/gun_actions/ocarina_b.png",
	related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_b.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_B
	spawn_probability                 = "0", -- OCARINA_B
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/ocarina/ocarina_b.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/