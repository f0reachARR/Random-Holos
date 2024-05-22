package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.MikometArrowEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

public class MikometArrowRenderer extends EntityRenderer<MikometArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/mikomet_arrow/mikomet_arrow.png");
    protected MikometArrowModel model;
    public MikometArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new MikometArrowModel(pContext.bakeLayer(ModModelLayers.MIKOMET_ARROW_LAYER));
    }

    @Override
    public void render(MikometArrowEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, false);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MikometArrowEntity pEntity) {
        return TEXTURE;
    }
}
