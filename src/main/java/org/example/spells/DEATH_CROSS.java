package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_DEATH_CROSS;

public class DEATH_CROSS extends Spell{
    @Override
    protected void initialization(){
        this.name = "Death Cross";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "death_cross.png";
        this.emote = "<:death_cross:1447276648109052084>";
        this.description = "A deadly energy cross that explodes after a short time";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_DEATH_CROSS();
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 0.8, 0.6, 0.5, 0.5, 0.3, 0, 0, 0, 0);
        this.price = 210;
        this.manaCost = 80;
        this.castDelay = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "DEATH_CROSS",
	name 		= "$action_death_cross",
	description = "$actiondesc_death_cross",
	sprite 		= "data/ui_gfx/gun_actions/death_cross.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/death_cross_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/death_cross.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3,4,5,6", -- DEATH_CROSS
	spawn_probability                        = "1,0.8,0.6,0.5,0.5,0.3", -- DEATH_CROSS
	price = 210,
	mana = 80,
	custom_xml_file = "data/entities/misc/custom_cards/death_cross.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/death_cross.xml")
		c.fire_rate_wait = c.fire_rate_wait + 40
	end,
}*/