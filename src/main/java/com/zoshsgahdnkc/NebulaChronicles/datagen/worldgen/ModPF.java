package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPF {
    //TODO Too little ores
    public static final ResourceKey<PlacedFeature> PLACE_STRANGE_FERN = createKey("place_strange_fern");
    public static final ResourceKey<PlacedFeature> PLACE_WHITE_BUD = createKey("place_white_bud");
    public static final ResourceKey<PlacedFeature> PLACE_SILVERBLANC_FLOWER = createKey("place_silverblanc_flower");
    public static final ResourceKey<PlacedFeature> PLACE_WHITE_KODOKU_FLOWER = createKey("place_white_kodoku_flower");
    public static final ResourceKey<PlacedFeature> PLACE_CAVE_AMETHYST = createKey("place_cave_amethyst");
    public static final ResourceKey<PlacedFeature> PLACE_SILVERBLANC_STONE_SLAB = createKey("place_silverblanc_stone_slab");
    public static final ResourceKey<PlacedFeature> PLACE_STONE_SLAB = createKey("place_stone_slab");
    public static final ResourceKey<PlacedFeature> PLACE_SB_SALTY_ICE_LAKE = createKey("place_silverblanc_salty_ice_lake");
    public static final ResourceKey<PlacedFeature> PLACE_DISK_FROZEN_SOIL = createKey("place_disk_frozen_soil");
    public static final ResourceKey<PlacedFeature> SB_ORE_FROZEN_SOIL = createKey("sb_ore_frozen_soil");
    public static final ResourceKey<PlacedFeature> SB_ORE_DIORITE = createKey("sb_ore_diorite");
    public static final ResourceKey<PlacedFeature> SB_ORE_COSMIC_SAND = createKey("sb_ore_cosmic_sand");
    public static final ResourceKey<PlacedFeature> SB_ORE_COSMIC_SANDSTONE = createKey("sb_ore_cosmic_sandstone");
    public static final ResourceKey<PlacedFeature> SB_ORE_CALCITE = createKey("sb_ore_calcite");
    public static final ResourceKey<PlacedFeature> SB_ORE_DEEPSLATE = createKey("sb_ore_deepslate");
    public static final ResourceKey<PlacedFeature> SB_ORE_COPPER_LARGE = createKey("sb_ore_copper_large");
    public static final ResourceKey<PlacedFeature> SB_ORE_COPPER = createKey("sb_ore_copper");
    public static final ResourceKey<PlacedFeature> SB_ORE_IRON_LARGE = createKey("sb_ore_iron_large");
    public static final ResourceKey<PlacedFeature> SB_ORE_IRON = createKey("sb_ore_iron");
    public static final ResourceKey<PlacedFeature> SB_ORE_IRON_UPPER = createKey("sb_ore_iron_upper");
    public static final ResourceKey<PlacedFeature> SB_ORE_NICKEL_LARGE = createKey("sb_ore_nickel_large");
    public static final ResourceKey<PlacedFeature> SB_ORE_NICKEL = createKey("sb_ore_nickel");
    public static final ResourceKey<PlacedFeature> SB_ORE_GOLD_LARGE = createKey("sb_ore_gold_large");
    public static final ResourceKey<PlacedFeature> SB_ORE_GOLD = createKey("sb_ore_gold");
    public static final ResourceKey<PlacedFeature> SB_ORE_DIAMOND_LOWER = createKey("sb_ore_diamond_large");
    public static final ResourceKey<PlacedFeature> SB_ORE_DIAMOND_UPPER = createKey("sb_ore_diamond");
    public static final ResourceKey<PlacedFeature> SB_ORE_LAPIS = createKey("sb_ore_lapis");
    public static final ResourceKey<PlacedFeature> PLACE_COARSE_CACTUS = createKey("coarse_cactus");
    public static final ResourceKey<PlacedFeature> PLACE_AETHER_ROOT = createKey("aether_root");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PLACE_STRANGE_FERN, features.getOrThrow(ModCF.STRANGE_FERN),
                NoiseThresholdCountPlacement.of(1, 2, 10), RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread()
                , HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome());
        register(context, PLACE_WHITE_BUD, features.getOrThrow(ModCF.WHITE_BUD),
                NoiseThresholdCountPlacement.of(0.4, 5, 10), InSquarePlacement.spread()
                , HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome());
        register(context, PLACE_SILVERBLANC_FLOWER, features.getOrThrow(ModCF.SILVERBLANC_FLOWER),
                NoiseThresholdCountPlacement.of(-0.8, 15, 4), RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome());
        register(context, PLACE_WHITE_KODOKU_FLOWER, features.getOrThrow(ModCF.WHITE_KODOKU_FLOWER),
                NoiseThresholdCountPlacement.of(-0.8, 15, 4), RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome());
        register(context, PLACE_COARSE_CACTUS, features.getOrThrow(ModCF.COARSE_CACTUS),
                NoiseThresholdCountPlacement.of(-0.8, 15, 4), RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING), BiomeFilter.biome());
        register(context, PLACE_CAVE_AMETHYST, features.getOrThrow(ModCF.AMETHYST),
                NoiseBasedCountPlacement.of(80, 0D, 0.34D), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64)),
                InSquarePlacement.spread(), BiomeFilter.biome());
        register(context, PLACE_SILVERBLANC_STONE_SLAB, features.getOrThrow(ModCF.SILVERBLANC_STONE_SLAB),
                CountPlacement.of(256), HeightRangePlacement.uniform(VerticalAnchor.absolute(-7), VerticalAnchor.absolute(30)),
                InSquarePlacement.spread(), BiomeFilter.biome());
        register(context, PLACE_STONE_SLAB, features.getOrThrow(ModCF.STONE_SLAB),
                CountPlacement.of(256), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(-13)),
                InSquarePlacement.spread(), BiomeFilter.biome());
        register(context, PLACE_SB_SALTY_ICE_LAKE, features.getOrThrow(ModCF.SILVERBLANC_SALTY_ICE_LAKE),
                RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome());
        register(context, PLACE_DISK_FROZEN_SOIL, features.getOrThrow(ModCF.DISK_FROZEN_SOIL),
                InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                BiomeFilter.biome(), RarityFilter.onAverageOnceEvery(2));
        register(context, PLACE_AETHER_ROOT, features.getOrThrow(ModCF.AETHER_ROOT),
                CountPlacement.of(8), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64)),
                InSquarePlacement.spread(), BiomeFilter.biome());

        register(context, SB_ORE_FROZEN_SOIL, features.getOrThrow(ModCF.ORE_FROZEN_SOIL),
                CountPlacement.of(1), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(64)));
        register(context, SB_ORE_COSMIC_SAND, features.getOrThrow(ModCF.ORE_COSMIC_SAND),
                CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(80)));
        register(context, SB_ORE_COSMIC_SANDSTONE, features.getOrThrow(ModCF.ORE_COSMIC_SANDSTONE),
                CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(64)));
        register(context, SB_ORE_DIORITE, features.getOrThrow(ModCF.ORE_DIORITE),
                CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48)));
        register(context, SB_ORE_CALCITE, features.getOrThrow(ModCF.ORE_CALCITE),
                CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48)));
        register(context, SB_ORE_DEEPSLATE, features.getOrThrow(ModCF.ORE_DEEPSLATE),
                CountPlacement.of(2), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(0)));

        registerOreCount(context, features, SB_ORE_COPPER, ModCF.ORE_COPPER_SMALL, 6, 32, 160);
        registerOreCount(context, features, SB_ORE_COPPER_LARGE, ModCF.ORE_COPPER_LARGE, 4, 32, 160);
        registerOreCount(context, features, SB_ORE_IRON_UPPER, ModCF.ORE_IRON_SMALL, 48, 0, 160);
        registerOreCount(context, features, SB_ORE_IRON, ModCF.ORE_IRON_SMALL, 12, 0, 112);
        registerOreCount(context, features, SB_ORE_IRON_LARGE, ModCF.ORE_IRON_LARGE, 8, 16, 88);
        registerOreCount(context, features, SB_ORE_NICKEL, ModCF.ORE_NICKEL_SMALL, 12, 0, 96);
        registerOreCount(context, features, SB_ORE_NICKEL_LARGE, ModCF.ORE_NICKEL_LARGE, 12, 0, 80);
        registerOreCount(context, features, SB_ORE_GOLD, ModCF.ORE_GOLD_SMALL, 5, 0, 96);
        registerOreCount(context, features, SB_ORE_GOLD_LARGE, ModCF.ORE_GOLD_LARGE, 3, 0, 56);
        registerOreCount(context, features, SB_ORE_LAPIS, ModCF.ORE_LAPIS, 5, 16, 80);
        registerOreCount(context, features, SB_ORE_DIAMOND_UPPER, ModCF.ORE_DIAMOND_UPPER, 3, 28, 68);
        registerOreRarity(context, features, SB_ORE_DIAMOND_LOWER, ModCF.ORE_DIAMOND_LOWER, 1, -40, 40);
    }
    protected static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, key));
    }
    protected static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                   Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        context.register(key, new PlacedFeature(feature, List.of(modifiers)));
    }
    private static void registerOreCount(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> features,
                                         ResourceKey<PlacedFeature> placed, ResourceKey<ConfiguredFeature<?, ?>> configured,
                                         IntProvider count, int trapezoidMin, int trapezoidMax) {
        register(context, placed, features.getOrThrow(configured), CountPlacement.of(count), InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(trapezoidMin), VerticalAnchor.aboveBottom(trapezoidMax))),
                BiomeFilter.biome());
    }
    private static void registerOreCount(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> features,
                                         ResourceKey<PlacedFeature> placed, ResourceKey<ConfiguredFeature<?, ?>> configured,
                                         int count, int trapezoidMin, int trapezoidMax) {
        register(context, placed, features.getOrThrow(configured), CountPlacement.of(count), InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(trapezoidMin), VerticalAnchor.aboveBottom(trapezoidMax))),
                BiomeFilter.biome());
    }
    private static void registerOreRarity(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> features,
                                         ResourceKey<PlacedFeature> placed, ResourceKey<ConfiguredFeature<?, ?>> configured,
                                         int rarity, int trapezoidMin, int trapezoidMax) {
        register(context, placed, features.getOrThrow(configured), RarityFilter.onAverageOnceEvery(rarity), InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(trapezoidMin), VerticalAnchor.aboveBottom(trapezoidMax))),
                BiomeFilter.biome());
    }
}
