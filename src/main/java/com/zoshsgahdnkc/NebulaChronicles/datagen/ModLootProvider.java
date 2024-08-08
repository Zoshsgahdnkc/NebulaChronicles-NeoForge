package com.zoshsgahdnkc.NebulaChronicles.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootProvider {
    public static LootTableProvider add(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(
                ModBlockLoot::new, LootContextParamSets.BLOCK
        )));
    }
}
