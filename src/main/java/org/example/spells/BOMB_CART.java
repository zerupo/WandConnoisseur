package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_BOMB_CART;

public class BOMB_CART extends Spell{
    @Override
    protected void initialization(){
        this.name = "Bomb Cart";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bomb_cart.png";
        this.emote = "<:bomb_cart:1447276642337685597>";
        this.description = "Summons a self-propeled mine cart loaded with explosives";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BOMB_CART();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.6, 0.6, 0.5, 0.8, 0.6, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 75;
        this.hasCharges = true;
        this.maxCharges = 6;
        this.castDelay = 60;
        this.recoil = 200.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "BOMB_CART",
	name 		= "$action_bomb_cart",
	description = "$actiondesc_bomb_cart",
	sprite 		= "data/ui_gfx/gun_actions/bomb_cart.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/bomb_cart.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "2,3,4,5,6", -- BOMB_CART
	spawn_probability                 = "0.6,0.6,0.5,0.8,0.6", -- BOMB_CART
	price = 200,
	mana = 75,
	max_uses    = 6,
	action 		= function()
		add_projectile("data/entities/projectiles/bomb_cart.xml")
		shot_effects.recoil_knockback = shot_effects.recoil_knockback + 200.0
		c.fire_rate_wait = c.fire_rate_wait + 60
	end,
}*/