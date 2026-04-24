package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;

import java.lang.invoke.MethodHandles;

public class ENERGY_SHIELD extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Energy shield";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "energy_shield.png";
        this.emote = staticEmote;
        this.description = "Deflects incoming projectiles";
        this.type = SpellType.passif;
        this.spawnProbabilities = new SpawnProbabilities(0, 0.05, 0.4, 0.8, 0.4, 0.4, 0.6, 0, 0, 0, 0);
        this.price = 220;
        this.manaCost = 0;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        cardPool.draw(1, true, castState);
    }
}

/*{
	id          = "ENERGY_SHIELD",
	name 		= "$action_energy_shield",
	description = "$actiondesc_energy_shield",
	sprite 		= "data/ui_gfx/gun_actions/energy_shield.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/energy_shield_unidentified.png",
	type 		= ACTION_TYPE_PASSIVE,
	spawn_level                       = "1,2,3,4,5,6", -- ENERGY_SHIELD
	spawn_probability                 = "0.05,0.4,0.8,0.4,0.4,0.6", -- ENERGY_SHIELD
	price = 220,
	custom_xml_file = "data/entities/misc/custom_cards/energy_shield.xml",
	action 		= function()
		-- does nothing to the projectiles
		draw_actions( 1, true )
	end,
}*/