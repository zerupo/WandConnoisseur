package org.example.spells;

import org.example.config.EmoteConfig;
import org.example.main.*;

import java.lang.invoke.MethodHandles;
import java.util.Random;

public class RANDOM_SPELL extends Spell{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Random spell";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "random_spell.png";
        this.emote = staticEmote;
        this.description = "Casts a spell, any spell, at random!";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.2, 0.3, 0.2, 0.1, 0, 0, 0, 0.5);
        this.recursive = true;
        this.price = 100;
        this.manaCost = 5;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        int frame = Global.longToInt(Global.getCurrentFrame());
        Spell[] spells = recursionLevel < recursionLimit ? Global.getSpellList().getSpells(false) : Global.getSpellListNonRecursive().getSpells(false);

        if(spells.length == 0){
            return;
        }

        copy(cardPool, castState, spells[new Random(Global.intToLong(frame + cardPool.getDeckSize(), frame + 263)).nextInt(0, spells.length)], recursionLevel);
    }
}

/*{
	id          = "RANDOM_SPELL",
	name 		= "$action_random_spell",
	description = "$actiondesc_random_spell",
	sprite 		= "data/ui_gfx/gun_actions/random_spell.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
	spawn_requires_flag = "card_unlocked_pyramid",
	type 		= ACTION_TYPE_OTHER,
	recursive	= true,
	spawn_level                       = "3,4,5,6,10", -- MANA_REDUCE
	spawn_probability                 = "0.2,0.3,0.2,0.1,0.5", -- MANA_REDUCE
	price = 100,
	mana = 5,
	action 		= function( recursion_level, iteration )
		SetRandomSeed( GameGetFrameNum() + #deck, GameGetFrameNum() + 263 )
		local rnd = Random( 1, #actions )
		local data = actions[rnd]

		local safety = 0
		local rec = check_recursion( data, recursion_level )
		local flag = data.spawn_requires_flag
		local usable = true
		if ( flag ~= nil ) and ( HasFlagPersistent( flag ) == false ) then
			usable = false
		end

		while ( safety < 100 ) and ( ( rec == -1 ) or ( usable == false ) ) do
			rnd = Random( 1, #actions )
			data = actions[rnd]
			rec = check_recursion( data, recursion_level )
			flag = data.spawn_requires_flag
			usable = true
			if ( flag ~= nil ) and ( HasFlagPersistent( flag ) == false ) then
				usable = false
			end

			safety = safety + 1
		end

		data.action( rec )
	end,
}*/