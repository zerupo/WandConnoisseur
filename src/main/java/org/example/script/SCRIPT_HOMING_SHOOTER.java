package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_SHOOTER extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Boomerang";
        this.imageFile = "homing_shooter.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<HomingComponent
		target_who_shot="1"
		homing_targeting_coeff="30.0"
		homing_velocity_multiplier="0.99"
		detect_distance="300"
	>
	</HomingComponent>
</Entity>*/