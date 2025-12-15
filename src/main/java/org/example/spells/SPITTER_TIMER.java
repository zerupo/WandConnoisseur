package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_SPITTER;
import org.example.projectiles.Projectile;

public class SPITTER_TIMER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Spitter Bolt With Timer";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "spitter timer"};
        this.imageFile = "spitter_timer.png";
        this.emote = "<:spitter_timer:1433949685369802864>";
        this.description = "A short-lived magical bolt that casts another spell after a timer runs out";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_SPITTER();
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0.5, 0.5, 1, 0, 0, 0, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 10;
        this.castDelay = -1;
        this.spread = 6;
        this.screenshake = 0.1;
        // c.dampening = 0.1
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Projectile newProjectile = this.relatedProjectile.clone();
        CastState newCastState = new CastState();

        newProjectile.addTrigger(Projectile.TriggerType.timer, 40, newCastState);
        castState.addProjectile(newProjectile);
        cardPool.draw(1, true, newCastState);
    }
}

/*{
	id          = "SPITTER_TIMER",
	name 		= "$action_spitter_timer",
	description = "$actiondesc_spitter_timer",
	sprite 		= "data/ui_gfx/gun_actions/spitter_timer.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spitter_timer_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/spitter.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,1,2,3", -- SPITTER_TIMER
	spawn_probability                 = "0.5,0.5,0.5,1", -- SPITTER_TIMER
	price = 140,
	mana = 10,
	--max_uses = 120,
	action 		= function()
		-- damage = 0.1
		c.fire_rate_wait = c.fire_rate_wait - 1
		c.screenshake = c.screenshake + 0.1
		c.dampening = 0.1
		c.spread_degrees = c.spread_degrees + 6.0
		add_projectile_trigger_timer("data/entities/projectiles/deck/spitter.xml", 40, 1)
	end,
}*/