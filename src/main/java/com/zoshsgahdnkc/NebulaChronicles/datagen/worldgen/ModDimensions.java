package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.OptionalLong;

public class ModDimensions {
    public static ResourceLocation rl(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }
    public static final ResourceKey<Level> SB_LEVEL = ResourceKey.create(Registries.DIMENSION, rl("silverblanc"));
    public static final ResourceKey<LevelStem> SB_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, rl("silverblanc"));
    public static final ResourceKey<DimensionType> SB_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, rl("silverblanc"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(SB_TYPE, new DimensionType(
                OptionalLong.of(18000L), //fixed time
                true, //skylight
                false, //ceiling
                false, //ultrawarm
                true, //natural
                1, //coordinate scale
                true, //bed works
                false, //respawn anchor works
                -48, // Minimum Y Level
                256, // Height + Min Y = Max Y
                256, // Logical Height
                BlockTags.INFINIBURN_OVERWORLD, //infiniburn
                rl("silverblanc"), // DimensionRenderInfo
                0.04f, // ambient light
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)));
    }

    //TODO Add Noise Datagen
    private static final ResourceKey<NoiseGeneratorSettings> NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "silverblanc"));

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        context.register(SB_LEVEL_STEM, new LevelStem(dimTypes.getOrThrow(SB_TYPE),
                new NoiseBasedChunkGenerator(ModBiomes.genBiomeSourceSB(biomeRegistry), noiseGenSettings.getOrThrow(NOISE_GEN))));
    }
}
