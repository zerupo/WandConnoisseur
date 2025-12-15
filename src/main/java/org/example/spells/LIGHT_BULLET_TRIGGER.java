package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_LIGHT_BULLET;
import org.example.projectiles.Projectile;

public class LIGHT_BULLET_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spark Bolt With Trigger";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "spark trigger"};
        this.imageFile = "light_bullet_trigger.png";
        this.emote = "<:light_bullet_trigger:1433949665597984951>";
        this.description = "A spark bolt that casts another spell upon collision";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_LIGHT_BULLET();
        this.spawnProbabilities = new SpawnProbabilities(1, 0.5, 0.5, 0.5, 0, 0, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
        this.castDelay = 3;
        this.critRate = 5;
        this.screenshake = 0.5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Projectile newProjectile = this.relatedProjectile.clone();
        CastState newCastState = new CastState();

        newProjectile.addTrigger(Projectile.TriggerType.trigger, newCastState);
        castState.addProjectile(newProjectile);
        cardPool.draw(1, true, newCastState);
    }
}

/*{
	id          = "LIGHT_BULLET_TRIGGER",
	name 		= "$action_light_bullet_trigger",
	description = "$actiondesc_light_bullet_trigger",
	sprite 		= "data/ui_gfx/gun_actions/light_bullet_trigger.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/light_bullet_trigger_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/light_bullet.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                         = "0,1,2,3", -- LIGHT_BULLET_TRIGGER
	spawn_probability                   = "1,0.5,0.5,0.5", -- LIGHT_BULLET_TRIGGER
	price = 140,
	mana = 10,
	--max_uses = 100,
	action 		= function()
		c.fire_rate_wait = c.fire_rate_wait + 3
		c.screenshake = c.screenshake + 0.5
		c.damage_critical_chance = c.damage_critical_chance + 5
		add_projectile_trigger_hit_world("data/entities/projectiles/deck/light_bullet.xml", 1)
	end,
}*/