package org.example.spells;

import org.example.main.*;

public class LASER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Concentrated light";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "laser.png";
        //this.emote = "";
        this.description = "A pinpointed beam of light";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "LASER",
		name 		= "$action_laser",
		description = "$actiondesc_laser",
		sprite 		= "data/ui_gfx/gun_actions/laser.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/laser_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/laser.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "1,2,4", -- LASER
		spawn_probability                 = "1,1,1", -- LASER
		price = 180,
		mana = 30,
		--max_uses = 80,
		custom_xml_file = "data/entities/misc/custom_cards/laser.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/laser.xml")
			c.fire_rate_wait = c.fire_rate_wait - 22
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_disintegrated.xml,"
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 20.0
		end,
	},
	},
*/
}
