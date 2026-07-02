package org.example.projectiles;

import org.example.config.EmoteConfig;
import org.example.main.ProjectileComponent;

import java.lang.invoke.MethodHandles;

public abstract class ProjectileBaseItem extends Projectile{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public ProjectileBaseItem(){
        this.name = "Base item";
        this.imageFile = "_unidentified.png";
        this.emote = staticEmote;
        this.projectileComponent = (this.projectileComponent == null ? new ProjectileComponent() : this.projectileComponent)
            .setLifetime(-1)
            // penetrate_entities="1"
            .setDamageScaledBySpeed(true)
            .setDamageScaleMaxSpeed(200.0);
            // never_hit_player="1"
        this.projectileComponent.getDamageComponent().setProjectile(0.0);
        this.projectileComponent.getDamageComponent().setMelee(12.5);
    }
}

/*<!-- used for throwable items that cannot collide with enemies but which we want to damage enemies with -->
<Entity>
	<ProjectileComponent
		_tags="enabled_in_world"
		lifetime="-1"
		damage="0"
		penetrate_entities="1"
		damage_scaled_by_speed="1"
		damage_scale_max_speed="200"
		never_hit_player="1"
		>
		<damage_by_type
			melee="0.5"
			>
		</damage_by_type>
	</ProjectileComponent>
</Entity>*/