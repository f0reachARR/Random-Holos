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
import net.minecraft.util.Mth;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.projectile.BulletProjectileEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

public class BulletProjectileRenderer extends EntityRenderer<BulletProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/bullet_projectile/bullet_projectile.png");
    protected BulletProjectileModel model;

    public BulletProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new BulletProjectileModel(pContext.bakeLayer(ModModelLayers.BULLET_PROJECTILE_LAYER));
    }

    public void render(BulletProjectileEntity entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(entity)), false, false);

        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, ((float) 128 /255), ((float) 78 /255), ((float) 127 /255), 0.5f);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BulletProjectileEntity pEntity) {
        return TEXTURE;
    }
}
