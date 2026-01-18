package org.example.spells;

import org.example.main.*;

public class HITFX_TOXIC_CHARM extends Spell{
    @Override
    protected void initialization(){
        this.name = "Charm on toxic sludge";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "charm_on_toxic.png";
        //this.emote = "";
        this.description = "Makes a projectile charm creatures covered in toxic sludge";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.2, 0, 0.2, 0.3, 0.2, 0, 0, 0, 0, 0);
        this.price = 150;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "HITFX_TOXIC_CHARM",
		name 		= "$action_hitfx_toxic_charm",
		description = "$actiondesc_hitfx_toxic_charm",
		sprite 		= "data/ui_gfx/gun_actions/charm_on_toxic.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/freeze_unidentified.png",
		related_extra_entities = { "data/entities/misc/hitfx_toxic_charm.xml" },
		type 		= ACTION_TYPE_MODIFIER,
		spawn_level                       = "1,3,4,5", -- HITFX_TOXIC_CHARM
		spawn_probability                 = "0.2,0.2,0.3,0.2", -- HITFX_TOXIC_CHARM
		price = 150,
		mana = 70,
		--max_uses = 50,
		action 		= function()
			c.extra_entities = c.extra_entities .. "data/entities/misc/hitfx_toxic_charm.xml,"
			draw_actions( 1, true )
		end,
	},
	},
*/
}
