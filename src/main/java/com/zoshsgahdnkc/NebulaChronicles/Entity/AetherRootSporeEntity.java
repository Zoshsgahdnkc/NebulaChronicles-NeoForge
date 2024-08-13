package com.zoshsgahdnkc.NebulaChronicles.Entity;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AetherRootSporeEntity extends ThrowableItemProjectile {
    public AetherRootSporeEntity(LivingEntity pShooter, Level pLevel) {
        super(ModEntities.AETHER_ROOT_SPORE.get(), pShooter, pLevel);
    }
    public AetherRootSporeEntity(EntityType<AetherRootSporeEntity> aetherRootSporeEntityEntityType, Level level) {
        super(aetherRootSporeEntityEntityType, level);
    }
    public AetherRootSporeEntity(double pX, double pY, double pZ, Level pLevel) {
        super(ModEntities.AETHER_ROOT_SPORE.get(), pX, pY, pZ, pLevel);
    }
    public static final int LIFETIME = 30;
    public int age = 0;
    public boolean removing = false;

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity * 0.35f, pInaccuracy * 4);
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(getDeltaMovement().scale(0.94));
        spawnSpore();
        age++;
        if (age >= LIFETIME) {
            die();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        BlockPos pos = new BlockPos((int) position().x, (int) position().y, (int) position().z);
        super.onHitBlock(pResult);
        level().playSound(null, pos, SoundEvents.GRASS_FALL, SoundSource.NEUTRAL, 0.5f, 1f);
        removing = true;
    }

    protected void spawnSpore() {
        if (age % 2 == 0) {
            level().addParticle(ModParticles.AETHER_SPORE.get(),
                    getX(), getY(), getZ(),
                    random.nextFloat() * 0.2, random.nextFloat() * 0.2 - 0.3, random.nextFloat() * 0.2);
        }
    }

    public void die() {
        BlockPos pos = new BlockPos((int) position().x, (int) position().y, (int) position().z);
        if (level().getBlockState(pos).isAir() && !removing) {
            this.level().setBlock(pos, ModBlocks.AETHER_ROOT.get().defaultBlockState(), 3);
            removing = true;
        }
        this.discard();
    }

    @Override
    protected void applyGravity() {
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.AETHER_ROOT_SPORE.get();
    }
}
