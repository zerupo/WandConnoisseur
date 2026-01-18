package org.example.spells;

import org.example.main.*;

public class WALL_HORIZONTAL extends Spell{
    @Override
    protected void initialization(){
        this.name = "Horizontal barrier";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "wall_horizontal.png";
        //this.emote = "";
        this.description = "A thin";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.4, 0.4, 0.6, 0, 0.5, 0.2, 0, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 70;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "WALL_HORIZONTAL",
		name 		= "$action_wall_horizontal",
		description = "$actiondesc_wall_horizontal",
		sprite 		= "data/ui_gfx/gun_actions/wall_horizontal.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/wall_horizontal.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,4,5", -- WALL_HORIZONTAL
		spawn_probability                 = "0.4,0.4,0.6,0.5,0.2", -- WALL_HORIZONTAL
		price = 160,
		mana = 70,
		--max_uses = 80,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/wall_horizontal.xml")
			c.fire_rate_wait = c.fire_rate_wait + 5
		end,
	},
	},
*/
}
