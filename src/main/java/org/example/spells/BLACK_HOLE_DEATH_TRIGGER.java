package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.Projectile;
import org.example.projectiles.PROJECTILE_BLACK_HOLE;

import java.lang.invoke.MethodHandles;

public class BLACK_HOLE_DEATH_TRIGGER extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Black Hole With Death Trigger";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "bh timer", "bh expiration"};
        this.imageFile = "black_hole_timer.png";
        this.emote = staticEmote;
        this.description = "A slow orb of void that eats through all obstacles and casts another spell as it expires";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BLACK_HOLE();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.5, 0, 0.5, 0.5, 0.5, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 200;
        this.hasCharges = true;
        this.maxCharges = 3;
        this.neverUnlimited = true;
        this.castDelay = 90;
        this.screenshake = 20.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Projectile newProjectile = this.relatedProjectile.clone();
        CastState newCastState = new CastState();

        newProjectile.addTrigger(Projectile.TriggerType.expiration, newCastState);
        castState.addProjectile(newProjectile);
        cardPool.draw(1, true, newCastState);
    }
}

/*{
	id          = "BLACK_HOLE_DEATH_TRIGGER",
	name 		= "$action_black_hole_death_trigger",
	description = "$actiondesc_black_hole_death_trigger",
	sprite 		= "data/ui_gfx/gun_actions/black_hole_timer.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/black_hole_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/black_hole.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "2,4,5,6", -- BLACK_HOLE
	spawn_probability                 = "0.5,0.5,0.5,0.5", -- BLACK_HOLE
	price = 220,
	mana = 200,
	max_uses    = 3,
	never_unlimited = true,
	custom_xml_file = "data/entities/misc/custom_cards/black_hole.xml",
	action 		= function()
		add_projectile_trigger_death("data/entities/projectiles/deck/black_hole.xml", 1)
		c.fire_rate_wait = c.fire_rate_wait + 90
		c.screenshake = c.screenshake + 20
	end,
}*/