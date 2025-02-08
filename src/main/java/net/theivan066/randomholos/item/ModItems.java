package net.theivan066.randomholos.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.custom.*;
import net.theivan066.randomholos.item.custom.base_items.DrinkItem;
import net.theivan066.randomholos.item.custom.base_items.GunItem;
import net.theivan066.randomholos.item.custom.base_items.HammerItem;
import net.theivan066.randomholos.item.custom.base_items.ShiftTooltipItem;
import net.theivan066.randomholos.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(RandomHolos.MOD_ID);

    public static final DeferredItem<Item> TEST = ITEMS.register("test", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HOLO_LOGO = ITEMS.register("holo_logo", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LETTER = ITEMS.register("letter", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().food(ModFoods.BAGUETTE)));
    public static final DeferredItem<Item> HUMONGOUS_BAGUETTE = ITEMS.register("humongous_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.HUMONGOUS_BAGUETTE)));
    public static final DeferredItem<Item> GARGANTUAN_BAGUETTE = ITEMS.register("gargantuan_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.GARGANTUAN_BAGUETTE)));


    public static final DeferredItem<Item> SHAKEN_BOTTLE = ITEMS.register("shaken_bottle",
            () -> new ShakenBottleItem(new Item.Properties()));
    public static final DeferredItem<Item> SODA_WATER = ITEMS.register("soda_water",
            () -> new DrinkItem(new Item.Properties().food(ModFoods.SODA_WATER)));
    public static final DeferredItem<Item> CAN = ITEMS.register("can", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TOKINO_SODA = ITEMS.register("tokino_soda",
            () -> new DrinkItem(new Item.Properties().food(ModFoods.TOKINO_SODA)));
    public static final DeferredItem<Item> RAW_RICE = ITEMS.register("raw_rice", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNCOOKED_RICE = ITEMS.register("uncooked_rice", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COOKED_RICE = ITEMS.register("cooked_rice",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_RICE).craftRemainder(Items.BOWL)));
    public static final DeferredItem<Item> FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHRIMP = ITEMS.register("shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.SHRIMP)));
    public static final DeferredItem<Item> COOKED_SHRIMP = ITEMS.register("cooked_shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SHRIMP)));
    public static final DeferredItem<Item> OIL = ITEMS.register("oil", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EBI_KATSU = ITEMS.register("ebi_katsu",
            () -> new Item(new Item.Properties().food(ModFoods.EBI_KATSU)));
    public static final DeferredItem<Item> CHOCOLATE = ITEMS.register("chocolate",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE)));
    public static final DeferredItem<Item> CHOCOLATE_CAKE = ITEMS.register("chocolate_cake",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_CAKE)));
    public static final DeferredItem<Item> CURRY = ITEMS.register("curry",
            () -> new Item(new Item.Properties().food(ModFoods.CURRY)));
    public static final DeferredItem<Item> CURRY_BREAD = ITEMS.register("curry_bread",
            () -> new Item(new Item.Properties().food(ModFoods.CURRY_BREAD)));
    public static final DeferredItem<Item> TOKINOSORA_SET_MEAL_A = ITEMS.register("tokinosora_set_meal_a",
            () -> new Item(new Item.Properties().food(ModFoods.TOKINOSORA_SET_MEAL_A)));
    public static final DeferredItem<Item> TOKINOSORA_SET_MEAL_B = ITEMS.register("tokinosora_set_meal_b",
            () -> new Item(new Item.Properties().food(ModFoods.TOKINOSORA_SET_MEAL_B)));
    public static final DeferredItem<Item> BROKEN_HAIR_ACCESSORY = ITEMS.register("broken_hair_accessory", () -> new ShiftTooltipItem(new Item.Properties(), Component.translatable("tooltip.randomholos.broken_hair_accessory.shift")));
    public static final DeferredItem<Item> TALISMAN_OF_THE_ETHER = ITEMS.register("talisman_of_the_ether", () -> new TalismanOfTheEtherItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> BLUEPRINT = ITEMS.register("blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALLOY_BLUEPRINT = ITEMS.register("alloy_blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIREARM_BLUEPRINT = ITEMS.register("firearm_blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLADE_BLUEPRINT = ITEMS.register("blade_blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLUNT_BLUEPRINT = ITEMS.register("blunt_blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ENERGY_CORE = ITEMS.register("energy_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROBOTIC_PARTS = ITEMS.register("robotic_parts", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROBOGUN_AMMO = ITEMS.register("robogun_ammo", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROBOSNIPER_AMMO = ITEMS.register("robosniper_ammo", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHIVES = ITEMS.register("chives",
            () -> new ItemNameBlockItem(ModBlocks.CHIVES_CROP.get(), new Item.Properties().food(ModFoods.CHIVES)));
    public static final DeferredItem<Item> SCALLION = ITEMS.register("scallion",
            () -> new ItemNameBlockItem(ModBlocks.SCALLION_CROP.get(), new Item.Properties().food(ModFoods.SCALLION)));
    public static final DeferredItem<Item> CHINESE_CABBAGE = ITEMS.register("chinese_cabbage",
            () -> new ItemNameBlockItem(ModBlocks.CHINESE_CABBAGE_CROP.get(), new Item.Properties().food(ModFoods.CHINESE_CABBAGE)));
    public static final DeferredItem<Item> RED_CHILI_PEPPER = ITEMS.register("red_chili_pepper",
            () -> new ItemNameBlockItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.RED_CHILI_PEPPER)));
    public static final DeferredItem<Item> GREEN_CHILI_PEPPER = ITEMS.register("green_chili_pepper",
            () -> new ItemNameBlockItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.GREEN_CHILI_PEPPER)));
    public static final DeferredItem<Item> GHOST_PEPPER = ITEMS.register("ghost_pepper",
            () -> new GhostPepperItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.GHOST_PEPPER)));
    public static final DeferredItem<Item> KIMCHI = ITEMS.register("kimchi",
            () -> new Item(new Item.Properties().food(ModFoods.KIMCHI)));


    public static final DeferredItem<Item> ROBOGUN = ITEMS.register("robogun",
            () -> new RobogunItem(
                    new Item.Properties().stacksTo(1),
                    8,
                    15,
                    5,
                    20,
                    ROBOGUN_AMMO.get(),
                    50,
                    new float[]{0.1f, 0.1f},
                    new float[]{0f, 0f},
                    1,
                    SoundEvents.CROSSBOW_QUICK_CHARGE_3,
                    ModSounds.ROBOGUN_SHOOT,
                    1,
                    false,
                    false,
                    10,
                    11,
                    21,
                    GunItem.LoadingType.MAGAZINE,
                    GunItem.FiringType.SEMI_AUTO
            ));

    public static final DeferredItem<Item> ROBOSNIPER = ITEMS.register("robosniper",
            () -> new RobosniperItem(
                    new Item.Properties().stacksTo(1),
                    30,
                    40,
                    30,
                    3,
                    ROBOSNIPER_AMMO.get(),
                    100,
                    new float[]{0.1f, 0.1f},
                    new float[]{1f, 0.2f},
                    1,
                    SoundEvents.CROSSBOW_QUICK_CHARGE_3,
                    ModSounds.ROBOGUN_SHOOT,
                    1,
                    false,
                    false,
                    10,
                    11,
                    21,
                    GunItem.LoadingType.MAGAZINE,
                    GunItem.FiringType.SEMI_AUTO
            ));

    public static final DeferredItem<Item> GLASS_HEELS = ITEMS.register("glass_heels",
            () -> new GlassHeelsBootItem(ModArmorMaterials.GLASS, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final DeferredItem<Item> PSYCHOPATH_AXE = ITEMS.register("psychopath_axe",
            () -> new PsychopathAxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.DIAMOND, 5.5f, -3.0f)).durability(1024)));
    public static final DeferredItem<Item> SINGLE_GLASS_HEEL = ITEMS.register("glass_heel_single_side", () -> new SingleGlassHeelItem(new Item.Properties()));
    public static final DeferredItem<Item> METEORITE_PIECE = ITEMS.register("meteorite_piece", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STELLARITE = ITEMS.register("stellarite", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new AppleJuiceItem(new Item.Properties().food(ModFoods.APPLE_JUICE), Component.translatable("tooltip.randomholos.apple_juice.shift")));

    public static final DeferredItem<Item> ELITE_LAVA_BUCKET = ITEMS.register("elite_lava_bucket",
            () -> new EliteLavaBucketItem(ModFluids.SOURCE_ELITE_LAVA, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> AHOGE = ITEMS.register("ahoge",
            () -> new AhogeItem(new Item.Properties()));
    public static final DeferredItem<Item> AHOGE_STRING = ITEMS.register("ahoge_string",
            () -> new AhogeStringItem(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_ROD = ITEMS.register("copper_rod", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REINFORCED_COPPER_ROD = ITEMS.register("reinforced_copper_rod", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MIKO_BOW = ITEMS.register("miko_bow",
            () -> new MikoBowItem(new Item.Properties().durability(350)));
    public static final DeferredItem<Item> MIKOMET_BOW = ITEMS.register("mikomet_bow",
            () -> new MikometBowItem(new Item.Properties().durability(700)));
    public static final DeferredItem<Item> MIKOMET_ARROW = ITEMS.register("mikomet_arrow",
            () -> new MikometArrowItem(new Item.Properties()));

    public static final DeferredItem<Item> GUESSER_PIN = ITEMS.register("guesser_pin",
            () -> new GuesserPinItem(new Item.Properties()));
    public static final DeferredItem<Item> MICSABER = ITEMS.register("micsaber",
            () -> new MicsaberItem(ModToolTiers.ABOVE_DIAMOND, 4, -2.2f, new Item.Properties().durability(2000)));
    public static final DeferredItem<Item> TOURMALINE = ITEMS.register("tourmaline", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SIDEREAL_INGOT = ITEMS.register("sidereal_ingot", () -> new ShiftTooltipItem(new Item.Properties(), Component.translatable("tooltip.randomholos.sidereal_ingot.shift")));
    public static final DeferredItem<Item> STAR_DIVA_HAMMER = ITEMS.register("star_diva_hammer",
            () -> new StarDivaHammerItem(ModToolTiers.SIDEREAL, new Item.Properties().attributes(HammerItem.createAttributes(ModToolTiers.SIDEREAL, 5, -3.25f)).durability(4000)));

    public static final DeferredItem<Item> SORA_SPAWN_EGG = ITEMS.register("sora_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SORA, 0x0146ea, 0x266aff,
                    new Item.Properties()));
    public static final DeferredItem<Item> KUROSORA_SPAWN_EGG = ITEMS.register("kurosora_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.KUROSORA, 0x0146ea, 0x000000,
                    new Item.Properties()));
    public static final DeferredItem<Item> NUNNUN_SPAWN_EGG = ITEMS.register("nunnun_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.NUNNUN, 0xffffff, 0xe84b6f,
                    new Item.Properties()));
    public static final DeferredItem<Item> ROBOCO_SPAWN_EGG = ITEMS.register("roboco_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ROBOCO, 0x804e7f, 0xd192fe,
                    new Item.Properties()));
    public static final DeferredItem<Item> SUISEI_SPAWN_EGG = ITEMS.register("suisei_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SUISEI, 0x2dcde4, 0x50e5f9,
                    new Item.Properties()));
    public static final DeferredItem<Item> MIKO_SPAWN_EGG = ITEMS.register("miko_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MIKO, 0xfe4b74, 0xfe4b74,
                    new Item.Properties()));
    public static final DeferredItem<Item> MIKOP_SPAWN_EGG = ITEMS.register("mikop_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MIKOP, 0xffffff, 0xfcaebc,
                    new Item.Properties()));
    public static final DeferredItem<Item> AZKI_SPAWN_EGG = ITEMS.register("azki_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.AZKI, 0xd11c76, 0xf4348b,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
