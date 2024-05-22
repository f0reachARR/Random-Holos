package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.NunnunEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;


public class NunnunRenderer extends MobRenderer<NunnunEntity, NunnunModel<NunnunEntity>> {

    private static final ResourceLocation NUNNUN_LOCATION = new ResourceLocation(RandomHolos.MOD_ID,"textures/entity/nunnun/nunnun.png");

    public NunnunRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NunnunModel<>(pContext.bakeLayer(ModModelLayers.NUNNUN_LAYER)), 0.25f);
    }

    @Override
    protected void scale(NunnunEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.999F, 0.999F, 0.999F);
        pPoseStack.translate(0.0F, 0.001F, 0.0F);
        float size = (float)pLivingEntity.getSize();
        float f2 = Mth.lerp(pPartialTickTime, pLivingEntity.oSquish, pLivingEntity.squish) / (size * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        pPoseStack.scale(f3 * size, 1.0F / f3 * size, f3 * size);
    }

    @Override
    public void render(NunnunEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float)pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
    @Override
    public ResourceLocation getTextureLocation(NunnunEntity pEntity) {
        return NUNNUN_LOCATION;
    }
}
