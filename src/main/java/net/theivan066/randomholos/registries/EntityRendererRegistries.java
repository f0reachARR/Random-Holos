package net.theivan066.randomholos.registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.client.*;
import net.theivan066.randomholos.entity.client.boss.KurosoraRenderer;
import net.theivan066.randomholos.entity.client.projectile.*;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EntityRendererRegistries {
    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SORA.get(), SoraRenderer::new);
        event.registerEntityRenderer(ModEntities.KUROSORA.get(), KurosoraRenderer::new);
        event.registerEntityRenderer(ModEntities.NUNNUN.get(), NunnunRenderer::new);
        event.registerEntityRenderer(ModEntities.ROBOCO.get(), RobocoRenderer::new);
        event.registerEntityRenderer(ModEntities.SUISEI.get(), SuiseiRenderer::new);
        event.registerEntityRenderer(ModEntities.MIKO.get(), MikoRenderer::new);
        event.registerEntityRenderer(ModEntities.MIKOP.get(), MikopRenderer::new);
        event.registerEntityRenderer(ModEntities.AZKI.get(), AzkiRenderer::new);
        event.registerEntityRenderer(ModEntities.GLASS_HEELS_PROJECTILE.get(), GlassHeelProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.GUESSER_PIN_PROJECTILE.get(), GuesserPinProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.NOTE_PROJECTILE.get(), NoteProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.DART_PROJECTILE.get(), DartProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.MIKOMET_ARROW.get(), MikometArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.BULLET_PROJECTILE.get(), BulletProjectileRenderer::new);

    }
}
