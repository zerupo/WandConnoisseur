package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_TOXIC_TO_ACID extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Toxic sludge to acid";
        this.imageFile = "toxic_to_acid.png";
        this.emote = staticEmote;
    }
}

/*<Entity >
	<MagicConvertMaterialComponent
      kill_when_finished="0"
      from_material="radioactive_liquid"
      steps_per_frame="48"
      to_material="acid"
      clean_stains="0"
      is_circle="1"
      radius="48"
	  loop="1"
	  >
    </MagicConvertMaterialComponent>

	<MagicConvertMaterialComponent
      kill_when_finished="0"
      from_material="radioactive_liquid_yellow"
      steps_per_frame="48"
      to_material="acid"
      clean_stains="0"
      is_circle="1"
      radius="48"
	  loop="1"
	  >
    </MagicConvertMaterialComponent>

	<MagicConvertMaterialComponent
      kill_when_finished="0"
      from_material="radioactive_liquid_fading"
      steps_per_frame="48"
      to_material="acid"
      clean_stains="0"
      is_circle="1"
      radius="48"
	  loop="1"
	  >
    </MagicConvertMaterialComponent>

	<MagicConvertMaterialComponent
      kill_when_finished="0"
      from_material="cloud_radioactive"
      steps_per_frame="48"
      to_material="acid"
      clean_stains="0"
      is_circle="1"
      radius="48"
	  loop="1"
	  >
    </MagicConvertMaterialComponent>
</Entity>*/