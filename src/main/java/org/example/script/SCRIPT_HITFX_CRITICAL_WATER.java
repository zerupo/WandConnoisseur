package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HITFX_CRITICAL_WATER extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Critical on wet (water) enemies";
        this.imageFile = "critical_water.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <HitEffectComponent
        condition_effect="WET"
        effect_hit="CRITICAL_HIT_BOOST"
        value="100" >
	</HitEffectComponent >

</Entity>*/