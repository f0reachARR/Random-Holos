package net.theivan066.randomholos.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RandomHolos.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RANDOM_TEST = CREATIVE_MODE_TABS.register("random_test",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST.get()))
                    .title(Component.translatable("creativetab.random_test"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TEST.get());
                        pOutput.accept(ModBlocks.TEST_BLOCK.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RANDOM_FOOD = CREATIVE_MODE_TABS.register("random_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAGUETTE.get()))
                    .title(Component.translatable("creativetab.random_food"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BAGUETTE.get());
                        pOutput.accept(ModItems.HUMONGOUS_BAGUETTE.get());
                        pOutput.accept(ModItems.GARGANTUAN_BAGUETTE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> HOLO = CREATIVE_MODE_TABS.register("holo",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAGUETTE.get()))
                    .title(Component.translatable("creativetab.holo"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PSYCHOPATH_AXE.get());
                        pOutput.accept(ModItems.GLASS_HEELS.get());
                        pOutput.accept(ModItems.SINGLE_GLASS_HEEL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
