package com.zoshsgahdnkc.NebulaChronicles.utils;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> BASE_STONE = create("base_stone");
    public static final TagKey<Block> SB_ORE_REPLACEABLE = create("sb_ore_replaceable");
    private static TagKey<Block> create(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name));
    }
}
