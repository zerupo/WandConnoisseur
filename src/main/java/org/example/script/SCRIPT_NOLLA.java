package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_NOLLA extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Nolla";
        this.imageFile = "nolla.png";
        this.emote = staticEmote;
    }
}

/*<Entity >

	<LuaComponent
		script_source_file="data/scripts/projectiles/nolla.lua"
		execute_every_n_frame="1"
		remove_after_executed="1"
		>
	</LuaComponent>

</Entity>*/

// data/scripts/projectiles/nolla.lua
/*dofile_once("data/scripts/lib/utilities.lua")

local entity_id = GetUpdatedEntityID()
local x, y = EntityGetTransform( entity_id )

entity_id = EntityGetRootEntity( entity_id )

if ( entity_id ~= NULL_ENTITY ) then
	edit_component( entity_id, "ProjectileComponent", function(comp,vars)
		ComponentSetValue( comp, "lifetime", 1 )
	end)

	edit_component( entity_id, "LifetimeComponent", function(comp,vars)
		ComponentSetValue( comp, "lifetime", 1 )
	end)
end*/