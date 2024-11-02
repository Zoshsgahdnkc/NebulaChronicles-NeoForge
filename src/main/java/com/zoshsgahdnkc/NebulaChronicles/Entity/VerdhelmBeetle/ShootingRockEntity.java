package com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModEffects;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModSounds;
import com.zoshsgahdnkc.NebulaChronicles.utils.Utils;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ShootingRockEntity extends AbstractHurtingProjectile {
    public ShootingRockEntity(EntityType<? extends Entity> entityType, Level pLevel) {
        super(ModEntities.SHOOTING_ROCK.get(), pLevel);
    }

    public ShootingRockEntity(LivingEntity shooter, Level pLevel, Vec3 direction) {
        super(ModEntities.SHOOTING_ROCK.get(), shooter, direction, pLevel);
        this.setOwner(shooter);
        this.setRot(shooter.getYRot(), shooter.getXRot());
    }

    @Override
    protected float getInertia() {
        return 1f;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!level().isClientSide()) {
            Entity entity = pResult.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource;
            if (entity.isAlive() && !(entity instanceof SpikedVerdhelmBeetleEntity) && entity.canBeHitByProjectile()) {
                if (owner instanceof LivingEntity owner1) {
                    damageSource = damageSources().mobProjectile(this, owner1);
                } else {
                    damageSource = damageSources().magic();
                }
                if (entity instanceof LivingEntity target && entity.hurt(damageSource, level().getDifficulty() == Difficulty.EASY ? 3 : 6)) {
                    target.addEffect(new MobEffectInstance(ModEffects.CRIPPLE, 160, 2));
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        playSound(ModSounds.SHOOTING_ROCK_BREAK.get(), 1f, Utils.randomPitch(getRandom()));
        if (!this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }
}
