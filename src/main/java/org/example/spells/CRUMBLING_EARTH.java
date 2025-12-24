package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_CRUMBLING_EARTH;

public class CRUMBLING_EARTH extends Spell{
    @Override
    protected void initialization(){
        this.name = "Earthquake";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "crumbling_earth.png";
        this.emote = "<:crumbling_earth:1453399893166133339>";
        this.description = "Calls the anger of the earth";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_CRUMBLING_EARTH();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.3, 0.5, 0.6, 0.9, 0, 0, 0, 0);
        this.price = 300;
        this.manaCost = 240;
        this.hasCharges = true;
        this.maxCharges = 3;
        this.castDelay = 3;
        this.spread = -1;
        this.critRate = 5;
        this.screenshake = 0.5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "CRUMBLING_EARTH",
	name 		= "$action_crumbling_earth",
	description = "$actiondesc_crumbling_earth",
	spawn_requires_flag = "card_unlocked_crumbling_earth",
	sprite 		= "data/ui_gfx/gun_actions/crumbling_earth.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/crumbling_earth.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "2,3,4,5,6", -- CRUMBLING_EARTH
	spawn_probability                 = "0.4,0.3,0.5,0.6,0.9", -- CRUMBLING_EARTH
	price = 300,
	mana = 240,
	max_uses    = 3,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/crumbling_earth.xml")
	end,
}*/