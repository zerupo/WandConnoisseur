package org.example.spells;

import org.example.main.*;

public class THUNDER_BLAST extends Spell{
    @Override
    protected void initialization(){
        this.name = "Explosion of thunder";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "thunder_blast.png";
        //this.emote = "";
        this.description = "An electric explosion";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 0, 0.6, 0, 0.7, 0.5, 0, 0, 0, 0.1);
        this.price = 180;
        this.manaCost = 110;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "THUNDER_BLAST",
		name 		= "$action_thunder_blast",
		description = "$actiondesc_thunder_blast",
		sprite 		= "data/ui_gfx/gun_actions/thunder_blast.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/thunder_blast_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/thunder_blast.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "1,3,5,6,10", -- THUNDER_BLAST
		spawn_probability                 = "0.5,0.6,0.7,0.5,0.1", -- THUNDER_BLAST
		price = 180,
		mana = 110,
		--max_uses = 30,
		custom_xml_file = "data/entities/misc/custom_cards/thunder_blast.xml",
		is_dangerous_blast = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/thunder_blast.xml")
			c.fire_rate_wait = c.fire_rate_wait + 15
			c.screenshake = c.screenshake + 3.0
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
		end,
	},
	},
*/
}
