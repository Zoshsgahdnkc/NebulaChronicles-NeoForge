package com.zoshsgahdnkc.NebulaChronicles.Entity;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
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

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity * 0.35f, pInaccuracy * 4);
    }

    @Override
    public void tick() {
        this.setDeltaMovement(getDeltaMovement().scale(0.93));
        super.tick();
        age++;
        if (age >= LIFETIME) {
            die();
        }
    }

    public void die() {
        this.level().setBlock(BlockPos.containing(position().x, position().y, position().z),
                ModBlocks.AETHER_ROOT.get().defaultBlockState(), 3);
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
