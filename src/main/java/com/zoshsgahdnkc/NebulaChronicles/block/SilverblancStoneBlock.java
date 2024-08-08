package com.zoshsgahdnkc.NebulaChronicles.block;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.utils.BlockTraverse;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SilverblancStoneBlock extends Block implements BonemealableBlock {
    private static final int SPREAD_DISTANCE = 5;
    public SilverblancStoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        for (BlockPos pos : BlockTraverse.traverseRhombus(blockPos, SPREAD_DISTANCE)) {
            boolean above = level.getBlockState(pos.above()).isAir();
            BlockState posState = level.getBlockState(pos);
            if (above && posState.is(ModBlocks.SILVERBLANC_STONE.get())) {
                level.setBlock(pos, ModBlocks.MOSS_SILVERBLANC_STONE.get().defaultBlockState(), 3);
            } else if (above && posState.is(ModBlocks.FROZEN_SOIL.get())) {
                level.setBlock(pos, ModBlocks.MOSS_FROZEN_SOIL.get().defaultBlockState(), 3);
            }
        };
    }
}
