package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_ARC_ELECTRIC extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Electric Arc";
        this.imageFile = "arc_electric.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<LightningComponent>
	</LightningComponent>

	<ArcComponent
		type="LIGHTNING"
		lifetime="120"
		material="" >
	</ArcComponent>
</Entity>*/