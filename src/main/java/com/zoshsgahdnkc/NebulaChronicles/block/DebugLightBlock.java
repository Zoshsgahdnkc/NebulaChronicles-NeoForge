package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DebugLightBlock extends AbstractMachineBlock{
    public DebugLightBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getPowerConsumption(BlockState state, Level level, BlockPos pos, RandomSource random) {
        return 5;
    }

    @Override
    protected int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.getLightBlock(pState, pLevel, pPos);
    }
}
