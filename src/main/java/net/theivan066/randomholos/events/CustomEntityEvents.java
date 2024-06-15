package net.theivan066.randomholos.events;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.client.*;
import net.theivan066.randomholos.entity.client.boss.KurosoraModel;
import net.theivan066.randomholos.entity.client.projectile.BulletProjectileModel;
import net.theivan066.randomholos.entity.client.projectile.DartProjectileModel;
import net.theivan066.randomholos.entity.client.projectile.MikometArrowModel;
import net.theivan066.randomholos.entity.client.projectile.NoteProjectileModel;
import net.theivan066.randomholos.entity.custom.*;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomEntityEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SORA_LAYER, SoraModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.NUNNUN_LAYER, NunnunModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.KUROSORA_LAYER, KurosoraModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ROBOCO_LAYER, RobocoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SUISEI_LAYER, SuiseiModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIKO_LAYER, MikoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIKOP_LAYER, MikopModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.AZKI_LAYER, AzkiModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.NOTE_PROJECTILE_LAYER, NoteProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DART_PROJECTILE_LAYER, DartProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIKOMET_ARROW_LAYER, MikometArrowModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BULLET_PROJECTILE_LAYER, BulletProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SORA.get(), SoraEntity.createAttributes().build());
        event.put(ModEntities.NUNNUN.get(), NunnunEntity.createAttributes().build());
        event.put(ModEntities.KUROSORA.get(), KurosoraEntity.createAttributes().build());
        event.put(ModEntities.ROBOCO.get(), RobocoEntity.createAttributes().build());
        event.put(ModEntities.SUISEI.get(), SuiseiEntity.createAttributes().build());
        event.put(ModEntities.MIKO.get(), MikoEntity.createAttributes().build());
        event.put(ModEntities.MIKOP.get(), MikopEntity.createAttributes().build());
        event.put(ModEntities.AZKI.get(), AzkiEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.MIKO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.MIKOP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.SUISEI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.AZKI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.SORA.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.NUNNUN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
