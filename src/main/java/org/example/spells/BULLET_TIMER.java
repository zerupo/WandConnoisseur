package org.example.spells;

import org.example.main.*;
import org.example.projectiles.Projectile;
import org.example.projectiles.PROJECTILE_BULLET;

public class BULLET_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Magic arrow with timer";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "magic arrow timer"};
        this.imageFile = "bullet_timer.png";
        this.emote = "<:bullet_timer:1464974839558177034>";
        this.description = "A magical arrow that casts another spell after a timer runs out";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BULLET();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0.5, 0.5, 0.5, 0.6, 0, 0, 0, 0);
        this.price = 190;
        this.manaCost = 35;
        this.castDelay = 4;
        this.critRate = 5;
        this.spread = 2.0;
        this.recoil = 23.0;
        this.screenshake = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Projectile newProjectile = this.relatedProjectile.clone();
        CastState newCastState = new CastState();

        newProjectile.addTrigger(Projectile.TriggerType.timer, 10, newCastState);
        castState.addProjectile(newProjectile);
        cardPool.draw(1, true, newCastState);
    }
}

/*{
	id          = "BULLET_TIMER",
	name 		= "$action_bullet_timer",
	description = "$actiondesc_bullet_timer",
	sprite 		= "data/ui_gfx/gun_actions/bullet_timer.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/bullet_timer_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/bullet.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                         = "2,3,4,5,6", -- BULLET_TIMER
	spawn_probability                   = "0.5,0.5,0.5,0.5,0.6", -- BULLET_TIMER
	price = 190,
	mana = 35,
	--max_uses = 80,
	action 		= function()
		c.fire_rate_wait = c.fire_rate_wait + 4
		c.screenshake = c.screenshake + 2
		c.spread_degrees = c.spread_degrees + 2.0
		c.damage_critical_chance = c.damage_critical_chance + 5
		add_projectile_trigger_timer("data/entities/projectiles/deck/bullet.xml", 10, 1)
		shot_effects.recoil_knockback = shot_effects.recoil_knockback + 23.0
	end,
}*/