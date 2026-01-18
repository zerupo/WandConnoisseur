package org.example.spells;

import org.example.main.*;

public class SLOW_BULLET extends Spell{
    @Override
    protected void initialization(){
        this.name = "Energy orb";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "slow_bullet.png";
        //this.emote = "";
        this.description = "A slow but powerful orb of energy";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SLOW_BULLET",
		name 		= "$action_slow_bullet",
		description = "$actiondesc_slow_bullet",
		sprite 		= "data/ui_gfx/gun_actions/slow_bullet.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/slow_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bullet_slow.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4", -- SLOW_BULLET
		spawn_probability                 = "1,1,1,1", -- SLOW_BULLET
		price = 160,
		mana = 30,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/bullet_slow.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/bullet_slow.xml")
			c.fire_rate_wait = c.fire_rate_wait + 6
			c.screenshake = c.screenshake + 2
			c.spread_degrees = c.spread_degrees + 3.6
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 20.0
		end,
	},
	},
*/
}
