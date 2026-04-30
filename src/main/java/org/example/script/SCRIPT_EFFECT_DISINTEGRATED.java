package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_DISINTEGRATED extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "disintegrate";
        this.imageFile = "effect_disintegrated.png";
        this.emote = staticEmote;
    }
}

/*<Entity>
	<InheritTransformComponent
		_enabled="1" >
    </InheritTransformComponent>

    <GameEffectComponent
        effect="NONE"
        frames="5"
        disable_movement="0"
        ragdoll_effect="DISINTEGRATED"
        ragdoll_material="soil"
    >
	</GameEffectComponent >

</Entity>*/