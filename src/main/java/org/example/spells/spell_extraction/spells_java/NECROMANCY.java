package org.example.spells;

import org.example.main.*;

public class NECROMANCY extends Spell{
    @Override
    protected void initialization(){
        this.name = "Necromancy";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "necromancy.png";
        //this.emote = "";
        this.description = "Makes corpses of creatures killed by a projectile rise to your aid";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.6, 0.6, 0.3, 0, 0, 0, 0, 0);
        this.price = 80;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "NECROMANCY",
		name 		= "$action_necromancy",
		description = "$actiondesc_necromancy",
		spawn_requires_flag = "card_unlocked_necromancy",
		sprite 		= "data/ui_gfx/gun_actions/necromancy.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "2,3,4,5", -- NECROMANCY
		spawn_probability                 = "0.4,0.6,0.6,0.3", -- NECROMANCY
		price = 80,
		mana = 20,
		--max_uses = 50,
		action 		= function()
			c.game_effect_entities = c.game_effect_entities .. "data/entities/misc/effect_necromancy.xml,"
			c.fire_rate_wait = c.fire_rate_wait + 10
			draw_actions( 1, true )
		end,
	},
	},
*/
}
