package org.example.spells;

import org.example.main.*;

public class BURST_4 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Quadruple Spell";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "quad", "quad cast", "quad spell", "quadruple", "quadruple cast", "quadruple spell"};
        this.imageFile = "burst_4.png";
        this.emote = "<:burst_4:1433949554243272906>";
        this.description = "Simultaneously casts 4 spells";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.4, 0.5, 0.6, 0.6, 0.6, 0, 0, 0, 0);
        this.price = 180;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(4, true, castState);
    }
}

/*{
	id          = "BURST_4",
	name 		= "$action_burst_4",
	description = "$actiondesc_burst_4",
	sprite 		= "data/ui_gfx/gun_actions/burst_4.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/burst_4_unidentified.png",
	type 		= ACTION_TYPE_DRAW_MANY,
	spawn_level                       = "2,3,4,5,6", -- BURST_4
	spawn_probability                 = "0.4,0.5,0.6,0.6,0.6", -- BURST_4
	price = 180,
	mana = 5,
    --max_uses = 100,
	action 		= function()
		draw_actions( 4, true )
	end,
}*/