package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_CLUSTERMOD extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Clusterbolt";
        this.imageFile = "clusterbomb.png";
        this.emote = staticEmote;
    }
}

/*<Entity >

	<LuaComponent
		script_source_file="data/scripts/projectiles/clusterbomb_new.lua"
		execute_every_n_frame="99999"
		execute_on_removed="1"
		>
	</LuaComponent>

</Entity>*/