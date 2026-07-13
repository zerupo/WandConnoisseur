package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_APPLY_FIRE extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Effect apply on fire";
        this.imageFile = "effect_apply_fire.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

	<InheritTransformComponent
		_enabled="1" >
    </InheritTransformComponent>

    <GameEffectComponent
        effect="ON_FIRE"
        frames="720"
    >
	</GameEffectComponent >

</Entity>*/