package org.example.spells;

import org.example.main.*;

public class HEAVY_BULLET extends Spell{
    @Override
    protected void initialization(){
        this.name = "Magic bolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "heavy_bullet.png";
        //this.emote = "";
        this.description = "A powerful magical bolt";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 1, 1, 1, 1, 1, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HEAVY_BULLET",
		name 		= "$action_heavy_bullet",
		description = "$actiondesc_heavy_bullet",
		sprite 		= "data/ui_gfx/gun_actions/heavy_bullet.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/heavy_bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/bullet_heavy.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5,6", -- HEAVY_BULLET
		spawn_probability                 = "0.5,1,1,1,1,1", -- HEAVY_BULLET
		price = 200,
		mana = 30,
		--max_uses = 50,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/bullet_heavy.xml")
			c.fire_rate_wait = c.fire_rate_wait + 7
			c.screenshake = c.screenshake + 2.5
			c.spread_degrees = c.spread_degrees + 5.0
			c.damage_critical_chance = c.damage_critical_chance + 5
			-- c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 50.0
		end,
	},
	},
*/
}
