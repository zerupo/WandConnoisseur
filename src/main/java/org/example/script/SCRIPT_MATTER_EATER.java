package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_MATTER_EATER extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Matter eater";
        this.imageFile = "matter_eater.png";
        this.emote = staticEmote;
    }
}

/*<Entity>
	<InheritTransformComponent>
    </InheritTransformComponent>

    <CellEaterComponent
		eat_probability="90"
        radius="8"
		ignored_material="rock_static_cursed"
		ignored_material_tag="[matter_eater_ignore_list]"
		>
    </CellEaterComponent>

	<LifetimeComponent
		lifetime="200"
		randomize_lifetime.min="-50"
		randomize_lifetime.max="50"
		>
	</LifetimeComponent>
</Entity>*/