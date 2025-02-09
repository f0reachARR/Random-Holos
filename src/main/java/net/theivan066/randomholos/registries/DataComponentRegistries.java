package net.theivan066.randomholos.registries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.item.custom.base_items.GunItem;

public class DataComponentRegistries {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, RandomHolos.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GunItem.GunItemRecord>> GUN_ITEM_RECORD
            = REGISTRAR.registerComponentType(
            "gun_item_record",
            builder -> builder
                    .persistent(GunItem.GunItemRecord.CODEC)
                    .networkSynchronized(GunItem.GunItemRecord.STREAM_CODEC));

    public static void register(IEventBus bus) {
        REGISTRAR.register(bus);
    }
}
