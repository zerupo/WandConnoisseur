package org.example.spells;

import org.example.main.*;

public class DAMAGE_FOREVER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Mana To Damage";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "damage_forever.png";
        //this.emote = "";
        this.description = "If the wand has more than 50 mana, all mana over that is converted into additional damage";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0.6, 0.5, 0.2, 0, 0, 0, 0.2);
        this.price = 240;
        this.manaCost = 0;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "DAMAGE_FOREVER",
		name 		= "$action_damage_forever",
		description = "$actiondesc_damage_forever",
		sprite 		= "data/ui_gfx/gun_actions/damage_forever.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/damage_unidentified.png",
		related_extra_entities = { "data/entities/particles/tinyspark_red.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5,6,10", -- DAMAGE
		spawn_probability                 = "0.2,0.3,0.6,0.5,0.2,0.2", -- DAMAGE
		price = 240,
		mana = 0,
		max_uses = 20,
		never_unlimited = true,
		custom_xml_file = "data/entities/misc/custom_cards/damage_forever.xml",
		action 		= function()
			if ( mana > 50 ) then
				local manaforspell = mana - 50
				c.damage_projectile_add = c.damage_projectile_add + 0.025 * manaforspell
				mana = 50
			end
			
			c.gore_particles    = c.gore_particles + 15
			c.fire_rate_wait    = c.fire_rate_wait + 15
			current_reload_time = current_reload_time + 10
			c.extra_entities    = c.extra_entities .. "data/entities/particles/tinyspark_red.xml,"
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			draw_actions( 1, true )
		end,
	},
	},
*/
}
