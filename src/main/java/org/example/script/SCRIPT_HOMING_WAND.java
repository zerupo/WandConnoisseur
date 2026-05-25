package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_WAND extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Wand Homing";
        this.imageFile = "homing_wand.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<HomingComponent
		homing_targeting_coeff="64.0"
		homing_velocity_multiplier="0.76"
		target_tag="wand"
		detect_distance="1024"
		look_for_root_entities_only="1"
	>
	</HomingComponent>
</Entity>*/