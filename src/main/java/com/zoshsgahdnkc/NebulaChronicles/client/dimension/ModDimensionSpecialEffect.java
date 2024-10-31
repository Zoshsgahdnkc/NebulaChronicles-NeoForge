package com.zoshsgahdnkc.NebulaChronicles.client.dimension;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelTimeAccess;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ModDimensionSpecialEffect {

    public static Vec3 getSkyColor(ClientLevel level, Vec3 pPos, float pPartialTick, float skyColorStrength) {
        float f = level.getTimeOfDay(pPartialTick);
        Vec3 vec3 = pPos.subtract(2.0, 2.0, 2.0).scale(0.25);
        BiomeManager biomemanager = level.getBiomeManager();
        Vec3 vec31 = CubicSampler.gaussianSampleVec3(
                vec3, (x, y, z) -> Vec3.fromRGB24(biomemanager.getNoiseBiomeAtQuart(x, y, z).value().getSkyColor())
        );
        float f1 = Mth.cos(f * (float) (Math.PI * 2)) * 2.0F + 0.5F;
        f1 = Mth.clamp(f1, 0.0F, skyColorStrength);
        float f2 = (float)vec31.x * f1;
        float f3 = (float)vec31.y * f1;
        float f4 = (float)vec31.z * f1;

        int i = level.getSkyFlashTime();
        if (i > 0) {
            float f11 = (float)i - pPartialTick;
            if (f11 > 1.0F) {
                f11 = 1.0F;
            }

            f11 *= 0.45F;
            f2 = f2 * (1.0F - f11) + 0.8F * f11;
            f3 = f3 * (1.0F - f11) + 0.8F * f11;
            f4 = f4 * (1.0F - f11) + 1.0F * f11;
        }

        return new Vec3(f2, f3, f4);
    }
    private static MeshData buildSkyDisc(Tesselator pTesselator, float pY) {
        float f = Math.signum(pY) * 512.0F;
        float f1 = 512.0F;
        BufferBuilder bufferbuilder = pTesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
        bufferbuilder.addVertex(0.0F, pY, 0.0F);

        for (int i = -180; i <= 180; i += 45) {
            bufferbuilder.addVertex(f * Mth.cos((float)i * (float) (Math.PI / 180.0)), pY, 512.0F * Mth.sin((float)i * (float) (Math.PI / 180.0)));
        }

        return bufferbuilder.buildOrThrow();
    }
    private static MeshData drawStars(Tesselator pTesselator) {
        RandomSource randomsource = RandomSource.create(10842L);
        int i = 1500;
        float f = 100.0F;
        BufferBuilder bufferbuilder = pTesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        for (int j = 0; j < 1500; j++) {
            float f1 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f2 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f3 = randomsource.nextFloat() * 2.0F - 1.0F;
            float f4 = 0.15F + randomsource.nextFloat() * 0.1F;
            float f5 = Mth.lengthSquared(f1, f2, f3);
            if (!(f5 <= 0.010000001F) && !(f5 >= 1.0F)) {
                Vector3f vector3f = new Vector3f(f1, f2, f3).normalize(100.0F);
                float f6 = (float)(randomsource.nextDouble() * (float) Math.PI * 2.0);
                Quaternionf quaternionf = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), vector3f).rotateZ(f6);
                bufferbuilder.addVertex(vector3f.add(new Vector3f(f4, -f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(f4, f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(-f4, f4, 0.0F).rotate(quaternionf)));
                bufferbuilder.addVertex(vector3f.add(new Vector3f(-f4, -f4, 0.0F).rotate(quaternionf)));
            }
        }

        return bufferbuilder.buildOrThrow();
    }

    @OnlyIn(Dist.CLIENT)
    public static class SilverblancEffect extends DimensionSpecialEffects {
        private VertexBuffer skyBuffer;
        private VertexBuffer starBuffer;
        private VertexBuffer darkBuffer;
        private static final ResourceLocation OVERWORLD_LOCATION = ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "textures/environment/silverblanc_overworld.png");
        private static final ResourceLocation SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "textures/environment/silverblanc_sun.png");
        public SilverblancEffect() {
            super(Float.NaN,
                    true,
                    SkyType.NORMAL,
                    false,
                    false);
            createLightSky();
            createStars();
            createDarkSky();
        }
        private void createStars() {
            if (this.starBuffer != null) {
                this.starBuffer.close();
            }

            this.starBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            this.starBuffer.bind();
            this.starBuffer.upload(drawStars(Tesselator.getInstance()));
            VertexBuffer.unbind();
        }
        private void createLightSky() {
            if (this.skyBuffer != null) {
                this.skyBuffer.close();
            }

            this.skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            this.skyBuffer.bind();
            this.skyBuffer.upload(buildSkyDisc(Tesselator.getInstance(), 16.0F));
            VertexBuffer.unbind();
        }

        private void createDarkSky() {
            if (this.darkBuffer != null) {
                this.darkBuffer.close();
            }

            this.darkBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            this.darkBuffer.bind();
            this.darkBuffer.upload(buildSkyDisc(Tesselator.getInstance(), -16.0F));
            VertexBuffer.unbind();
        }

        @Override
        public Vec3 getBrightnessDependentFogColor(Vec3 pFogColor, float pBrightness) {
            return pFogColor.scale(0.5);
        }

        @Override
        public boolean isFoggyAt(int pX, int pY) {
            return false;
        }

        @Override
        public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
            PoseStack posestack = new PoseStack();
            posestack.mulPose(modelViewMatrix);
            Vec3 vec3 = getSkyColor(level, Minecraft.getInstance().gameRenderer.getMainCamera().getPosition(), partialTick, 0.35f);
            float f = (float)vec3.x;
            float f1 = (float)vec3.y;
            float f2 = (float)vec3.z;
            FogRenderer.levelFogColor();
            Tesselator tesselator = Tesselator.getInstance();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
            ShaderInstance shaderinstance = RenderSystem.getShader();
            skyBuffer.bind();
            skyBuffer.drawWithShader(posestack.last().pose(), projectionMatrix, shaderinstance);
            VertexBuffer.unbind();
            RenderSystem.enableBlend();
            float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
            if (afloat != null) {
                // tweaking sunrise color alpha
                afloat[3] *= 0.2f;
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                posestack.pushPose();
                posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
                float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
                posestack.mulPose(Axis.ZP.rotationDegrees(f3));
                posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                float f4 = afloat[0];
                float f5 = afloat[1];
                float f6 = afloat[2];
                Matrix4f matrix4f = posestack.last().pose();
                BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                bufferbuilder.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(f4, f5, f6, afloat[3]);
                int i = 16;

                for (int j = 0; j <= 16; j++) {
                    float f7 = (float)j * (float) (Math.PI * 2) / 16.0F;
                    float f8 = Mth.sin(f7);
                    float f9 = Mth.cos(f7);
                    bufferbuilder.addVertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
                            .setColor(afloat[0], afloat[1], afloat[2], 0.0F);
                }

                BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
                posestack.popPose();
            }

            RenderSystem.blendFuncSeparate(
                    GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
            );

            posestack.pushPose();
            float f11 = 1.0F - level.getRainLevel(partialTick);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
            posestack.mulPose(Axis.YP.rotationDegrees(-90.0F));
            posestack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F));
            Matrix4f matrix4f1 = posestack.last().pose();
            float f12 = 20.0F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, SUN_LOCATION);
            BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder1.addVertex(matrix4f1, -f12, 160.0F, -f12).setUv(0.0F, 0.0F);
            bufferbuilder1.addVertex(matrix4f1, f12, 160.0F, -f12).setUv(1.0F, 0.0F);
            bufferbuilder1.addVertex(matrix4f1, f12, 160.0F, f12).setUv(1.0F, 1.0F);
            bufferbuilder1.addVertex(matrix4f1, -f12, 160.0F, f12).setUv(0.0F, 1.0F);
            BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
            float f10 = (level.getStarBrightness(partialTick) + 0.3f) * f11;
            if (f10 > 0.0F) {
                RenderSystem.setShaderColor(f10, f10, f10, f10);
                FogRenderer.setupNoFog();
                this.starBuffer.bind();
                this.starBuffer.drawWithShader(posestack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                VertexBuffer.unbind();
                setupFog.run();
            }
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            RenderSystem.disableBlend();
//            RenderSystem.defaultBlendFunc();
            posestack.popPose();

            posestack.pushPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
            matrix4f1 = posestack.last().pose();
            posestack.mulPose(Axis.YP.rotationDegrees(-30.0F));
            float overworldDegree = -45f;
            posestack.mulPose(Axis.XP.rotationDegrees((overworldDegree)));
            f12 = 30.0F;
            RenderSystem.setShaderTexture(0, OVERWORLD_LOCATION);
            bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder1.addVertex(matrix4f1, -f12, 140.0F, -f12).setUv(0.0F, 0.0F);
            bufferbuilder1.addVertex(matrix4f1, f12, 140.0F, -f12).setUv(1.0F, 0.0F);
            bufferbuilder1.addVertex(matrix4f1, f12, 140.0F, f12).setUv(1.0F, 1.0F);
            bufferbuilder1.addVertex(matrix4f1, -f12, 140.0F, f12).setUv(0.0F, 1.0F);
            BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            posestack.popPose();

            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
            double d0 = Minecraft.getInstance().player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);
            if (d0 < 0.0) {
                posestack.pushPose();
                posestack.translate(0.0F, 12.0F, 0.0F);
                this.darkBuffer.bind();
                this.darkBuffer.drawWithShader(posestack.last().pose(), projectionMatrix, shaderinstance);
                VertexBuffer.unbind();
                posestack.popPose();
            }

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.depthMask(true);
            return true;
        }
    }
}
