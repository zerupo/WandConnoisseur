package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_LASER_EMITTER_CUTTER;

public class LASER_EMITTER_CUTTER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Plasma Cutter";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "laser_emitter_cutter.png";
        this.emote = "<:laser_emitter_cutter:1433949661336436736>";
        this.description = "A plasma beam specialized in cutting materials!";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_LASER_EMITTER_CUTTER();
        this.spawnProbabilities = new SpawnProbabilities(0.2, 0.3, 1, 0.5, 1, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 40;
        this.castDelay = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
        // c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
    }
}

/*{
	id          = "LASER_EMITTER_CUTTER",
	name 		= "$action_laser_emitter_cutter",
	description = "$actiondesc_laser_emitter_cutter",
	sprite 		= "data/ui_gfx/gun_actions/laser_emitter_cutter.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/laser_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/orb_laseremitter_cutter.xml"},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,1,2,3,4", -- LASER
	spawn_probability                 = "0.2,0.3,1,0.5,0.1", -- LASER
	price = 120,
	mana = 40,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/orb_laseremitter_cutter.xml")
		current_reload_time = current_reload_time + 10
		c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
	end,
}*/