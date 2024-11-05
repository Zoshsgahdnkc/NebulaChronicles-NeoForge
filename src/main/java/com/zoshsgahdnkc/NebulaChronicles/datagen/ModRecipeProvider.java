package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.datagen.tags.ModItemTags;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;
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
        // Main
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AETHER_ROOT)
                .define('#', ModItems.AETHER_ROOT_SPORE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(null)
                .unlockedBy(getHasName(ModItems.AETHER_ROOT_SPORE), has(ModItems.AETHER_ROOT_SPORE))
                .save(output, ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, getSimpleRecipeName(ModBlocks.AETHER_ROOT)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VACUUM_TUBE)
                .define('#', ModItems.NICKELSTEEL_PLASTIC)
                .define('0', Tags.Items.GLASS_BLOCKS)
                .pattern(" 0#")
                .pattern("0 0")
                .pattern("#0 ")
                .group(null)
                .unlockedBy(getHasName(ModItems.NICKELSTEEL_PLASTIC), has(ModItems.NICKELSTEEL_PLASTIC))
                .save(output);
        stonecutterResultFromBase(output, RecipeCategory.MISC, ModItems.CALCITE_CASING, Blocks.CALCITE);

        // Astral Kitchen
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_BUD_STEW)
                .requires(Items.BOWL)
                .requires(ModItems.WHITE_BUD_LEAVES)
                .requires(ModItems.WHITE_BUD_LEAVES)
                .requires(ModItems.AETHER_ROOT_SPORE)
                .requires(ModItems.PEBBLE_BERRIES)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SILVERBLANC_STONE_BRICKS)
                .define('#', ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB)
                .pattern("#")
                .pattern("#")
                .group(null)
                .unlockedBy(getHasName(ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB), has(ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB))
                .save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SILVERBLANC_STONE_BRICKS, ModBlocks.SILVERBLANC_STONE_BRICKS);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FORTRESS_BLOCK, 24)
                .define('N', ModItems.NICKELSTEEL_PLASTIC)
                .define('I', Items.IRON_INGOT)
                .pattern("NI")
                .pattern("IN")
                .group(null)
                .unlockedBy(getHasName(ModItems.NICKELSTEEL_PLASTIC), has(ModItems.NICKELSTEEL_PLASTIC))
                .save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FORTRESS_WALL, ModBlocks.FORTRESS_BLOCK);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FORTRESS_WALL_LIGHT_UNLIT, ModBlocks.FORTRESS_BLOCK);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FORTRESS_DOOR, ModBlocks.FORTRESS_BLOCK);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FORTRESS_WALL_LIGHT)
                .requires(ModBlocks.FORTRESS_WALL_LIGHT_UNLIT)
                .requires(Tags.Items.DUSTS_GLOWSTONE)
                .unlockedBy(getHasName(ModBlocks.FORTRESS_WALL_LIGHT_UNLIT), has(ModBlocks.FORTRESS_WALL_LIGHT_UNLIT))
                .save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_BRICKS, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_BRICKS_SLAB, Items.IRON_INGOT, 16);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_BRICKS_STAIRS, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_COLLAGE, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BUNKER_BRICKS, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_TILE, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_TILE, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SIMPLE_VAULT_STAIRS, Items.IRON_INGOT, 8);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.THICK_VAULT_STAIRS, Items.IRON_INGOT, 4);



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

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemTags.SMELTS_TO_NICKEL), RecipeCategory.MISC, ModItems.NICKEL_INGOT, 0.7f, 100).unlockedBy("has_smelts_to_nickel", has(ModItemTags.SMELTS_TO_NICKEL)).save(output, getBlastingRecipeName(ModItems.NICKEL_INGOT));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemTags.SMELTS_TO_NICKEL), RecipeCategory.MISC, ModItems.NICKEL_INGOT, 0.7f, 200).unlockedBy("has_smelts_to_nickel", has(ModItemTags.SMELTS_TO_NICKEL)).save(output, getSmeltingRecipeName(ModItems.NICKEL_INGOT));
        smeltingOrBlasting(output, RecipeCategory.MISC, ModBlocks.SILVERBLANC_COPPER_ORE, Items.COPPER_INGOT, 0.7f, 100);
        smeltingOrBlasting(output, RecipeCategory.MISC, ModBlocks.SILVERBLANC_IRON_ORE, Items.IRON_INGOT, 0.7f, 100);
        smeltingOrBlasting(output, RecipeCategory.MISC, ModBlocks.SILVERBLANC_GOLD_ORE, Items.GOLD_INGOT, 1f, 100);
        smeltingOrBlasting(output, RecipeCategory.MISC, ModBlocks.SILVERBLANC_LAPIS_ORE, Items.LAPIS_LAZULI, 0.2f, 100);
        smeltingOrBlasting(output, RecipeCategory.MISC, ModBlocks.SILVERBLANC_DIAMOND_ORE, Items.DIAMOND, 1f, 100);
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

    protected static void smeltingOrBlasting(RecipeOutput output, RecipeCategory category, ItemLike from, ItemLike to, float exp, int blastingTime) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(from), category, to, exp, blastingTime).unlockedBy(getHasName(from), has(from)).save(output, getBlastingRecipeName(to));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(from), category, to, exp, blastingTime * 2).unlockedBy(getHasName(from), has(from)).save(output, getSmeltingRecipeName(to));
    }

}
