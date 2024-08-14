package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootProvider{
    public ModLootProvider() {
    }
        public static LootTableProvider add(PackOutput output, CompletableFuture<HolderLookup.Provider> lookUpProvider) {
        return new LootTableProvider(
                output,
                Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(ChestLootPrvider::new, LootContextParamSets.CHEST)
                ),
                lookUpProvider
                );
    }
    public record ChestLootPrvider(HolderLookup.Provider provider) implements LootTableSubProvider {

        private static ResourceLocation getRL(String name) {
            return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name);
        }

        private static ResourceKey<LootTable> getChestKey(String name) {
            return ResourceKey.create(Registries.LOOT_TABLE, getRL("chests/" + name));
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(getChestKey("frost_bear_loot"), LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(3))
                                    .add(LootItem.lootTableItem(ModItems.LEMON))
                                    .setRolls(UniformGenerator.between(1, 3))
                    )
            );
        }
    }
}
