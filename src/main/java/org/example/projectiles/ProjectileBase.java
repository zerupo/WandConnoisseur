package org.example.projectiles;

import org.example.config.EmoteConfig;
import org.example.main.VelocityComponent;

import java.lang.invoke.MethodHandles;

public abstract class ProjectileBase extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public ProjectileBase(){
        this.name = "Projectile base";
        this.imageFile = "_unidentified.png";
        this.emote = staticEmote;
        this.velocityComponent = (this.velocityComponent == null ? new VelocityComponent() : this.velocityComponent)
            .setMass(0.05)
            .setAffectPhysicsBodies(true);
    }
}

/*<Entity tags="teleportable_NOT,projectile">

	<VelocityComponent
		mass="0.05"
		affect_physics_bodies="1" >
	</VelocityComponent>

	<AudioComponent
		file="data/audio/Desktop/projectiles.bank"
		audio_physics_material="projectile"
		event_root="projectiles/hit" >
	</AudioComponent>

</Entity>*/