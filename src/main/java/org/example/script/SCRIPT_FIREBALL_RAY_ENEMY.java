package org.example.script;

import org.example.config.EmoteConfig;

import java.lang.invoke.MethodHandles;

public class SCRIPT_FIREBALL_RAY_ENEMY extends Script{
    static String staticEmote = EmoteConfig.getEmote(MethodHandles.lookup().lookupClass().getSimpleName().toLowerCase());

    @Override
    protected void initialization(){
        this.name = "Personal fireball thrower";
        this.imageFile = "fireball_ray_enemy.png";
        this.emote = staticEmote;
    }
}

/*<Entity>

    <HitEffectComponent
        effect_hit="LOAD_UNIQUE_CHILD_ENTITY"
        value_string="data/entities/misc/fireball_ray_enemy.xml" >
	</HitEffectComponent >

</Entity>*/

// data/entities/misc/fireball_ray_enemy.xml
/*<Entity>
	<InheritTransformComponent />

	<LuaComponent
		_enabled="1"
		script_source_file="data/scripts/projectiles/fireball_ray_enemy.lua"
		execute_every_n_frame="10">
   </LuaComponent>

	<LifetimeComponent
		lifetime="5000"
		>
	</LifetimeComponent>
</Entity>*/

// data/scripts/projectiles/fireball_ray_enemy.lua
/*dofile_once("data/scripts/lib/utilities.lua")

local entity_id    = GetUpdatedEntityID()
entity_id = EntityGetRootEntity( entity_id )

local pos_x, pos_y = EntityGetTransform( entity_id )

SetRandomSeed( GameGetFrameNum() + GetUpdatedComponentID(), pos_x + pos_y + entity_id )

local angle = math.rad(Random(0,359))
local length = 3000

local vel_x = math.cos( angle ) * length
local vel_y = 0 - math.sin( angle ) * length

shoot_projectile( entity_id, "data/entities/projectiles/deck/fireball_ray.xml", pos_x, pos_y, vel_x, vel_y )*/