package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> c) {
        createIngotRecipes(c, ModItems.ORGANIC_PLASTIC_NUGGET, ModItems.ORGANIC_PLASTIC, ModBlocks.ORGANIC_PLASTIC_BLOCK);
        createIngotRecipes(c, ModItems.NICKEL_NUGGET, ModItems.NICKEL_INGOT, ModBlocks.NICKEL_BLOCK);
        createIngotRecipes(c, ModItems.NICKELSTEEL_PLASTIC_NUGGET, ModItems.NICKELSTEEL_PLASTIC, ModBlocks.NICKELSTEEL_PLASTIC_BLOCK);
        createIngotRecipes(c, ModItems.THULIUM_188_NUGGET, ModItems.THULIUM_188_INGOT, ModBlocks.THULIUM_188_BLOCK);
        createIngotRecipes(c, ModItems.ULTRALLOY_NUGGET, ModItems.ULTRALLOY_INGOT, ModBlocks.ULTRALLOY_BLOCK);
        createIngotRecipes(c, ModItems.LEMURIUM_NUGGET, ModItems.LEMURIUM_INGOT, ModBlocks.LEMURIUM_BLOCK);
    }

    protected static void createIngotRecipes(Consumer<FinishedRecipe> c, RegistryObject<Item> nugget, RegistryObject<Item> ingot, RegistryObject<Block> block) {
        Item n = nugget.get();
        Item i = ingot.get();
        Block b = block.get();
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, i, 9).requires(b).group(null).unlockedBy(getHasName(b), has(b)).save(c, new ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, b)));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, b).define('#', i).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(i), has(i)).save(c, new ResourceLocation(NebulaChronicles.MODID, getSimpleRecipeName(b)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, n, 9).requires(i).group(null).unlockedBy(getHasName(i), has(i)).save(c, new ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(n, i)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, i).define('#', n).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(n), has(n)).save(c, new ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, n)));
    }
}
