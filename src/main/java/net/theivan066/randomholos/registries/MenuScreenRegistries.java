package net.theivan066.randomholos.registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.screen.HumidifierScreen;
import net.theivan066.randomholos.screen.ManufacturingTableScreen;
import net.theivan066.randomholos.screen.ModMenuTypes;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MenuScreenRegistries {
    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.MANUFACTURING_TABLE_MENU.get(), ManufacturingTableScreen::new);
        event.register(ModMenuTypes.HUMIDIFIER_MENU.get(), HumidifierScreen::new);
    }
}
