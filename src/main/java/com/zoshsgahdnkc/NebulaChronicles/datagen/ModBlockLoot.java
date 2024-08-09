package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.google.common.collect.ImmutableSet;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLoot extends BlockLootSubProvider {

    public ModBlockLoot(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }
    private static final ImmutableSet<DeferredHolder<Block, Block>> IGNORES = ImmutableSet.of(
            ModBlocks.FORTRESS_DOOR,
            ModBlocks.COARSE_CACTUS_DOOR,
            ModBlocks.CARGO_BOX,
            ModBlocks.MOSS_SILVERBLANC_STONE
    );

    @Override
    protected void generate() {
        ModBlocks.BLOCKS.getEntries().stream().filter(e -> !IGNORES.contains(e)).map(DeferredHolder::get).forEach(this::dropSelf);

        add(ModBlocks.FORTRESS_DOOR.get(), (this::createDoorTable));
        add(ModBlocks.COARSE_CACTUS_DOOR.get(), (this::createDoorTable));
        add(ModBlocks.COARSE_CACTUS_TRAPDOOR.get(), (this::createDoorTable));
        add(ModBlocks.MOSS_SILVERBLANC_STONE.get(),(block) ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.SILVERBLANC_STONE.get()));
        add(ModBlocks.CARGO_BOX.get(), new LootTable.Builder().withPool(this.applyExplosionCondition(ModBlocks.CARGO_BOX.get(),
                LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(2, 0.5F))
                        .add(LootItem.lootTableItem(ModItems.LEMON.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
    }
}
