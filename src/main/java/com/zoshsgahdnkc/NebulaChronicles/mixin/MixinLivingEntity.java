package com.zoshsgahdnkc.NebulaChronicles.mixin;

import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModDimensions;
import com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils.*;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "tick", at = @At("HEAD"))
    public void nchTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        PlanetUtils.applyOrRemoveAttributes(entity);
    }

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

    @ModifyVariable(method = "calculateFallDamage", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    public float nchCalculateFallDamageModifyModifier(float pDamageMultiplier) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Planet planet = PlanetUtils.getPlanet(entity);
        if (planet != null) {
            pDamageMultiplier *= getGravityRatio(planet) * 1.5f;
        }
        return pDamageMultiplier;
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
