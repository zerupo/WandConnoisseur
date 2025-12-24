package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_GRENADE;

public class GRENADE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Firebolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "grenade.png";
        this.emote = "<:grenade:1453399897503174778>";
        this.description = "A bouncy, explosive bolt";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_GRENADE();
        this.spawnProbabilities = new SpawnProbabilities(1, 1, 0.5, 0.25, 0.2, 0, 0, 0, 0, 0, 0);
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
	id          = "GRENADE",
	name 		= "$action_grenade",
	description = "$actiondesc_grenade",
	sprite 		= "data/ui_gfx/gun_actions/grenade.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/grenade_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/grenade.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,1,2,3,4", -- GRENADE
	spawn_probability                 = "1,1,0.5,0.25,0.2", -- GRENADE
	price = 170,
	mana = 50,
	max_uses    = 25,
	custom_xml_file = "data/entities/misc/custom_cards/grenade.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/grenade.xml")
		c.fire_rate_wait = c.fire_rate_wait + 30
		c.screenshake = c.screenshake + 4.0
		c.child_speed_multiplier = c.child_speed_multiplier * 0.75
		--current_reload_time = current_reload_time + 40
		shot_effects.recoil_knockback = 80.0
	end,
}*/