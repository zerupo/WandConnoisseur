package org.example.spells;

import org.example.main.*;

public class PURPLE_EXPLOSION_FIELD extends Spell{
    @Override
    protected void initialization(){
        this.name = "Glittering field";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "purple_explosion_field.png";
        //this.emote = "";
        this.description = "Small explosions appear randomly over a large area";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0.7, 1, 0.7, 0, 0.5, 0.5, 0.3, 0, 0, 0, 0);
        this.price = 160;
        this.manaCost = 90;
        this.hasCharges = true;
        this.maxCharges = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "PURPLE_EXPLOSION_FIELD",
		name 		= "$action_purple_explosion_field",
		description = "$actiondesc_purple_explosion_field",
		sprite 		= "data/ui_gfx/gun_actions/purple_explosion_field.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/teleport_projectile_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/purple_explosion_field.xml"},
		type 		= ACTION_TYPE_STATIC_PROJECTILE,
		spawn_level                       = "0,1,2,4,5,6", -- PURPLE_EXPLOSION_FIELD
		spawn_probability                 = "0.7,1,0.7,0.5,0.5,0.3", -- PURPLE_EXPLOSION_FIELD
		price = 160,
		mana = 90,
		max_uses = 20,
		action 		= function()
			add_projectile("data/entities/projectiles/deck/purple_explosion_field.xml")
			c.fire_rate_wait = c.fire_rate_wait + 10
			c.speed_multiplier = c.speed_multiplier - 2
			
			if ( c.speed_multiplier >= 20 ) then
				c.speed_multiplier = math.min( c.speed_multiplier, 20 )
			elseif ( c.speed_multiplier < 0 ) then
				c.speed_multiplier = 0
			end
		end,
	},
	},
*/
}
