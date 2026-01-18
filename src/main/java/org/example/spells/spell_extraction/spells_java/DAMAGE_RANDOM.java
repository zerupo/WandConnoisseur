package org.example.spells;

import org.example.main.*;

public class DAMAGE_RANDOM extends Spell{
    @Override
    protected void initialization(){
        this.name = "Random damage";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "damage_random.png";
        //this.emote = "";
        this.description = "Randomly increases or lowers the damage done by projectiles";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.7, 0.6, 0.6, 0, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DAMAGE_RANDOM",
		name 		= "$action_damage_random",
		description = "$actiondesc_damage_random",
		sprite 		= "data/ui_gfx/gun_actions/damage_random.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/damage_unidentified.png",
		spawn_requires_flag = "card_unlocked_pyramid",
		related_extra_entities = { "data/entities/particles/tinyspark_yellow.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "3,4,5", -- DAMAGE
		spawn_probability                 = "0.7,0.6,0.6", -- DAMAGE
		price = 200,
		mana = 15,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/damage_random.xml",
		action 		= function()
			SetRandomSeed( GameGetFrameNum(), GameGetFrameNum() + 253 )
			local multiplier = 0
			multiplier = Random( -3, 4 ) * Random( 0, 2 )
			local result = 0
			result = c.damage_projectile_add + 0.4 * multiplier
			c.damage_projectile_add = result
			c.gore_particles    = c.gore_particles + 5 * multiplier
			c.fire_rate_wait    = c.fire_rate_wait + 5
			c.extra_entities    = c.extra_entities .. "data/entities/particles/tinyspark_yellow.xml,"
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0 * multiplier
			draw_actions( 1, true )
		end,
	},
	},
*/
}
