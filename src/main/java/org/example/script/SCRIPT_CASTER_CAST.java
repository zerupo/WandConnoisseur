package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_CASTER_CAST extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Inner spell";
        this.imageFile = "caster_cast.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

	<LuaComponent
		script_source_file="data/scripts/projectiles/caster_cast.lua"
		execute_on_added="1"
		remove_after_executed="1"
		>
	</LuaComponent>

</Entity>*/

// data/scripts/projectiles/caster_cast.lua
/*dofile_once( "data/scripts/lib/utilities.lua" )

local entity_id    = GetUpdatedEntityID()
local pos_x, pos_y = EntityGetTransform( entity_id )
local owner_id = 0

local comp = EntityGetFirstComponent( entity_id, "ProjectileComponent" )
if ( comp ~= nil ) then
	owner_id = ComponentGetValue2( comp, "mWhoShot" )
end

if ( owner_id ~= nil ) and ( owner_id ~= NULL_ENTITY ) then
	local tx, ty = EntityGetFirstHitboxCenter( owner_id )

	EntitySetTransform( entity_id, tx, ty )
end*/