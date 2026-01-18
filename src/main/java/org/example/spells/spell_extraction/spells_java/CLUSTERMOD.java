package org.example.spells;

import org.example.main.*;

public class CLUSTERMOD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Clusterbolt";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "clusterbomb.png";
        //this.emote = "";
        this.description = "Makes a projectile release a cluster of explosive bolts upon hitting a wall";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.5, 1, 0.6, 0, 0, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "CLUSTERMOD",
		name 		= "$action_clustermod",
		description = "$actiondesc_clustermod",
		sprite 		= "data/ui_gfx/gun_actions/clusterbomb.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/explosive_projectile_unidentified.png",
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,2,3", -- EXPLOSIVE_PROJECTILE
		spawn_probability                 = "0.5,1,0.6", -- EXPLOSIVE_PROJECTILE
		price = 160,
		mana = 30,
		--max_uses = 50,
		custom_xml_file = "data/entities/misc/custom_cards/clusterbomb.xml",
		action 		= function()
			c.explosion_radius = c.explosion_radius + 4.0
			c.damage_explosion_add = c.damage_explosion_add + 0.2
			c.fire_rate_wait   = c.fire_rate_wait + 20
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 10.0
			
			c.extra_entities = c.extra_entities .. "data/entities/misc/clusterbomb.xml,"
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
