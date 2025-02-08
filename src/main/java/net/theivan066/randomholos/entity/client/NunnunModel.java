package net.theivan066.randomholos.entity.client;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.theivan066.randomholos.entity.animations.ModAnimationDefinitions;
import net.theivan066.randomholos.entity.custom.NunnunEntity;

public class NunnunModel<T extends NunnunEntity> extends HierarchicalModel<T> {
    private final ModelPart nunnun;
    private final ModelPart head;

    public NunnunModel(ModelPart root) {
        this.nunnun = root.getChild("nunnun");

        this.head = nunnun.getChild("head");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition nunnun = partdefinition.addOrReplaceChild("nunnun", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = nunnun.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.0F));

        PartDefinition bottom = head.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bottomlower = bottom.addOrReplaceChild("bottomlower", CubeListBuilder.create().texOffs(51, 46).addBox(-6.5F, -1.0F, -6.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(6.5F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(51, 46).addBox(-6.5F, -1.0F, 5.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(-7.5F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(-6.5F, -1.0F, -5.0F, 13.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bottomupper = bottom.addOrReplaceChild("bottomupper", CubeListBuilder.create().texOffs(2, 2).addBox(-7.5F, -2.0F, -6.0F, 15.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(-8.5F, -2.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(60, 60).addBox(-6.5F, -2.0F, -7.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(7.5F, -2.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(13, 13).addBox(-6.5F, -2.0F, 6.0F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition top = head.addOrReplaceChild("top", CubeListBuilder.create().texOffs(13, 13).addBox(-6.5F, -10.0F, 6.0F, 13.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(-7.5F, -9.0F, -6.0F, 15.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(13, 13).addBox(6.5F, -9.0F, 6.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 13).addBox(-7.5F, -9.0F, 6.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 61).addBox(-6.5F, -10.0F, -7.0F, 13.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 60).addBox(-7.5F, -9.0F, -7.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(53, 45).addBox(6.5F, -9.0F, -7.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 39).addBox(-5.5F, -12.0F, -4.0F, 11.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 35).addBox(-7.5F, -10.0F, -6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(40, 35).addBox(6.5F, -10.0F, -6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(40, 35).addBox(-6.5F, -10.0F, -6.0F, 13.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(-6.5F, -11.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(-5.5F, -11.0F, -5.0F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(42, 37).addBox(5.5F, -11.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(51, 46).addBox(-5.5F, -11.0F, -6.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(51, 46).addBox(-5.5F, -11.0F, 5.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(51, 46).addBox(-4.5F, -12.0F, -5.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(51, 46).addBox(-4.5F, -12.0F, 4.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(-9.5F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(-8.5F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(7.5F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(8.5F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(-8.5F, -9.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(59, 24).addBox(-8.5F, -9.0F, -6.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 24).addBox(-8.5F, -9.0F, 5.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(7.5F, -9.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(59, 24).addBox(7.5F, -9.0F, -6.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 24).addBox(7.5F, -9.0F, 5.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(NunnunEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.NUNNUN_WALK, limbSwing, limbSwingAmount, 1f, 2.5f);
        this.animate(entity.idleAnimationState, ModAnimationDefinitions.NUNNUN_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(NunnunEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return nunnun;
    }
}