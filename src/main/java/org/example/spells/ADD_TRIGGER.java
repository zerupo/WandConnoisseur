package org.example.spells;

import org.example.main.CardPool;
import org.example.main.CastState;
import org.example.main.SpawnProbabilities;
import org.example.projectiles.Projectile;

public class ADD_TRIGGER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Add Trigger";
        this.imageFile = "trigger.png";
        this.emote = "<:add_trigger:1433949483225583817>";
        this.description = "Makes a projectile cast another spell upon collision";
        this.type = SpellType.other;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.3, 0.6, 0.6, 0, 0, 0, 0, 1);
        this.price = 100;
        this.manaCost = 10;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        int spellCount = 0;
        Spell currentSpell = null;
        Spell payloadTarget = null;
        boolean validPayload = false;

        if(cardPool.getDeckSize() > 0){
            currentSpell = cardPool.getDeckSpell(0);
        }else{
            return;
        }

        while(spellCount < cardPool.getDeckSize() && (currentSpell.getType() == SpellType.modifier || currentSpell.getType() == SpellType.passif || currentSpell.getType() == SpellType.other || currentSpell.getType() == SpellType.multicast)){
            if(!currentSpell.getHasCharges() || currentSpell.getChargesLeft() >= 0){
                if(currentSpell.getType() == SpellType.modifier){
                    cardPool.disableDraw();
                    copy(cardPool, castState, currentSpell, recursionLevel);
                    cardPool.enableDraw();
                }
            }
            spellCount++;
            currentSpell = cardPool.getDeckSpell(spellCount);
        }

        if(currentSpell != null && currentSpell.getRelatedProjectile() != null && (!currentSpell.getHasCharges() || currentSpell.getChargesLeft() > 0)){
            cardPool.discardNext(spellCount + 1);
            for(int i=0; i < cardPool.getDeckSize(); i++){
                payloadTarget = cardPool.getDeckSpell(i);

                if(payloadTarget != null && (payloadTarget.getType() == SpellType.projectile || payloadTarget.getType() == SpellType.static_projectile || payloadTarget.getType() == SpellType.material || payloadTarget.getType() == SpellType.utility)){
                    validPayload = true;
                    break;
                }
            }

            if(!currentSpell.getHasCharges() || currentSpell.getChargesLeft() >= 0){
                currentSpell.removeCharge();
                // does not remove a charge if already removed this cast
            }

            if(validPayload){
                for(int i=0; i < currentSpell.getRelatedProjectileCount(); i++){
                    Projectile newProjectile = currentSpell.getRelatedProjectile().clone();
                    CastState newCastState = new CastState();

                    newProjectile.addTrigger(Projectile. TriggerType.trigger, newCastState);
                    castState.addProjectile(newProjectile);
                    cardPool.draw(1, true, newCastState);
                }
            }else{
                cardPool.disableDraw();
                copy(cardPool, castState, currentSpell, recursionLevel);
                cardPool.enableDraw();
            }
        }
    }
}

/*{
	id          = "ADD_TRIGGER",
	name 		= "$action_add_trigger",
	description = "$actiondesc_add_trigger",
	sprite 		= "data/ui_gfx/gun_actions/trigger.png",
	sprite_unidentified = "data/ui_gfx/gun_actions/damage_unidentified.png",
	spawn_requires_flag = "card_unlocked_mestari",
	type 		= ACTION_TYPE_OTHER,
	spawn_level                       = "3,4,5,10", -- CRITICAL_HIT
	spawn_probability                 = "0.3,0.6,0.6,1", -- CRITICAL_HIT
	price = 100,
	mana = 10,
	--max_uses = 50,
	action 		= function()
		local data = {}

		local how_many = 1

		if ( #deck > 0 ) then
			data = deck[1]
		else
			data = nil
		end

		if ( data ~= nil ) then
			while ( #deck >= how_many ) and ( ( data.type == ACTION_TYPE_MODIFIER ) or ( data.type == ACTION_TYPE_PASSIVE ) or ( data.type == ACTION_TYPE_OTHER ) or ( data.type == ACTION_TYPE_DRAW_MANY ) ) do
				if ( ( data.uses_remaining == nil ) or ( data.uses_remaining ~= 0 ) ) and ( data.id ~= "ADD_TRIGGER" ) and ( data.id ~= "ADD_TIMER" ) and ( data.id ~= "ADD_DEATH_TRIGGER" ) then
					if ( data.type == ACTION_TYPE_MODIFIER ) then
						dont_draw_actions = true
						data.action()
						dont_draw_actions = false
					end
				end
				how_many = how_many + 1
				data = deck[how_many]
			end

			if ( data ~= nil ) and ( data.related_projectiles ~= nil ) and ( ( data.uses_remaining == nil ) or ( data.uses_remaining ~= 0 ) ) then
				local target = data.related_projectiles[1]
				local count = data.related_projectiles[2] or 1

				for i=1,how_many do
					data = deck[1]
					table.insert( discarded, data )
					table.remove( deck, 1 )
				end

				local valid = false

				for i=1,#deck do
					local check = deck[i]

					if ( check ~= nil ) and ( ( check.type == ACTION_TYPE_PROJECTILE ) or ( check.type == ACTION_TYPE_STATIC_PROJECTILE ) or ( check.type == ACTION_TYPE_MATERIAL ) or ( check.type == ACTION_TYPE_UTILITY ) ) then
						valid = true
						break
					end
				end

				if ( data.uses_remaining ~= nil ) and ( data.uses_remaining > 0 ) then
					data.uses_remaining = data.uses_remaining - 1

					local reduce_uses = ActionUsesRemainingChanged( data.inventoryitem_id, data.uses_remaining )
					if not reduce_uses then
						data.uses_remaining = data.uses_remaining + 1 -- cancel the reduction
					end
				end

				if valid then
					for i=1,count do
						add_projectile_trigger_hit_world(target, 1)
					end
				else
					dont_draw_actions = true
					data.action()
					dont_draw_actions = false
				end
			end
		end
	end,
}*/