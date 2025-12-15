package org.example.spells;

import org.example.main.*;

public class PHASING_ARC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Phasing Arc";
        this.alias = new String[]{this.getClass().getSimpleName(), this.name, "phasing"};
        this.imageFile = "phasing_arc.png";
        this.emote = "<:phasing_arc:1433949674968191137>";
        this.description = "Makes a projectile fly much slower, but teleport short distances over its flight";
        this.type = SpellType.modifier;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0.3, 0.6, 0.1, 0, 0, 0, 0, 0);
        this.price = 170;
        this.manaCost = 2;
        this.autoStat = false;
        this.castDelay = -12;
        //this.speedMultiplier = 0.33;
        this.lifetime = 80;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // c.extra_entities = c.extra_entities .. "data/entities/misc/phasing_arc.xml,"
        cardPool.draw(1, true, castState);
        castState.addCastDelay(this.castDelay);
        // castState.addSpeedMultiplier(this.speed);
        castState.addLifetime(this.lifetime);

        /*if ( c.speed_multiplier >= 20 ) then
			c.speed_multiplier = math.min( c.speed_multiplier, 20 )
		elseif ( c.speed_multiplier < 0 ) then
			c.speed_multiplier = 0
		end*/
    }
}

/*{
	id          = "PHASING_ARC",
	name 		= "$action_phasing_arc",
	description = "$actiondesc_phasing_arc",
	sprite 		= "data/ui_gfx/gun_actions/phasing_arc.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/sinewave_unidentified.png",
	related_extra_entities = { "data/entities/misc/phasing_arc.xml" },
	type 		= ACTION_TYPE_MODIFIER,
	spawn_level                       = "2,3,4,5", -- HORIZONTAL_ARC
	spawn_probability                 = "0.2,0.3,0.6,0.1", -- HORIZONTAL_ARC
	price = 170,
	mana = 2,
	--max_uses = 150,
	action 		= function()
		c.extra_entities = c.extra_entities .. "data/entities/misc/phasing_arc.xml,"
		draw_actions( 1, true )
		c.fire_rate_wait    = c.fire_rate_wait - 12
		c.lifetime_add 		= c.lifetime_add + 80
		c.speed_multiplier	= c.speed_multiplier * 0.33
		c.child_speed_multiplier	= c.child_speed_multiplier * 0.33

		if ( c.speed_multiplier >= 20 ) then
			c.speed_multiplier = math.min( c.speed_multiplier, 20 )
		elseif ( c.speed_multiplier < 0 ) then
			c.speed_multiplier = 0
		end
	end,
}*/