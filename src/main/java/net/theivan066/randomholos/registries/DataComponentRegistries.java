package net.theivan066.randomholos.registries;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class DataComponentRegistries {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, RandomHolos.MOD_ID);

    public static void register(IEventBus bus) {
        REGISTRAR.register(bus);
    }
}
