package com.zoshsgahdnkc.NebulaChronicles.client.entity;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.SpikedVerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SpikedVerdhelmBeetleModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "spiked_verdhelm_beetle"), "main");
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart beetle;
	private final ModelPart body_spike;
	private final ModelPart head;
	private final ModelPart head_spike;

	public SpikedVerdhelmBeetleModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.beetle = this.body.getChild("beetle");
		this.body_spike = this.beetle.getChild("body_spike");
		this.head = this.body.getChild("head");
		this.head_spike = this.head.getChild("head_spike");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(2.5F, 24.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 29).addBox(-4.5F, -3.0F, 4.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(36, 25).addBox(-3.0F, -4.0F, -3.4F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.5F, 6.9F, 0.3491F, 0.0F, 0.0F));

		PartDefinition beetle = body.addOrReplaceChild("beetle", CubeListBuilder.create().texOffs(0, 15).addBox(-6.5F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -7.0F, -4.25F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(36, 6).addBox(-7.5F, -7.5F, -3.8F, 10.0F, 5.0F, 3.0F, new CubeDeformation(-0.2F))
				.texOffs(0, 35).addBox(-7.5F, -7.5F, -0.55F, 10.0F, 5.0F, 3.0F, new CubeDeformation(-0.2F))
				.texOffs(31, 33).addBox(-7.5F, -7.5F, 2.65F, 10.0F, 5.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_spike = beetle.addOrReplaceChild("body_spike", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 29).addBox(1.5F, -5.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-3.5F, -5.0F, -2.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 15).addBox(-3.0F, -4.0F, -4.75F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -1.0F, -3.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 0).addBox(-3.0F, -3.0F, -2.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, -1.75F, 0.0436F, 0.0F, 0.0F));

		PartDefinition head_spike = head.addOrReplaceChild("head_spike", CubeListBuilder.create(), PartPose.offset(0.5F, -7.1313F, -5.5119F));

		PartDefinition cube_r3 = head_spike.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(27, 33).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1641F, -0.4998F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r4 = head_spike.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.9F, 0.5F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r5 = head_spike.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 21).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.1313F, 1.6119F, 0.6981F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animate(((SpikedVerdhelmBeetleEntity) entity).idleState, SpikedVerdhelmBeetleAnimation.IDLE, ageInTicks, 1f);
		this.animate(((SpikedVerdhelmBeetleEntity) entity).attackingState, SpikedVerdhelmBeetleAnimation.ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);

	}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
		body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);

	}

	@Override
	public ModelPart root() {
		return body;
	}
}