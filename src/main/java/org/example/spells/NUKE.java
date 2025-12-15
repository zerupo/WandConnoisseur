package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_NUKE;

public class NUKE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Nuke";
        this.imageFile = "nuke.png";
        this.emote = "<:nuke:1433949670249468016>";
        this.description = "Take cover!";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_NUKE();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0, 0, 0, 1, 1, 0, 0, 0, 0.2);
        this.price = 400;
        this.manaCost = 200;
        this.hasCharges = true;
        this.maxCharges = 1;
        this.castDelay = 20;
        this.rechargeTime = 600;
        this.recoil = 300.0;
        this.screenshake = 10.5;
        //c.speed_multiplier = c.speed_multiplier * 0.75
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
        /*c.material = "fire"
		c.material_amount = c.material_amount + 60
		c.ragdoll_fx = 2
		c.gore_particles = c.gore_particles + 10*/
    }
}

/*{
	id          = "NUKE",
	name 		= "$action_nuke",
	description = "$actiondesc_nuke",
	spawn_requires_flag = "card_unlocked_nuke",
	sprite 		= "data/ui_gfx/gun_actions/nuke.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/nuke_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/nuke.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,5,6,10", -- NUKE
	spawn_probability                 = "0.3,1,1,0.2", -- NUKE
	price = 400,
	mana = 200,
	max_uses    = 1,
	custom_xml_file = "data/entities/misc/custom_cards/nuke.xml",
	action 		= function()
		add_projectile("data/entities/projectiles/deck/nuke.xml")
		c.fire_rate_wait = 20
		c.speed_multiplier = c.speed_multiplier * 0.75
		c.material = "fire"
		c.material_amount = c.material_amount + 60
		c.ragdoll_fx = 2
		c.gore_particles = c.gore_particles + 10
		c.screenshake = c.screenshake + 10.5
		current_reload_time = current_reload_time + 600
		shot_effects.recoil_knockback = shot_effects.recoil_knockback + 300.0

		if ( c.speed_multiplier >= 20 ) then
			c.speed_multiplier = math.min( c.speed_multiplier, 20 )
		elseif ( c.speed_multiplier < 0 ) then
			c.speed_multiplier = 0
		end
	end,
}*/