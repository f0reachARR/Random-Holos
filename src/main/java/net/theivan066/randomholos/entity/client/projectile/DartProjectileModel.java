package net.theivan066.randomholos.entity.client.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DartProjectileModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart bb_main;

    public DartProjectileModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(-4, -5).addBox(-0.5F, -2.0F, 0.3F, 1.0F, 1.0F, 7.0F, new CubeDeformation(-0.3F))
                .texOffs(2, 0).addBox(0.0F, -3.0F, 5.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 0).addBox(-1.5F, -1.5F, 5.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(3, 1).addBox(0.0F, -2.5F, 4.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-1.0F, -1.5F, 4.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, -1).addBox(-0.5F, -2.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F))
                .texOffs(2, 1).addBox(-0.5F, -2.0F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.3F))
                .texOffs(-1, -2).addBox(-0.5F, -2.0F, -3.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        bb_main.render(poseStack, vertexConsumer, i, i1, i2);
    }
}
