package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;
import org.example.projectiles.PROJECTILE_POLYMORPH_FIELD;

import java.lang.invoke.MethodHandles;

public class POLYMORPH_FIELD extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Circle of transmogrification";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "circle of poly"};
        this.imageFile = "polymorph_field.png";
        this.emote = staticEmote;
        this.description = "A field of sheep-like magic";
        this.type = SpellType.static_projectile;
        this.relatedProjectile = new PROJECTILE_POLYMORPH_FIELD();
        this.spawnProbabilities = new SpawnProbabilities(0.3, 0.3, 0.3, 0.8, 0.8, 0.3, 0.3, 0, 0, 0, 0);
        this.price = 200;
        this.manaCost = 50;
        this.hasCharges = true;
        this.maxCharges = 5;
        this.castDelay = 15;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        castState.addProjectile(this.relatedProjectile.clone());
    }
}

/*{
	id          = "POLYMORPH_FIELD",
	name 		= "$action_polymorph_field",
	description = "$actiondesc_polymorph_field",
	sprite 		= "data/ui_gfx/gun_actions/polymorph_field.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/polymorph_field_unidentified.png",
	related_projectiles	= {"data/entities/projectiles/deck/polymorph_field.xml"},
	type 		= ACTION_TYPE_STATIC_PROJECTILE,
	spawn_level                       = "0,1,2,3,4,5,6", -- POLYMORPH_FIELD
	spawn_probability                 = "0.3,0.3,0.3,0.8,0.8,0.3,0.3", -- POLYMORPH_FIELD
	price = 200,
	mana = 50,
	max_uses = 5,
	action 		= function()
		add_projectile("data/entities/projectiles/deck/polymorph_field.xml")
		c.fire_rate_wait = c.fire_rate_wait + 15
	end,
}*/