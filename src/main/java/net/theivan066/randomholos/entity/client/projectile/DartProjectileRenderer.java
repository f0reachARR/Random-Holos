package net.theivan066.randomholos.entity.client.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.projectile.DartProjectileEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

public class DartProjectileRenderer extends EntityRenderer<DartProjectileEntity> {
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/note_projectile/note_projectile.png");
    protected DartProjectileModel model;

    public DartProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new DartProjectileModel(pContext.bakeLayer(ModModelLayers.DART_PROJECTILE_LAYER));
    }

    public void render(DartProjectileEntity entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0, -4, 0);
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(entity)), false, false);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        pPoseStack.scale(10, 10, 10);

        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(DartProjectileEntity pEntity) {
        return TEXTURE;
    }
}
