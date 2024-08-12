package com.zoshsgahdnkc.NebulaChronicles.datagen;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModBiomes;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModCF;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModDimensions;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModPF;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModJukeboxSongs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DatapackRegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModCF::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPF::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);

    public DatapackRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> lookUpProvider) {
        super(output, lookUpProvider, BUILDER, Set.of(NebulaChronicles.MODID));
    }
}
