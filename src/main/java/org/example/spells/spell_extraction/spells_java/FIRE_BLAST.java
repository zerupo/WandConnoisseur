package org.example.spells;

import org.example.main.*;

public class FIRE_BLAST extends Spell{
    @Override
    protected void initialization(){
        this.name = "Explosion of brimstone";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fire_blast.png";
        //this.emote = "";
        this.description = "A fiery explosion";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0.7, 0, 0.6, 0, 0.4, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "FIRE_BLAST",
		name 		= "$action_fire_blast",
		description = "$actiondesc_fire_blast",
		sprite 		= "data/ui_gfx/gun_actions/fire_blast.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/fire_blast_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/fireblast.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,3,5", -- FIRE_BLAST
		spawn_probability                 = "0.5,0.7,0.6,0.4", -- FIRE_BLAST
		price = 120,
		mana = 10,
		--max_uses = 30,
		custom_xml_file = "data/entities/misc/custom_cards/fire_blast.xml",
		is_dangerous_blast = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/fireblast.xml")
			c.fire_rate_wait = c.fire_rate_wait + 3
			c.screenshake = c.screenshake + 0.5
		end,
	},
	},
*/
}
