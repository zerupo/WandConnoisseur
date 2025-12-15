package org.example.spells;

import org.example.main.*;

public class PINGPONG_PATH extends Spell{
    @Override
    protected void initialization(){
        this.name = "Ping-Pong Path";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "ppp", "ping pong", "pingpong path", "ping pong path"};
        this.imageFile = "pingpong_path.png";
        this.emote = "<:pingpong_path:1433949677555810405>";
        this.description = "Makes a projectile fly back and forth";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.4, 0, 0.5, 0, 0.4, 0, 0, 0, 0, 0);
        this.price = 20;
        this.manaCost = 0;
        this.lifetime = 25;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/misc/pingpong_path.xml,"
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "PINGPONG_PATH",
    name 		= "$action_pingpong_path",
	description = "$actiondesc_pingpong_path",
	sprite 		= "data/ui_gfx/gun_actions/pingpong_path.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
	related_extra_entities = { "data/entities/misc/pingpong_path.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "1,3,5", -- PINGPONG_PATH
	spawn_probability                 = "0.4,0.5,0.4", -- PINGPONG_PATH
	price = 20,
	mana = 0,
	--max_uses = 150,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/pingpong_path.xml,"
		c.lifetime_add = c.lifetime_add + 25
		draw_actions( 1, true )
	end,
}*/