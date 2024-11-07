package com.zoshsgahdnkc.NebulaChronicles.mixin;

import com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils.*;

@Mixin(net.minecraft.world.entity.Entity.class)
public abstract class MixinEntity {

    // Activate Planet Gravity For Items, Projectiles and Vehicles
    @ModifyVariable(method = "applyGravity", at = @At("STORE"), ordinal = 0)
    public double modifyGravity(double gravity) {
        Entity entity = (Entity) (Object) this;
        Planet planet = PlanetUtils.getPlanet(entity);
        if (planet == null) return gravity;
        Vec3 speed = entity.getDeltaMovement();
        double gravityModifier = (gravity * (1 - getGravityRatio(planet)) * getGravityDecreaseFactor(planet, speed.y));
        return gravity - gravityModifier;
    }

}
