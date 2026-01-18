package org.example.spells;

import org.example.main.*;

public class EXPLOSION extends Spell{
    @Override
    protected void initialization(){
        this.name = "Explosion";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "explosion.png";
        //this.emote = "";
        this.description = "A powerful explosion";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.5, 0, 1, 0, 1, 0.7, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 80;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "EXPLOSION",
		name 		= "$action_explosion",
		description = "$actiondesc_explosion",
		sprite 		= "data/ui_gfx/gun_actions/explosion.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosion_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/explosion.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,2,4,5", -- EXPLOSION
		spawn_probability                 = "0.5,1,1,0.7", -- EXPLOSION
		price = 160,
		mana = 80,
		--max_uses = 30,
		custom_xml_file = "data/entities/misc/custom_cards/explosion.xml",
		is_dangerous_blast = true,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/explosion.xml")
			c.fire_rate_wait = c.fire_rate_wait + 3
			c.screenshake = c.screenshake + 2.5
		end,
	},
	},
*/
}
