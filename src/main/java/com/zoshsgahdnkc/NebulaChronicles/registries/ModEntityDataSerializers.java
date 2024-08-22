package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModEntityDataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, NebulaChronicles.MODID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<VerdhelmBeetleEntity.AnimState>> VERDHELM_ANIM_STATE = SERIALIZERS.register("verdhelm_anim_state", () -> EntityDataSerializer.forValueType(VerdhelmBeetleEntity.AnimState.STREAM_CODEC));
}
