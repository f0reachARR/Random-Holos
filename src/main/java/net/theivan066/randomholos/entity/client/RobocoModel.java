package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.theivan066.randomholos.entity.animations.ModAnimationDefinitions;
import net.theivan066.randomholos.entity.custom.RobocoEntity;

public class RobocoModel<T extends RobocoEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart roboco;
	private final ModelPart head;

	public RobocoModel(ModelPart root) {
		this.roboco = root.getChild("roboco");

		this.head = roboco.getChild("head");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roboco = partdefinition.addOrReplaceChild("roboco", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition gun = roboco.addOrReplaceChild("gun", CubeListBuilder.create(), PartPose.offset(2.5F, -23.0F, 0.0F));

		PartDefinition scope = gun.addOrReplaceChild("scope", CubeListBuilder.create().texOffs(86, 75).addBox(-23.0F, 7.0F, 18.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 75).addBox(-23.0F, 7.0F, 24.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(77, 72).addBox(-21.5F, 4.0F, 18.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(78, 73).addBox(-24.5F, 4.0F, 18.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(78, 71).addBox(-23.5F, 3.0F, 18.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(87, 80).addBox(-23.5F, 2.0F, 23.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(87, 80).addBox(-23.5F, 2.0F, 20.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(78, 71).addBox(-23.5F, 6.0F, 18.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-25.5F, 4.0F, 21.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-21.5F, 6.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-20.5F, 4.0F, 21.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-24.5F, 6.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-21.5F, 3.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-24.5F, 3.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-23.5F, 2.0F, 21.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-21.5F, 2.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-24.5F, 2.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-24.5F, 1.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-21.5F, 1.0F, 21.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(87, 80).addBox(-23.5F, 1.0F, 23.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(86, 79).addBox(-23.5F, 1.0F, 21.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(87, 80).addBox(-23.5F, 1.0F, 20.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition front = scope.addOrReplaceChild("front", CubeListBuilder.create().texOffs(80, 73).addBox(-23.5F, 3.0F, 12.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(79, 74).addBox(-21.5F, 3.0F, 12.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-20.5F, 4.0F, 12.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(80, 73).addBox(-23.5F, 6.0F, 12.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(80, 75).addBox(-24.5F, 3.0F, 12.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-25.5F, 4.0F, 12.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-25.5F, 3.0F, 8.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-20.5F, 3.0F, 8.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-24.5F, 2.0F, 8.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-24.5F, 7.0F, 8.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-23.5F, 2.0F, 12.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(84, 79).addBox(-23.5F, 7.0F, 12.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition back = scope.addOrReplaceChild("back", CubeListBuilder.create().texOffs(82, 75).addBox(-23.5F, 7.0F, 28.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-23.5F, 2.0F, 28.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-25.5F, 4.0F, 28.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-20.5F, 4.0F, 28.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-21.5F, 3.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-24.5F, 3.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-21.5F, 6.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(82, 75).addBox(-24.5F, 6.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition magchamber = gun.addOrReplaceChild("magchamber", CubeListBuilder.create().texOffs(221, 143).addBox(-20.5F, 13.0F, 12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(171, 153).addBox(-25.5F, 15.0F, 14.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(141, 139).addBox(-25.5F, 13.0F, 12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(144, 134).addBox(-25.5F, 13.0F, 13.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(222, 107).addBox(-20.5F, 13.0F, 13.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(81, 240).addBox(-21.5F, 14.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(193, 158).addBox(-20.5F, 13.0F, 15.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(191, 157).addBox(-20.5F, 13.0F, 14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(187, 152).addBox(-20.5F, 13.0F, 18.0F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(60, 117).addBox(-20.5F, 17.0F, 18.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(79, 236).addBox(-24.5F, 15.0F, 26.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(79, 236).addBox(-21.5F, 14.0F, 26.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(79, 236).addBox(-24.5F, 14.0F, 26.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(67, 133).addBox(-20.5F, 18.0F, 21.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(53, 160).addBox(-20.5F, 19.0F, 24.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(77, 234).addBox(-24.5F, 19.0F, 24.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(77, 234).addBox(-24.5F, 18.0F, 21.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(77, 234).addBox(-24.5F, 17.0F, 18.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(179, 152).addBox(-25.5F, 17.0F, 18.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(114, 166).addBox(-25.5F, 13.0F, 15.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(190, 154).addBox(-25.5F, 18.0F, 21.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(149, 151).addBox(-25.5F, 13.0F, 14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(109, 180).addBox(-25.5F, 13.0F, 18.0F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(209, 160).addBox(-25.5F, 19.0F, 24.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(151, 150).addBox(-24.5F, 16.0F, 15.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(81, 239).addBox(-24.5F, 14.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition bkstock = gun.addOrReplaceChild("bkstock", CubeListBuilder.create().texOffs(234, 203).addBox(-23.5F, 19.0F, -33.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(230, 172).addBox(-23.5F, 19.0F, -24.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(225, 195).addBox(-23.5F, 18.0F, -31.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(243, 215).addBox(-23.5F, 17.0F, -32.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(238, 239).addBox(-23.5F, 15.0F, -33.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(63, 245).addBox(-23.5F, 9.0F, -17.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(26, 244).addBox(-24.5F, 10.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(146, 247).addBox(-23.5F, 20.0F, -17.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(65, 179).addBox(-24.5F, 19.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(61, 193).addBox(-21.5F, 19.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(104, 215).addBox(-23.5F, 19.0F, -17.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(116, 245).addBox(-23.5F, 10.0F, -15.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(145, 247).addBox(-24.5F, 17.0F, -15.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(104, 246).addBox(-24.5F, 11.0F, -15.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(87, 246).addBox(-24.5F, 13.0F, -16.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 247).addBox(-25.5F, 11.0F, -17.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(99, 245).addBox(-20.5F, 13.0F, -17.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 248).addBox(-25.5F, 13.0F, -17.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(129, 242).addBox(-21.5F, 10.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(118, 244).addBox(-20.5F, 11.0F, -17.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(139, 228).addBox(-25.5F, 17.0F, -17.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(102, 226).addBox(-20.5F, 17.0F, -17.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, 71.0F));

		PartDefinition bolt = gun.addOrReplaceChild("bolt", CubeListBuilder.create().texOffs(21, 136).addBox(-24.5F, 12.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(8, 173).addBox(-24.5F, 11.0F, 14.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(9, 156).addBox(-26.5F, 12.0F, 16.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(9, 137).addBox(-26.5F, 11.0F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(10, 128).addBox(-27.5F, 10.0F, 15.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition gunbody = gun.addOrReplaceChild("gunbody", CubeListBuilder.create().texOffs(6, 92).addBox(-24.5F, 10.0F, 7.0F, 1.0F, 1.0F, 55.0F, new CubeDeformation(0.0F))
				.texOffs(27, 106).addBox(-24.5F, 13.0F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(72, 0).addBox(-24.5F, 13.0F, 7.0F, 1.0F, 1.0F, 55.0F, new CubeDeformation(0.0F))
				.texOffs(147, 169).addBox(-21.5F, 13.0F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, -11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, 96).addBox(-21.5F, 10.0F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(138, 39).addBox(-21.5F, 10.0F, 7.0F, 1.0F, 1.0F, 55.0F, new CubeDeformation(0.0F))
				.texOffs(60, 47).addBox(-21.5F, 13.0F, 7.0F, 1.0F, 1.0F, 55.0F, new CubeDeformation(0.0F))
				.texOffs(36, 151).addBox(-24.5F, 10.0F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(125, 64).addBox(-25.5F, 11.0F, 34.0F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(170, 98).addBox(-25.5F, 11.0F, -14.0F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(21, 136).addBox(-24.5F, 11.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(143, 78).addBox(-25.5F, 10.0F, 13.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(5, 240).addBox(-25.5F, 11.0F, 28.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(140, 195).addBox(-25.5F, 13.0F, 27.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(36, 19).addBox(-20.5F, 11.0F, -14.0F, 1.0F, 2.0F, 76.0F, new CubeDeformation(0.0F))
				.texOffs(100, 39).addBox(-23.5F, 9.0F, -14.0F, 2.0F, 1.0F, 76.0F, new CubeDeformation(0.0F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -14.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -13.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -12.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -11.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -8.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -7.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -9.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -10.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -0.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 0.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -1.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(45, 96).addBox(-23.0F, 7.9F, -2.4F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.3F))
				.texOffs(39, 79).addBox(-19.6F, -0.9F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.3F))
				.texOffs(93, 80).addBox(-23.0F, 7.9F, -4.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.3F))
				.texOffs(35, 90).addBox(-22.6F, 7.9F, -1.4F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.3F))
				.texOffs(52, 92).addBox(-19.6F, -0.9F, -1.4F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.3F))
				.texOffs(230, 61).addBox(-18.2F, -0.5F, -1.4F, 1.0F, 9.0F, 1.0F, new CubeDeformation(-0.3F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -5.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, -6.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 15.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 16.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 14.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 13.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 12.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 11.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 10.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 9.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 8.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 7.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 6.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 5.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 4.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 3.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 2.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 1.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 31.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 32.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 33.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(164, 247).addBox(-24.0F, 7.5F, 33.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(-0.47F))
				.texOffs(169, 246).addBox(-23.5F, 8.0F, 35.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(84, 80).addBox(-23.5F, 8.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(181, 230).addBox(-19.6F, -0.9F, 1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 30.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 29.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 28.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 27.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 26.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 22.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 21.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(79, 236).addBox(-24.0F, 8.4F, 20.6F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(19, 91).addBox(-23.5F, 10.0F, -14.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 146).addBox(-23.0F, 15.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(171, 170).addBox(-22.5F, 15.0F, -6.0F, 1.0F, 2.0F, 17.0F, new CubeDeformation(-0.4F))
				.texOffs(175, 197).addBox(-23.5F, 15.0F, -6.0F, 1.0F, 2.0F, 17.0F, new CubeDeformation(-0.4F))
				.texOffs(213, 64).addBox(-22.3F, 15.5F, -6.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(-0.4F))
				.texOffs(173, 146).addBox(-23.7F, 15.5F, -6.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(-0.4F))
				.texOffs(12, 98).addBox(-22.9F, 15.0F, 11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(12, 98).addBox(-24.3F, 15.0F, 11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.4F))
				.texOffs(100, 3).addBox(-23.5F, 14.0F, -14.0F, 2.0F, 1.0F, 76.0F, new CubeDeformation(0.0F))
				.texOffs(148, 78).addBox(-24.5F, 11.0F, 34.0F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(170, 100).addBox(-24.5F, 11.0F, 8.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(128, 58).addBox(-23.5F, 11.0F, 14.0F, 3.0F, 2.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(128, 58).addBox(-23.5F, 10.0F, 14.0F, 2.0F, 1.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(170, 100).addBox(-23.5F, 10.0F, 8.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(19, 91).addBox(-21.5F, 11.0F, -14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 91).addBox(-24.5F, 11.0F, -14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 91).addBox(-21.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 91).addBox(-23.5F, 10.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 91).addBox(-24.5F, 11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 10.0F, -11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-24.5F, 13.0F, -11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(115, 216).addBox(-21.5F, 13.0F, -11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition trigger = gun.addOrReplaceChild("trigger", CubeListBuilder.create().texOffs(65, 206).addBox(-24.5F, 19.0F, 29.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 118).addBox(-24.5F, 16.8F, 28.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F))
				.texOffs(32, 127).addBox(-24.5F, 17.4F, 28.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F))
				.texOffs(43, 136).addBox(-24.5F, 18.0F, 27.9F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F))
				.texOffs(54, 224).addBox(-24.5F, 17.0F, 30.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(42, 219).addBox(-24.5F, 20.0F, 30.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(25, 209).addBox(-24.5F, 25.0F, 35.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(47, 218).addBox(-24.5F, 24.0F, 34.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(61, 215).addBox(-24.5F, 23.0F, 33.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(4, 223).addBox(-24.5F, 22.0F, 32.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 222).addBox(-24.5F, 21.0F, 31.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(124, 135).addBox(-24.5F, 16.0F, 27.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(57, 161).addBox(-24.5F, 15.0F, 33.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(155, 212).addBox(-21.5F, 14.0F, 33.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(92, 183).addBox(-24.5F, 14.0F, 33.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(118, 202).addBox(-21.5F, 14.0F, 27.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(47, 172).addBox(-24.5F, 14.0F, 27.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(103, 129).addBox(-24.5F, 19.5F, 28.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(49, 200).addBox(-24.5F, 19.0F, 27.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition mag = gun.addOrReplaceChild("mag", CubeListBuilder.create().texOffs(168, 230).addBox(-24.5F, 20.0F, 24.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(132, 223).addBox(-24.5F, 25.0F, 23.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(99, 205).addBox(-24.5F, 17.0F, 16.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(121, 218).addBox(-24.5F, 19.0F, 21.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(89, 217).addBox(-24.5F, 18.0F, 18.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(11, 112).addBox(-24.5F, 19.8F, 26.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition barrel = gun.addOrReplaceChild("barrel", CubeListBuilder.create().texOffs(7, 107).addBox(-23.5F, 11.0F, -52.0F, 2.0F, 2.0F, 59.0F, new CubeDeformation(0.0F))
				.texOffs(211, 136).addBox(-23.5F, 10.0F, -52.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(212, 140).addBox(-23.5F, 13.0F, -52.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(214, 141).addBox(-25.5F, 11.0F, -51.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(215, 140).addBox(-25.5F, 10.0F, -51.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 11.0F, -51.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 10.0F, -51.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 13.0F, -51.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(214, 141).addBox(-25.5F, 13.0F, -51.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 11.0F, -49.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 10.0F, -49.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 160).addBox(-24.5F, 10.0F, -49.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(215, 140).addBox(-25.5F, 10.0F, -50.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(215, 140).addBox(-25.5F, 10.0F, -48.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(244, 128).addBox(-21.5F, 10.0F, -48.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 10.0F, -50.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 160).addBox(-25.5F, 13.0F, -50.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(207, 139).addBox(-25.5F, 13.0F, -48.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 13.0F, -48.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 13.0F, -50.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(214, 141).addBox(-25.5F, 11.0F, -49.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 11.0F, -47.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(218, 144).addBox(-21.5F, 10.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(244, 128).addBox(-20.5F, 10.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(210, 137).addBox(-25.5F, 10.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(208, 136).addBox(-25.5F, 13.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-20.5F, 13.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 160).addBox(-21.5F, 13.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(216, 144).addBox(-21.5F, 13.0F, -49.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 160).addBox(-24.5F, 10.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 160).addBox(-24.5F, 13.0F, -47.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(209, 139).addBox(-25.5F, 11.0F, -47.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -5.0F, -8.0F));

		PartDefinition shell = roboco.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(203, 34).addBox(-21.5F, 4.0037F, 14.5177F, 2.0F, 2.0F, 9.0F, new CubeDeformation(-0.1F))
				.texOffs(210, 26).addBox(-21.5F, 4.0037F, 12.8177F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.15F)), PartPose.offset(-6.0F, -21.0F, -5.0F));

		PartDefinition head = roboco.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = roboco.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition jacket = body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = roboco.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition left_pants = left_leg.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = roboco.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		PartDefinition right_pants = right_leg.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = roboco.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -23.0F, 0.0F));

		PartDefinition left_sleve = left_arm.addOrReplaceChild("left_sleve", CubeListBuilder.create().texOffs(48, 48).addBox(9.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-10.5F, 1.0F, 0.0F));

		PartDefinition right_arm = roboco.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -23.0F, 0.0F));

		PartDefinition right_sleve = right_arm.addOrReplaceChild("right_sleve", CubeListBuilder.create().texOffs(40, 32).addBox(-12.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(10.5F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(RobocoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.ROBOCO_WALK, limbSwing, limbSwingAmount, 1f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.ROBOCO_IDLE, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.ROBOCO_ATTACK, ageInTicks, 1f);
		this.animate(entity.attackLoadedAnimationState, ModAnimationDefinitions.ROBOCO_ATTACK_LOADED, ageInTicks, 1f);
	}

	private void applyHeadRotation(RobocoEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		roboco.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return roboco;
	}
}