package com.zoshsgahdnkc.NebulaChronicles.event;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.client.dimension.ModDimensionSpecialEffect;
import com.zoshsgahdnkc.NebulaChronicles.client.entity.*;
import com.zoshsgahdnkc.NebulaChronicles.particle.AetherSporeParticle;
import com.zoshsgahdnkc.NebulaChronicles.particle.MossClumpsParticle;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModParticles;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.checkerframework.checker.units.qual.N;

@EventBusSubscriber(modid = NebulaChronicles.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        // There are multiple ways to register providers, all differing in the functional type they provide in the
        // second parameter. For example, #registerSpriteSet represents a Function<SpriteSet, ParticleProvider<?>>:
        event.registerSpriteSet(ModParticles.AETHER_SPORE.get(), AetherSporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.MOSS_CLUMPS.get(), MossClumpsParticle.Provider::new);
        // Other methods include #registerSprite, which is essentially a Supplier<TextureSheetParticle>,
        // and #registerSpecial, which maps to a Supplier<Particle>. See the source code of the event for further info.
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.VERDHELM_BEETLE.get(), VerdhelmBeetleRenderer::new);
        event.registerEntityRenderer(ModEntities.SPIKED_VERDHELM_BEETLE.get(), SpikedVerdhelmBeetleRenderer::new);
        event.registerEntityRenderer(ModEntities.SHOOTING_ROCK.get(), ShootingRockRenderer::new);
        event.registerEntityRenderer(ModEntities.VERDHELM_LARVAE_DUMMY.get(), NoopRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ShootingRockModel.LAYER_LOCATION, ShootingRockModel::createBodyLayer);
        event.registerLayerDefinition(VerdhelmBeetleModel.LAYER_LOCATION, VerdhelmBeetleModel::createBodyLayer);
        event.registerLayerDefinition(SpikedVerdhelmBeetleModel.LAYER_LOCATION, SpikedVerdhelmBeetleModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "silverblanc"),
                new ModDimensionSpecialEffect.SilverblancEffect());

    }
}
