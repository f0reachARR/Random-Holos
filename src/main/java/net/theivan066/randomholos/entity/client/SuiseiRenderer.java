package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.SuiseiEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.SuiseiVariant;

import java.util.Map;


public class SuiseiRenderer extends MobRenderer<SuiseiEntity, SuiseiModel<SuiseiEntity>> {

    private static final Map<SuiseiVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SuiseiVariant.class), map -> {
                map.put(SuiseiVariant.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/suisei.png"));
                map.put(SuiseiVariant.KOJINSEI,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/kojinsei_suisei.png"));
                map.put(SuiseiVariant.NORMAL_IDOL,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/normal_idol_suisei.png"));
                map.put(SuiseiVariant.SAILOR,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/sailor_suisei.png"));
                map.put(SuiseiVariant.WAR_MAID,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/war_maid_suisei.png"));
                map.put(SuiseiVariant.IKEMEN_NI_NACCHATTA,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/suisei/ikemen_ni_nacchatta_suisei.png"));
            });

    public SuiseiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SuiseiModel<>(pContext.bakeLayer(ModModelLayers.SUISEI_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SuiseiEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(SuiseiEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

//        if(pEntity.getVariant().getId() == 1) {
//            pMatrixStack.scale(1f, 1f, 1f);
//        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
