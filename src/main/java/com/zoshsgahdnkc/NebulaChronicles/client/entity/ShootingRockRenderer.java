package com.zoshsgahdnkc.NebulaChronicles.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.ShootingRockEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ShootingRockRenderer extends EntityRenderer<ShootingRockEntity> {
    private final ShootingRockModel<ShootingRockEntity> model;
    public ShootingRockRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new ShootingRockModel<>(pContext.bakeLayer(ShootingRockModel.LAYER_LOCATION));
    }

    @Override
    public void render(ShootingRockEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
        pPoseStack.pushPose();
        pPoseStack.translate(0, -2.5f, 0);
        pPoseStack.scale(2f, 2f, 2f);
        this.model.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ShootingRockEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "textures/entity/shooting_rock.png");
    }
}
