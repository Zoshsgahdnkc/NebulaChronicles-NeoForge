package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.MobSensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntitySensors {
    public static final DeferredRegister<SensorType<?>> SENSORS = DeferredRegister.create(BuiltInRegistries.SENSOR_TYPE, NebulaChronicles.MODID);

    public static final DeferredHolder<SensorType<?>, SensorType<MobSensor<VerdhelmBeetleEntity>>> VERDHELM_SCARED_DETECTED = register(
            "verdhelm_scare_detected", () ->  new MobSensor<>(5, VerdhelmBeetleEntity::isScaredBy, VerdhelmBeetleEntity::canStayRolledUp, MemoryModuleType.DANGER_DETECTED_RECENTLY, 80));

    public static <U extends Sensor<?>> DeferredHolder<SensorType<?>, SensorType<U>> register(String name, Supplier<U> sensorSupplier) {
        return SENSORS.register(name, () -> new SensorType<>(sensorSupplier));
    }
}
