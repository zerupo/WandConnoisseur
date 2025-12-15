package org.example.spells;

import org.example.main.*;

public class Y_SHAPE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Formation - Bifurcated";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "y shape", "bifurcated"};
        this.imageFile = "y_shape.png";
        this.emote = "<:y_shape:1433949690923319488>";
        this.description = "Casts 2 spells in a bifurcated pattern";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0.8, 0.5, 0.4, 0.3, 0, 0, 0, 0, 0, 0, 0);
        this.price = 30;
        this.manaCost = 2;
        this.autoStat = false;
        this.patern = 45;
        this.spread = -8.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(2, true, castState);
        castState.setPatern(this.patern);
        castState.addSpread(this.spread);
    }
}

/*{
	id          = "Y_SHAPE",
	name 		= "$action_y_shape",
	description = "$actiondesc_y_shape",
	sprite 		= "data/ui_gfx/gun_actions/y_shape.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/y_shape_unidentified.png",
	type 		= ACTION_TYPE_DRAW_MANY,
	spawn_level                       = "0,1,2,3", -- Y_SHAPE
	spawn_probability                 = "0.8,0.5,0.4,0.3", -- Y_SHAPE
	price = 30,
	mana = 2,
	--max_uses = 100,
	action 		= function()
		draw_actions(2, true)
		c.pattern_degrees = 45
		c.spread_degrees = c.spread_degrees - 8.0
	end,
}*/