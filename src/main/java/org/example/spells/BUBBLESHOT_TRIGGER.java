package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_BUBBLESHOT;
import org.example.projectiles.Projectile;

public class BUBBLESHOT_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Bubble Spark With Trigger";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "bubble trigger"};
        this.imageFile = "bubbleshot_trigger.png";
        this.emote = "<:bubbleshot_trigger:1447276644761862415>";
        this.description = "A bouncy, inaccurate spell that casts another spell upon collision";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BUBBLESHOT();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0.5, 1, 0, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 16;
        this.castDelay = -5;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Projectile newProjectile = this.relatedProjectile.clone();
        CastState newCastState = new CastState();

        // c.dampening = 0.1
        newProjectile.addTrigger(Projectile.TriggerType.trigger, newCastState);
        castState.addProjectile(newProjectile);
        cardPool.draw(1, true, newCastState);

    }
}

/*{
	id          = "BUBBLESHOT_TRIGGER",
	name 		= "$action_bubbleshot_trigger",
	description = "$actiondesc_bubbleshot_trigger",
	sprite 		= "data/ui_gfx/gun_actions/bubbleshot_trigger.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/bubbleshot_trigger_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/bubbleshot.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "1,2,3", -- BUBBLESHOT_TRIGGER
	spawn_probability                 = "0.5,0.5,1", -- BUBBLESHOT_TRIGGER
	price = 120,
	mana = 16,
	--max_uses = 120,
	action 		= function()
		-- damage = 0.1
		c.fire_rate_wait = c.fire_rate_wait - 5
		c.dampening = 0.1
		add_projectile_trigger_hit_world("data/entities/projectiles/deck/bubbleshot.xml", 1)
	end,
}*/