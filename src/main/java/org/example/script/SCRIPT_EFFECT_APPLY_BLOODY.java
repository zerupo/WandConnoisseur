package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_APPLY_BLOODY extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Bloody";
        this.imageFile = "bloody.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

	<InheritTransformComponent
		_enabled="1" >
    </InheritTransformComponent>

    <GameEffectComponent
        effect="BLOODY"
        frames="720"
    >
	</GameEffectComponent >

</Entity>*/