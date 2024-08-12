package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature;

import com.mojang.serialization.Codec;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.configurations.SimpleReplacementConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class StoneSlabFeature extends Feature<SimpleReplacementConfiguration> {
    private static final int TRIES = 48;
    private static final int XZ_SPREAD = 5;
    private static final int Y_SPREAD = 1;
    public StoneSlabFeature(Codec<SimpleReplacementConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleReplacementConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomSource = context.random();
        RuleTest ruleTest = context.config().targetBlockState().target;
        BlockState toPlace = context.config().targetBlockState().state;
        if (isAvailablePosition(pos, ruleTest, level, randomSource) && nearbyAirCount(level, pos) >= 1) {
            this.setBlock(level, pos, toPlace);
            for (int i = 0; i < TRIES; i++) {
                BlockPos newPos = pos.offset(randomSource.nextInt(XZ_SPREAD), Y_SPREAD, randomSource.nextInt(XZ_SPREAD));
                int air = nearbyAirCount(level, newPos);
                int slab = nearbySlabCount(level, newPos, toPlace);
                if (isAvailablePosition(newPos, ruleTest, level, randomSource) &&
                        (air >= 2 || (air == 1 && slab >= 1))) {
                    this.setBlock(level, newPos, toPlace);
                }
            }
            return true;
        }
        return false;
    }
    private boolean isAvailablePosition(BlockPos pos, RuleTest test, LevelReader level, RandomSource randomSource) {
        return test.test(level.getBlockState(pos), randomSource) && isAirAbove(level, pos);
    }
    private boolean isAirAbove(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).isAir();
    }
    private int nearbyAirCount(LevelReader level, BlockPos pos) {
        int count = 0;
        if (level.getBlockState(pos.south()).isAir()) count += 1;
        if (level.getBlockState(pos.west()).isAir()) count += 1;
        if (level.getBlockState(pos.north()).isAir()) count += 1;
        if (level.getBlockState(pos.east()).isAir()) count += 1;
        return count;
    }
    private int nearbySlabCount(LevelReader level, BlockPos pos, BlockState state) {
        int count = 0;
        Block block = state.getBlock();
        if (level.getBlockState(pos.south()).is(block)) count += 1;
        if (level.getBlockState(pos.west()).is(block)) count += 1;
        if (level.getBlockState(pos.north()).is(block)) count += 1;
        if (level.getBlockState(pos.east()).is(block)) count += 1;
        return count;
    }

}
