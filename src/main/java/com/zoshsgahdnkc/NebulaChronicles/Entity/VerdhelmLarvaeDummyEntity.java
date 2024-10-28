package com.zoshsgahdnkc.NebulaChronicles.Entity;

import com.zoshsgahdnkc.NebulaChronicles.datagen.tags.ModBlockTags;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

//TODO: Try making larvae remember the list of dug blocks, to mimic its pathing.
public class VerdhelmLarvaeDummyEntity extends Entity {
    public VerdhelmLarvaeDummyEntity(double pX, double pY, double pZ, Level level, Direction facing) {
        this(ModEntities.VERDHELM_LARVAE_DUMMY.get(), level);
        setPos(pX, pY, pZ);
        setProgress(0);
        setFacing(facing);
    }
    public VerdhelmLarvaeDummyEntity(EntityType<VerdhelmLarvaeDummyEntity> entityEntityType, Level level) {
        super(entityEntityType, level);
    }
    private static final int LIFETIME = 80;
    private static final int TRIES = 32;
    private static final int NEURON_COUNT = 2;
    private static final float STRENGTH = 0.25f;
    private static final float OFFSET = 0.2f;
    private BlockPos.MutableBlockPos[] neurons = new BlockPos.MutableBlockPos[NEURON_COUNT];
    private static final EntityDataAccessor<Integer> DATA_PROGRESS = SynchedEntityData.defineId(VerdhelmLarvaeDummyEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Direction> DATA_FACING = SynchedEntityData.defineId(VerdhelmLarvaeDummyEntity.class, EntityDataSerializers.DIRECTION);

    public int getProgress() {
        return entityData.get(DATA_PROGRESS);
    }
    public Direction getFacing(){
        return entityData.get(DATA_FACING);
    }
    public void setProgress(int progress) {
        entityData.set(DATA_PROGRESS, progress);
    }
    public void setFacing(Direction facing) {
        entityData.set(DATA_FACING, facing);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(DATA_FACING, Direction.DOWN);
        pBuilder.define(DATA_PROGRESS, 0);
    }

    @Override
    public void tick() {
        if (level().isClientSide()) return;
        RandomSource random = RandomSource.create();

        // aging
        if (getProgress() > LIFETIME) {
            this.discard();
        }
        this.setProgress(getProgress() + 1);

        // randomly decide if a neuron should be initialized.
        for (BlockPos.MutableBlockPos neuron : neurons){
            if (neuron == null || random.nextFloat() < STRENGTH) {
                neuron = this.blockPosition().mutable();
            }
            for (int n = 0; n < TRIES; n++) {
                if (isBreakable(level().getBlockState(neuron))) {
                    level().destroyBlock(neuron, true, this);
                    break;
                } else {
                    if (random.nextFloat() < OFFSET) {
                        neuron.move(getFacing().getOpposite());
                    } else {
                        switch (random.nextInt(3)) {
                            case 0 -> neuron.move(random.nextBoolean() ? 1 : -1, 0, 0);
                            case 1 -> neuron.move(0, random.nextBoolean() ? 1 : -1, 0);
                            case 2 -> neuron.move(0, 0, random.nextBoolean() ? 1 : -1);
                        }
                    }
                }
            }
        }
    }

    public boolean isBreakable(BlockState block) {
        return block.is(ModBlockTags.LARVAE_CAN_DIG);
    }
    @Override
    protected void applyGravity() {
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        setProgress(pCompound.getInt("progress"));
        setFacing(Direction.from3DDataValue(pCompound.getByte("facing")));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("progress", getProgress());
        pCompound.putByte("facing", ((byte) getFacing().get3DDataValue()));
    }
}
