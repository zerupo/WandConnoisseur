package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_BLACK_HOLE;

public class BLACK_HOLE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Black Hole";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "bh"};
        this.imageFile = "black_hole.png";
        this.emote = "<:black_hole:1433949520550428862>";
        this.description = "A slow orb of void that eats through all obstacles";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BLACK_HOLE();
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0, 0.8, 0, 0.8, 0.8, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 180;
        this.hasCharges = true;
        this.maxCharges = 3;
        this.neverUnlimited = true;
        this.castDelay = 80;
        this.screenshake = 20.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "BLACK_HOLE",
	name 		= "$action_black_hole",
	description = "$actiondesc_black_hole",
	sprite 		= "data/ui_gfx/gun_actions/black_hole.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/black_hole.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,2,4,5", -- BLACK_HOLE
	spawn_probability                 = "0.8,0.8,0.8,0.8", -- BLACK_HOLE
	price = 200,
	mana = 180,
	max_uses    = 3,
	never_unlimited = true,
	custom_xml_file = "data/entities/misc/custom_cards/black_hole.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/black_hole.xml")
		c.fire_rate_wait = c.fire_rate_wait + 80
		c.screenshake = c.screenshake + 20
	end,
}*/