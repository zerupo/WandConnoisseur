package org.example.spells;

import org.example.main.*;

public class SCATTER_3 extends Spell{
    @Override
    protected void initialization(){
        this.name = "Triple Scatter Spell";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "triple scatter", "triple spread"};
        this.imageFile = "scatter_3.png";
        this.emote = "<:scatter_3:1433949679980380311>";
        this.description = "Simultaneously casts 3 spells with low accuracy";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0.6, 0.7, 0.7, 0.8, 0, 0, 0, 0, 0, 0, 0);
        this.price = 120;
        this.manaCost = 1;
        this.autoStat = false;
        this.spread = 20.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(3, true, castState);
        castState.addSpread(this.spread);
    }
}

/*{
	id          = "SCATTER_3",
	name 		= "$action_scatter_3",
	description = "$actiondesc_scatter_3",
	sprite 		= "data/ui_gfx/gun_actions/scatter_3.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/scatter_3_unidentified.png",
	type 		= ACTION_TYPE_DRAW_MANY,
	spawn_level                      = "0,1,2,3", -- SCATTER_3
	spawn_probability                = "0.6,0.7,0.7,0.8", -- SCATTER_3
	price = 120,
	mana = 1,
	--max_uses = 100,
	action 		= function()
		draw_actions( 3, true )
		c.spread_degrees = c.spread_degrees + 20.0
	end,
}*/