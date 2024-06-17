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
    public static final RegistryObject<CreativeModeTab> AASORA = CREATIVE_MODE_TABS.register("aasora",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TOKINOSORA_SET_MEAL_A.get()))
                    .title(Component.translatable("creativetab.sora"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SORA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.KUROSORA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.NUNNUN_SPAWN_EGG.get());
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
                        pOutput.accept(ModItems.BROKEN_HAIR_ACCESSORY.get());
                        pOutput.accept(ModItems.TALISMAN_OF_THE_ETHER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ABROBOCO = CREATIVE_MODE_TABS.register("abroboco",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROBOGUN.get()))
                    .title(Component.translatable("creativetab.roboco"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ROBOCO_SPAWN_EGG.get());
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
                        pOutput.accept(ModItems.KIMCHI.get());
                        pOutput.accept(ModBlocks.COOKING_POT.get());
                        pOutput.accept(ModBlocks.KIMCHI_HOTPOT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ACMIKO = CREATIVE_MODE_TABS.register("acmiko",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ELITE_LAVA_BUCKET.get()))
                    .title(Component.translatable("creativetab.miko"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.MIKO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKOP_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ELITE_LAVA_BUCKET.get());
                        pOutput.accept(ModItems.AHOGE.get());;
                        pOutput.accept(ModItems.AHOGE_STRING.get());
                        pOutput.accept(ModItems.MIKO_BOW.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ADSUISEI = CREATIVE_MODE_TABS.register("adsuisei",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GLASS_HEELS.get()))
                    .title(Component.translatable("creativetab.suisei"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SUISEI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PSYCHOPATH_AXE.get());
                        pOutput.accept(ModItems.GLASS_HEELS.get());
                        pOutput.accept(ModItems.SINGLE_GLASS_HEEL.get());
                        pOutput.accept(ModBlocks.METEORITE.get());
                        pOutput.accept(ModBlocks.CRUDE_METEORITE.get());
                        pOutput.accept(ModBlocks.RICH_METEORITE.get());
                        pOutput.accept(ModItems.METEORITE_PIECE.get());
                        pOutput.accept(ModItems.STELLARITE.get());
                        pOutput.accept(ModItems.APPLE_JUICE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> AEAZKI = CREATIVE_MODE_TABS.register("aeazki",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GUESSER_PIN.get()))
                    .title(Component.translatable("creativetab.azki"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AZKI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GUESSER_PIN.get());
                        pOutput.accept(ModItems.MICSABER.get());
                        pOutput.accept(ModBlocks.TOURMALINE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_TOURMALINE_ORE.get());
                        pOutput.accept(ModItems.TOURMALINE.get());
                        pOutput.accept(ModBlocks.TOURMALINE_BlOCK.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> AMAYAME = CREATIVE_MODE_TABS.register("amayame",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.KAKURIYO_PORTAL.get()))
                    .title(Component.translatable("creativetab.ayame"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.KAKURIYO_PORTAL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ZACOLLAB = CREATIVE_MODE_TABS.register("zacollab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MIKOMET_BOW.get()))
                    .title(Component.translatable("creativetab.collab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.MIKOMET_BOW.get());
                        pOutput.accept(ModItems.MIKOMET_ARROW.get());
                        pOutput.accept(ModItems.SIDEREAL_INGOT.get());
                        pOutput.accept(ModItems.STAR_DIVA_HAMMER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ZBMATERIALS = CREATIVE_MODE_TABS.register("zbmaterials",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STELLARITE.get()))
                    .title(Component.translatable("creativetab.materials"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ENERGY_CORE.get());
                        pOutput.accept(ModItems.ROBOTIC_PARTS.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModBlocks.STEEL_BLOCK.get());
                        pOutput.accept(ModItems.COPPER_ROD.get());
                        pOutput.accept(ModItems.REINFORCED_COPPER_ROD.get());
                        pOutput.accept(ModItems.AHOGE.get());;
                        pOutput.accept(ModItems.AHOGE_STRING.get());
                        pOutput.accept(ModBlocks.METEORITE.get());
                        pOutput.accept(ModBlocks.CRUDE_METEORITE.get());
                        pOutput.accept(ModBlocks.RICH_METEORITE.get());
                        pOutput.accept(ModItems.METEORITE_PIECE.get());
                        pOutput.accept(ModItems.STELLARITE.get());
                        pOutput.accept(ModBlocks.TOURMALINE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_TOURMALINE_ORE.get());
                        pOutput.accept(ModItems.TOURMALINE.get());
                        pOutput.accept(ModItems.SIDEREAL_INGOT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ZCHOLO_SPAWN_EGGS = CREATIVE_MODE_TABS.register("zcholo_spawn_eggs",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SUISEI_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.holo_spawn_eggs"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SORA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.KUROSORA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.NUNNUN_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ROBOCO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SUISEI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIKOP_SPAWN_EGG.get());
                        pOutput.accept(ModItems.AZKI_SPAWN_EGG.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ZZBRANDOM_BLOCKS = CREATIVE_MODE_TABS.register("zzbrandom_blocks",
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

    public static final RegistryObject<CreativeModeTab> ZZARANDOM_FOOD = CREATIVE_MODE_TABS.register("zzarandom_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAGUETTE.get()))
                    .title(Component.translatable("creativetab.random_food"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BAGUETTE.get());
                        pOutput.accept(ModItems.HUMONGOUS_BAGUETTE.get());
                        pOutput.accept(ModItems.GARGANTUAN_BAGUETTE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ZYBOOK = CREATIVE_MODE_TABS.register("zybook",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LETTER.get()))
                    .title(Component.translatable("creativetab.book"))
                    .displayItems((pParameters, pOutput) -> {
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
