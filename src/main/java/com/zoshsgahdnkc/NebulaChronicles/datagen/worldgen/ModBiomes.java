package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {
    private static ResourceLocation rl(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }
    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, rl(name));
    }

    public static final MobSpawnSettings DUMMY_SETTING = new MobSpawnSettings.Builder().build();
    private static BiomeSpecialEffects generateEffects(int... colors) {
//        if (colors.length < 6) {
//            return new BiomeSpecialEffects.Builder()
//                    .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2))
//                    .build();
//        }
        return new BiomeSpecialEffects.Builder()
                .skyColor(colors[0])
                .fogColor(colors[1])
                .waterColor(colors[2])
                .waterFogColor(colors[3])
                .grassColorOverride(colors[4])
                .foliageColorOverride(colors[5])
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2))
                .build();
    }


    // Silverblanc

    public static final ResourceKey<Biome> SILVERBLANC_HILLS = createKey("silverblanc_hills");
    public static final ResourceKey<Biome> SILVERBLANC_BARRENS = createKey("silverblanc_barrens");
    public static final ResourceKey<Biome> SILVERBLANC_LOWLAND = createKey("silverblanc_lowland");
    public static final ResourceKey<Biome> SALTY_MARSH = createKey("salty_marsh");
    public static final ResourceKey<Biome> SILVERBLANC_PLATEAU = createKey("silverblanc_plateau");
    public static final ResourceKey<Biome> SILVERBLANC_DESERT = createKey("silverblanc_desert");
    public static final ResourceKey<Biome> GREEN_GLACIER = createKey("green_glacier");
    public static final ResourceKey<Biome> SILVERBLANC_CAVE = createKey("silverblanc_cave");
    public static final ResourceKey<Biome> FROST_CAVE = createKey("frost_cave");
    public static final int[] colors_SB = {7907327,12638463,8185086,2124168,4191135,3144081};
    public static BiomeGenerationSettings.Builder genCarversSB(BiomeGenerationSettings.Builder builder) {
        return builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_FROZEN_SOIL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DIORITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_CALCITE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_COSMIC_SAND)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_COPPER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_COPPER_LARGE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_IRON)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_IRON_UPPER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_NICKEL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_GOLD)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_GOLD_LARGE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);
    }
    protected static void registerSB(BootstrapContext<Biome> context, HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        context.register(SILVERBLANC_HILLS, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_WHITE_BUD)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_SILVERBLANC_FLOWER)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_STRANGE_FERN)
                                .build()
                )
                .build());

        context.register(SILVERBLANC_PLATEAU, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_WHITE_BUD)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_SILVERBLANC_FLOWER)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_STRANGE_FERN)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_WHITE_KODOKU_FLOWER)
                                .build()
                )
                .build());

        context.register(SILVERBLANC_LOWLAND, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_WHITE_BUD)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_SILVERBLANC_FLOWER)
                                .build()
                )
                .build());

        context.register(SALTY_MARSH, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_WHITE_BUD)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_SILVERBLANC_FLOWER)
                                .addFeature(GenerationStep.Decoration.LAKES, ModPF.PLACE_SB_SALTY_ICE_LAKE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.PLACE_DISK_FROZEN_SOIL)
                                .build()
                )
                .build());

        context.register(SILVERBLANC_BARRENS, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .build()
                )
                .build());

        context.register(SILVERBLANC_DESERT, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(10341858, 10529722, 6528417, 5404312, 9629122, 6205555))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH)
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPF.PLACE_COARSE_CACTUS)
                                .build()
                )
                .build());

        context.register(SILVERBLANC_CAVE, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_LAPIS)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DIAMOND_LOWER)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DIAMOND_UPPER)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_NICKEL_LARGE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_COSMIC_SANDSTONE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DEEPSLATE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_SILVERBLANC_STONE_SLAB)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_STONE_SLAB)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_CAVE_AMETHYST)
                                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_AETHER_ROOT)
                                .build()
                )
                .build());

        context.register(FROST_CAVE, new Biome.BiomeBuilder()
                .temperature(0.25f)
                .downfall(0.1f)
                .hasPrecipitation(true)
                .specialEffects(generateEffects(colors_SB))
                .mobSpawnSettings(DUMMY_SETTING)
                .generationSettings(
                        genCarversSB(new BiomeGenerationSettings.Builder(featureGetter,carverGetter))
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_OBSIDIAN)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_CRYING_OBSIDIAN)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_LAPIS)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DIAMOND_LOWER)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DIAMOND_UPPER)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_NICKEL_LARGE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_COSMIC_SANDSTONE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPF.SB_ORE_DEEPSLATE)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_SILVERBLANC_STONE_SLAB)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_STONE_SLAB)
                                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER)
                                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_LAVA_FROZEN)
                                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPF.PLACE_AETHER_ROOT)
                                .build()
                )
                .build());
    }
    public static BiomeSource genBiomeSourceSB(HolderGetter<Biome> biomeRegistry) {
        return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(ImmutableList.of(
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(0.75f, 1f),     // Continentalness
                        Climate.Parameter.point(0),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(SILVERBLANC_PLATEAU)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(0.75f, 0.8f),     // Continentalness
                        Climate.Parameter.span(-0.25f, 0.75f),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(SILVERBLANC_HILLS)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(-1f, -0.3f),     // Continentalness
                        Climate.Parameter.point(0),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(SILVERBLANC_DESERT)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(0.5f, 0.9f),     // Continentalness
                        Climate.Parameter.point(0.8f),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0.35f), biomeRegistry.getOrThrow(SILVERBLANC_BARRENS)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(-0.3f, 0.2f),     // Continentalness
                        Climate.Parameter.point(0),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(SILVERBLANC_LOWLAND)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.span(-0.1f, 0f),     // Continentalness
                        Climate.Parameter.point(0),      // erosion
                        Climate.Parameter.point(-1.4f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0.25f), biomeRegistry.getOrThrow(SALTY_MARSH)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.point(0f),     // Continentalness
                        Climate.Parameter.span(-0.3f, 1f),      // erosion
                        Climate.Parameter.point(1.9f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(SILVERBLANC_CAVE)),
                Pair.of(Climate.parameters(
                        Climate.Parameter.point(0f),     // Temperature
                        Climate.Parameter.point(0f),     // Humidity
                        Climate.Parameter.point(0.25f),     // Continentalness
                        Climate.Parameter.span(-1f, 0.3f),      // erosion
                        Climate.Parameter.point(1.9f),      // depth
                        Climate.Parameter.point(0f),     //weirdness
                        0f), biomeRegistry.getOrThrow(FROST_CAVE))
        )));
    }


    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);

        registerSB(context, featureGetter, carverGetter);
    }
}
