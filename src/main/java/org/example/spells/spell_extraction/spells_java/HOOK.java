package org.example.spells;

import org.example.main.*;

public class HOOK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Hookbolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "hook.png";
        //this.emote = "";
        this.description = "A glowing hook that pulls the caster towards itself upon collision";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.1, 0.3, 0.4, 0.2, 0.1, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HOOK",
		name 		= "$action_hook",
		description = "$actiondesc_hook",
		sprite 		= "data/ui_gfx/gun_actions/hook.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bullet_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/hook.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5", -- BULLET
		spawn_probability                 = "0.1,0.3,0.4,0.2,0.1", -- BULLET
		price = 120,
		mana = 30,
		--max_uses = -1,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/hook.xml")
			c.fire_rate_wait = c.fire_rate_wait + 12
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
		end,
	},
	},
*/
}
