package org.example.projectiles;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class PROJECTILE_TOUCH_GOLD extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public PROJECTILE_TOUCH_GOLD(){
        this.name = "Touch of Gold";
        this.imageFile = "touch_gold.png";
        this.emote = staticEmote;
    }
}

/*<Entity tags="projectile_player" >
	<InheritTransformComponent>
    </InheritTransformComponent>

	<MagicConvertMaterialComponent
		from_any_material="1"
        to_material="gold"
        steps_per_frame="2"
        loop="0"
		is_circle="1"
        radius="10" >
    </MagicConvertMaterialComponent>

	<MagicConvertMaterialComponent
		from_any_material="1"
		convert_entities="1"
        to_material="gold"
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