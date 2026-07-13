package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_ARC_FIRE extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Fire Arc";
        this.imageFile = "arc_fire.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<ArcComponent
		lifetime="120"
		material="fire" >
	</ArcComponent>
</Entity>*/