package org.example.spells;

import org.example.main.*;
import org.example.projectiles.PROJECTILE_OCARINA_GSHARP;

public class OCARINA_GSHARP extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ocarina - Note G+";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ocarina g#", "g#"};
        this.imageFile = "ocarina_gsharp.png";
        this.emote = "<:ocarina_gsharp:1451342046777573376>";
        this.description = "Music for your ears!";
        this.type = SpellType.other;
        this.relatedProjectile = new PROJECTILE_OCARINA_GSHARP();
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.price = 10;
        this.manaCost = 1;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "OCARINA_GSHARP",
	name 		= "$action_ocarina_gsharp",
	description = "$actiondesc_ocarina_gsharp",
	spawn_requires_flag = "card_unlocked_ocarina",
	sprite 		= "data/ui_gfx/gun_actions/ocarina_gsharp.png",
	related_projectiles	= {"data/entities/projectiles/deck/ocarina/ocarina_gsharp.xml"},
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "10", -- OCARINA_GSHARP
	spawn_probability                 = "0", -- OCARINA_GSHARP
	price = 10,
	mana = 1,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/ocarina/ocarina_gsharp.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/