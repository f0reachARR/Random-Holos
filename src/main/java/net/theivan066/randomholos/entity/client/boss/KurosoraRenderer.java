package net.theivan066.randomholos.entity.client.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

public class KurosoraRenderer extends MobRenderer<KurosoraEntity, KurosoraModel<KurosoraEntity>> {
    private static final ResourceLocation KUROSORA_LOCATION = new ResourceLocation(RandomHolos.MOD_ID,"textures/entity/kurosora/kurosora.png");

    public KurosoraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KurosoraModel<>(pContext.bakeLayer(ModModelLayers.KUROSORA_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(KurosoraEntity pEntity) {
        return KUROSORA_LOCATION;
    }

    @Override
    public void render(KurosoraEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
