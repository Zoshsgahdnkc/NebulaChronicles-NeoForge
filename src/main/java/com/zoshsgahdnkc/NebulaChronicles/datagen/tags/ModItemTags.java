package com.zoshsgahdnkc.NebulaChronicles.datagen.tags;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(PackOutput output,
                       CompletableFuture<HolderLookup.Provider> lookupProvider,
                       CompletableFuture<TagLookup<Block>> blockTagProvider,
                       ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, NebulaChronicles.MODID, existingFileHelper);
    }
    public static final TagKey<Item> METAL_SCAFFOLDING = TagKey.create(Registries.ITEM, getRL("metal_scaffolding"));

    private static ResourceLocation getRL(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(METAL_SCAFFOLDING)
                .add(ModBlocks.IRON_SCAFFOLDING.asItem())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_SCAFFOLDING.asItem());
    }

    private ResourceLocation rl(DeferredBlock<Block> block) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, block.get().toString());
    }
}
