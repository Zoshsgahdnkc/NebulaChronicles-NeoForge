package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NebulaChronicles.MODID, existingFileHelper);
    }
    public static final TagKey<Block> BASE_STONE = TagKey.create(Registries.BLOCK, getRL("base_stone"));
    public static final TagKey<Block> SB_ORE_REPLACEABLE = TagKey.create(Registries.BLOCK, getRL("sb_ore_replaceable"));

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
    }
}
