package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.google.common.collect.ImmutableSet;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModBlocks;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLoot extends BlockLootSubProvider {

    public ModBlockLoot(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }
    private static final ImmutableSet<DeferredHolder<Block, Block>> IGNORES = ImmutableSet.of(
            ModBlocks.AETHER_ROOT,
            ModBlocks.AETHER_ROOT_HAIR,
            ModBlocks.FORTRESS_DOOR,
            ModBlocks.COARSE_CACTUS_DOOR,
            ModBlocks.CARGO_BOX,
            ModBlocks.WHITE_BUD,

            ModBlocks.SILVERBLANC_STONE,
            ModBlocks.MOSS_SILVERBLANC_STONE,
            ModBlocks.MOSS_CRYOSOL,

            ModBlocks.NICKEL_ORE,
            ModBlocks.DEEPSLATE_NICKEL_ORE,
            ModBlocks.SILVERBLANC_COPPER_ORE,
            ModBlocks.SILVERBLANC_IRON_ORE,
            ModBlocks.SILVERBLANC_NICKEL_ORE,
            ModBlocks.SILVERBLANC_GOLD_ORE,
            ModBlocks.SILVERBLANC_LAPIS_ORE,
            ModBlocks.SILVERBLANC_DIAMOND_ORE
    );

    @Override
    protected void generate() {
        ModBlocks.BLOCKS.getEntries().stream().filter(e -> !IGNORES.contains(e)).map(DeferredHolder::get).forEach(this::dropSelf);

        add(ModBlocks.FORTRESS_DOOR.get(), (this::createDoorTable));
        add(ModBlocks.COARSE_CACTUS_DOOR.get(), (this::createDoorTable));
        add(ModBlocks.COARSE_CACTUS_TRAPDOOR.get(), (this::createDoorTable));
        add(ModBlocks.WHITE_BUD.get(), createSilkTouchDispatchTable(ModBlocks.WHITE_BUD.get(),
                LootItem.lootTableItem(ModItems.WHITE_BUD_LEAVES)
                        .apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(2, 0.3f)))));
        add(ModBlocks.SILVERBLANC_STONE.get(),(block) ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.SILVERBLANC_COBBLESTONE.get()));
        add(ModBlocks.MOSS_SILVERBLANC_STONE.get(),(block) ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.SILVERBLANC_COBBLESTONE.get()));
        add(ModBlocks.MOSS_CRYOSOL.get(),(block) ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.CRYOSOL.get()));
        add(ModBlocks.CARGO_BOX.get(), new LootTable.Builder().withPool(this.applyExplosionCondition(ModBlocks.CARGO_BOX.get(),
                LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(2, 0.5F))
                        .add(LootItem.lootTableItem(ModItems.LEMON)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
        add(ModBlocks.AETHER_ROOT.get(), new LootTable.Builder()
                .withPool(
                        this.applyExplosionCondition(ModBlocks.AETHER_ROOT.get(),
                                LootPool.lootPool().when(this.doesNotHaveSilkTouch())
                                        .setRolls(BinomialDistributionGenerator.binomial(5, 0.6f))
                                        .add(LootItem.lootTableItem(ModItems.AETHER_ROOT_SPORE))))
                .withPool(
                        this.applyExplosionCondition(ModBlocks.AETHER_ROOT.get(),
                                LootPool.lootPool().when(this.hasSilkTouch())
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(LootItem.lootTableItem(ModBlocks.AETHER_ROOT)))
                ));
        add(ModBlocks.AETHER_ROOT_HAIR.get(), new LootTable.Builder().withPool(this.applyExplosionCondition(ModBlocks.AETHER_ROOT.get(),
                LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.4f))
                        .add(LootItem.lootTableItem(ModBlocks.AETHER_ROOT_HAIR)).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))));
        add(ModBlocks.NICKEL_ORE.get(), createOreDrop(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        add(ModBlocks.SILVERBLANC_NICKEL_ORE.get(), createOreDrop(ModBlocks.SILVERBLANC_NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        add(ModBlocks.SILVERBLANC_COPPER_ORE.get(), createOreDrop(ModBlocks.SILVERBLANC_COPPER_ORE.get(), Items.RAW_COPPER));
        add(ModBlocks.SILVERBLANC_IRON_ORE.get(), createOreDrop(ModBlocks.SILVERBLANC_IRON_ORE.get(), Items.RAW_IRON));
        add(ModBlocks.SILVERBLANC_GOLD_ORE.get(), createOreDrop(ModBlocks.SILVERBLANC_GOLD_ORE.get(), Items.RAW_GOLD));
        add(ModBlocks.SILVERBLANC_DIAMOND_ORE.get(), createOreDrop(ModBlocks.SILVERBLANC_DIAMOND_ORE.get(), Items.DIAMOND));
        add(ModBlocks.SILVERBLANC_LAPIS_ORE.get(), createLapisOreDrops(ModBlocks.SILVERBLANC_LAPIS_ORE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
    }
}
