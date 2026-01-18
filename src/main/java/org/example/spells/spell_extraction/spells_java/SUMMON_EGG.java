package org.example.spells;

import org.example.main.*;

public class SUMMON_EGG extends Spell{
    @Override
    protected void initialization(){
        this.name = "Summon egg";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "summon_egg.png";
        //this.emote = "";
        this.description = "Summons an egg that houses a friendly creature";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.7, 0.8, 0.8, 0.7, 0.6, 0.6, 0.5, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 100;
        this.hasCharges = true;
        this.maxCharges = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "SUMMON_EGG",
		name 		= "$action_summon_egg",
		description = "$actiondesc_summon_egg",
		sprite 		= "data/ui_gfx/gun_actions/summon_egg.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= {"data/entities/items/pickup/egg_monster.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5,6", -- SUMMON_EGG
		spawn_probability                 = "0.7,0.8,0.8,0.7,0.6,0.6,0.5", -- SUMMON_EGG
		price = 220,
		mana = 100, 
		max_uses    = 2, 
		action 		= function()
			SetRandomSeed( GameGetFrameNum(), GameGetFrameNum() )
			local types = {"monster","slime","red","fire"}
			local rnd = Random(1, #types)
			local egg_name = "egg_" .. tostring(types[rnd]) .. ".xml"
			add_projectile("data/entities/items/pickup/" .. egg_name)
		end,
	},
	},
*/
}
