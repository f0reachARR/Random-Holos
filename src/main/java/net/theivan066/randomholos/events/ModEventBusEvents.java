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
import net.theivan066.randomholos.entity.client.MikoModel;
import net.theivan066.randomholos.entity.client.MikopModel;
import net.theivan066.randomholos.entity.client.SuiseiModel;
import net.theivan066.randomholos.entity.custom.MikoEntity;
import net.theivan066.randomholos.entity.custom.MikopEntity;
import net.theivan066.randomholos.entity.custom.SuiseiEntity;
import net.theivan066.randomholos.entity.layers.ModModelLayers;

@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SUISEI_LAYER, SuiseiModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIKO_LAYER, MikoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIKOP_LAYER, MikopModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SUISEI.get(), SuiseiEntity.createAttributes().build());
        event.put(ModEntities.MIKO.get(), MikoEntity.createAttributes().build());
        event.put(ModEntities.MIKOP.get(), MikopEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.MIKO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
