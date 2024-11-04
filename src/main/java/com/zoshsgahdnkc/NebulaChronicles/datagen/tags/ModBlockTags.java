package com.zoshsgahdnkc.NebulaChronicles.datagen.tags;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NebulaChronicles.MODID, existingFileHelper);
    }
    public static final TagKey<Block> BASE_STONE = TagKey.create(Registries.BLOCK, getRL("base_stone"));
    public static final TagKey<Block> SB_ORE_REPLACEABLE = TagKey.create(Registries.BLOCK, getRL("sb_ore_replaceable"));
    public static final TagKey<Block> METAL_SCAFFOLDING = TagKey.create(Registries.BLOCK, getRL("metal_scaffolding"));
    public static final TagKey<Block> ICE = TagKey.create(Registries.BLOCK, getRL("ice"));
    public static final TagKey<Block> LARVAE_CAN_DIG = TagKey.create(Registries.BLOCK, getRL("larvae_can_dig"));

    private static ResourceLocation getRL(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ICE)
                .add(ModBlocks.SALTY_ICE.get())
                .add(Blocks.ICE)
                .add(Blocks.PACKED_ICE)
                .add(Blocks.BLUE_ICE);
        tag(SB_ORE_REPLACEABLE)
                .add(ModBlocks.SILVERBLANC_STONE.get())
                .add(Blocks.CALCITE)
                .add(Blocks.GRANITE)
                .add(Blocks.DIORITE);
        tag(BASE_STONE)
                .add(ModBlocks.SILVERBLANC_STONE.get())
                .add(Blocks.STONE)
                .add(Blocks.ANDESITE)
                .add(Blocks.GRANITE)
                .add(Blocks.DIORITE)
                .add(Blocks.TUFF)
                .add(Blocks.DEEPSLATE);
        tag(METAL_SCAFFOLDING)
                .add(ModBlocks.IRON_SCAFFOLDING.get())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_SCAFFOLDING.get());
        tag(LARVAE_CAN_DIG)
                .addTag(BlockTags.BASE_STONE_OVERWORLD)
                .addTag(BlockTags.BASE_STONE_NETHER)
                .add(Blocks.CRIMSON_NYLIUM)
                .add(Blocks.WARPED_NYLIUM)
                .addTag(BASE_STONE)
                .add(ModBlocks.MOSS_SILVERBLANC_STONE.get())
                .addTag(ICE)
                .add(Blocks.SANDSTONE)
                .add(Blocks.CALCITE)
                .add(Blocks.END_STONE)
                .add(Blocks.MAGMA_BLOCK)
                .add(ModBlocks.COSMIC_SANDSTONE.get())
                .add(Blocks.PRISMARINE);


        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .replace(false)
                .add(ModBlocks.ARCHEOVA_STONE.get())
                .add(ModBlocks.COSMIC_STONE.get())
                .add(ModBlocks.DEEPSPACE_STONE.get())
                .add(ModBlocks.BUNKER_BRICKS.get())
                .add(ModBlocks.CARGO_BOX.get())
                .add(ModBlocks.DARK_TILE.get())
                .add(ModBlocks.ERODED_DARK_TILE.get())
                .add(ModBlocks.LIGHT_TILE.get())
                .add(ModBlocks.ERODED_LIGHT_TILE.get())
                .add(ModBlocks.FORTRESS_BLOCK.get())
                .add(ModBlocks.FORTRESS_WALL.get())
                .add(ModBlocks.FORTRESS_WALL_LIGHT.get())
                .add(ModBlocks.FORTRESS_WALL_LIGHT_UNLIT.get())
                .add(ModBlocks.FORTRESS_DOOR.get())
                .add(ModBlocks.IRON_BRICKS.get())
                .add(ModBlocks.IRON_BRICKS_SLAB.get())
                .add(ModBlocks.IRON_BRICKS_STAIRS.get())
                .add(ModBlocks.IRON_COLLAGE.get())
                .add(ModBlocks.LOW_FENCE.get())
                .add(ModBlocks.SIMPLE_VAULT_STAIRS.get())
                .add(ModBlocks.THICK_VAULT_STAIRS.get())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_CONTAINER.get())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_BLOCK.get())
                .add(ModBlocks.THULIUM_188_BLOCK.get())
                .add(ModBlocks.ULTRALLOY_BLOCK.get())
                .add(ModBlocks.LEMURIUM_BLOCK.get())
                .add(ModBlocks.TECH_TILE.get())
                .add(ModBlocks.TECH_TILE_WITH_SIGN.get())
                .add(ModBlocks.COSMIC_SANDSTONE.get())
                .add(ModBlocks.SALTY_ICE.get())
                .add(ModBlocks.SILVERBLANC_STONE.get())
                .add(ModBlocks.SILVERBLANC_STONE_SLAB.get())
                .add(ModBlocks.SILVERBLANC_STONE_STAIRS.get())
                .add(ModBlocks.SILVERBLANC_STONE_WALL.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_SLAB.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_WALL.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_STAIRS.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_WALL.get())
                .add(ModBlocks.MOSS_SILVERBLANC_STONE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.SILVERBLANC_COPPER_ORE.get())
                .add(ModBlocks.SILVERBLANC_IRON_ORE.get())
                .add(ModBlocks.SILVERBLANC_NICKEL_ORE.get())
                .add(ModBlocks.SILVERBLANC_GOLD_ORE.get())
                .add(ModBlocks.SILVERBLANC_LAPIS_ORE.get())
                .add(ModBlocks.SILVERBLANC_DIAMOND_ORE.get())
                .add(ModBlocks.IRON_SCAFFOLDING.get())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_SCAFFOLDING.get());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .replace(false)
                .add(ModBlocks.COARSE_CACTUS.get())
                .add(ModBlocks.COARSE_CACTUS_PLANKS.get())
                .add(ModBlocks.COARSE_CACTUS_SLAB.get())
                .add(ModBlocks.COARSE_CACTUS_STAIRS.get())
                .add(ModBlocks.COARSE_CACTUS_DOOR.get())
                .add(ModBlocks.COARSE_CACTUS_TRAPDOOR.get())
                .add(ModBlocks.SPIKED_VERDHELM_BEETLE_SKULL.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .replace(false)
                .add(ModBlocks.COSMIC_SAND.get())
                .add(ModBlocks.CRYOSOL.get())
                .add(ModBlocks.MOSS_CRYOSOL.get());


        tag(BlockTags.CLIMBABLE)
                .replace(false)
                .add(ModBlocks.AETHER_ROOT_HAIR.get())
                .addTag(METAL_SCAFFOLDING);
        tag(BlockTags.WALLS)
                .replace(false)
                .add(ModBlocks.SILVERBLANC_STONE_WALL.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_WALL.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_WALL.get());
        tag(BlockTags.SLABS)
                .replace(false)
                .add(ModBlocks.SILVERBLANC_STONE_SLAB.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_SLAB.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB.get());
        tag(BlockTags.STAIRS)
                .replace(false)
                .add(ModBlocks.SILVERBLANC_STONE_STAIRS.get())
                .add(ModBlocks.SILVERBLANC_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.SILVERBLANC_STONE_BRICKS_STAIRS.get());
        tag(BlockTags.DIRT)
                .replace(false)
                .add(ModBlocks.CRYOSOL.get())
                .add(ModBlocks.MOSS_CRYOSOL.get())
                .add(ModBlocks.MOSS_SILVERBLANC_STONE.get());
        tag(BlockTags.SAND)
                .replace(false)
                .add(ModBlocks.COSMIC_SAND.get());
        tag(BlockTags.BASE_STONE_OVERWORLD)
                .replace(false)
                .add(ModBlocks.SILVERBLANC_STONE.get());
    }

    private ResourceLocation rl(DeferredBlock<Block> block) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, block.get().toString());
    }
}
