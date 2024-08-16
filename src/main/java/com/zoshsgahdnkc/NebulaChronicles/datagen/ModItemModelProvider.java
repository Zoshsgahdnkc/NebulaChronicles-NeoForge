package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.google.common.collect.ImmutableSet;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NebulaChronicles.MODID, existingFileHelper);
    }

    public static final ImmutableSet<DeferredHolder<Item, ? extends Item>> IGNORES = ImmutableSet.of(
    );
    public static final ImmutableSet<DeferredHolder<Item, ? extends Item>> SPAWN_EGGS = ImmutableSet.of(
            ModItems.VERDHELM_BEETLE_SPAWN_EGG
    );

    private static boolean predicateItemEntry(DeferredHolder<Item, ? extends Item> entry) {
        return !(entry.get() instanceof BlockItem) &&
                !(IGNORES.contains(entry)) &&
                !(SPAWN_EGGS.contains(entry));
    }

    @Override
    protected void registerModels() {
        var itemStream = ModItems.ITEMS.getEntries().stream();
        itemStream.filter(ModItemModelProvider::predicateItemEntry).map(DeferredHolder::get).forEach(this::basicItem);
        for (var item: SPAWN_EGGS) {
            spawnEgg(item);
        }

        blockWithTexture(ModBlocks.FORTRESS_DOOR);
        blockWithTexture(ModBlocks.COARSE_CACTUS_DOOR);
        blockWithTexture(ModBlocks.SOLAR_PANEL);
        blockWithTexture(ModBlocks.REDSTONE_POWER_PANEL);
        blockWithTexture(ModBlocks.TACHYON_PROJECTION_PANEL);
        blockWithTexture(ModBlocks.DARK_MATTER_RENDER_PANEL);
        blockWithTexture(ModBlocks.WHITE_BUD);
        blockWithTexture(ModBlocks.COARSE_CACTUS);

    }

    private ItemModelBuilder blockWithTexture(DeferredBlock<Block> block) {
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID,ITEM_FOLDER + "/" + block.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(DeferredHolder<Item, ? extends Item> spawnEgg) {
        return withExistingParent(spawnEgg.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
