package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.AzkiEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.AzkiVariant;

import java.util.Map;


public class AzkiRenderer extends MobRenderer<AzkiEntity, AzkiModel<AzkiEntity>> {

    private static final Map<AzkiVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(AzkiVariant.class), map -> {
                map.put(AzkiVariant.DEFAULT,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/azki/azki.png"));
                map.put(AzkiVariant.SECOND,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/azki/second_azki.png"));
                map.put(AzkiVariant.FOURTH,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/azki/fourth_azki.png"));
                map.put(AzkiVariant.DRESS,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/azki/dress_azki.png"));
            });

    public AzkiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AzkiModel<>(pContext.bakeLayer(ModModelLayers.AZKI_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(AzkiEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(AzkiEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
