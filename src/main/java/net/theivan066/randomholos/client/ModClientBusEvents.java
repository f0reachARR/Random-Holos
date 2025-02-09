package net.theivan066.randomholos.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.effect.ModEffects;
import org.joml.Matrix3f;
import org.joml.Matrix4f;


@EventBusSubscriber(modid = RandomHolos.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class ModClientBusEvents {
    //Effect
    private static final ResourceLocation PIN_TEXTURE = ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/particle/guesser_pin.png");

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<LivingEntity, ?> event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = entity.getEffect(ModEffects.ZERO_GUESSER_EFFECT);

        if (effectInstance != null) {
            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();
            double d0 = entity.getBbHeight() + 2.5D + (effectInstance.getAmplifier() * 0.1);
            // Adjust the position of the pin above the entity
            poseStack.translate(0.0D, d0, 0.0D);

            // Scaling the pin
            float scale = (effectInstance.getAmplifier() * 0.2f) + 0.75f; // Adjust this value to control the size of the pin
            poseStack.scale(scale, scale, scale);

            // Binding the custom texture
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, PIN_TEXTURE);

            // Render the pin (assuming a square pin texture of 16x16 pixels)
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();

            MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
            var buffer = bufferSource.getBuffer(RenderType.entityCutout(PIN_TEXTURE));

            renderQuad(poseStack, buffer);

            RenderSystem.disableBlend();
            poseStack.popPose();
        }
    }

    public static void renderQuad(PoseStack poseStack, VertexConsumer buffer) {
        PoseStack.Pose lastPose = poseStack.last();
        Matrix4f pose = lastPose.pose();
        Matrix3f normal = lastPose.normal();

        float size = 0.5F;

        // Front face
        buffer.addVertex(pose, -size, size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, -1.0F);
        buffer.addVertex(pose, size, size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, -1.0F);
        buffer.addVertex(pose, size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, -1.0F);
        buffer.addVertex(pose, -size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, -1.0F);

        // Back face
        buffer.addVertex(pose, -size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, 1.0F);
        buffer.addVertex(pose, size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, 1.0F);
        buffer.addVertex(pose, size, size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, 1.0F);
        buffer.addVertex(pose, -size, size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(lastPose, 0.0F, 0.0F, 1.0F);
    }
}
