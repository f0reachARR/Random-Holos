package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.SoraEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.SoraVariant;

import java.util.Map;


public class SoraRenderer extends MobRenderer<SoraEntity, SoraModel<SoraEntity>> {

    private static final Map<SoraVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SoraVariant.class), map -> {
                map.put(SoraVariant.DEFAULT,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/sora/sora.png"));
                map.put(SoraVariant.CASUAL,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/sora/casual_sora.png"));
                map.put(SoraVariant.HOODIE,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/sora/hoodie_sora.png"));
              });

    public SoraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SoraModel<>(pContext.bakeLayer(ModModelLayers.SORA_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SoraEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(SoraEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

//        if(pEntity.getVariant().getId() == 1) {
//            pMatrixStack.scale(1f, 1f, 1f);
//        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
