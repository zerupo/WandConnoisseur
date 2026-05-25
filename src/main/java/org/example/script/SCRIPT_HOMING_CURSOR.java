package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_CURSOR extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Aiming Arc";
        this.imageFile = "homing_cursor.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<LuaComponent
		script_source_file="data/scripts/projectiles/homing_cursor.lua"
		execute_every_n_frame="1"
		>
	</LuaComponent>
</Entity>*/