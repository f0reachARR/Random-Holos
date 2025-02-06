package net.theivan066.randomholos.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.theivan066.randomholos.RandomHolos;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyRegistryEvent {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.INSTANCE.ABILITY_KEY);
        event.register(ModKeyBindings.INSTANCE.SECONDARY_ABILITY_KEY);
    }
}
