package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_SINEWAVE extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Slithering path";
        this.imageFile = "sinewave.png";
        this.emote = staticEmote;
    }
}

/*<Entity >


  <SineWaveComponent
    _enabled="1"
    sinewave_freq="1.0"
    sinewave_m="0.6"
    lifetime="-1"
	 >
  </SineWaveComponent>

</Entity>*/