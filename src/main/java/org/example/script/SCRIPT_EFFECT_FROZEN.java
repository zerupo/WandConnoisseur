package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_EFFECT_FROZEN extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Effect frozen";
        this.imageFile = "frozen.png";
        this.emote = staticEmote;
    }
}

/*<Entity>
    <InheritTransformComponent>
    </InheritTransformComponent>

    <GameEffectComponent
        effect="FROZEN"
        frames="120"
        disable_movement="1"
        ragdoll_effect="FROZEN"
        ragdoll_material="ice_glass_b2"
    >
	</GameEffectComponent >

    <AudioComponent
        file="data/audio/Desktop/misc.bank"
        event_root="game_effect/frozen" >
    </AudioComponent>

</Entity>*/