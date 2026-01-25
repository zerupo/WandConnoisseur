package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_METEOR;

public class METEOR extends Spell{
    @Override
    protected void initialization(){
        this.name = "Meteor";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "meteor.png";
        this.emote = "<:meteor:1464974867228004483>";
        this.description = "A destructive projectile from the skies!";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_METEOR();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0.6, 0.6, 0.7, 0, 0, 0, 0.5);
        this.price = 280;
        this.manaCost = 150;
        this.hasCharges = true;
        this.maxCharges = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "METEOR",
	name 		= "$action_meteor",
	description = "$actiondesc_meteor",
	sprite 		= "data/ui_gfx/gun_actions/meteor.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/meteor_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/meteor.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "4,5,6,10", -- METEOR
	spawn_probability                 = "0.6,0.6,0.7,0.5", -- METEOR
	price = 280,
	mana = 150,
	max_uses = 10,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/meteor.xml")
	end,
}*/