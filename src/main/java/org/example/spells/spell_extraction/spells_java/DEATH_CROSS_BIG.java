package org.example.spells;

import org.example.main.*;

public class DEATH_CROSS_BIG extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giga death cross";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "death_cross_big.png";
        //this.emote = "";
        this.description = "A giant";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.5, 0.55, 0.3, 0.4, 0, 0, 0, 0.2);
        this.price = 310;
        this.manaCost = 150;
        this.hasCharges = true;
        this.maxCharges = 8;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DEATH_CROSS_BIG",
		name 		= "$action_death_cross_big",
		description = "$actiondesc_death_cross_big",
		sprite 		= "data/ui_gfx/gun_actions/death_cross_big.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/death_cross_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/death_cross_big.xml"},
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "2,3,4,5,6,10", -- DEATH_CROSS_BIG
		spawn_probability                        = "0.4,0.5,0.55,0.3,0.4,0.2", -- DEATH_CROSS_BIG
		price = 310,
		mana = 150,
		max_uses = 8,
		custom_xml_file = "data/entities/misc/custom_cards/death_cross.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/death_cross_big.xml")
			c.fire_rate_wait = c.fire_rate_wait + 70
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 30.0
		end,
	},
	},
*/
}
