package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_TEMPORARY_WALL;

import java.lang.invoke.MethodHandles;

public class TEMPORARY_WALL extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Summon Wall";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "temporary_wall.png";
        this.emote = staticEmote;
        this.description = "Summons a shortlived obstacle";
        this.type = SpellType.utility;
        this.relatedProjectile = new PROJECTILE_TEMPORARY_WALL();
        this.spawnProbabilities = new SpawnProbabilities(0.1, 0.1, 0.3, 0, 0.4, 0.2, 0, 0, 0, 0, 0);
        this.price = 100;
        this.manaCost = 40;
        this.hasCharges = true;
        this.maxCharges = 20;
        this.castDelay = 40;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "TEMPORARY_WALL",
	name 		= "$action_temporary_wall",
	description = "$actiondesc_temporary_wall",
	sprite 		= "data/ui_gfx/gun_actions/temporary_wall.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/temporary_wall.xml"},
	type 		= ACTION_TYPE_UTILITY,
	spawn_level                       = "0,1,2,4,5", -- WALL_SQUARE
	spawn_probability                 = "0.1,0.1,0.3,0.4,0.2", -- WALL_SQUARE
	price = 100,
	mana = 40,
	max_uses = 20,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/temporary_wall.xml")
		c.fire_rate_wait = c.fire_rate_wait + 40
	end,
}*/