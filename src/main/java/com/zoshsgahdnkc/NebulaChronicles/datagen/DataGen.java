package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.datagen.tags.ModBlockTags;
import com.zoshsgahdnkc.NebulaChronicles.datagen.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NebulaChronicles.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();

        BlockTagsProvider blockTagsProvider = new ModBlockTags(output, lookupProvider, helper);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModItemTags(output,lookupProvider, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(true, new ModRecipeProvider(output, lookupProvider));
        generator.addProvider(true, ModLootProvider.add(output, lookupProvider));
        generator.addProvider(true, new ModBlockStateProvider(output, helper));
        generator.addProvider(true, new ModItemModelProvider(output, helper));
        generator.addProvider(e.includeServer(), new DatapackRegistries(output, lookupProvider));
    }
}
