package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_STATIC_TO_SAND extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Ground to sand";
        this.imageFile = "static_to_sand.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<MagicConvertMaterialComponent
      kill_when_finished="0"
      from_material_tag="[solid]"
      steps_per_frame="32"
      to_material="sand"
      clean_stains="0"
      is_circle="1"
      radius="32"
	  loop="1"
	  >
    </MagicConvertMaterialComponent>
</Entity>*/