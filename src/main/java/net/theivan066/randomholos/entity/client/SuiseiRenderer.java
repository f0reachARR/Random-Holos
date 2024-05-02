package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.SuiseiEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

public class SuiseiRenderer extends MobRenderer<SuiseiEntity, SuiseiModel<SuiseiEntity>> {
private static final ResourceLocation SUISEI_LOCATION = new ResourceLocation(RandomHolos.MOD_ID,"textures/entity/suisei.png");

    public SuiseiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SuiseiModel<>(pContext.bakeLayer(ModModelLayers.SUISEI_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SuiseiEntity pEntity) {
        return SUISEI_LOCATION;
    }

    @Override
    public void render(SuiseiEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
