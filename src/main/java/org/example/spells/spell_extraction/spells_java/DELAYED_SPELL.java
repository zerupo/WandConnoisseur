package org.example.spells;

import org.example.main.*;

public class DELAYED_SPELL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Delayed spellcast";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "delayed_spell.png";
        //this.emote = "";
        this.description = "A static";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.8, 1, 0, 0.7, 0.5, 0.4, 0, 0, 0, 0);
        this.price = 240;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DELAYED_SPELL",
		name 		= "$action_delayed_spell",
		description = "$actiondesc_delayed_spell",
		sprite 		= "data/ui_gfx/gun_actions/delayed_spell.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/delayed_spell.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- DELAYED_SPELL
		spawn_probability                 = "0.8,0.8,1,0.7,0.5,0.4", -- DELAYED_SPELL
		price = 240,
		mana = 20,
		action 		= function()
			add_projectile_trigger_death("data/entities/projectiles/deck/delayed_spell.xml", 3)
			c.fire_rate_wait = c.fire_rate_wait + 10
		end,
	},
	},
*/
}
