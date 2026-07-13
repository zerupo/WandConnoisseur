package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_ARC_POISON extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Poison Arc";
        this.imageFile = "arc_poison.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<ArcComponent
		lifetime="120"
		material="poison" >
	</ArcComponent>
</Entity>*/