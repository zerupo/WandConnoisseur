package org.example.spells;

import org.example.main.*;

public class FIREWORK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Fireworks!";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "fireworks.png";
        //this.emote = "";
        this.description = "A fiery, explosive projectile";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 0.8, 1, 1, 0.5, 0.3, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 70;
        this.hasCharges = true;
        this.maxCharges = 25;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BAAB_LOVE",
		name 		= "$action_baab_love",
		description = "$actiondesc_baab_love",
		sprite 		= "data/ui_gfx/gun_actions/baab_love.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/baab_love.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "", -- BAAB_LOVE
		spawn_probability                 = "", -- BAAB_LOVE
		price = 140,
		mana = 0,
		--max_uses = 100,
		action 		= function()
			baab_instruction( "magic_liquid_charm" )
			draw_actions( 1, true )
		end,
	},]]--
		id          = "FIREWORK",
		name 		= "$action_firework",
		description = "$actiondesc_firework",
		spawn_requires_flag = "card_unlocked_firework",
		sprite 		= "data/ui_gfx/gun_actions/fireworks.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/rocket_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/fireworks/firework_pink.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,3,4,5,6", -- FIREWORK
		spawn_probability                 = "1,0.8,1,1,0.5,0.3", -- FIREWORK
		price = 220,
		mana = 70,
		max_uses    = 25, 
		action 		= function()
			SetRandomSeed( GameGetFrameNum(), GameGetFrameNum() )
			local types = {"pink","green","blue","orange"}
			local rnd = Random(1, #types)
			local firework_name = "firework_" .. tostring(types[rnd]) .. ".xml"
			add_projectile("data/entities/projectiles/deck/fireworks/" .. firework_name)
			c.fire_rate_wait = c.fire_rate_wait + 60
			--current_reload_time = current_reload_time + 40
			c.ragdoll_fx = 2
			shot_effects.recoil_knockback = 120.0
		end,
	},
	},
*/
}
