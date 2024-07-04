package net.theivan066.randomholos.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
        DeferredRegister.create(ForgeRegistries.MENU_TYPES, RandomHolos.MOD_ID);

    public static final RegistryObject<MenuType<ManufacturingTableMenu>> MANUFACTURING_TABLE_MENU =
            registerMenuType(ManufacturingTableMenu::new, "manufacturing_table_menu");
    public static final RegistryObject<MenuType<HumidifierMenu>> HUMIDIFIER_MENU =
            registerMenuType(HumidifierMenu::new, "humidifier_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

