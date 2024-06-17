package net.theivan066.randomholos.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;

@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyRegistryEvent {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.INSTANCE.ABILITY_KEY);
        event.register(ModKeyBindings.INSTANCE.SECONDARY_ABILITY_KEY);
    }
}
