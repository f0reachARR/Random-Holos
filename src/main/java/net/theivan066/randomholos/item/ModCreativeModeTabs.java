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

    public static final RegistryObject<CreativeModeTab> HOLO = CREATIVE_MODE_TABS.register("holo",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SINGLE_GLASS_HEEL.get()))
                    .title(Component.translatable("creativetab.holo"))
                    .displayItems((pParameters, pOutput) -> {
                        //sora
                        pOutput.accept(ModItems.SHAKEN_BOTTLE.get());
                        pOutput.accept(ModItems.SODA_WATER.get());
                        pOutput.accept(ModItems.CAN.get());
                        pOutput.accept(ModItems.TOKINO_SODA.get());
                        pOutput.accept(ModItems.RAW_RICE.get());
                        pOutput.accept(ModItems.UNCOOKED_RICE.get());
                        pOutput.accept(ModItems.COOKED_RICE.get());
                        pOutput.accept(ModItems.FLOUR.get());
                        pOutput.accept(ModItems.SHRIMP.get());
                        pOutput.accept(ModItems.COOKED_SHRIMP.get());
                        pOutput.accept(ModItems.OIL.get());
                        pOutput.accept(ModItems.EBI_KATSU.get());
                        pOutput.accept(ModItems.CHOCOLATE.get());
                        pOutput.accept(ModItems.CHOCOLATE_CAKE.get());
                        pOutput.accept(ModItems.CURRY.get());
                        pOutput.accept(ModItems.CURRY_BREAD.get());
                        pOutput.accept(ModItems.TOKINOSORA_SET_MEAL_A.get());
                        pOutput.accept(ModItems.TOKINOSORA_SET_MEAL_B.get());
                        //roboko
                        pOutput.accept(ModItems.BLUEPRINT.get());
                        pOutput.accept(ModItems.FIREARM_BLUEPRINT.get());
                        pOutput.accept(ModItems.BLADE_BLUEPRINT.get());
                        pOutput.accept(ModItems.BLUNT_BLUEPRINT.get());
                        pOutput.accept(ModItems.ALLOY_BLUEPRINT.get());
                        pOutput.accept(ModItems.ENERGY_CORE.get());
                        pOutput.accept(ModItems.ROBOTIC_PARTS.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModBlocks.STEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.MANUFACTURING_TABLE.get());
                        pOutput.accept(ModItems.ROBOGUN.get());
                        pOutput.accept(ModItems.ROBOGUN_AMMO.get());
                        pOutput.accept(ModItems.ROBOSNIPER.get());
                        pOutput.accept(ModItems.ROBOSNIPER_AMMO.get());
                        pOutput.accept(ModItems.CHIVES.get());
                        pOutput.accept(ModItems.SCALLION.get());
                        pOutput.accept(ModItems.CHINESE_CABBAGE.get());
                        pOutput.accept(ModItems.RED_CHILI_PEPPER.get());
                        pOutput.accept(ModItems.GREEN_CHILI_PEPPER.get());
                        pOutput.accept(ModItems.GHOST_PEPPER.get());
                        pOutput.accept(ModBlocks.COOKING_POT.get());
                        pOutput.accept(ModBlocks.KIMCHI_HOTPOT.get());
                        //suisei
                        pOutput.accept(ModItems.PSYCHOPATH_AXE.get());
                        pOutput.accept(ModItems.GLASS_HEELS.get());
                        pOutput.accept(ModItems.SINGLE_GLASS_HEEL.get());
                        pOutput.accept(ModBlocks.METEORITE.get());
                        pOutput.accept(ModBlocks.CRUDE_METEORITE.get());
                        pOutput.accept(ModBlocks.RICH_METEORITE.get());
                        pOutput.accept(ModItems.METEORITE_PIECE.get());
                        pOutput.accept(ModItems.STELLARITE.get());
                        pOutput.accept(ModItems.APPLE_JUICE.get());
                        //miko
                        pOutput.accept(ModItems.ELITE_LAVA_BUCKET.get());
                        pOutput.accept(ModItems.AHOGE.get());;
                        pOutput.accept(ModItems.AHOGE_STRING.get());
                        pOutput.accept(ModItems.MIKO_BOW.get());
                        //mikomet
                        pOutput.accept(ModItems.MIKOMET_BOW.get());
                        pOutput.accept(ModItems.MIKOMET_ARROW.get());
                        //azki
                        pOutput.accept(ModItems.GUESSER_PIN.get());
                        //ayame
                        pOutput.accept(ModBlocks.KAKURIYO_PORTAL.get());
                        //mats
                        pOutput.accept(ModItems.COPPER_ROD.get());
                        pOutput.accept(ModItems.REINFORCED_COPPER_ROD.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> HOLO_SPAWN_EGGS = CREATIVE_MODE_TABS.register("holo_spawn_eggs",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SUISEI_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.holo_spawn_eggs"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SORA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.NUNNUN_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ROBOCO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SUISEI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKOP_SPAWN_EGG.get());
                        pOutput.accept(ModItems.AZKI_SPAWN_EGG.get());
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

    public static final RegistryObject<CreativeModeTab> RANDOM_FOOD = CREATIVE_MODE_TABS.register("random_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAGUETTE.get()))
                    .title(Component.translatable("creativetab.random_food"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BAGUETTE.get());
                        pOutput.accept(ModItems.HUMONGOUS_BAGUETTE.get());
                        pOutput.accept(ModItems.GARGANTUAN_BAGUETTE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
