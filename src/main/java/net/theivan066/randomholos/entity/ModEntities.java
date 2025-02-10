package net.theivan066.randomholos.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.*;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.entity.custom.projectile.*;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, RandomHolos.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<SoraEntity>> SORA =
            ENTITY_TYPES.register("sora", () -> EntityType.Builder.of(SoraEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("sora"));
    public static final DeferredHolder<EntityType<?>, EntityType<KurosoraEntity>> KUROSORA =
            ENTITY_TYPES.register("kurosora", () -> EntityType.Builder.of(KurosoraEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("kurosora"));
    public static final DeferredHolder<EntityType<?>, EntityType<NunnunEntity>> NUNNUN =
            ENTITY_TYPES.register("nunnun", () -> EntityType.Builder.of(NunnunEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1f).build("nunnun"));
    public static final DeferredHolder<EntityType<?>, EntityType<RobocoEntity>> ROBOCO =
            ENTITY_TYPES.register("roboco", () -> EntityType.Builder.of(RobocoEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("roboco"));
    public static final DeferredHolder<EntityType<?>, EntityType<SuiseiEntity>> SUISEI =
            ENTITY_TYPES.register("suisei", () -> EntityType.Builder.of(SuiseiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("suisei"));
    public static final DeferredHolder<EntityType<?>, EntityType<MikoEntity>> MIKO =
            ENTITY_TYPES.register("miko", () -> EntityType.Builder.of(MikoEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("miko"));
    public static final DeferredHolder<EntityType<?>, EntityType<MikopEntity>> MIKOP =
            ENTITY_TYPES.register("mikop", () -> EntityType.Builder.of(MikopEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.6f).build("mikop"));
    public static final DeferredHolder<EntityType<?>, EntityType<AzkiEntity>> AZKI =
            ENTITY_TYPES.register("azki", () -> EntityType.Builder.of(AzkiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f).build("azki"));
    public static final DeferredHolder<EntityType<?>, EntityType<GlassHeelsProjectileEntity>> GLASS_HEELS_PROJECTILE =
            ENTITY_TYPES.register("glass_heels_projectile",
                    () -> EntityType.Builder.<GlassHeelsProjectileEntity>of(GlassHeelsProjectileEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
//                            .setCustomClientFactory((spawnEntity, level) -> new GlassHeelsProjectileEntity(level))
                            .build("glass_heels_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<GuesserPinProjectileEntity>> GUESSER_PIN_PROJECTILE =
            ENTITY_TYPES.register("guesser_pin_projectile",
                    () -> EntityType.Builder.<GuesserPinProjectileEntity>of(GuesserPinProjectileEntity::new, MobCategory.MISC)
                            .sized(0.3f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
//                            .setCustomClientFactory((spawnEntity, level) -> new GuesserPinProjectileEntity(level))
                            .build("guesser_pin_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<BulletProjectileEntity>> BULLET_PROJECTILE =
            ENTITY_TYPES.register("bullet_projectile",
                    () -> EntityType.Builder.<BulletProjectileEntity>of(BulletProjectileEntity::new, MobCategory.MISC)
                            .sized(0.0625f, 0.0625f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("bullet_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<NoteProjectileEntity>> NOTE_PROJECTILE =
            ENTITY_TYPES.register("note_projectile",
                    () -> EntityType.Builder.<NoteProjectileEntity>of(NoteProjectileEntity::new, MobCategory.MISC)
                            .sized(0.0625f, 0.4375f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("note_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<DartProjectileEntity>> DART_PROJECTILE =
            ENTITY_TYPES.register("dart_projectile",
                    () -> EntityType.Builder.<DartProjectileEntity>of(DartProjectileEntity::new, MobCategory.MISC)
                            .sized(0.2f, 1f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("dart_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<MikometArrowEntity>> MIKOMET_ARROW =
            ENTITY_TYPES.register("mikomet_arrow",
                    () -> EntityType.Builder.<MikometArrowEntity>of(MikometArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("mikomet_arrow"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
