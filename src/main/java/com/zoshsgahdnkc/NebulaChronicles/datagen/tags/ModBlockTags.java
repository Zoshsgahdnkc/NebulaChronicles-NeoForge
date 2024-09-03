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

    private static ResourceLocation getRL(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
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


        tag(BlockTags.CLIMBABLE)
                .replace(false)
                .add(ModBlocks.AETHER_ROOT_HAIR.get())
                .addTag(METAL_SCAFFOLDING);
    }

    private ResourceLocation rl(DeferredBlock<Block> block) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, block.get().toString());
    }
}
