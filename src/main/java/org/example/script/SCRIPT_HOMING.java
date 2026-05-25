package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Homing";
        this.imageFile = "homing.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<HomingComponent
		homing_targeting_coeff="130.0"
		homing_velocity_multiplier="0.86"
	>
	</HomingComponent>
</Entity>*/