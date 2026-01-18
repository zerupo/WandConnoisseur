package org.example.spells;

import org.example.main.*;

public class BLOOD_TO_POWER extends Spell{
    @Override
    protected void initialization(){
        this.name = "Blood to Power";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "blood_punch.png";
        //this.emote = "";
        this.description = "A projectile gains additional damage at the cost of 20% of your health";
        this.type = SpellType.utility;
        this.spawnProbabilities = new SpawnProbabilities(0, 0, 0.2, 0, 0, 0.8, 0.2, 0, 0, 0, 0.5);
        this.price = 150;
        this.manaCost = 20;
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
	{
		id          = "BLOOD_TO_POWER",
		name 		= "$action_blood_to_power",
		description = "$actiondesc_blood_to_power",
		sprite 		= "data/ui_gfx/gun_actions/blood_punch.png",
		sprite_unidentified = "data/ui_gfx/gun_actions/spread_reduce_unidentified.png",
		related_extra_entities = { "data/entities/particles/blood_sparks.xml" },
		type 		= ACTION_TYPE_UTILITY,
		spawn_level                       = "2,5,6,10", -- MANA_REDUCE
		spawn_probability                 = "0.2,0.8,0.2,0.5", -- MANA_REDUCE
		price = 150,
		mana = 20,
		custom_xml_file = "data/entities/misc/custom_cards/blood_to_power.xml",
		action 		= function()
			local entity_id = GetUpdatedEntityID()
			
			local dcomp = EntityGetFirstComponent( entity_id, "DamageModelComponent" )
			
			if ( dcomp ~= nil ) then
				local hp = ComponentGetValue2( dcomp, "hp" )
				local damage = math.min( hp * 0.44, 960 )
				local self_damage = hp * 0.2
				
				if ( hp >= 0.4 ) and ( self_damage > 0.2 ) then
					c.extra_entities = c.extra_entities .. "data/entities/particles/blood_sparks.xml,"
					
					EntityInflictDamage( entity_id, self_damage, "DAMAGE_CURSE", "$action_blood_to_power", "NONE", 0, 0, entity_id )
					
					-- print( "Spent " .. tostring( damage ) )
					
					c.damage_projectile_add = c.damage_projectile_add + damage
				end
			end
			
			draw_actions( 1, true )
		end,
	},
	},
*/
}
