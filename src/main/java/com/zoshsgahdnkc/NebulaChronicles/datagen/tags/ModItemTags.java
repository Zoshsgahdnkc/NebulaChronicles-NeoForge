package com.zoshsgahdnkc.NebulaChronicles.datagen.tags;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
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
import org.apache.http.cookie.SM;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(PackOutput output,
                       CompletableFuture<HolderLookup.Provider> lookupProvider,
                       CompletableFuture<TagLookup<Block>> blockTagProvider,
                       ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, NebulaChronicles.MODID, existingFileHelper);
    }
    public static final TagKey<Item> METAL_SCAFFOLDING = TagKey.create(Registries.ITEM, getRL("metal_scaffolding"));
    public static final TagKey<Item> SMELTS_TO_NICKEL = TagKey.create(Registries.ITEM, getRL("smelts_to_nickel"));

    private static ResourceLocation getRL(String name) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(METAL_SCAFFOLDING)
                .add(ModBlocks.IRON_SCAFFOLDING.asItem())
                .add(ModBlocks.NICKELSTEEL_PLASTIC_SCAFFOLDING.asItem());
        tag(SMELTS_TO_NICKEL)
                .add(ModItems.RAW_NICKEL.get())
                .add(ModBlocks.NICKEL_ORE.asItem())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.asItem())
                .add(ModBlocks.SILVERBLANC_NICKEL_ORE.asItem());
    }

    private ResourceLocation rl(DeferredBlock<Block> block) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, block.get().toString());
    }
}
