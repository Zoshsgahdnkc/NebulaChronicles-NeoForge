package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnergyPlate extends Block {
    private static final VoxelShape voxelShape = Block.box(0,0,0,16,4,16);
    private final int level;
    public EnergyPlate(int level,Properties properties ) {
        super(properties);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return voxelShape;
    }
}
