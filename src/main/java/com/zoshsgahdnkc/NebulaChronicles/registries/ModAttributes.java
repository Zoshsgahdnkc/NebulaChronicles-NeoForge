package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, NebulaChronicles.MODID);

    public static final DeferredHolder<Attribute, Attribute> VERDHELM_SIZE = ATTRIBUTES.register("verdhelm_size", () -> new RangedAttribute("nch.verdhelm_size", 100, 1, 200).setSyncable(true));
}
