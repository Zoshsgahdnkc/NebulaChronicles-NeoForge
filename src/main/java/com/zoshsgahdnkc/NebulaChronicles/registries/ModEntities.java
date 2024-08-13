package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.Entity.AetherRootSporeEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NebulaChronicles.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<AetherRootSporeEntity>> AETHER_ROOT_SPORE =
            ENTITIES.register("aether_root_spore", () -> EntityType.Builder
                    .<AetherRootSporeEntity>of(AetherRootSporeEntity::new, MobCategory.MISC)
                    .build("aether_root_spore"));
}
