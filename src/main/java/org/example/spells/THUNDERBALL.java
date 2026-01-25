package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_THUNDERBALL;

public class THUNDERBALL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Thunder charge";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "thunderball.png";
        this.emote = "<:thunderball:1464974877164306494>";
        this.description = "A projectile with immense stored electricity";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_THUNDERBALL();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.9, 0, 1, 0, 0.7, 0, 0, 0, 0.2);
        this.price = 300;
        this.manaCost = 120;
        this.hasCharges = true;
        this.maxCharges = 3;
        this.castDelay = 120;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "THUNDERBALL",
	name 		= "$action_thunderball",
	description = "$actiondesc_thunderball",
	sprite 		= "data/ui_gfx/gun_actions/thunderball.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/thunderball_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/thunderball.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "2,4,6,10", -- THUNDERBALL
	spawn_probability                 = "0.9,1,0.7,0.2", -- THUNDERBALL
	price = 300,
	mana = 120,
	max_uses    = 3,
	custom_xml_file = "data/entities/misc/custom_cards/thunderball.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/thunderball.xml")
		c.fire_rate_wait = c.fire_rate_wait + 120
	end,
}*/