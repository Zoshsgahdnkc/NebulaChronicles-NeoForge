package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.block.blockentity.NickelsteelPlasticContainerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NickelsteelPlasticContainerBlockEntity>> NICKELSTEEL_PLASTIC_CONTAINER =
            BLOCK_ENTITIES.register("nickelsteel_plastic_container",
                    ()->BlockEntityType.Builder.of(NickelsteelPlasticContainerBlockEntity::new,
                            ModBlocks.NICKELSTEEL_PLASTIC_CONTAINER.get()).build(null));
}
