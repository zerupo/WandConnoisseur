package org.example.spells;

import org.example.main.*;

public class PHI extends Spell{
    @Override
    protected void initialization(){
        this.name = "Phi";
        this.imageFile = "phi.png";
        this.emote = "<:phi:1433949676477878282>";
        this.description = "Casts a copy of every projectile-type spell in the current wand";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.2, 0, 0, 0, 1);
        this.recursive = true;
        this.price = 500;
        this.manaCost = 120;
        this.castDelay = 50;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Spell currentSpell;
        int currentCastDelay = castState.getCastDelay();
        int currentRechareTime = cardPool.getRechargeTime();
        int currentMana = cardPool.getManaUsage();

        for(int i=0; i < cardPool.getDiscardSize(); i++){
            currentSpell = cardPool.getDiscardSpell(i);
            if(currentSpell.type == SpellType.projectile){
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            }
        }
        for(int i=0; i < cardPool.getHandSize(); i++){
            currentSpell = cardPool.getHandSpell(i);
            if(currentSpell.type == SpellType.projectile){
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            }
        }
        for(int i=0; i < cardPool.getDeckSize(); i++){
            currentSpell = cardPool.getDeckSpell(i);
            if(currentSpell.type == SpellType.projectile){
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            }
        }

        castState.setCastDelay(currentCastDelay);
        cardPool.setRechargeTime(currentRechareTime);
        cardPool.setManaUsage(currentMana);
    }
}

/*{
	id          = "PHI",
	name 		= "$action_phi",
	description = "$actiondesc_phi",
	sprite 		= "data/ui_gfx/gun_actions/phi.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
	spawn_requires_flag = "card_unlocked_duplicate",
	type 		= ACTION_TYPE_OTHER,
	recursive	= true,
	spawn_level                       = "5,6,10", -- MANA_REDUCE
	spawn_probability                 = "0.1,0.2,1", -- MANA_REDUCE
	price = 500,
	mana = 120,
	action 		= function( recursion_level, iteration )
		c.fire_rate_wait = c.fire_rate_wait + 50

		local firerate = c.fire_rate_wait
		local reload = current_reload_time
		local mana_ = mana

		if ( discarded ~= nil ) then
			for i,data in ipairs( discarded ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( data.type == 0 ) and ( rec > -1 ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end

		if ( hand ~= nil ) then
			for i,data in ipairs( hand ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( data.type == 0 ) and ( rec > -1 ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end

		if ( deck ~= nil ) then
			for i,data in ipairs( deck ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( data.type == 0 ) and ( rec > -1 ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end

		c.fire_rate_wait = firerate
		current_reload_time = reload
		mana = mana_
	end,
}*/