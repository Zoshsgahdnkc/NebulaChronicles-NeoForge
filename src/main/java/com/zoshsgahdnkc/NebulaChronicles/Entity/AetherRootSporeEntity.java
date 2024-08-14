package com.zoshsgahdnkc.NebulaChronicles.Entity;

import com.zoshsgahdnkc.NebulaChronicles.block.AetherRootBlock;
import com.zoshsgahdnkc.NebulaChronicles.block.AetherRootHairBlock;
import com.zoshsgahdnkc.NebulaChronicles.registries.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
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
    public boolean isChild = false;

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity * 0.35f, pInaccuracy * 4);
    }

    @Override
    public void tick() {
        if (removing) discard();
        super.tick();
        this.setDeltaMovement(getDeltaMovement().scale(0.94));
        spawnSpore();
        if (age != -1) age++;
        if (age >= LIFETIME && !removing) {
            die();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (age < 3) return;
        if (removing) return;
        BlockPos pos = pResult.getBlockPos();
        super.onHitBlock(pResult);
        if (!isChild && level().getBlockState(pos).is(ModBlocks.AETHER_ROOT.get())) {
            level().playSound(null, pos, ModSounds.AETHER_ROOT_SPORE_FISSION.get(), SoundSource.BLOCKS, 1f, 1f + 0.4f * random.nextFloat());
            double startX = (pos.getX() + 0.5) * 2 - getX();
            double startY = (pos.getY() + 0.5) * 2 - getY();
            double startZ = (pos.getZ() + 0.5) * 2 - getZ();
            double vecOffsetX = random.nextInt(-1, 3) * (0.06 + random.nextFloat() * 0.1);
            double vecOffsetY = random.nextInt(-1, 3) * (0.06 + random.nextFloat() * 0.1);
            double vecOffsetZ = random.nextInt(-1, 3) * (0.06 + random.nextFloat() * 0.1);
            Vec3 vec3 = getDeltaMovement();
            AetherRootSporeEntity Plus = new AetherRootSporeEntity(startX, startY, startZ, level());
            Plus.setDeltaMovement(vec3.add(vecOffsetX, vecOffsetY, vecOffsetZ));
            Plus.isChild = true;
            AetherRootSporeEntity Minus = new AetherRootSporeEntity(startX, startY, startZ, level());
            Minus.setDeltaMovement(vec3.add(-vecOffsetX, -vecOffsetY, -vecOffsetZ));
            Minus.isChild = true;
            level().addFreshEntity(Plus);
            level().addFreshEntity(Minus);
        } else {
            level().playSound(null, pos, SoundEvents.GRASS_FALL, SoundSource.NEUTRAL, 1f, 1f);
        }
        removing = true;
    }

    protected void spawnSpore() {
        if (age % 2 == 0) {
            level().addParticle(ModParticles.AETHER_SPORE.get(),
                    getX(), getY(), getZ(),
                    random.nextFloat() * 0.2, random.nextFloat() * 0.2 - 0.4, random.nextFloat() * 0.2);
        }
        if (age % 6 == 0) {
            level().playSound(null, getX(), getY(), getZ(),
                    ModSounds.AETHER_ROOT_SPORE_SPREAD.get(), SoundSource.NEUTRAL, 0.2f + 0.1f * random.nextFloat(), 1.5f + 0.5f * random.nextFloat());
        }
    }

    public void die() {
        if (level().isClientSide()) return;
        BlockPos pos = this.blockPosition();
        if (level().getBlockState(pos).isAir()) {
            this.level().playSound(null, pos, ModSounds.AETHER_ROOT_SPORE_INFLATE.get(), SoundSource.BLOCKS, 1f, 0.8f + 0.4f * random.nextFloat());
            this.level().setBlock(pos, ModBlocks.AETHER_ROOT.get().defaultBlockState(), 3);
            int span = 0;
            for (int tries = 0; tries < 3; tries ++) {
                if (random.nextFloat() > 0.2 + 0.3 * tries) span ++;
            }
            for (int i = 1; i < span + 2; i ++) {
                if (!level().getBlockState(pos.below(i)).isAir()) break;
                this.level().setBlock(pos.below(i), ModBlocks.AETHER_ROOT_HAIR.get().defaultBlockState(), 3);
                this.level().updateNeighborsAt(pos, ModBlocks.AETHER_ROOT_HAIR.get());
            }
            removing = true;
        }
    }

    @Override
    protected void applyGravity() {
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.AETHER_ROOT_SPORE.get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("age", IntTag.valueOf(age));
        nbt.put("child", ByteTag.valueOf(isChild));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        age = nbt.getInt("age");
        isChild = nbt.getBoolean("child");
    }
}
