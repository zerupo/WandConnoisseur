package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_SHORT extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Short-range Homing";
        this.imageFile = "homing_short.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<HomingComponent
		homing_targeting_coeff="480.0"
		homing_velocity_multiplier="0.83"
		detect_distance="60"
	>
	</HomingComponent>
</Entity>*/