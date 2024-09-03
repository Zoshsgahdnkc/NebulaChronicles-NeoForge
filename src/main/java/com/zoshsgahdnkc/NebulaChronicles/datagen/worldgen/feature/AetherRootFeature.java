package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature;

import com.mojang.serialization.Codec;
import com.zoshsgahdnkc.NebulaChronicles.block.AetherRootHairBlock;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModBiomes;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.lwjgl.opengl.WGL;

import java.util.ArrayList;
import java.util.List;

public class AetherRootFeature extends Feature<NoneFeatureConfiguration> {
    public AetherRootFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    private static final int DISTANCE = 4;

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomSource = context.random();
        if (isSpaceEnough(level, pos)) {
            int span = randomSource.nextInt(0, 5);
            setBlock(level, pos.above(), ModBlocks.AETHER_ROOT.get().defaultBlockState());
            for (int i = 0; i < span; i++) {
                setBlock(level, pos.below(i), ModBlocks.AETHER_ROOT_HAIR.get().defaultBlockState().setValue(AetherRootHairBlock.END, false));
            }
            setBlock(level, pos.below(span), ModBlocks.AETHER_ROOT_HAIR.get().defaultBlockState().setValue(AetherRootHairBlock.END, true));
            return true;
        }
        return false;
    }
    // detect block at 6 directions and around
    // must have at least a distance of 4 blocks to generate
    private boolean isSpaceEnough(WorldGenLevel level, BlockPos pos) {
        int[] corner = {-1, 1};
        for (int x: corner) {
            for (int y: corner) {
                for (int z: corner) {
                    if (!level.getBlockState(pos.mutable().move(x, y, z)).isAir()) {
                        return false;
                    }
                }
            }
        }
        for (int i = 1; i < DISTANCE + 1; i++) {
            boolean east = level.getBlockState(pos.east(i)).isAir();
            boolean west = level.getBlockState(pos.west(i)).isAir();
            boolean north = level.getBlockState(pos.north(i)).isAir();
            boolean south = level.getBlockState(pos.south(i)).isAir();
            boolean above = level.getBlockState(pos.above(i)).isAir();
            boolean below = level.getBlockState(pos.below(i)).isAir();
            if (!east || !west || !north || !south || !above || !below) return false;
        }
        return true;
    }
}
