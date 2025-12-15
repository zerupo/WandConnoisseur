package org.example.spells;

import org.example.main.CardPool;
import org.example.main.CastState;
import org.example.main.SpawnProbabilities;
import org.example.projectiles.PROJECTILE_BUCKSHOT;

public class BUCKSHOT extends Spell{
    @Override
    protected void initialization(){
        this.name = "Triplicate Bolt";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "triplicate", "shotgun"};
        this.imageFile = "buckshot.png";
        this.emote = "<:buckshot:1433949550917455893>";
        this.description = "A formation of three small, fast bolts";
        this.type = SpellType.projectile;
        this.relatedProjectile = new PROJECTILE_BUCKSHOT();
        this.relatedProjectileCount = 3;
        this.spawnProbabilities = new SpawnProbabilities(1, 1, 0.9, 0.9, 0.6, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 25;
        this.castDelay = 8;
        this.spread = 14;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        for(int i=0; i < this.relatedProjectileCount; i++){
            castState.addProjectile(this.relatedProjectile.clone());
        }
    }
}

/*{
	id          = "BUCKSHOT",
	name 		= "$action_buckshot",
	description = "$actiondesc_buckshot",
	sprite 		= "data/ui_gfx/gun_actions/buckshot.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/dynamite_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/buckshot_player.xml",3},
	type 		= ACTION_TYPE_PROJECTILE,
	spawn_level                       = "0,1,2,3,4", -- BUCKSHOT
	spawn_probability                 = "1,1,0.9,0.9,0.6", -- BUCKSHOT
	price = 160,
	mana = 25,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/buckshot_player.xml")
		add_projectile("data/entities/projectiles/deck/buckshot_player.xml")
		add_projectile("data/entities/projectiles/deck/buckshot_player.xml")
		c.fire_rate_wait = c.fire_rate_wait + 8
		c.spread_degrees = c.spread_degrees + 14.0
	end,
}*/