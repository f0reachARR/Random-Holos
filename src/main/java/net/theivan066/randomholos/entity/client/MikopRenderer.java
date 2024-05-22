package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.MikopEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.MikopVariant;

import java.util.Map;


public class MikopRenderer extends MobRenderer<MikopEntity, MikopModel<MikopEntity>> {

    private static final Map<MikopVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MikopVariant.class), map -> {
                map.put(MikopVariant.DEFAULT,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/mikop/mikop.png"));
                map.put(MikopVariant.NYE,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/mikop/mikop_nye.png"));
                map.put(MikopVariant.SHADES,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/mikop/mikop_shades.png"));
                map.put(MikopVariant.SHADES_2,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/mikop/mikop_shades_2.png"));
           });

    public MikopRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MikopModel<>(pContext.bakeLayer(ModModelLayers.MIKOP_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MikopEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(MikopEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.35f, 0.35f, 0.35f);
        } else
        pMatrixStack.scale(0.75f, 0.75f, 0.75f);
//        if(pEntity.getVariant().getId() == 1) {
//            pMatrixStack.scale(1f, 1f, 1f);
//        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
