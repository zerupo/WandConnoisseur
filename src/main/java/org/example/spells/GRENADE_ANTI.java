package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_GRENADE_ANTI;

public class GRENADE_ANTI extends Spell{
    @Override
    protected void initialization(){
        this.name = "Odd Firebolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade_anti.png";
        this.emote = "<:grenade_anti:1453399899143147530>";
        this.description = "A somewhat peculiar bouncy, explosive bolt";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_GRENADE_ANTI();
        this.spawnProbabilities = new SpawnProbabilities(0.4, 0.4, 0.7, 0.4, 0.4, 0.4, 0, 0, 0, 0, 0);
        this.price = 170;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 25;
        this.autoStat = false;
        this.castDelay = 30;
        this.recoil = 80.0;
        this.screenshake = 4.0;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addCastDelay(this.castDelay);
        cardPool.addScreenshake(this.screenshake);
        cardPool.setRecoil(this.recoil);
        // c.child_speed_multiplier = c.child_speed_multiplier * 0.75
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "GRENADE_ANTI",
	name 		= "$action_grenade_anti",
	description = "$actiondesc_grenade_anti",
	sprite 		= "data/ui_gfx/gun_actions/grenade_anti.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/grenade_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/grenade_anti.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,1,2,3,4,5", -- GRENADE_ANTI
	spawn_probability                 = "0.4,0.4,0.7,0.4,0.4,0.4", -- GRENADE_ANTI
	price = 170,
	mana = 50,
	max_uses    = 25,
	custom_xml_file = "data/entities/misc/custom_cards/grenade.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/grenade_anti.xml")
		c.fire_rate_wait = c.fire_rate_wait + 30
		c.screenshake = c.screenshake + 4.0
		c.child_speed_multiplier = c.child_speed_multiplier * 0.75
		--current_reload_time = current_reload_time + 40
		shot_effects.recoil_knockback = 80.0
	end,
}*/