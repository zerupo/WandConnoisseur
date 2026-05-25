package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_AREA extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Projectile Area Teleport";
        this.imageFile = "homing_area.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

	<LuaComponent
		script_source_file="data/scripts/projectiles/homing_area.lua"
		execute_every_n_frame="1"
		>
	</LuaComponent>

	<SpriteComponent
		_enabled="1"
		alpha="1"
		image_file="data/particles/area_indicator_064_blue.png"
		next_rect_animation=""
		offset_x="32"
		offset_y="32"
		rect_animation="spawn"
		z_index="1.1"
		never_ragdollify_on_death="1"
		>
	</SpriteComponent>

</Entity>*/

// data/scripts/projectiles/homing_area.lua
/*dofile_once("data/scripts/lib/utilities.lua")

local entity_id    = GetUpdatedEntityID()
local root_id = EntityGetRootEntity( entity_id )
local x, y = EntityGetTransform( entity_id )
local radius = 32
local targets = EntityGetInRadiusWithTag( x, y, radius, "homing_target" )
local comp = EntityGetFirstComponent( root_id, "ProjectileComponent" )

if ( comp ~= nil ) then
	local target = ComponentGetValue2( comp, "mWhoShot" )

	for i,v in ipairs( targets ) do
		if ( v ~= target ) and ( GameGetGameEffect( v, "CHARM" ) == 0 ) and ( EntityGetHerdRelation( target, v ) < 60 ) then
			local tx, ty = EntityGetFirstHitboxCenter( v )

			EntitySetTransform( root_id, tx, ty )
			EntityApplyTransform( root_id, tx, ty )
			break
		end
	end
end*/