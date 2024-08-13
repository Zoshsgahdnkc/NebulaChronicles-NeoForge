package com.zoshsgahdnkc.NebulaChronicles;

import com.mojang.logging.LogUtils;
import com.zoshsgahdnkc.NebulaChronicles.block.blockentity.ModBlockEntities;
import com.zoshsgahdnkc.NebulaChronicles.registries.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NebulaChronicles.MODID)
public class NebulaChronicles {
    public static final String MODID = "nch";
    private static final Logger LOGGER = LogUtils.getLogger();
    public NebulaChronicles(IEventBus modEventBus, Dist dist, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModSounds.SOUND_EVENT.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModTabs.TABS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);

//        NeoForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Greetings from the stars.");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.AETHER_ROOT_SPORE.get(), ThrownItemRenderer::new);
        }
    }
}
