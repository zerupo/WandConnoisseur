package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_APPLY_WET extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Wet";
        this.imageFile = "wet.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

	<InheritTransformComponent
		_enabled="1" >
    </InheritTransformComponent>

    <GameEffectComponent
        effect="WET"
        frames="720"
    >
	</GameEffectComponent >

</Entity>*/