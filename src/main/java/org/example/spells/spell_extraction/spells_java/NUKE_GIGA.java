package org.example.spells;

import org.example.main.*;

public class NUKE_GIGA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Giga Nuke";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "nuke_giga.png";
        //this.emote = "";
        this.description = "What do you expect?";
        this.type = SpellType.projectile;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        this.price = 800;
        this.manaCost = 500;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "NUKE_GIGA",
		name 		= "$action_nuke_giga",
		description = "$actiondesc_nuke_giga",
		sprite 		= "data/ui_gfx/gun_actions/nuke_giga.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/nuke_unidentified.png",
		related_projectiles	= {"data/entities/projectiles/deck/nuke_giga.xml"},
		spawn_requires_flag = "card_unlocked_nukegiga",
		spawn_manual_unlock = true,
		never_unlimited		= true,
		recursive	= true,
		ai_never_uses = true,
		type 		= ACTION_TYPE_PROJECTILE,
		spawn_level                       = "10", -- NUKE
		spawn_probability                 = "1", -- NUKE
		price = 800,
		mana = 500,
		max_uses    = 1,
		custom_xml_file = "data/entities/misc/custom_cards/nuke_giga.xml",
		action 		= function()
			add_projectile("data/entities/projectiles/deck/nuke_giga.xml")
			c.fire_rate_wait = 50
			c.speed_multiplier = c.speed_multiplier * 0.5
			c.material = "fire"
			c.material_amount = c.material_amount + 80
			c.ragdoll_fx = 2
			c.gore_particles = c.gore_particles + 30
			c.screenshake = c.screenshake + 30.5
			current_reload_time = current_reload_time + 800
			shot_effects.recoil_knockback = shot_effects.recoil_knockback + 300.0
			
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
