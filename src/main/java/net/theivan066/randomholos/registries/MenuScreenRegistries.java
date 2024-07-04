package net.theivan066.randomholos.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.theivan066.randomholos.screen.HumidifierScreen;
import net.theivan066.randomholos.screen.ManufacturingTableScreen;
import net.theivan066.randomholos.screen.ModMenuTypes;

public class MenuScreenRegistries {
    public static void registerMenuScreens() {
        MenuScreens.register(ModMenuTypes.MANUFACTURING_TABLE_MENU.get(), ManufacturingTableScreen::new);
        MenuScreens.register(ModMenuTypes.HUMIDIFIER_MENU.get(), HumidifierScreen::new);
    }
}
