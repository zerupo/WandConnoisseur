package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_NECROMANCY extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Necromancy";
        this.imageFile = "necromancy.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <GameEffectComponent
        effect="CUSTOM"
        custom_effect_id="PHYSICS_RAGDOLL_AI"
        frames="200"
		ragdoll_effect="CUSTOM_RAGDOLL_ENTITY"
		ragdoll_effect_custom_entity_file="data/entities/misc/physics_ragdoll_ai.xml"
		ragdoll_fx_custom_entity_apply_only_to_largest_body="1"
    >
	</GameEffectComponent >

</Entity>*/