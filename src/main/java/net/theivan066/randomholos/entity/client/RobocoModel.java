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

        PartDefinition gun = roboco.addOrReplaceChild("gun", CubeListBuilder.create(), PartPose.offset(-6.5F, -23.0F, 0.0F));

        PartDefinition scope = gun.addOrReplaceChild("scope", CubeListBuilder.create().texOffs(117, 105).addBox(-6.9F, 2.1F, 5.4F, 0.3F, 0.6F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 105).addBox(-6.9F, 2.1F, 7.2F, 0.3F, 0.6F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(108, 102).addBox(-6.45F, 1.2F, 5.4F, 0.3F, 0.6F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(109, 103).addBox(-7.35F, 1.2F, 5.4F, 0.3F, 0.6F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(109, 101).addBox(-7.05F, 0.9F, 5.4F, 0.6F, 0.3F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(118, 110).addBox(-7.05F, 0.6F, 6.9F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(118, 110).addBox(-7.05F, 0.6F, 6.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(109, 101).addBox(-7.05F, 1.8F, 5.4F, 0.6F, 0.3F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.65F, 1.2F, 6.3F, 0.3F, 0.6F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-6.45F, 1.8F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-6.15F, 1.2F, 6.3F, 0.3F, 0.6F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.35F, 1.8F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-6.45F, 0.9F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.35F, 0.9F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.05F, 0.6F, 6.3F, 0.6F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-6.45F, 0.6F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.35F, 0.6F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.35F, 0.3F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-6.45F, 0.3F, 6.3F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(118, 110).addBox(-7.05F, 0.3F, 6.9F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(117, 109).addBox(-7.05F, 0.3F, 6.3F, 0.6F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(118, 110).addBox(-7.05F, 0.3F, 6.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition front = scope.addOrReplaceChild("front", CubeListBuilder.create().texOffs(111, 103).addBox(-7.05F, 0.9F, 3.6F, 0.6F, 0.3F, 2.4F, new CubeDeformation(0.0F))
                .texOffs(110, 104).addBox(-6.45F, 0.9F, 3.6F, 0.3F, 1.2F, 2.4F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-6.15F, 1.2F, 3.6F, 0.3F, 0.6F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(111, 103).addBox(-7.05F, 1.8F, 3.6F, 0.6F, 0.3F, 2.4F, new CubeDeformation(0.0F))
                .texOffs(111, 105).addBox(-7.35F, 0.9F, 3.6F, 0.3F, 1.2F, 2.4F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.65F, 1.2F, 3.6F, 0.3F, 0.6F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.65F, 0.9F, 2.4F, 0.3F, 1.2F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-6.15F, 0.9F, 2.4F, 0.3F, 1.2F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.35F, 0.6F, 2.4F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.35F, 2.1F, 2.4F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.05F, 0.6F, 3.6F, 0.6F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(115, 109).addBox(-7.05F, 2.1F, 3.6F, 0.6F, 0.3F, 1.2F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.6F));

        PartDefinition back = scope.addOrReplaceChild("back", CubeListBuilder.create().texOffs(113, 105).addBox(-7.05F, 2.1F, 8.4F, 0.6F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-7.05F, 0.6F, 8.4F, 0.6F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-7.65F, 1.2F, 8.4F, 0.3F, 0.6F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-6.15F, 1.2F, 8.4F, 0.3F, 0.6F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-6.45F, 0.9F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-7.35F, 0.9F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-6.45F, 1.8F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(113, 105).addBox(-7.35F, 1.8F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition magchamber = gun.addOrReplaceChild("magchamber", CubeListBuilder.create().texOffs(221, 143).addBox(-6.15F, 3.9F, 3.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(171, 153).addBox(-7.65F, 4.5F, 4.2F, 1.8F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(141, 139).addBox(-7.65F, 3.9F, 3.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(144, 134).addBox(-7.65F, 3.9F, 3.9F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(222, 107).addBox(-6.15F, 3.9F, 3.9F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(81, 240).addBox(-6.45F, 4.2F, 3.9F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(193, 158).addBox(-6.15F, 3.9F, 4.5F, 0.3F, 1.2F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(191, 157).addBox(-6.15F, 3.9F, 4.2F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(187, 152).addBox(-6.15F, 3.9F, 5.4F, 0.3F, 1.2F, 2.7F, new CubeDeformation(0.0F))
                .texOffs(60, 117).addBox(-6.15F, 5.1F, 5.4F, 0.3F, 0.3F, 2.7F, new CubeDeformation(0.0F))
                .texOffs(79, 236).addBox(-7.35F, 4.5F, 7.8F, 1.2F, 1.2F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(79, 236).addBox(-6.45F, 4.2F, 7.8F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(79, 236).addBox(-7.35F, 4.2F, 7.8F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(67, 133).addBox(-6.15F, 5.4F, 6.3F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(53, 160).addBox(-6.15F, 5.7F, 7.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(77, 234).addBox(-7.35F, 5.7F, 7.2F, 1.2F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(77, 234).addBox(-7.35F, 5.4F, 6.3F, 1.2F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(77, 234).addBox(-7.35F, 5.1F, 5.4F, 1.2F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(179, 152).addBox(-7.65F, 5.1F, 5.4F, 0.3F, 0.3F, 2.7F, new CubeDeformation(0.0F))
                .texOffs(114, 166).addBox(-7.65F, 3.9F, 4.5F, 0.3F, 1.2F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(190, 154).addBox(-7.65F, 5.4F, 6.3F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(149, 151).addBox(-7.65F, 3.9F, 4.2F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(109, 180).addBox(-7.65F, 3.9F, 5.4F, 0.3F, 1.2F, 2.7F, new CubeDeformation(0.0F))
                .texOffs(209, 160).addBox(-7.65F, 5.7F, 7.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(151, 150).addBox(-7.35F, 4.8F, 4.5F, 1.2F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(81, 239).addBox(-7.35F, 4.2F, 3.9F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition bkstock = gun.addOrReplaceChild("bkstock", CubeListBuilder.create().texOffs(234, 203).addBox(-7.05F, 5.7F, -9.9F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(230, 172).addBox(-7.05F, 5.7F, -7.2F, 0.6F, 0.3F, 2.1F, new CubeDeformation(0.0F))
                .texOffs(225, 195).addBox(-7.05F, 5.4F, -9.3F, 0.6F, 0.3F, 2.1F, new CubeDeformation(0.0F))
                .texOffs(243, 215).addBox(-7.05F, 5.1F, -9.6F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(238, 239).addBox(-7.05F, 4.1F, -9.9F, 0.6F, 1.0F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(63, 245).addBox(-7.05F, 2.7F, -5.1F, 0.6F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(26, 244).addBox(-7.35F, 3.0F, -5.1F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(146, 247).addBox(-7.05F, 6.0F, -5.1F, 0.6F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(65, 179).addBox(-7.35F, 5.7F, -5.1F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(61, 193).addBox(-6.45F, 5.7F, -5.1F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(104, 215).addBox(-7.05F, 5.7F, -5.1F, 0.6F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(116, 245).addBox(-7.05F, 3.0F, -4.5F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(145, 247).addBox(-7.35F, 5.1F, -4.5F, 1.2F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(104, 246).addBox(-7.35F, 3.3F, -4.5F, 1.2F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(87, 246).addBox(-7.35F, 3.9F, -4.8F, 1.2F, 1.8F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(42, 247).addBox(-7.65F, 3.3F, -5.1F, 0.3F, 0.6F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(99, 245).addBox(-6.15F, 3.9F, -5.1F, 0.3F, 1.2F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(86, 248).addBox(-7.65F, 3.9F, -5.1F, 0.3F, 1.2F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(129, 242).addBox(-6.45F, 3.0F, -5.1F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(118, 244).addBox(-6.15F, 3.3F, -5.1F, 0.3F, 0.6F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(139, 228).addBox(-7.65F, 5.1F, -5.1F, 0.3F, 0.6F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(102, 226).addBox(-6.15F, 5.1F, -5.1F, 0.3F, 0.6F, 0.9F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, 21.3F));

        PartDefinition bolt = gun.addOrReplaceChild("bolt", CubeListBuilder.create().texOffs(21, 136).addBox(-7.35F, 3.6F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(8, 173).addBox(-7.35F, 3.3F, 4.2F, 0.3F, 0.6F, 4.2F, new CubeDeformation(0.0F))
                .texOffs(9, 156).addBox(-7.95F, 3.6F, 4.8F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(9, 137).addBox(-7.95F, 3.3F, 4.5F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(10, 128).addBox(-8.25F, 3.0F, 4.5F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition gunbody = gun.addOrReplaceChild("gunbody", CubeListBuilder.create().texOffs(6, 92).addBox(-7.35F, 3.0F, 2.1F, 0.3F, 0.3F, 16.5F, new CubeDeformation(0.0F))
                .texOffs(27, 106).addBox(-7.35F, 3.9F, -4.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, -2.4F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, -1.5F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, -0.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, 0.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, 1.2F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(72, 0).addBox(-7.35F, 3.9F, 2.1F, 0.3F, 0.3F, 16.5F, new CubeDeformation(0.0F))
                .texOffs(147, 169).addBox(-6.45F, 3.9F, -4.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, -3.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(33, 96).addBox(-6.45F, 3.0F, -4.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, -2.4F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, -2.4F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, -1.5F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, -1.5F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, -0.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, -0.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, 0.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, 0.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.0F, 1.2F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, 1.2F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(138, 39).addBox(-6.45F, 3.0F, 2.1F, 0.3F, 0.3F, 16.5F, new CubeDeformation(0.0F))
                .texOffs(91, 77).addBox(-6.45F, 3.9F, 2.1F, 0.3F, 0.3F, 16.5F, new CubeDeformation(0.0F))
                .texOffs(36, 151).addBox(-7.35F, 3.0F, -4.2F, 0.3F, 0.3F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, 1.2F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, -1.5F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, -0.6F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, 0.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(156, 94).addBox(-7.65F, 3.3F, 10.2F, 0.3F, 0.6F, 8.4F, new CubeDeformation(0.0F))
                .texOffs(170, 98).addBox(-7.65F, 3.3F, -4.2F, 0.3F, 0.6F, 8.4F, new CubeDeformation(0.0F))
                .texOffs(21, 136).addBox(-7.35F, 3.3F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(143, 78).addBox(-7.65F, 3.0F, 3.9F, 0.3F, 0.3F, 4.8F, new CubeDeformation(0.0F))
                .texOffs(5, 240).addBox(-7.65F, 3.3F, 8.4F, 0.3F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(140, 195).addBox(-7.65F, 3.9F, 8.1F, 0.3F, 0.3F, 2.4F, new CubeDeformation(0.0F))
                .texOffs(67, 49).addBox(-6.15F, 3.3F, -4.2F, 0.3F, 0.6F, 22.8F, new CubeDeformation(0.0F))
                .texOffs(131, 69).addBox(-7.05F, 2.7F, -4.2F, 0.6F, 0.3F, 22.8F, new CubeDeformation(0.0F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -4.6F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -4.3F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -4.0F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -3.7F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -2.8F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -2.5F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -3.1F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -3.4F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -0.4F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -0.1F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -0.7F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(45, 96).addBox(-7.11F, 2.16F, -0.93F, 0.72F, 0.72F, 1.02F, new CubeDeformation(-0.3F))
                .texOffs(39, 79).addBox(-6.09F, -0.48F, -0.51F, 0.72F, 0.72F, 1.02F, new CubeDeformation(-0.3F))
                .texOffs(124, 110).addBox(-7.11F, 2.16F, -1.62F, 0.72F, 0.72F, 0.72F, new CubeDeformation(-0.3F))
                .texOffs(35, 90).addBox(-6.99F, 2.16F, -0.63F, 1.92F, 0.72F, 0.72F, new CubeDeformation(-0.3F))
                .texOffs(52, 92).addBox(-6.09F, -0.48F, -0.63F, 1.02F, 0.72F, 0.72F, new CubeDeformation(-0.3F))
                .texOffs(230, 61).addBox(-5.67F, -0.36F, -0.63F, 0.72F, 3.12F, 0.72F, new CubeDeformation(-0.3F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -1.9F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, -2.2F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 4.4F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 4.7F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 4.1F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 3.8F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 3.5F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 3.2F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 2.9F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 2.6F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 2.3F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 2.0F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 1.7F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 1.4F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 1.1F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 0.8F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 0.5F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 0.2F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 9.2F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 9.5F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 9.8F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(164, 247).addBox(-7.529F, 1.921F, 9.721F, 1.558F, 0.958F, 1.558F, new CubeDeformation(-0.47F))
                .texOffs(169, 246).addBox(-7.05F, 2.4F, 10.5F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 110).addBox(-7.05F, 2.4F, -1.2F, 0.6F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(181, 230).addBox(-5.6F, 0.01F, 0.58F, -0.26F, -0.26F, 1.84F, new CubeDeformation(0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 8.9F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 8.6F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 8.3F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 8.0F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 7.7F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 6.5F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 6.2F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(79, 236).addBox(-7.48F, 2.24F, 5.9F, 1.46F, 0.86F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(19, 91).addBox(-7.05F, 3.0F, -4.2F, 0.6F, 1.2F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(36, 146).addBox(-6.9F, 4.5F, -1.8F, 0.3F, 0.6F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(171, 170).addBox(-7.03F, 4.22F, -2.08F, 0.86F, 1.16F, 5.66F, new CubeDeformation(-0.4F))
                .texOffs(175, 197).addBox(-7.33F, 4.22F, -2.08F, 0.86F, 1.16F, 5.66F, new CubeDeformation(-0.4F))
                .texOffs(213, 64).addBox(-6.97F, 4.37F, -2.08F, 0.86F, 0.86F, 5.96F, new CubeDeformation(-0.4F))
                .texOffs(173, 146).addBox(-7.39F, 4.37F, -2.08F, 0.86F, 0.86F, 5.96F, new CubeDeformation(-0.4F))
                .texOffs(12, 98).addBox(-7.15F, 4.22F, 3.02F, 1.16F, 1.16F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(12, 98).addBox(-7.57F, 4.22F, 3.02F, 1.16F, 1.16F, 0.86F, new CubeDeformation(-0.4F))
                .texOffs(100, 3).addBox(-7.05F, 4.2F, -4.2F, 0.6F, 0.3F, 22.8F, new CubeDeformation(0.0F))
                .texOffs(148, 78).addBox(-7.35F, 3.3F, 10.2F, 0.3F, 0.6F, 8.4F, new CubeDeformation(0.0F))
                .texOffs(170, 100).addBox(-7.35F, 3.3F, 2.4F, 1.2F, 0.6F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(159, 88).addBox(-7.05F, 3.3F, 4.2F, 0.9F, 0.6F, 14.4F, new CubeDeformation(0.0F))
                .texOffs(159, 88).addBox(-7.05F, 3.0F, 4.2F, 0.6F, 0.3F, 14.4F, new CubeDeformation(0.0F))
                .texOffs(170, 100).addBox(-7.05F, 3.0F, 2.4F, 0.6F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(19, 91).addBox(-6.45F, 3.3F, -4.2F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(19, 91).addBox(-7.35F, 3.3F, -4.2F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(19, 91).addBox(-6.45F, 3.3F, 2.1F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(19, 91).addBox(-7.05F, 3.0F, 2.1F, 0.6F, 1.2F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(19, 91).addBox(-7.35F, 3.3F, 2.1F, 0.3F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, -2.4F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.0F, -3.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-7.35F, 3.9F, -3.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(115, 216).addBox(-6.45F, 3.9F, -3.3F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition trigger = gun.addOrReplaceChild("trigger", CubeListBuilder.create().texOffs(65, 206).addBox(-7.35F, 5.7F, 8.7F, 1.2F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(29, 118).addBox(-7.49F, 4.9F, 8.41F, 1.48F, 0.58F, 0.58F, new CubeDeformation(-0.2F))
                .texOffs(32, 127).addBox(-7.49F, 5.08F, 8.41F, 1.48F, 0.58F, 0.58F, new CubeDeformation(-0.2F))
                .texOffs(43, 136).addBox(-7.49F, 5.26F, 8.23F, 1.48F, 0.58F, 0.58F, new CubeDeformation(-0.2F))
                .texOffs(54, 224).addBox(-7.35F, 5.1F, 9.0F, 1.2F, 0.9F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(42, 219).addBox(-7.35F, 6.0F, 9.0F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(25, 209).addBox(-7.35F, 7.5F, 10.5F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(47, 218).addBox(-7.35F, 7.2F, 10.2F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(61, 215).addBox(-7.35F, 6.9F, 9.9F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(4, 223).addBox(-7.35F, 6.6F, 9.6F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(24, 222).addBox(-7.35F, 6.3F, 9.3F, 1.2F, 0.3F, 1.2F, new CubeDeformation(0.0F))
                .texOffs(124, 135).addBox(-7.35F, 4.8F, 8.1F, 1.2F, 0.3F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(57, 161).addBox(-7.35F, 4.5F, 9.9F, 1.2F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(155, 212).addBox(-6.45F, 4.2F, 9.9F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(92, 183).addBox(-7.35F, 4.2F, 9.9F, 0.3F, 0.3F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(118, 202).addBox(-6.45F, 4.2F, 8.1F, 0.3F, 0.6F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(47, 172).addBox(-7.35F, 4.2F, 8.1F, 0.3F, 0.6F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(103, 129).addBox(-7.35F, 5.85F, 8.4F, 1.2F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(49, 200).addBox(-7.35F, 5.7F, 8.1F, 1.2F, 0.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition mag = gun.addOrReplaceChild("mag", CubeListBuilder.create().texOffs(168, 230).addBox(-7.35F, 6.0F, 7.2F, 1.2F, 1.8F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(132, 223).addBox(-7.35F, 7.5F, 6.9F, 1.2F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(99, 205).addBox(-7.35F, 5.1F, 4.8F, 1.2F, 1.8F, 0.6F, new CubeDeformation(0.0F))
                .texOffs(121, 218).addBox(-7.35F, 5.7F, 6.3F, 1.2F, 1.8F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(89, 217).addBox(-7.35F, 5.4F, 5.4F, 1.2F, 1.8F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(11, 112).addBox(-7.49F, 5.8F, 7.66F, 1.48F, 0.58F, 0.58F, new CubeDeformation(-0.2F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition barrel = gun.addOrReplaceChild("barrel", CubeListBuilder.create().texOffs(7, 107).addBox(-7.05F, 3.3F, -15.6F, 0.6F, 0.6F, 17.7F, new CubeDeformation(0.0F))
                .texOffs(211, 136).addBox(-7.05F, 3.0F, -15.6F, 0.6F, 0.3F, 1.5F, new CubeDeformation(0.0F))
                .texOffs(212, 140).addBox(-7.05F, 3.9F, -15.6F, 0.6F, 0.3F, 1.5F, new CubeDeformation(0.0F))
                .texOffs(214, 141).addBox(-7.65F, 3.3F, -15.3F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(215, 140).addBox(-7.65F, 3.0F, -15.3F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.3F, -15.3F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.0F, -15.3F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.9F, -15.3F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(214, 141).addBox(-7.65F, 3.9F, -15.3F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.3F, -14.7F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.0F, -14.7F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(190, 160).addBox(-7.35F, 3.0F, -14.7F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(215, 140).addBox(-7.65F, 3.0F, -15.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(215, 140).addBox(-7.65F, 3.0F, -14.4F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(244, 128).addBox(-6.45F, 3.0F, -14.4F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.0F, -15.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(190, 160).addBox(-7.65F, 3.9F, -15.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(207, 139).addBox(-7.65F, 3.9F, -14.4F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.9F, -14.4F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.9F, -15.0F, 0.6F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(214, 141).addBox(-7.65F, 3.3F, -14.7F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.3F, -14.1F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(218, 144).addBox(-6.45F, 3.0F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(244, 128).addBox(-6.15F, 3.0F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(210, 137).addBox(-7.65F, 3.0F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(208, 136).addBox(-7.65F, 3.9F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.15F, 3.9F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(190, 160).addBox(-6.45F, 3.9F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(216, 144).addBox(-6.45F, 3.9F, -14.7F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(190, 160).addBox(-7.35F, 3.0F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(190, 160).addBox(-7.35F, 3.9F, -14.1F, 0.3F, 0.3F, 0.3F, new CubeDeformation(0.0F))
                .texOffs(209, 139).addBox(-7.65F, 3.3F, -14.1F, 0.6F, 0.6F, 0.3F, new CubeDeformation(0.0F)), PartPose.offset(2.55F, -1.5F, -2.4F));

        PartDefinition shell = roboco.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(203, 34).addBox(-6.52F, 1.1311F, 4.2853F, 0.74F, 0.74F, 2.84F, new CubeDeformation(-0.1F))
                .texOffs(210, 26).addBox(-6.555F, 1.0961F, 3.7403F, 0.81F, 0.81F, 0.81F, new CubeDeformation(-0.15F)), PartPose.offset(-8.0F, -21.0F, -3.0F));

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

        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
    }
	
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        roboco.render(poseStack, buffer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return roboco;
    }
}