package org.example.spells;

import org.example.main.*;

public class OMEGA extends Spell{
    @Override
    protected void initialization(){
        this.name = "Omega";
        this.imageFile = "omega.png";
        this.emote = "<:omega:1433949671486656673>";
        this.description = "Casts copies of every spell in your wand";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0, 0, 0.1, 0.1, 0, 0, 0, 1);
        this.recursive = true;
        this.price = 600;
        this.manaCost = 320;
        this.castDelay = 50;
    }

    // TODO
    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        Spell currentSpell;

        for(int i=0; i < cardPool.getDiscardSize(); i++){
            currentSpell = cardPool.getDiscardSpell(i);
            //if(not wand refresh){
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            //}
        }
        for(int i=0; i < cardPool.getHandSize(); i++){
            currentSpell = cardPool.getHandSpell(i);
            if(!currentSpell.recursive){ // and not wand refresh
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            }
        }
        for(int i=0; i < cardPool.getDeckSize(); i++){
            currentSpell = cardPool.getDeckSpell(i);
            //if(not wand refresh){
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            //}
        }
    }
}

/*{
	id          = "OMEGA",
	name 		= "$action_omega",
	description = "$actiondesc_omega",
	sprite 		= "data/ui_gfx/gun_actions/omega.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
	spawn_requires_flag = "card_unlocked_duplicate",
	type 		= ACTION_TYPE_OTHER,
	recursive	= true,
	spawn_level                       = "5,6,10", -- MANA_REDUCE
	spawn_probability                 = "0.1,0.1,1", -- MANA_REDUCE
	price = 600,
	mana = 320,
	action 		= function( recursion_level, iteration )
		c.fire_rate_wait = c.fire_rate_wait + 50

		if ( discarded ~= nil ) then
			for i,data in ipairs( discarded ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( rec > -1 ) and ( data.id ~= "RESET" ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end

		if ( hand ~= nil ) then
			for i,data in ipairs( hand ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( ( data.recursive == nil ) or ( data.recursive == false ) ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end

		if ( deck ~= nil ) then
			for i,data in ipairs( deck ) do
				local rec = check_recursion( data, recursion_level )
				if ( data ~= nil ) and ( rec > -1 ) and ( data.id ~= "RESET" ) then
					dont_draw_actions = true
					data.action( rec )
					dont_draw_actions = false
				end
			end
		end
	end,
}*/