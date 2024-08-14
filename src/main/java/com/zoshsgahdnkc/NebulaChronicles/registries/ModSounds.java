package com.zoshsgahdnkc.NebulaChronicles.registries;


import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, NebulaChronicles.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> AETHER_ROOT_SPORE_SPREAD = register("aether_root_spore_spread");
    public static final DeferredHolder<SoundEvent, SoundEvent> AETHER_ROOT_SPORE_INFLATE = register("aether_root_spore_inflate");
    public static final DeferredHolder<SoundEvent, SoundEvent> AETHER_ROOT_SPORE_FISSION = register("aether_root_spore_fission");

    public static final DeferredHolder<SoundEvent, SoundEvent> TECH_BLOCK_HIT = register("tech_block_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> TECH_BLOCK_BREAK = register("tech_block_break");
    public static final DeferredHolder<SoundEvent, SoundEvent> TECH_BLOCK_PLACE = register("tech_block_place");
    public static final DeferredHolder<SoundEvent, SoundEvent> TECH_BLOCK_STEP = register("tech_block_step");
    public static final DeferredHolder<SoundEvent, SoundEvent> TECH_BLOCK_FALL = register("tech_block_fall");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_HALFWAY = register("music_disc_halfway");

    public static final SoundType TECH_BLOCK = new DeferredSoundType(
            0.5f,1f,
            TECH_BLOCK_BREAK,
            TECH_BLOCK_STEP,
            TECH_BLOCK_PLACE,
            TECH_BLOCK_HIT,
            TECH_BLOCK_FALL);

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name){
        return SOUND_EVENT.register(
                name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, name))
        );
    }
}
