package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.CaveAmethystFeature;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.StoneSlabFeature;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.feature.configurations.SimpleReplacementConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, NebulaChronicles.MODID);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CAVE_AMETHYST = FEATURES.register("cave_amethyst", () -> new CaveAmethystFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<SimpleReplacementConfiguration>> STONE_SLAB = FEATURES.register("stone_slab", () -> new StoneSlabFeature(SimpleReplacementConfiguration.CODEC));

}
