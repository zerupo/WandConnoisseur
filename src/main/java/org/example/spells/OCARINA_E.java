package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_OCARINA_E;

public class OCARINA_E extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - Note E";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ocarina e"};
        this.imageFile = "ocarina_e.png";
        this.emote = "<:ocarina_e:1451342044466384968>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_OCARINA_E();
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
	id          = "OCARINA_E",
	name 		= "$action_ocarina_e",
	description = "$actiondesc_ocarina_e",
	spawn_requires_flag = "card_unlocked_ocarina",
	sprite 		= "data/ui_gfx/gun_actions/ocarina_e.png",
	related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_e.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_E
	spawn_probability                 = "0", -- OCARINA_E
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/ocarina/ocarina_e.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/