package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_CRUMBLING_EARTH_PROJECTILE extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Earthquake shot";
        this.imageFile = "crumbling_earth_projectile.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<LuaComponent
		script_source_file="data/scripts/projectiles/crumbling_earth_projectile.lua"
		execute_every_n_frame="99999999"
		execute_on_removed="1"
		>
	</LuaComponent>
</Entity>*/

// data/scripts/projectiles/crumbling_earth_projectile.lua
/*x, y = EntityGetTransform( GetUpdatedEntityID() )
EntityLoad( "data/entities/misc/loose_chunks_projectile.xml", x, y )*/