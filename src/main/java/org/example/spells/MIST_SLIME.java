package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_MIST_SLIME;

public class MIST_SLIME extends Spell{
    @Override
    protected void initialization(){
        this.name = "Slime Mist";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "mist_slime.png";
        this.emote = getEmoteConfig("mist_slime");
        this.description = "A cloud of slimy mist";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_MIST_SLIME();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 40;
        this.castDelay = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "MIST_SLIME",
	name 		= "$action_mist_slime",
	description = "$actiondesc_mist_slime",
	sprite 		= "data/ui_gfx/gun_actions/mist_slime.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/slimeball_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/mist_slime.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3,4", -- MIST_SLIME
	spawn_probability                 = "0.4,0.4,0.4,0.4", -- MIST_SLIME
	price = 80,
	mana = 40,
	--max_uses = 10,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/mist_slime.xml")
		c.fire_rate_wait = c.fire_rate_wait + 10
	end,
}*/