package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;

import java.lang.invoke.MethodHandles;

public class BLOODLUST extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Bloodlust";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bloodlust.png";
        this.emote = staticEmote;
        this.description = "A projectile gains a hefty damage boost, but is also able to hurt you";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.3, 0.6, 0.7, 0.3, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 2;
        this.castDelay = 8;
        this.damageComponent.setProjectile(32.5);
        this.spread = 6.0;
        this.recoil = 30.0;
        this.setFriendlyFire = true;
        this.friendlyFire = true;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.gore_particles    = c.gore_particles + 15
        // c.extra_entities    = c.extra_entities .. "data/entities/particles/tinyspark_red.xml,"
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "BLOODLUST",
	name 		= "$action_bloodlust",
	description = "$actiondesc_bloodlust",
	sprite 		= "data/ui_gfx/gun_actions/bloodlust.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/homing_unidentified.png",
	related_extra_entities = { "data/entities/particles/tinyspark_red.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,3,4,5,6", -- PIERCING_SHOT
	spawn_probability                 = "0.2,0.3,0.6,0.7,0.3", -- PIERCING_SHOT
	price = 160,
	mana = 2,
	--max_uses = 100,
	action 		= function()
		c.damage_projectile_add = c.damage_projectile_add + 1.3
		c.gore_particles    = c.gore_particles + 15
		c.fire_rate_wait    = c.fire_rate_wait + 8
		c.friendly_fire		= true
		shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
		c.spread_degrees = c.spread_degrees + 6
		c.extra_entities    = c.extra_entities .. "data/entities/particles/tinyspark_red.xml,"
		draw_actions( 1, true )
	end,
}*/