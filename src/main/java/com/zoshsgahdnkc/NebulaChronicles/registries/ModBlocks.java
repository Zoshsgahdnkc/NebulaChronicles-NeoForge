package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.block.*;
import com.zoshsgahdnkc.NebulaChronicles.block.entityblock.NickelsteelPlasticContainerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NebulaChronicles.MODID);
    private static DeferredBlock<Block> registerBlock(String name, Supplier<? extends Block> block){
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.registerSimpleBlockItem(toReturn);
        return toReturn;
    }


    public static final DeferredBlock<Block> FORTRESS_BLOCK = registerBlock("fortress_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(ModSounds.TECH_BLOCK)
                    .strength(3.5f,6f)
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<Block> FORTRESS_WALL = registerBlock("fortress_wall",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get())));
    public static final DeferredBlock<Block> CARGO_BOX = registerBlock("cargo_box",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).strength(2f,2f)));
    public static final DeferredBlock<Block> FORTRESS_WALL_LIGHT = registerBlock("fortress_wall_light",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get()).lightLevel((p_50874_)-> 15)));
    public static final DeferredBlock<Block> FORTRESS_WALL_LIGHT_UNLIT = registerBlock("fortress_wall_light_unlit",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get())));
    public static final DeferredBlock<Block> FORTRESS_DOOR = registerBlock("fortress_door",
            () -> new FortressDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_DOOR).noOcclusion().requiresCorrectToolForDrops()
                    .strength(3.5f,6f)));
    public static final DeferredBlock<Block> TECH_TILE = registerBlock("tech_tile",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.BLUE_TERRACOTTA)));
    public static final DeferredBlock<Block> TECH_TILE_WITH_SIGN = registerBlock("tech_tile_with_sign",
            () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofLegacyCopy(TECH_TILE.get())));
    public static final DeferredBlock<Block> COARSE_CACTUS_PLANKS = registerBlock("coarse_cactus_planks",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD)));
    public static final DeferredBlock<Block> COARSE_CACTUS_DOOR = registerBlock("coarse_cactus_door",
            () -> new DoorBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofLegacyCopy(COARSE_CACTUS_PLANKS.get())));
    public static final DeferredBlock<Block> COARSE_CACTUS_TRAPDOOR = registerBlock("coarse_cactus_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.BAMBOO, BlockBehaviour.Properties.ofLegacyCopy(COARSE_CACTUS_PLANKS.get()).noOcclusion()));
    public static final DeferredBlock<Block> DARK_TILE = registerBlock("dark_tile",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get()).sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> ERODED_DARK_TILE = registerBlock("eroded_dark_tile",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(DARK_TILE.get())));
    public static final DeferredBlock<Block> LIGHT_TILE = registerBlock("light_tile",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(DARK_TILE.get())));
    public static final DeferredBlock<Block> ERODED_LIGHT_TILE = registerBlock("eroded_light_tile",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(DARK_TILE.get())));
    public static final DeferredBlock<Block> IRON_BRICKS = registerBlock("iron_bricks",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> IRON_COLLAGE = registerBlock("iron_collage",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> BUNKER_BRICKS = registerBlock("bunker_bricks",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_BRICKS)));
    public static final DeferredBlock<Block> IRON_BRICKS_SLAB = registerBlock("iron_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofLegacyCopy(IRON_BRICKS.get())));
    public static final DeferredBlock<Block> IRON_BRICKS_STAIRS = registerBlock("iron_bricks_stairs",
            () -> new StairBlock(IRON_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.ofLegacyCopy(IRON_BRICKS.get())));
    public static final DeferredBlock<Block> SIMPLE_VAULT_STAIRS = registerBlock("simple_vault_stairs",
            () -> new SimpleVaultStairsBlock(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> THICK_VAULT_STAIRS = registerBlock("thick_vault_stairs",
            () -> new ThickVaultStairsBlock(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> LOW_FENCE = registerBlock("low_fence",
            () -> new LowFenceBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.COPPER_BLOCK).strength(4f,8f).noOcclusion()));
    public static final DeferredBlock<Block> NICKELSTEEL_PLASTIC_CONTAINER = registerBlock("nickelsteel_plastic_container",
            () -> new NickelsteelPlasticContainerBlock(BlockBehaviour.Properties.ofLegacyCopy(FORTRESS_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> NICKEL_BLOCK = registerBlock("nickel_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> NICKELSTEEL_PLASTIC_BLOCK = registerBlock("nickelsteel_plastic_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> ULTRALLOY_BLOCK = registerBlock("ultralloy_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> LEMURIUM_BLOCK = registerBlock("lemurium_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> THULIUM_188_BLOCK = registerBlock("thulium_188_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> ORGANIC_PLASTIC_BLOCK = registerBlock("organic_plastic_block",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).strength(2f,4f)));
    public static final DeferredBlock<Block> NICKEL_ORE = registerBlock("nickel_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_NICKEL_ORE = registerBlock("deepslate_nickel_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final DeferredBlock<Block> ARCHEOVA_STONE = registerBlock("archeova_stone",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE).strength(6f,7f)));
    public static final DeferredBlock<Block> COSMIC_STONE = registerBlock("cosmic_stone",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).strength(5f,6f)));
    public static final DeferredBlock<Block> DEEPSPACE_STONE = registerBlock("deepspace_stone",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).strength(7.5f,12f)));
    public static final DeferredBlock<Block> COSMIC_SAND = registerBlock("cosmic_sand",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.SAND).strength(1f)));
    public static final DeferredBlock<Block> COSMIC_SANDSTONE = registerBlock("cosmic_sandstone",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.SANDSTONE).strength(1f)));
    public static final DeferredBlock<Block> SILVERBLANC_STONE = registerBlock("silverblanc_stone",
            () -> new SilverblancStoneBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).strength(1f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_SLAB = registerBlock("silverblanc_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_STAIRS = registerBlock("silverblanc_stone_stairs",
            () -> new StairBlock(SILVERBLANC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_WALL = registerBlock("silverblanc_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_COPPER_ORE = registerBlock("silverblanc_copper_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get()).strength(2F)));
    public static final DeferredBlock<Block> SILVERBLANC_IRON_ORE = registerBlock("silverblanc_iron_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COPPER_ORE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_GOLD_ORE = registerBlock("silverblanc_gold_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COPPER_ORE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_NICKEL_ORE = registerBlock("silverblanc_nickel_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COPPER_ORE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_LAPIS_ORE = registerBlock("silverblanc_lapis_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COPPER_ORE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_DIAMOND_ORE = registerBlock("silverblanc_diamond_ore",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COPPER_ORE.get())));
    public static final DeferredBlock<Block> MOSS_SILVERBLANC_STONE = registerBlock("moss_silverblanc_stone",
            () -> new SilverblancMossBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get()).sound(SoundType.NYLIUM).randomTicks(), SILVERBLANC_STONE));
    public static final DeferredBlock<Block> SOLAR_PANEL = registerBlock("solar_panel",
            () -> new EnergyPlate(1, BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> REDSTONE_POWER_PANEL = registerBlock("redstone_power_panel",
            () -> new EnergyPlate(2, BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> TACHYON_PROJECTION_PANEL = registerBlock("tachyon_projection_panel",
            () -> new EnergyPlate(3, BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> DARK_MATTER_RENDER_PANEL = registerBlock("dark_matter_render_panel",
            () -> new EnergyPlate(4, BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> WALL_PAPER = registerBlock("wall_paper",
            () -> new ColumnBlock( BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> WHITE_BUD = registerBlock("white_bud",
            () -> new WhiteBudBlock( BlockBehaviour.Properties.ofLegacyCopy(Blocks.SHORT_GRASS)));
    public static final DeferredBlock<Block> STRANGE_FERN = registerBlock("strange_fern",
            () -> new StrangeFernBlock( BlockBehaviour.Properties.ofLegacyCopy(Blocks.SHORT_GRASS)));
    public static final DeferredBlock<Block> SILVERBLANC_COBBLESTONE = registerBlock("silverblanc_cobblestone",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE.get()).strength(1.5f)));
    public static final DeferredBlock<Block> SILVERBLANC_COBBLESTONE_SLAB = registerBlock("silverblanc_cobblestone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COBBLESTONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_COBBLESTONE_STAIRS = registerBlock("silverblanc_cobblestone_stairs",
            () -> new StairBlock(SILVERBLANC_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COBBLESTONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_COBBLESTONE_WALL = registerBlock("silverblanc_cobblestone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COBBLESTONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_BRICKS = registerBlock("silverblanc_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_COBBLESTONE.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_BRICKS_STAIRS = registerBlock("silverblanc_stone_bricks_stairs",
            () -> new StairBlock(SILVERBLANC_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE_BRICKS.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_BRICKS_SLAB = registerBlock("silverblanc_stone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE_BRICKS.get())));
    public static final DeferredBlock<Block> SILVERBLANC_STONE_BRICKS_WALL = registerBlock("silverblanc_stone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(SILVERBLANC_STONE_BRICKS.get())));
    public static final DeferredBlock<Block> FROZEN_SOIL = registerBlock("frozen_soil",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.DIRT).strength(2F)));
    public static final DeferredBlock<Block> MOSS_FROZEN_SOIL = registerBlock("moss_frozen_soil",
            () -> new SilverblancMossBlock(BlockBehaviour.Properties.ofLegacyCopy(FROZEN_SOIL.get()), FROZEN_SOIL));
    public static final DeferredBlock<Block> SALTY_ICE = registerBlock("salty_ice",
            () -> new Block(BlockBehaviour.Properties.ofLegacyCopy(Blocks.PACKED_ICE)));
    public static final DeferredBlock<Block> BLUE_KODOKU_FLOWER = registerBlock("blue_kodoku_flower",
            () -> new FlowerBlock(MobEffects.DIG_SLOWDOWN, 12, BlockBehaviour.Properties.ofLegacyCopy(Blocks.DANDELION)));
    public static final DeferredBlock<Block> PURPLE_KODOKU_FLOWER = registerBlock("purple_kodoku_flower",
            () -> new FlowerBlock(MobEffects.DIG_SLOWDOWN, 12, BlockBehaviour.Properties.ofLegacyCopy(Blocks.DANDELION)));
    public static final DeferredBlock<Block> WHITE_KODOKU_FLOWER = registerBlock("white_kodoku_flower",
            () -> new FlowerBlock(MobEffects.DIG_SPEED, 12, BlockBehaviour.Properties.ofLegacyCopy(Blocks.DANDELION)));
    public static final DeferredBlock<Block> COARSE_CACTUS = registerBlock("coarse_cactus",
            () -> new CoarseCactusBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.GRASS_BLOCK)));
    public static final DeferredBlock<Block> AETHER_ROOT = registerBlock("aether_root",
            () -> new AetherRootBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART_BLOCK).randomTicks()));
    public static final DeferredBlock<Block> AETHER_ROOT_HAIR = registerBlock("aether_root_hair",
            () -> new AetherRootHairBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.HANGING_ROOTS)));
    public static final DeferredBlock<Block> IRON_SCAFFOLDING = registerBlock("iron_scaffolding",
            () -> new MetalScaffoldingBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.COPPER)
                    .strength(0.7f, 2.0f)
                    .mapColor(DyeColor.GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .noCollission()
                    .isValidSpawn(Blocks::never)
                    .isSuffocating(ModBlocks::never)
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredBlock<Block> NICKELSTEEL_PLASTIC_SCAFFOLDING = registerBlock("nickelsteel_plastic_scaffolding",
            () -> new MetalScaffoldingBlock(BlockBehaviour.Properties.ofFullCopy(IRON_SCAFFOLDING.get())));

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}
