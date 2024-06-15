package net.theivan066.randomholos.registries;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.client.*;
import net.theivan066.randomholos.entity.client.boss.KurosoraRenderer;
import net.theivan066.randomholos.entity.client.projectile.*;

public class EntityRendererRegistries {
    public static void registerEntityRenderer() {
        EntityRenderers.register(ModEntities.SORA.get(), SoraRenderer::new);
        EntityRenderers.register(ModEntities.KUROSORA.get(), KurosoraRenderer::new);
        EntityRenderers.register(ModEntities.NUNNUN.get(), NunnunRenderer::new);
        EntityRenderers.register(ModEntities.ROBOCO.get(), RobocoRenderer::new);
        EntityRenderers.register(ModEntities.SUISEI.get(), SuiseiRenderer::new);
        EntityRenderers.register(ModEntities.MIKO.get(), MikoRenderer::new);
        EntityRenderers.register(ModEntities.MIKOP.get(), MikopRenderer::new);
        EntityRenderers.register(ModEntities.AZKI.get(), AzkiRenderer::new);
        EntityRenderers.register(ModEntities.GLASS_HEELS_PROJECTILE.get(), GlassHeelProjectileRenderer::new);
        EntityRenderers.register(ModEntities.GUESSER_PIN_PROJECTILE.get(), GuesserPinProjectileRenderer::new);
        EntityRenderers.register(ModEntities.NOTE_PROJECTILE.get(), NoteProjectileRenderer::new);
        EntityRenderers.register(ModEntities.DART_PROJECTILE.get(), DartProjectileRenderer::new);
        EntityRenderers.register(ModEntities.MIKOMET_ARROW.get(), MikometArrowRenderer::new);
        EntityRenderers.register(ModEntities.BULLET_PROJECTILE.get(), BulletProjectileRenderer::new);

    }
}
