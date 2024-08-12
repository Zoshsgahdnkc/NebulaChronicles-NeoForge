package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public record SimpleReplacementConfiguration(OreConfiguration.TargetBlockState targetBlockState) implements FeatureConfiguration {
    public static final Codec<SimpleReplacementConfiguration> CODEC = RecordCodecBuilder.create(o -> {
        return o.group(OreConfiguration.TargetBlockState.CODEC.fieldOf("target").forGetter(SimpleReplacementConfiguration::targetBlockState)).apply(o, SimpleReplacementConfiguration::new);
    } );
}
