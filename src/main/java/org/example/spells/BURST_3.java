package org.example.spells;

import org.example.main.*;

public class BURST_3 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Triple Spell";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "triple", "triple cast"};
        this.imageFile = "burst_3.png";
        this.emote = "<:burst_3:1433949553094168616>";
        this.description = "Simultaneously casts 3 spells";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0, 0, 0, 0);
        this.price = 140;
        this.manaCost = 2;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(3, true, castState);
    }
}

/*{
	id          = "BURST_3",
	name 		= "$action_burst_3",
	description = "$actiondesc_burst_3",
	sprite 		= "data/ui_gfx/gun_actions/burst_3.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/burst_3_unidentified.png",
	type 		= ACTION_TYPE_DRAW_MANY,
	spawn_level                       = "1,2,3,4,5,6", -- BURST_3
	spawn_probability                 = "0.7,0.7,0.7,0.7,0.7,0.7", -- BURST_3
	price = 160,
	mana = 2,
	--max_uses = 100,
	action 		= function()
		draw_actions( 3, true )
	end,
}*/