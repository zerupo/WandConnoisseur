package org.example.spells;

import org.example.main.*;

public class MONEY_MAGIC extends Spell{
    @Override
    protected void initialization(){
        this.name = "Gold to Power";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "golden_punch.png";
        //this.emote = "";
        this.description = "Spends 5% of your current gold and adds damage to a projectile proportional to the amount spent";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0, 0.2, 0, 0.8, 0.3, 0, 0, 0, 0.5);
        this.price = 200;
        this.manaCost = 30;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "MONEY_MAGIC",
		name 		= "$action_money_magic",
		description = "$actiondesc_money_magic",
		sprite 		= "data/ui_gfx/gun_actions/golden_punch.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		related_extra_entities = { "data/entities/particles/gold_sparks.xml" },
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "3,5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.2,0.8,0.3,0.5", -- MANA_REDUCE
		price = 200,
		mana = 30,
		custom_xml_file = "data/entities/misc/custom_cards/money_magic.xml",
		action 		= function()
			local entity_id = GetUpdatedEntityID()
			
			local dcomp = EntityGetFirstComponent( entity_id, "WalletComponent" )
			
			if ( dcomp ~= nil ) then
				local money = ComponentGetValue2( dcomp, "money" )
				local moneyspent = ComponentGetValue2( dcomp, "money_spent" )
				local damage = math.min( math.floor( money * 0.05 ), 24000 )
				
				if ( damage > 1 ) and ( money >= 10 ) then
					damage = math.max( damage, 10 )
					
					c.extra_entities = c.extra_entities .. "data/entities/particles/gold_sparks.xml,"
					
					money = money - damage
					moneyspent = moneyspent + damage
					ComponentSetValue2( dcomp, "money", money )
					ComponentSetValue2( dcomp, "money_spent", moneyspent )
					
					-- print( "Spent " .. tostring( damage ) )
					
					if ( damage < 120 ) then
						c.damage_projectile_add = c.damage_projectile_add + ( damage / 25 )
					elseif ( damage < 300 ) then
						c.damage_projectile_add = c.damage_projectile_add + ( damage / 35 )
					elseif ( damage < 500 ) then
						c.damage_projectile_add = c.damage_projectile_add + ( damage / 45 )
					else
						c.damage_projectile_add = c.damage_projectile_add + ( damage / 55 )
					end
				end
			end
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
