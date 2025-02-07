package net.theivan066.randomholos.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, RandomHolos.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<ManufacturingTableMenu>> MANUFACTURING_TABLE_MENU =
            registerMenuType(ManufacturingTableMenu::new, "manufacturing_table_menu");
    public static final DeferredHolder<MenuType<?>, MenuType<HumidifierMenu>> HUMIDIFIER_MENU =
            registerMenuType(HumidifierMenu::new, "humidifier_menu");

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, factory);
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

