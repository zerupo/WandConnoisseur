package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_ELECTROCUTION_FIELD;

import java.lang.invoke.MethodHandles;

public class ELECTROCUTION_FIELD extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Circle of thunder";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "electrocution_field.png";
        this.emote = staticEmote;
        this.description = "A field of electrifying magic";
        this.type = SpellType.static_projectile;
        this.relatedProjectile = new PROJECTILE_ELECTROCUTION_FIELD();
        this.spawnProbabilities = new SpawnProbabilities(0, 0.3, 0, 0.6, 0, 0.8, 0.3, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 60;
        this.hasCharges = true;
        this.maxCharges = 15;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "ELECTROCUTION_FIELD",
	name 		= "$action_electrocution_field",
	description = "$actiondesc_electrocution_field",
	sprite 		= "data/ui_gfx/gun_actions/electrocution_field.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/electrocution_field_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/electrocution_field.xml"},
	type 		= ACTION_TYPE_STATIC_PROJECTILE,
	spawn_level                       = "1,3,5,6", -- ELECTROCUTION_FIELD
	spawn_probability                 = "0.3,0.6,0.8,0.3", -- ELECTROCUTION_FIELD
	price = 200,
	mana = 60,
	max_uses = 15,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/electrocution_field.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/