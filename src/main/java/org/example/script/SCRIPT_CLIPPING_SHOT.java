package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_CLIPPING_SHOT extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Drilling shot";
        this.imageFile = "clipping_shot.png";
        this.emote = staticEmote;
    }
}

/*<Entity >

	<LuaComponent
		script_source_file="data/scripts/projectiles/clipping_shot.lua"
		execute_every_n_frame="1"
		remove_after_executed="1"
		>
	</LuaComponent>

</Entity>*/

// data/scripts/projectiles/clipping_shot.lua
/*dofile_once("data/scripts/lib/utilities.lua")

local entity_id    = GetUpdatedEntityID()
local x, y = EntityGetTransform( entity_id )

local parent_id = EntityGetParent( entity_id )

local target_id = 0

if ( parent_id ~= NULL_ENTITY ) then
	target_id = parent_id
else
	target_id = entity_id
end

if ( target_id ~= NULL_ENTITY ) then
	local projectile_components = EntityGetComponent( target_id, "ProjectileComponent" )
	local velocity_components = EntityGetComponent( target_id, "VelocityComponent" )

	if( projectile_components == nil ) then return end

	if ( #projectile_components > 0 ) then
		edit_component( target_id, "ProjectileComponent", function(comp,vars)
			vars.penetrate_world = 1
			vars.penetrate_world_velocity_coeff = 0.1

			local friction = ComponentGetValue( comp, "friction" )
			friction = math.max( 0, friction )
			vars.friction = friction
		end)
	end

	if( velocity_components == nil ) then return end

	if ( #velocity_components > 0 ) then
		edit_component( target_id, "VelocityComponent", function(comp,vars)
			local friction = ComponentGetValue( comp, "air_friction" )
			friction = math.max( 0, friction )
			vars.air_friction = friction
		end)
	end
end*/