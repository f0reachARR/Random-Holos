package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.theivan066.randomholos.entity.animations.ModAnimationDefinitions;
import net.theivan066.randomholos.entity.custom.MikoEntity;

public class MikoModel<T extends MikoEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart miko;
	private final ModelPart head;

	public MikoModel(ModelPart root) {
		this.miko = root.getChild("miko");

		this.head = miko.getChild("head");
	}


	public static LayerDefinition createBodyLayer() {

		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition miko = partdefinition.addOrReplaceChild("miko", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = miko.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ahoge = headwear.addOrReplaceChild("ahoge", CubeListBuilder.create().texOffs(4, 71).addBox(-2.0F, -10.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(4, 76).addBox(-1.0F, -10.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 71).addBox(-1.0F, -11.0F, 1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(4, 74).addBox(-2.0F, -9.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 74).addBox(-3.0F, -9.75F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(33, 73).addBox(1.0F, -10.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 76).addBox(0.75F, -10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = miko.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition jacket = body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = miko.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(9.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition left_sleve = left_arm.addOrReplaceChild("left_sleve", CubeListBuilder.create().texOffs(48, 48).addBox(9.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = miko.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-12.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition right_sleve = right_arm.addOrReplaceChild("right_sleve", CubeListBuilder.create().texOffs(40, 32).addBox(-12.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = miko.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition left_pants = left_leg.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = miko.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		PartDefinition right_pants = right_leg.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(MikoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.PLAYERLIKE_WALK, limbSwing, limbSwingAmount, 1f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.PLAYERLIKE_IDLE, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.MIKO_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(MikoEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		miko.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return miko;
	}
}