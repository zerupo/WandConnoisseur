package org.example.projectiles;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class PROJECTILE_TOUCH_OIL extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public PROJECTILE_TOUCH_OIL(){
        this.name = "Touch of Oil";
        this.imageFile = "touch_oil.png";
        this.emote = staticEmote;
    }
}

/*<Entity tags="projectile_player" >
	<InheritTransformComponent>
    </InheritTransformComponent>

	<MagicConvertMaterialComponent
		from_any_material="1"
        to_material="oil"
        steps_per_frame="4"
        loop="0"
		is_circle="1"
        radius="20" >
    </MagicConvertMaterialComponent>

	<MagicConvertMaterialComponent
		from_any_material="1"
		convert_entities="1"
        to_material="oil"
        steps_per_frame="7"
        loop="0"
		is_circle="1"
        radius="30" >
    </MagicConvertMaterialComponent>

	<LifetimeComponent
		lifetime="4"
	>
	</LifetimeComponent>

	<AudioComponent
		file="data/audio/Desktop/projectiles.bank"
		event_root="player_projectiles/touch">
	</AudioComponent>

</Entity>*/