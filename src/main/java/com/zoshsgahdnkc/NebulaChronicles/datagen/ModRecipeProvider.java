package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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


}
