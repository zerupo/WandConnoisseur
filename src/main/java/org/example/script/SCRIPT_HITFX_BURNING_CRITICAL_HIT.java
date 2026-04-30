package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HITFX_BURNING_CRITICAL_HIT extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Critical on burning";
        this.imageFile = "burning_critical.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <HitEffectComponent
        condition_effect="ON_FIRE"
        effect_hit="CRITICAL_HIT_BOOST"
        value="100" >
	</HitEffectComponent >

</Entity>*/