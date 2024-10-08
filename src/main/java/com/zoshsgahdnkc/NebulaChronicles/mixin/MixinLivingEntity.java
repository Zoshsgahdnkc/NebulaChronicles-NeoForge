package com.zoshsgahdnkc.NebulaChronicles.mixin;

import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModDimensions;
import com.zoshsgahdnkc.NebulaChronicles.planet.Planet;
import com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils.*;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow private float speed;

    @Shadow public abstract void push(Entity p_21294_);

    // Modify the gravity
    @Inject(method = "travel", at = @At("TAIL"))
    public void nchTravel(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Planet planet = PlanetUtils.getPlanet(entity);
        if (planet == null) return;
        Vec3 speed = entity.getDeltaMovement();
        float gravityModifier = (float) (BASE_GRAVITY * (1 - getGravityRatio(planet)) * getGravityDecreaseFactor(planet, speed.y));
        if (gravityModifier > 0 && !entity.isInFluidType() && !entity.isNoGravity() && !entity.isFallFlying() && !entity.hasEffect(MobEffects.SLOW_FALLING)) {
            entity.setDeltaMovement(speed.add(0, gravityModifier, 0));
        }
    }

    @ModifyArg(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;calculateFallDamage(FF)I"), index = 1)
    public float nchCalculateFallDamageModifyModifier(float modifier) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Planet planet = PlanetUtils.getPlanet(entity);
        if (planet != null) {
            modifier *= getGravityRatio(planet) * 1.5f;
        }
        return modifier;
    }

    @ModifyArg(method = "causeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;calculateFallDamage(FF)I"), index = 0)
    public float nchCalculateFallDamageModifyDistance(float distance) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Planet planet = PlanetUtils.getPlanet(entity);
        if (planet != null) {
            float safeDistance = 3F / Mth.sqrt(getGravityRatio(planet));
            distance -= safeDistance - 3;
        }
        return distance;
    }
    @Inject(method = "maxUpStep", at = @At("HEAD"), cancellable = true)
    public void nchMaxUpStep(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        ResourceKey<Level> dimension = entity.level().dimension();
        if (dimension == ModDimensions.SB_LEVEL) {
            float f = (float) entity.getAttributeValue(Attributes.STEP_HEIGHT);
            cir.setReturnValue(entity.getControllingPassenger() instanceof Player ? Math.max(f, 2f) : f * 2f);
        }
    }

}
