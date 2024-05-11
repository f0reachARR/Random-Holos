package net.theivan066.randomholos.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.MikoEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;
import net.theivan066.randomholos.entity.variant.MikoVariant;

import java.util.Map;


public class MikoRenderer extends MobRenderer<MikoEntity, MikoModel<MikoEntity>> {

    private static final Map<MikoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MikoVariant.class), map -> {
                map.put(MikoVariant.DEFAULT,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/miko.png"));
                map.put(MikoVariant.MIKOHUKU_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/mikohuku_miko.png"));
                map.put(MikoVariant.PRIVATE_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/private_miko.png"));
                map.put(MikoVariant.SHOUGATSU_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/shougatsu_miko.png"));
                map.put(MikoVariant.BATTLESUIT_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/battlesuit_miko.png"));
                map.put(MikoVariant.SCHOOL_UNIFORM_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/school_uniform_miko.png"));
                map.put(MikoVariant.DEKAKE_MIKO,
                        new ResourceLocation(RandomHolos.MOD_ID, "textures/entity/miko/dekake_miko.png"));
            });

    public MikoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MikoModel<>(pContext.bakeLayer(ModModelLayers.MIKO_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MikoEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(MikoEntity pEntity, float pEntityYaw, float pPartialTicks,
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
