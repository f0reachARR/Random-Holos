package net.theivan066.randomholos.enchantment;

import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.item.ModItems;

import java.util.List;
import java.util.Optional;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, RandomHolos.MOD_ID);


    public static final DeferredHolder<Enchantment, Enchantment> FAST_DRAW =
            ENCHANTMENTS.register("fast_draw",
                    () -> new Enchantment(
                            Component.translatable("enchantment.randomholos.fast_draw"),
                            new Enchantment.EnchantmentDefinition(
                                    HolderSet.direct(ModItems.ROBOGUN, ModItems.MIKO_BOW, ModItems.MIKOMET_BOW),
                                    Optional.empty(),
                                    30,
                                    4,
                                    new Enchantment.Cost(12, 15),
                                    new Enchantment.Cost(45, 0),
                                    2,
                                    List.of(EquipmentSlotGroup.MAINHAND)
                            ),
                            HolderSet.empty(),
                            DataComponentMap.EMPTY
                    )
            );

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
