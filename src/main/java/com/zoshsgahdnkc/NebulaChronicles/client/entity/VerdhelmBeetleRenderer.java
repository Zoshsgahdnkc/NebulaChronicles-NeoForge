package com.zoshsgahdnkc.NebulaChronicles.client.entity;

import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
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
}
