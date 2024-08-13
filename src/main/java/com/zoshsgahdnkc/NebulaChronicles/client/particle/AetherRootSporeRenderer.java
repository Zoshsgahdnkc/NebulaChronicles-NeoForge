package com.zoshsgahdnkc.NebulaChronicles.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zoshsgahdnkc.NebulaChronicles.Entity.AetherRootSporeEntity;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;

public class AetherRootSporeRenderer extends ThrownItemRenderer<AetherRootSporeEntity> {
    public AetherRootSporeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, 1f, false);
        context = pContext;
    }
    public EntityRendererProvider.Context context;

    @Override
    public void render(AetherRootSporeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(pEntity) < 12.25)) {
            pPoseStack.pushPose();
            pPoseStack.scale(1f, 1f, 1f);
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            context.getItemRenderer()
                    .renderStatic(
                            ModItems.AETHER_PARTICLE.toStack(),
                            ItemDisplayContext.GROUND,
                            pPackedLight,
                            OverlayTexture.NO_OVERLAY,
                            pPoseStack,
                            pBuffer,
                            pEntity.level(),
                            pEntity.getId()
                    );
            pPoseStack.popPose();
        }
    }
}
