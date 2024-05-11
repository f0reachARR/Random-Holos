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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SINGLE_GLASS_HEEL.get()))
                    .title(Component.translatable("creativetab.holo"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PSYCHOPATH_AXE.get());
                        pOutput.accept(ModItems.GLASS_HEELS.get());
                        pOutput.accept(ModItems.SINGLE_GLASS_HEEL.get());

                        pOutput.accept(ModBlocks.METEORITE.get());
                        pOutput.accept(ModBlocks.CRUDE_METEORITE.get());
                        pOutput.accept(ModBlocks.RICH_METEORITE.get());
                        pOutput.accept(ModItems.METEORITE_PIECE.get());
                        pOutput.accept(ModItems.STELLARITE.get());

                        pOutput.accept(ModItems.ELITE_LAVA_BUCKET.get());

                        pOutput.accept(ModBlocks.KAKURIYO_PORTAL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> HOLO_SPAWN_EGGS = CREATIVE_MODE_TABS.register("holo_spawn_eggs",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SUISEI_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.holo_spawn_eggs"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SUISEI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKOP_SPAWN_EGG.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RANDOM_BLOCKS = CREATIVE_MODE_TABS.register("random_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FALLEN_LEAVES.get()))
                    .title(Component.translatable("creativetab.random_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.MAPLE_LOG.get());
                        pOutput.accept(ModBlocks.MAPLE_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_MAPLE_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_MAPLE_WOOD.get());
                        pOutput.accept(ModBlocks.MAPLE_PLANKS.get());
                        pOutput.accept(ModBlocks.MAPLE_SAPLING.get());
                        pOutput.accept(ModBlocks.MAPLE_LEAVES.get());
                        pOutput.accept(ModBlocks.FALLEN_LEAVES.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
