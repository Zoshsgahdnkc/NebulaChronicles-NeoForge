package com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.block.CoarseCactusBlock;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModFeatures;
import com.zoshsgahdnkc.NebulaChronicles.utils.ModBlockTags;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.configurations.SimpleReplacementConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;

public class ModCF {
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRANGE_FERN = createKey("strange_fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_BUD = createKey("white_bud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVERBLANC_FLOWER = createKey("silverblanc_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_KODOKU_FLOWER = createKey("white_kodoku_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST = createKey("amethyst");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVERBLANC_SALTY_ICE_LAKE = createKey("silverblanc_salty_ice_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_FROZEN_SOIL = createKey("disk_frozen_soil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OBSIDIAN = createKey("ore_obsidian");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYING_OBSIDIAN = createKey("ore_crying_obsidian");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FROZEN_SOIL = createKey("ore_frozen_soil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CALCITE = createKey("ore_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COSMIC_SAND = createKey("ore_cosmic_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COSMIC_SANDSTONE = createKey("ore_cosmic_sandstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE = createKey("ore_deepslate");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIORITE = createKey("ore_diorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NICKEL_SMALL = createKey("ore_nickel_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NICKEL_LARGE = createKey("ore_nickel_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_IRON_SMALL = createKey("ore_iron_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_IRON_LARGE = createKey("ore_iron_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COPPER_SMALL = createKey("ore_copper_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COPPER_LARGE = createKey("ore_copper_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GOLD_SMALL = createKey("ore_gold_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GOLD_LARGE = createKey("ore_gold_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_UPPER = createKey("ore_diamond_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_LOWER = createKey("ore_diamond_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LAPIS = createKey("ore_lapis");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVERBLANC_STONE_SLAB = createKey("silverblanc_stone_slab");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_SLAB = createKey("stone_slab");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COARSE_CACTUS = createKey("coarse_cactus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_ROOT = createKey("aether_root");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, STRANGE_FERN, Feature.RANDOM_PATCH, grassPatch(ModBlocks.STRANGE_FERN.get(), 12));
        register(context, WHITE_BUD, Feature.RANDOM_PATCH, grassPatch(ModBlocks.WHITE_BUD.get(), 24));
        register(context, SILVERBLANC_FLOWER, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(ModBlocks.BLUE_KODOKU_FLOWER.get().defaultBlockState(), 1)
                .add(ModBlocks.PURPLE_KODOKU_FLOWER.get().defaultBlockState(), 1)), 6));
        register(context, WHITE_KODOKU_FLOWER, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(ModBlocks.WHITE_KODOKU_FLOWER.get().defaultBlockState()), 12));
        register(context, AMETHYST, ModFeatures.CAVE_AMETHYST.get(), new NoneFeatureConfiguration());
        register(context, SILVERBLANC_SALTY_ICE_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.SALTY_ICE.get()), BlockStateProvider.simple(ModBlocks.SILVERBLANC_STONE.get())));
        register(context, DISK_FROZEN_SOIL, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(ModBlocks.FROZEN_SOIL.get()),
                BlockPredicate.matchesBlocks(ModBlocks.FROZEN_SOIL.get(), ModBlocks.MOSS_FROZEN_SOIL.get()),
                UniformInt.of(3, 5), 1));
        register(context, ORE_DEEPSLATE, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                Blocks.DEEPSLATE.defaultBlockState(), 32));
        register(context, ORE_FROZEN_SOIL, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                ModBlocks.FROZEN_SOIL.get().defaultBlockState(), 48));
        register(context, ORE_CALCITE, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                Blocks.CALCITE.defaultBlockState(), 64));
        register(context, ORE_COSMIC_SAND, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                ModBlocks.COSMIC_SAND.get().defaultBlockState(), 64));
        register(context, ORE_COSMIC_SANDSTONE, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                ModBlocks.COSMIC_SANDSTONE.get().defaultBlockState(), 64));
        register(context, ORE_DIORITE, Feature.ORE, new OreConfiguration(new TagMatchTest(ModBlockTags.BASE_STONE),
                Blocks.DIORITE.defaultBlockState(), 64));
        register(context, SILVERBLANC_STONE_SLAB, ModFeatures.STONE_SLAB.get(),
                new SimpleReplacementConfiguration(OreConfiguration.target(new BlockMatchTest(ModBlocks.SILVERBLANC_STONE.get()),
                        ModBlocks.SILVERBLANC_STONE_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        register(context, STONE_SLAB, ModFeatures.STONE_SLAB.get(),
                new SimpleReplacementConfiguration(OreConfiguration.target(new BlockMatchTest(Blocks.STONE),
                        Blocks.STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        register(context, ORE_NICKEL_SMALL, Feature.ORE, new OreConfiguration(OreMatch.NICKEL_LIST, 4));
        register(context, ORE_NICKEL_LARGE, Feature.ORE, new OreConfiguration(OreMatch.NICKEL_LIST, 10, 0.1F));
        register(context, ORE_COPPER_SMALL, Feature.ORE, new OreConfiguration(OreMatch.COPPER_LIST, 4));
        register(context, ORE_COPPER_LARGE, Feature.ORE, new OreConfiguration(OreMatch.COPPER_LIST, 10, 0.1F));
        register(context, ORE_IRON_SMALL, Feature.ORE, new OreConfiguration(OreMatch.IRON_LIST, 4));
        register(context, ORE_IRON_LARGE, Feature.ORE, new OreConfiguration(OreMatch.IRON_LIST, 10, 0.1F));
        register(context, ORE_GOLD_SMALL, Feature.ORE, new OreConfiguration(OreMatch.GOLD_LIST, 4));
        register(context, ORE_GOLD_LARGE, Feature.ORE, new OreConfiguration(OreMatch.GOLD_LIST, 10, 0.3F));
        register(context, ORE_DIAMOND_UPPER, Feature.ORE, new OreConfiguration(OreMatch.DIAMOND_LIST, 5));
        register(context, ORE_DIAMOND_LOWER, Feature.ORE, new OreConfiguration(OreMatch.DIAMOND_LIST, 8, 0.3F));
//        register(context, ORE_LAPIS, Feature.ORE, new OreConfiguration(OreConfiguration.target(new TagMatchTest(ModBlockTags.BASE_STONE), Blocks.OBSIDIAN.defaultBlockState()), 7, 0.4F));
        register(context, ORE_LAPIS, Feature.ORE, new OreConfiguration(OreMatch.LAPIS_LIST, 7, 0.4F));
        register(context, ORE_CRYING_OBSIDIAN, Feature.ORE, new OreConfiguration(OreMatch.LAPIS_LIST, 7, 0.4F));

        register(context, COARSE_CACTUS, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(
                new BlockColumnConfiguration.Layer(UniformInt.of(1, 5), BlockStateProvider.simple(ModBlocks.COARSE_CACTUS.get().defaultBlockState().setValue(CoarseCactusBlock.TOP, false))),
                new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(ModBlocks.COARSE_CACTUS.get().defaultBlockState().setValue(CoarseCactusBlock.TOP, true)))
        ), Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
        register(context, AETHER_ROOT, ModFeatures.AETHER_ROOT.get(), new NoneFeatureConfiguration());
    }
    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> resourceKey,
            F feature, FC featureConfiguration
    ) {
        context.register(resourceKey, new ConfiguredFeature<>(feature, featureConfiguration));
    }
    protected static ResourceKey<ConfiguredFeature<?, ?>> createKey(String key) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, key));
    }
    protected static RandomPatchConfiguration simple(Block block) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)));
    }
    protected static RandomPatchConfiguration grassPatch(BlockStateProvider blockStateProvider, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(blockStateProvider)));
    }
    protected static RandomPatchConfiguration grassPatch(Block block, int tries) {
        return grassPatch(BlockStateProvider.simple(block), tries);
    }
    private static final class OreMatch{
        public static final RuleTest silverblanc = new TagMatchTest(ModBlockTags.SB_ORE_REPLACEABLE);
        public static final RuleTest stone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        public static final RuleTest deepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        public static final List<OreConfiguration.TargetBlockState> NICKEL_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_NICKEL_ORE)),
                OreConfiguration.target(stone, getState(ModBlocks.NICKEL_ORE)),
                OreConfiguration.target(deepslate, getState(ModBlocks.DEEPSLATE_NICKEL_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> COPPER_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_COPPER_ORE)),
                OreConfiguration.target(stone, getState(Blocks.COPPER_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_COPPER_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> IRON_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_IRON_ORE)),
                OreConfiguration.target(stone, getState(Blocks.IRON_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_IRON_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> GOLD_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_GOLD_ORE)),
                OreConfiguration.target(stone, getState(Blocks.GOLD_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_GOLD_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> DIAMOND_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_DIAMOND_ORE)),
                OreConfiguration.target(stone, getState(Blocks.DIAMOND_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_DIAMOND_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> LAPIS_LIST = List.of(
                OreConfiguration.target(silverblanc, getState(ModBlocks.SILVERBLANC_LAPIS_ORE)),
                OreConfiguration.target(stone, getState(Blocks.LAPIS_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_LAPIS_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> REDSTONE_LIST = List.of(
                OreConfiguration.target(stone, getState(Blocks.REDSTONE_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_REDSTONE_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> COAL_LIST = List.of(
                OreConfiguration.target(stone, getState(Blocks.COAL_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_COAL_ORE))
        );
        public static final List<OreConfiguration.TargetBlockState> EMERALD_LIST = List.of(
                OreConfiguration.target(stone, getState(Blocks.EMERALD_ORE)),
                OreConfiguration.target(deepslate, getState(Blocks.DEEPSLATE_EMERALD_ORE))
        );
        private static BlockState getState(DeferredBlock<Block> block) {
            return getState(block.get());
        }
        private static BlockState getState(Block block) {
            return block.defaultBlockState();
        }
    }

}

