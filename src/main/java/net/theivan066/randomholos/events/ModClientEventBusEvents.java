package net.theivan066.randomholos.events;

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
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.effect.ModEffects;
import net.theivan066.randomholos.particle.GuesserPinParticles;
import net.theivan066.randomholos.particle.ModParticles;
import org.joml.Matrix3f;
import org.joml.Matrix4f;


@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModClientEventBusEvents {
    //Particle
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.GUESSER_PIN_PARTICLES.get(), GuesserPinParticles.Provider::new);
    }


    //Effect
    private static final ResourceLocation PIN_TEXTURE = new ResourceLocation(RandomHolos.MOD_ID, "textures/particle/guesser_pin.png");

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<LivingEntity, ?> event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effectInstance = entity.getEffect(ModEffects.ZERO_GUESSER_EFFECT.get());

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
            System.out.println("Rendering pin on entity: " + entity.getType());


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
        buffer.vertex(pose, -size, size, 0.0F).color(255, 255, 255, 255).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, -1.0F).endVertex();
        buffer.vertex(pose, size, size, 0.0F).color(255, 255, 255, 255).uv(1.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, -1.0F).endVertex();
        buffer.vertex(pose, size, -size, 0.0F).color(255, 255, 255, 255).uv(1.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, -1.0F).endVertex();
        buffer.vertex(pose, -size, -size, 0.0F).color(255, 255, 255, 255).uv(0.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, -1.0F).endVertex();

        // Back face
        buffer.vertex(pose, -size, -size, 0.0F).color(255, 255, 255, 255).uv(0.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
        buffer.vertex(pose, size, -size, 0.0F).color(255, 255, 255, 255).uv(1.0F, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
        buffer.vertex(pose, size, size, 0.0F).color(255, 255, 255, 255).uv(1.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
        buffer.vertex(pose, -size, size, 0.0F).color(255, 255, 255, 255).uv(0.0F, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
    }
}
