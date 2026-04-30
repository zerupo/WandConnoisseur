package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HITFX_CRITICAL_BLOOD extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Critical on bloody enemies";
        this.imageFile = "critical_blood.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <HitEffectComponent
        condition_effect="BLOODY"
        effect_hit="CRITICAL_HIT_BOOST"
        value="100" >
	</HitEffectComponent >

</Entity>*/