package org.example.spells;

import org.example.main.*;

public class BOMB extends Spell{
    @Override
    protected void initialization(){
        this.name = "Bomb";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "bomb.png";
        //this.emote = "";
        this.description = "Summons a bomb that destroys ground very efficiently";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(1, 1, 1, 1, 0.5, 0.5, 0.1, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 25;
        this.hasCharges = true;
        this.maxCharges = 3;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
{
	-- projectiles --
	--[[{
		id          = "TESTBULLET", -- REMOVE THIS ONCE PHYSICS_EXPLOSION_POWER IS ADJUSTED, JUST FOR TESTING
		name 		= "$action_testbullet",
		description = "$actiondesc_testbullet",
		sprite 		= "data/debug/icon_testbullet.png",
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "", -- TESTBULLET
		spawn_probability                        = "", -- TESTBULLET
		price = 100,
		mana = 0,
		max_uses = 999,
		action 		= function()
			add_projectile("data/entities/animals/boss_centipede/firepillar.xml")
			c.fire_rate_wait = 0
			current_reload_time = current_reload_time * 0.01
		end,
	},]]--
		id          = "BOMB",
		name 		= "$action_bomb",
		description = "$actiondesc_bomb",
		sprite 		= "data/ui_gfx/gun_actions/bomb.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/bomb.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5,6", -- BOMB
		spawn_probability                 = "1,1,1,1,0.5,0.5,0.1", -- BOMB
		price = 200,
		mana = 25, 
		max_uses    = 3, 
		custom_xml_file = "data/entities/misc/custom_cards/bomb.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/bomb.xml")
			c.fire_rate_wait = c.fire_rate_wait + 100
		end,
	},
	},
*/
}
