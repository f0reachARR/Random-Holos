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
import net.theivan066.randomholos.entity.custom.projectile.NoteProjectileEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import org.joml.Random;

public class NoteProjectileRenderer extends EntityRenderer<NoteProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/note_projectile/note_projectile.png");
    protected NoteProjectileModel model;

    public NoteProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new NoteProjectileModel(pContext.bakeLayer(ModModelLayers.NOTE_PROJECTILE_LAYER));
    }

    public void render(NoteProjectileEntity entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(entity)), false, false);

        Random ran = new Random();
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, ((float) ran.nextInt(255) /255), ((float) ran.nextInt(255) /255), ((float) ran.nextInt(255) /255), 0f);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(NoteProjectileEntity pEntity) {
        return TEXTURE;
    }
}
