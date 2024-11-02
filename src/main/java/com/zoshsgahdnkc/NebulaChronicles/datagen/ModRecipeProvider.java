package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookUpProvider) {
        super(packOutput, lookUpProvider);
    }
    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        // Astral Kitchen
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_BUD_STEW)
                .requires(Items.BOWL)
                .requires(ModItems.WHITE_BUD_LEAVES)
                .requires(ModItems.WHITE_BUD_LEAVES)
                .requires(ModItems.AETHER_ROOT_SPORE)
                //TODO : GIMME PEBBLEBERRIES !
                .unlockedBy(getHasName(ModItems.WHITE_BUD_LEAVES), has(ModItems.WHITE_BUD_LEAVES))
                .unlockedBy(getHasName(ModItems.AETHER_ROOT_SPORE), has(ModItems.AETHER_ROOT_SPORE))
                .save(output);

        // Building Blocks
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.WHITE_BUD_LEAVES, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_BUD_LEAVES_BLOCK);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COARSE_CACTUS_PLANKS, 2)
                .define('#', ModBlocks.COARSE_CACTUS)
                .pattern("##")
                .pattern("##")
                .group(null)
                .unlockedBy(getHasName(ModBlocks.COARSE_CACTUS), has(ModBlocks.COARSE_CACTUS))
                .save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getSimpleRecipeName(ModBlocks.COARSE_CACTUS_PLANKS)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AETHER_ROOT)
                .define('#', ModItems.AETHER_ROOT_SPORE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(null)
                .unlockedBy(getHasName(ModItems.AETHER_ROOT_SPORE), has(ModItems.AETHER_ROOT_SPORE))
                .save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getSimpleRecipeName(ModBlocks.AETHER_ROOT)));

        // Block Variants
        slabAndStair(output, ModBlocks.IRON_BRICKS, ModBlocks.IRON_BRICKS_SLAB, ModBlocks.IRON_BRICKS_STAIRS);
        slabAndStair(output, ModBlocks.SILVERBLANC_STONE, ModBlocks.SILVERBLANC_STONE_SLAB, ModBlocks.SILVERBLANC_STONE_STAIRS);
        slabAndStair(output, ModBlocks.SILVERBLANC_COBBLESTONE, ModBlocks.SILVERBLANC_COBBLESTONE_SLAB, ModBlocks.SILVERBLANC_COBBLESTONE_STAIRS);
        slabAndStair(output, ModBlocks.SILVERBLANC_STONE_BRICKS, ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB, ModBlocks.SILVERBLANC_STONE_BRICKS_STAIRS);
        wall(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_STONE_WALL, ModBlocks.SILVERBLANC_STONE);
        wall(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_COBBLESTONE_WALL, ModBlocks.SILVERBLANC_COBBLESTONE);
        wall(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_STONE_BRICKS_WALL, ModBlocks.SILVERBLANC_STONE_BRICKS);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_STONE_WALL, ModBlocks.SILVERBLANC_STONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_COBBLESTONE_WALL, ModBlocks.SILVERBLANC_COBBLESTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVERBLANC_STONE_BRICKS_WALL, ModBlocks.SILVERBLANC_STONE_BRICKS);
        slab(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COARSE_CACTUS_SLAB, ModBlocks.COARSE_CACTUS_PLANKS);
        stairBuilder(ModBlocks.COARSE_CACTUS_STAIRS, Ingredient.of(ModBlocks.COARSE_CACTUS_PLANKS)).unlockedBy(getHasName(ModBlocks.COARSE_CACTUS_PLANKS), has(ModBlocks.COARSE_CACTUS_PLANKS)).save(output);
        doorBuilder(ModBlocks.COARSE_CACTUS_DOOR, Ingredient.of(ModBlocks.COARSE_CACTUS_PLANKS)).unlockedBy(getHasName(ModBlocks.COARSE_CACTUS_PLANKS), has(ModBlocks.COARSE_CACTUS_PLANKS)).save(output);
        trapdoorBuilder(ModBlocks.COARSE_CACTUS_TRAPDOOR, Ingredient.of(ModBlocks.COARSE_CACTUS_PLANKS)).unlockedBy(getHasName(ModBlocks.COARSE_CACTUS_PLANKS), has(ModBlocks.COARSE_CACTUS_PLANKS)).save(output);
        doorBuilder(ModBlocks.FORTRESS_DOOR, Ingredient.of(ModBlocks.FORTRESS_BLOCK)).unlockedBy(getHasName(ModBlocks.FORTRESS_BLOCK), has(ModBlocks.FORTRESS_BLOCK)).save(output);

        // Metal Basics
        createIngotRecipes(output, ModItems.ORGANIC_PLASTIC_NUGGET, ModItems.ORGANIC_PLASTIC, ModBlocks.ORGANIC_PLASTIC_BLOCK);
        createIngotRecipes(output, ModItems.NICKEL_NUGGET, ModItems.NICKEL_INGOT, ModBlocks.NICKEL_BLOCK);
        createIngotRecipes(output, ModItems.NICKELSTEEL_PLASTIC_NUGGET, ModItems.NICKELSTEEL_PLASTIC, ModBlocks.NICKELSTEEL_PLASTIC_BLOCK);
        createIngotRecipes(output, ModItems.THULIUM_188_NUGGET, ModItems.THULIUM_188_INGOT, ModBlocks.THULIUM_188_BLOCK);
        createIngotRecipes(output, ModItems.ULTRALLOY_NUGGET, ModItems.ULTRALLOY_INGOT, ModBlocks.ULTRALLOY_BLOCK);
        createIngotRecipes(output, ModItems.LEMURIUM_NUGGET, ModItems.LEMURIUM_INGOT, ModBlocks.LEMURIUM_BLOCK);
    }

    protected static void createIngotRecipes(RecipeOutput output,
                                             DeferredItem<Item> nugget,
                                             DeferredItem<Item> ingot,
                                             DeferredBlock<Block> block) {
        Item n = nugget.get();
        Item i = ingot.get();
        Block b = block.get();
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, i, 9).requires(b).group(null).unlockedBy(getHasName(b), has(b)).save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getConversionRecipeName(i, b)));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, b).define('#', i).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(i), has(i)).save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getSimpleRecipeName(b)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, n, 9).requires(i).group(null).unlockedBy(getHasName(i), has(i)).save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getConversionRecipeName(n, i)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, i).define('#', n).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(n), has(n)).save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getConversionRecipeName(i, n)));
    }

    protected static void slabAndStair(RecipeOutput output, DeferredBlock<Block> block, DeferredBlock<Block> slab, DeferredBlock<Block> stair) {
        slab(output, RecipeCategory.BUILDING_BLOCKS, slab, block);
        stairBuilder(stair, Ingredient.of(block)).unlockedBy(getHasName(block), has(block)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, slab, block, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, stair, block);
    }

}
