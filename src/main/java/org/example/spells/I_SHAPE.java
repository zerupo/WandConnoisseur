package org.example.spells;

import org.example.main.*;

public class I_SHAPE extends Spell{
    @Override
    protected void initialization(){
        this.name = "Formation - Behind Your Back";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "behind your back", "behind back"};
        this.imageFile = "i_shape.png";
        this.emote = "<:i_shape:1433949659944190054>";
        this.description = "Casts two spells: one ahead of and one behind the caster";
        this.type = SpellType.multicast;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0.5, 0.3, 0, 0, 0, 0, 0, 0, 0);
        this.price = 30;
        this.autoStat = false;
        this.patern = 180;
        this.spread = -5.0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(2, true, castState);
        castState.setPatern(this.patern);
        castState.addSpread(this.spread);
    }
}

/*{
	id          = "I_SHAPE",
	name 		= "$action_i_shape",
	description = "$actiondesc_i_shape",
	sprite 		= "data/ui_gfx/gun_actions/i_shape.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/i_shape_unidentified.png",
	type 		= ACTION_TYPE_DRAW_MANY,
	spawn_level                       = "1,2,3", -- I_SHAPE
	spawn_probability                 = "0.4,0.5,0.3", -- I_SHAPE
	price = 30,
	mana = 0,
	--max_uses = 100,
	action 		= function()
		draw_actions(2, true)
		c.pattern_degrees = 180
		c.spread_degrees = c.spread_degrees - 5.0
	end,
}*/