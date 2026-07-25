package org.example.projectiles;

import org.example.config.EmoteConfig;
import org.example.main.Global.DamageType;
import org.example.main.ProjectileComponent;

import java.lang.invoke.MethodHandles;

public class PROJECTILE_MISSILE extends ProjectileRocketTank{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    public PROJECTILE_MISSILE(){
        this.name = "Summon missile";
        this.imageFile = "missile.png";
        this.emote = staticEmote;
        this.projectileComponent = (this.projectileComponent == null ? new ProjectileComponent() : this.projectileComponent);
        this.projectileComponent.getDamageComponent().setDamage(50.0, DamageType.PROJECTILE);
    }
}

/*<Entity name="$projectile_default" tags="projectile_player" >
	<Base file="data/entities/projectiles/rocket_tank.xml">
		<HomingComponent
			target_tag="homing_target"
			>
		</HomingComponent>

		<ProjectileComponent
			damage="2"
			>
			<config_explosion
				damage="1"
				>
			</config_explosion>
		</ProjectileComponent>

		<AudioComponent
			file="data/audio/Desktop/projectiles.bank"
			event_root="player_projectiles/rocket">
		</AudioComponent>
	</Base>
</Entity>*/