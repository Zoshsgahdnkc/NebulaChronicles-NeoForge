package com.zoshsgahdnkc.NebulaChronicles.block;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.utils.BlockTraverse;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Optional;

public class SilverblancMossBlock extends NyliumBlock implements BonemealableBlock {
    private static final float GROWING_STRENGTH = 0.5F;
    private static WeightedRandomList<WeightedEntry.Wrapper<BlockState>> GROWABLE_FEATURES = WeightedRandomList.create();
    private final Block baseBlock;
    public SilverblancMossBlock(Properties properties, DeferredBlock<Block> baseBlock) {
        super(properties);
        this.baseBlock = baseBlock.get();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if (!canBeNylium(state, serverLevel, pos)) {
            serverLevel.setBlockAndUpdate(pos, baseBlock.defaultBlockState());
        }

    }


    private static boolean canBeNylium(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        int i = LightEngine.getLightBlockInto(levelReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(levelReader, blockpos));
        return i < levelReader.getMaxLightLevel();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos blockPos, BlockState state) {
        for (BlockPos pos : BlockTraverse.traverseSquare(blockPos, 3, 3, 1)) {
            boolean aboveEmpty = level.getBlockState(pos.above()).isAir();
            BlockState posState = level.getBlockState(pos);
            if (aboveEmpty && (posState.is(ModBlocks.MOSS_SILVERBLANC_STONE.get()) || posState.is(ModBlocks.MOSS_FROZEN_SOIL.get()))) {
                if (randomSource.nextFloat() < GROWING_STRENGTH) {
                    if (GROWABLE_FEATURES.isEmpty()) {
                        GROWABLE_FEATURES = WeightedRandomList.create(
                                WeightedEntry.wrap(ModBlocks.WHITE_BUD.get().defaultBlockState(), 15),
                                WeightedEntry.wrap(ModBlocks.STRANGE_FERN.get().defaultBlockState(), 2),
                                WeightedEntry.wrap(ModBlocks.BLUE_KODOKU_FLOWER.get().defaultBlockState(), 2),
                                WeightedEntry.wrap(ModBlocks.PURPLE_KODOKU_FLOWER.get().defaultBlockState(), 2),
                                WeightedEntry.wrap(Blocks.DEAD_BUSH.defaultBlockState(), 1)
                        );
                    }
                    Optional<WeightedEntry.Wrapper<BlockState>> optional = GROWABLE_FEATURES.getRandom(randomSource);
                    BlockState toPlace = Blocks.AIR.defaultBlockState();
                    if (optional.isPresent()){
                        toPlace = optional.get().data();
                    }
                    level.setBlock(pos.above(), toPlace, 3);
                }
            }
        };
    }
}
