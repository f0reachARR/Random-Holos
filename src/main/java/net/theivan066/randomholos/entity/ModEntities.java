package net.theivan066.randomholos.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.GlassHeelsProjectileEntity;
import net.theivan066.randomholos.entity.custom.MikoEntity;
import net.theivan066.randomholos.entity.custom.MikopEntity;
import net.theivan066.randomholos.entity.custom.SuiseiEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RandomHolos.MOD_ID);

    public static final RegistryObject<EntityType<SuiseiEntity>> SUISEI =
            ENTITY_TYPES.register("suisei", () -> EntityType.Builder.of(SuiseiEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.8f).build("suisei"));
    public static final RegistryObject<EntityType<MikoEntity>> MIKO =
            ENTITY_TYPES.register("miko", () -> EntityType.Builder.of(MikoEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.8f).build("miko"));
    public static final RegistryObject<EntityType<MikopEntity>> MIKOP =
            ENTITY_TYPES.register("mikop", () -> EntityType.Builder.of(MikopEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.8f).build("mikop"));

    public static final RegistryObject<EntityType<GlassHeelsProjectileEntity>> GLASS_HEELS_PROJECTILE =
            ENTITY_TYPES.register("glass_heels_projectile",
                    () -> EntityType.Builder.<GlassHeelsProjectileEntity>of(GlassHeelsProjectileEntity::new, MobCategory.MISC)
                            .sized(0.3f, 0.3f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .setCustomClientFactory((spawnEntity, level) -> new GlassHeelsProjectileEntity(level))
                            .build("glass_heels_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
