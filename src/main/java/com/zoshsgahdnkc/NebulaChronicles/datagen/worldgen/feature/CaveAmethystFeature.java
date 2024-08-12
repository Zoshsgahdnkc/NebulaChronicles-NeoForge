package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CaveAmethystFeature extends Feature<NoneFeatureConfiguration> {
    public CaveAmethystFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomSource = context.random();
        return tryPlace(level, randomSource, pos);
    }
    private boolean tryPlace(WorldGenLevel level, RandomSource randomSource, BlockPos pos) {
        if (level.getBlockState(pos).isAir()) {
            if (level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
                this.setBlock(level, pos, Blocks.AMETHYST_CLUSTER.defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.UP));
                return true;
            } else {
                Direction side = getSturdyBlockAround(level, pos, randomSource);
                if (side != null) {
                    this.setBlock(level, pos, Blocks.AMETHYST_CLUSTER.defaultBlockState().setValue(AmethystClusterBlock.FACING, side));
                    return true;
                } else if (level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) {
                    this.setBlock(level, pos, Blocks.AMETHYST_CLUSTER.defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.DOWN));
                    return true;
                }
            }
        }
        return false;
    }
    private Direction getSturdyBlockAround(WorldGenLevel level, BlockPos pos, RandomSource randomSource) {
        List<Direction> directions = new ArrayList<>(List.of());
        if (level.getBlockState(pos.north()).isFaceSturdy(level, pos.north(), Direction.SOUTH)) {
            directions.add(Direction.SOUTH);
        }
        if (level.getBlockState(pos.south()).isFaceSturdy(level, pos.south(), Direction.NORTH)) {
            directions.add(Direction.NORTH);
        }
        if (level.getBlockState(pos.east()).isFaceSturdy(level, pos.east(), Direction.WEST)) {
            directions.add(Direction.WEST);
        }
        if (level.getBlockState(pos.west()).isFaceSturdy(level, pos.west(), Direction.EAST)) {
            directions.add(Direction.EAST);
        }
        if (!directions.isEmpty()) {
            return directions.get(randomSource.nextInt(directions.size()));
        }
        return null;
    }
}
