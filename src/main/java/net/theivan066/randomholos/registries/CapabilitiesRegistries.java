package net.theivan066.randomholos.registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.entity.ModBlockEntities;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CapabilitiesRegistries {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.HUMIDIFIER_BE.get(),
                (o, direction) -> o.getItemHandler());
    }
}
