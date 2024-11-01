package com.zoshsgahdnkc.NebulaChronicles.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.SpikedVerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModAttributes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpikedVerdhelmBeetleRenderer extends MobRenderer<SpikedVerdhelmBeetleEntity, SpikedVerdhelmBeetleModel<SpikedVerdhelmBeetleEntity>> {
    public SpikedVerdhelmBeetleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SpikedVerdhelmBeetleModel<>(pContext.bakeLayer(SpikedVerdhelmBeetleModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SpikedVerdhelmBeetleEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "textures/entity/spiked_verdhelm_beetle.png");
    }

    @Override
    public void render(SpikedVerdhelmBeetleEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.45f, 1.45f, 1.45f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
