package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.RobocoEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.RobocoVariant;

import java.util.Map;


public class RobocoRenderer extends MobRenderer<RobocoEntity, RobocoModel<RobocoEntity>> {

    private static final Map<RobocoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RobocoVariant.class), map -> {
                map.put(RobocoVariant.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/roboco.png"));
                map.put(RobocoVariant.HOODIE_ROBOCO,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/hoodie_roboco.png"));
                map.put(RobocoVariant.SPORT_ROBOCO,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/sport_roboco.png"));
                map.put(RobocoVariant.COAT_ROBOCO,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/coat_roboco.png"));
                map.put(RobocoVariant.NIGHTY_DRESS_ROBOCO,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/nighty_dress_roboco.png"));
                map.put(RobocoVariant.SUMMER_ROBOCO,
                        ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/entity/roboco/summer_roboco.png"));
            });

    public RobocoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RobocoModel<>(pContext.bakeLayer(ModModelLayers.ROBOCO_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RobocoEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(RobocoEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
