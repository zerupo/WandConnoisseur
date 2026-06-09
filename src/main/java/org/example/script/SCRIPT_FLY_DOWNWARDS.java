package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_FLY_DOWNWARDS extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Fly downwards";
        this.imageFile = "fly_downwards.png";
        this.emote = staticEmote;
    }
}

/*<Entity >

	<LuaComponent
		script_source_file="data/scripts/projectiles/fly_downwards.lua"
		execute_every_n_frame="20"
		remove_after_executed="1"
		>
	</LuaComponent>

</Entity>*/

// data/scripts/projectiles/fly_downwards.lua
/*dofile_once("data/scripts/lib/utilities.lua")

local entity_id    = GetUpdatedEntityID()
local pos_x, pos_y = EntityGetTransform( entity_id )

edit_component( entity_id, "VelocityComponent", function(comp,vars)
	local vel_x,vel_y = ComponentGetValueVector2( comp, "mVelocity")

	local velocity = math.sqrt( ( vel_y ) ^ 2 + ( vel_x ) ^ 2 )

	vel_x = 0
	vel_y = velocity * 2

	ComponentSetValueVector2( comp, "mVelocity", vel_x, vel_y)
end)*/