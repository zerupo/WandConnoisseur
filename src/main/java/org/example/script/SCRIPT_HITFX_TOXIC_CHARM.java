package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_HITFX_TOXIC_CHARM extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Charm on toxic sludge";
        this.imageFile = "charm_on_toxic.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <HitEffectComponent
        condition_status="RADIOACTIVE"
        effect_hit="LOAD_UNIQUE_GAME_EFFECT"
        value_string="data/entities/misc/effect_charm_short.xml" >
	</HitEffectComponent >

</Entity>*/

// data/entities/misc/effect_charm_short.xml
/*<Entity>
	<Base file="data/entities/particles/charm.xml" >
		<SpriteParticleEmitterComponent
		    emission_interval_min_frames="5"
    		emission_interval_max_frames="15"
			randomize_position_inside_hitbox="1"  >
		</SpriteParticleEmitterComponent>
	</Base>

	<InheritTransformComponent
		_enabled="1" >
    </InheritTransformComponent>

    <GameEffectComponent
        effect="CHARM"
        frames="3600"
    >
	</GameEffectComponent >

	<AudioComponent
		file="data/audio/Desktop/misc.bank"
		event_root="game_effect/charm" >
	</AudioComponent>
</Entity>*/