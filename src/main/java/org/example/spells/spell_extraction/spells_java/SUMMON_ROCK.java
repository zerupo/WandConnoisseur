package org.example.spells;

import org.example.main.*;

public class SUMMON_ROCK extends Spell{
    @Override
    protected void initialization(){
        this.name = "Rock";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "summon_rock.png";
        //this.emote = "";
        this.description = "Create a mighty rock out of thin air";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.8, 0.6, 0.6, 0.3, 0.7, 0.7, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 100;
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
		id          = "SUMMON_ROCK",
		name 		= "$action_summon_rock",
		description = "$actiondesc_summon_rock",
		sprite 		= "data/ui_gfx/gun_actions/summon_rock.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/bomb_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/rock.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "0,1,2,3,4,5,6", -- SUMMON_ROCK
		spawn_probability                 = "0.8,0.8,0.6,0.6,0.3,0.7,0.7", -- SUMMON_ROCK
		price = 160,
		mana = 100, 
		max_uses    = 3, 
		custom_xml_file = "data/entities/misc/custom_cards/summon_rock.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/rock.xml")
		end,
	},
	},
*/
}
