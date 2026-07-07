package org.example.projectiles;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public abstract class ProjectileBaseItemPhysics2 extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public ProjectileBaseItemPhysics2(){
        this.name = "Base Item Physics 2";
        this.imageFile = "_unidentified.png";
        this.emote = staticEmote;
    }
}

/*<Entity tags="hittable,teleportable_NOT,prop,prop_physics">
  <PhysicsBody2Component
  	allow_sleep="1"
  	angular_damping="0"
  	linear_damping="0"
    kill_entity_after_initialized="1"
	>
  </PhysicsBody2Component>

  <PhysicsImageShapeComponent
  	is_root="1"
    centered="1"
    image_file="data/temp/landmine.png"
    material="steel" >
  </PhysicsImageShapeComponent>

</Entity>*/