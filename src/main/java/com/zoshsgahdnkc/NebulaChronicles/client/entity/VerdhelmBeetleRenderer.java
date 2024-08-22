package com.zoshsgahdnkc.NebulaChronicles.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModAttributes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VerdhelmBeetleRenderer extends MobRenderer<VerdhelmBeetleEntity, VerdhelmBeetleModel<VerdhelmBeetleEntity>> {
    public VerdhelmBeetleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new VerdhelmBeetleModel<>(pContext.bakeLayer(VerdhelmBeetleModel.LAYER_LOCATION)), 0.36f);
    }

    @Override
    public ResourceLocation getTextureLocation(VerdhelmBeetleEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "textures/entity/verdhelm_beetle.png");
    }

    @Override
    public void render(VerdhelmBeetleEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        float size = (float) (0.01f * pEntity.getAttribute(ModAttributes.VERDHELM_SIZE).getValue());
        pPoseStack.scale(size, size, size);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
