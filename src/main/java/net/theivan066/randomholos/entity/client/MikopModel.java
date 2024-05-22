package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.theivan066.randomholos.entity.animations.ModAnimationDefinitions;
import net.theivan066.randomholos.entity.custom.MikopEntity;

public class MikopModel<T extends MikopEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart mikop;
	private final ModelPart head;

	public MikopModel(ModelPart root) {
		this.mikop = root.getChild("mikop");

		this.head = mikop.getChild("full").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition mikop = partdefinition.addOrReplaceChild("mikop", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition full = mikop.addOrReplaceChild("full", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = full.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 2).addBox(-7.0F, -11.0F, -6.0F, 14.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(13, 13).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 13).addBox(-6.0F, -11.0F, 6.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 13).addBox(-6.0F, -2.0F, 5.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 13).addBox(6.0F, -10.0F, 6.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 13).addBox(-7.0F, -10.0F, 6.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 13).addBox(-0.5F, -5.0F, -7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(61, 61).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 60).addBox(-6.0F, -11.0F, -7.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 60).addBox(-6.0F, -2.0F, -7.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 60).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 60).addBox(6.0F, -10.0F, -7.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 37).addBox(-6.0F, -12.0F, -5.0F, 12.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(42, 37).addBox(6.0F, -12.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(42, 37).addBox(-7.0F, -12.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(51, 46).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(51, 46).addBox(-6.0F, -12.0F, 5.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(-8.0F, -11.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(-8.0F, -2.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(59, 24).addBox(-8.0F, -10.0F, -6.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 24).addBox(-8.0F, -10.0F, 5.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(46, 0).addBox(-7.0F, -1.0F, -6.0F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 47).addBox(-6.5F, 0.0F, -5.5F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(7.0F, -11.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(7.0F, -2.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(59, 24).addBox(7.0F, -10.0F, -6.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(59, 24).addBox(7.0F, -10.0F, 5.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 15).addBox(7.0F, -10.0F, -5.0F, 1.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -1.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(62, 13).addBox(0.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -7.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(62, 13).addBox(-2.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -7.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create().texOffs(4, 88).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(2, 86).addBox(-1.0F, -5.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 88).addBox(-16.0F, -4.0F, -1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 76).addBox(-16.0F, -5.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(5, 95).mirror().addBox(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -9.0F, 1.0F));

		PartDefinition body = full.addOrReplaceChild("body", CubeListBuilder.create().texOffs(2, 27).addBox(-6.0F, 12.0F, -6.0F, 12.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(4, 29).addBox(-5.0F, 20.0F, -5.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(11, 36).addBox(-5.0F, 20.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(11, 36).addBox(-5.0F, 20.0F, 3.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 29).addBox(5.0F, 20.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(4, 29).addBox(-6.0F, 20.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(6.0F, 13.0F, -5.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(35, 69).addBox(6.0F, 13.0F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(35, 69).addBox(6.0F, 13.0F, -6.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(6.0F, 19.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(6.0F, 12.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 13.0F, 4.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 12.0F, 4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 19.0F, 4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(5.0F, 13.0F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-6.0F, 13.0F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(39, 50).addBox(-5.5F, 11.0F, -5.5F, 11.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(47, 58).addBox(-5.5F, 11.0F, -6.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(47, 58).addBox(-5.5F, 11.0F, 3.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(39, 50).addBox(5.5F, 11.0F, -5.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(39, 50).addBox(-6.5F, 11.0F, -5.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(35, 69).addBox(-7.0F, 13.0F, -6.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(-7.0F, 12.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(35, 69).addBox(-7.0F, 13.0F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(-7.0F, 19.0F, -5.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 62).addBox(-7.0F, 13.0F, -5.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-6.0F, 13.0F, -7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 12.0F, -7.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(5.0F, 13.0F, -7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 13.0F, -7.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 48).addBox(-5.0F, 19.0F, -7.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(75, 24).addBox(-2.0F, -1.0F, -6.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.25F))
				.texOffs(82, 31).addBox(-2.0F, -1.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 12.0F, -2.0F, 0.5236F, -0.1745F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(75, 24).addBox(-1.0F, -1.0F, -6.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.25F))
				.texOffs(82, 31).addBox(-1.0F, -1.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 12.0F, -2.0F, 0.5236F, 0.1745F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 12).addBox(-1.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 25).addBox(-1.0F, -5.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-1.0F, -7.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 12).addBox(-1.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 19.0F, 5.0F));

		PartDefinition legs = full.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(14, 59).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(39, 61).addBox(-1.5F, 2.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offset(3.0F, -3.0F, -1.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 60).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(39, 61).addBox(-1.5F, 2.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offset(-3.0F, -3.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(MikopEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.MIKOP_WALK, limbSwing, limbSwingAmount, 1f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.MIKOP_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(MikopEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mikop.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return mikop;
	}
}