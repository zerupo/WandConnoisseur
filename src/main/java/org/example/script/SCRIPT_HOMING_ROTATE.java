package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HOMING_ROTATE extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Rotate towards foes";
        this.imageFile = "homing_rotate.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<HomingComponent
		just_rotate_towards_target="1"
		max_turn_rate="0.2"
	>
	</HomingComponent>
</Entity>*/