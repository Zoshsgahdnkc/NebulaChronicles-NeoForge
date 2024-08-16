package com.zoshsgahdnkc.NebulaChronicles.event;

import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = NebulaChronicles.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void putEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.VERDHELM_BEETLE.get(), VerdhelmBeetleEntity.createAttributes().build());
    }

//    @SubscribeEvent
//    public static void registerSpawn(RegisterSpawnPlacementsEvent event) {
//        event.register(ModEntities.VERDHELM_BEETLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, VerdhelmBeetleEntity::checkMobSpawnRules);
//    }
}
