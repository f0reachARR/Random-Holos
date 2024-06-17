package net.theivan066.randomholos.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.custom.*;
import net.theivan066.randomholos.item.custom.base_items.GunItem;
import net.theivan066.randomholos.item.custom.base_items.HammerItem;
import net.theivan066.randomholos.item.custom.base_items.ShiftTooltipItem;
import net.theivan066.randomholos.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RandomHolos.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOLO_LOGO = ITEMS.register("holo_logo", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> LETTER = ITEMS.register("letter", () ->  new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().food(ModFoods.BAGUETTE)));
    public static final RegistryObject<Item> HUMONGOUS_BAGUETTE = ITEMS.register("humongous_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.HUMONGOUS_BAGUETTE)));
    public static final RegistryObject<Item> GARGANTUAN_BAGUETTE = ITEMS.register("gargantuan_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.GARGANTUAN_BAGUETTE)));


    public static final RegistryObject<Item> SHAKEN_BOTTLE = ITEMS.register("shaken_bottle",
            () -> new ShakenBottleItem(new Item.Properties()));
    public static final RegistryObject<Item> SODA_WATER = ITEMS.register("soda_water",
            () -> new DrinkItem(new Item.Properties().food(ModFoods.SODA_WATER)));
    public static final RegistryObject<Item> CAN = ITEMS.register("can", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOKINO_SODA = ITEMS.register("tokino_soda",
            () -> new DrinkItem(new Item.Properties().food(ModFoods.TOKINO_SODA)));
    public static final RegistryObject<Item> RAW_RICE = ITEMS.register("raw_rice", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNCOOKED_RICE = ITEMS.register("uncooked_rice", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> COOKED_RICE = ITEMS.register("cooked_rice",
            () -> new BowlFoodItem(new Item.Properties().food(ModFoods.COOKED_RICE).craftRemainder(Items.BOWL)));
    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHRIMP = ITEMS.register("shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.SHRIMP)));
    public static final RegistryObject<Item> COOKED_SHRIMP = ITEMS.register("cooked_shrimp",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SHRIMP)));
    public static final RegistryObject<Item> OIL = ITEMS.register("oil", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> EBI_KATSU = ITEMS.register("ebi_katsu",
            () -> new Item(new Item.Properties().food(ModFoods.EBI_KATSU)));
    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE)));
    public static final RegistryObject<Item> CHOCOLATE_CAKE = ITEMS.register("chocolate_cake",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_CAKE)));
    public static final RegistryObject<Item> CURRY = ITEMS.register("curry",
            () -> new Item(new Item.Properties().food(ModFoods.CURRY)));
    public static final RegistryObject<Item> CURRY_BREAD = ITEMS.register("curry_bread",
            () -> new Item(new Item.Properties().food(ModFoods.CURRY_BREAD)));
    public static final RegistryObject<Item> TOKINOSORA_SET_MEAL_A = ITEMS.register("tokinosora_set_meal_a",
            () -> new BowlFoodItem(new Item.Properties().food(ModFoods.TOKINOSORA_SET_MEAL_A)));
    public static final RegistryObject<Item> TOKINOSORA_SET_MEAL_B = ITEMS.register("tokinosora_set_meal_b",
            () -> new Item(new Item.Properties().food(ModFoods.TOKINOSORA_SET_MEAL_B)));
    public static final RegistryObject<Item> BROKEN_HAIR_ACCESSORY = ITEMS.register("broken_hair_accessory", () ->  new ShiftTooltipItem(new Item.Properties(), Component.translatable("tooltip.randomholos.broken_hair_accessory.shift")));
    public static final RegistryObject<Item> TALISMAN_OF_THE_ETHER = ITEMS.register("talisman_of_the_ether", () ->  new TalismanOfTheEtherItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BLUEPRINT = ITEMS.register("blueprint", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY_BLUEPRINT = ITEMS.register("alloy_blueprint", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIREARM_BLUEPRINT = ITEMS.register("firearm_blueprint", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLADE_BLUEPRINT = ITEMS.register("blade_blueprint", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUNT_BLUEPRINT = ITEMS.register("blunt_blueprint", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENERGY_CORE = ITEMS.register("energy_core", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOTIC_PARTS = ITEMS.register("robotic_parts", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOGUN_AMMO = ITEMS.register("robogun_ammo", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOSNIPER_AMMO = ITEMS.register("robosniper_ammo", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHIVES = ITEMS.register("chives",
            () -> new ItemNameBlockItem(ModBlocks.CHIVES_CROP.get(), new Item.Properties().food(ModFoods.CHIVES)));
    public static final RegistryObject<Item> SCALLION = ITEMS.register("scallion",
            () -> new ItemNameBlockItem(ModBlocks.SCALLION_CROP.get(), new Item.Properties().food(ModFoods.SCALLION)));
    public static final RegistryObject<Item> CHINESE_CABBAGE = ITEMS.register("chinese_cabbage",
            () -> new ItemNameBlockItem(ModBlocks.CHINESE_CABBAGE_CROP.get(), new Item.Properties().food(ModFoods.CHINESE_CABBAGE)));
    public static final RegistryObject<Item> RED_CHILI_PEPPER = ITEMS.register("red_chili_pepper",
            () -> new ItemNameBlockItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.RED_CHILI_PEPPER)));
    public static final RegistryObject<Item> GREEN_CHILI_PEPPER = ITEMS.register("green_chili_pepper",
            () -> new ItemNameBlockItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.GREEN_CHILI_PEPPER)));
    public static final RegistryObject<Item> GHOST_PEPPER = ITEMS.register("ghost_pepper",
            () -> new GhostPepperItem(ModBlocks.CHILI_BUSH.get(), new Item.Properties().food(ModFoods.GHOST_PEPPER)));
    public static final RegistryObject<Item> KIMCHI = ITEMS.register("kimchi",
            () -> new Item(new Item.Properties().food(ModFoods.KIMCHI)));


    public static final RegistryObject<Item> ROBOGUN = ITEMS.register("robogun",
            () -> new RobogunItem(
                    new Item.Properties().stacksTo(1),
                    8,
                    15,
                    5,
                    20,
                    ROBOGUN_AMMO.get(),
                    50,
                    new float[] {0.1f, 0.1f},
                    new float[] {0f, 0f},
                    1,
                    SoundEvents.CROSSBOW_QUICK_CHARGE_3,
                    ModSounds.ROBOGUN_SHOOT.get(),
                    1,
                    false,
                    false,
                    10,
                    11,
                    21,
                    GunItem.LoadingType.MAGAZINE,
                    GunItem.FiringType.SEMI_AUTO
                    ));

    public static final RegistryObject<Item> ROBOSNIPER = ITEMS.register("robosniper",
            () -> new RobosniperItem(
                    new Item.Properties().stacksTo(1),
                    30,
                    40,
                    30,
                    3,
                    ROBOSNIPER_AMMO.get(),
                    100,
                    new float[] {0.1f, 0.1f},
                    new float[] {1f, 0.2f},
                    1,
                    SoundEvents.CROSSBOW_QUICK_CHARGE_3,
                    ModSounds.ROBOGUN_SHOOT.get(),
                    1,
                    false,
                    false,
                    10,
                    11,
                    21,
                    GunItem.LoadingType.MAGAZINE,
                    GunItem.FiringType.SEMI_AUTO
            ));

    public static final RegistryObject<Item> GLASS_HEELS = ITEMS.register("glass_heels",
            () -> new GlassHeelsBootItem(ModArmorMaterials.GLASS, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> PSYCHOPATH_AXE = ITEMS.register("psychopath_axe",
            () -> new PsychopathAxeItem(Tiers.DIAMOND, 5.5f, -3.0f, new Item.Properties().durability(1024)));
    public static final RegistryObject<Item> SINGLE_GLASS_HEEL = ITEMS.register("glass_heel_single_side", () ->  new SingleGlassHeelItem(new Item.Properties()));
    public static final RegistryObject<Item> METEORITE_PIECE = ITEMS.register("meteorite_piece", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> STELLARITE = ITEMS.register("stellarite", () ->  new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new AppleJuiceItem(new Item.Properties().food(ModFoods.APPLE_JUICE),  Component.translatable("tooltip.randomholos.apple_juice.shift")));

    public static final RegistryObject<Item> ELITE_LAVA_BUCKET = ITEMS.register("elite_lava_bucket",
            () -> new EliteLavaBucketItem(ModFluids.SOURCE_ELITE_LAVA, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> AHOGE = ITEMS.register("ahoge",
            () ->  new AhogeItem(new Item.Properties()));
    public static final RegistryObject<Item> AHOGE_STRING = ITEMS.register("ahoge_string",
            () ->  new AhogeStringItem(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> REINFORCED_COPPER_ROD = ITEMS.register("reinforced_copper_rod", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> MIKO_BOW = ITEMS.register("miko_bow",
            () -> new MikoBowItem(new Item.Properties().durability(350)));
    public static final RegistryObject<Item> MIKOMET_BOW = ITEMS.register("mikomet_bow",
            () -> new MikometBowItem(new Item.Properties().durability(700)));
    public static final RegistryObject<Item> MIKOMET_ARROW = ITEMS.register("mikomet_arrow",
            () -> new MikometArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> GUESSER_PIN = ITEMS.register("guesser_pin",
            () -> new GuesserPinItem(new Item.Properties()));
    public static final RegistryObject<Item> MICSABER = ITEMS.register("micsaber",
            () -> new MicsaberItem(ModToolTiers.ABOVE_DIAMOND, 4, -2.2f, new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> TOURMALINE = ITEMS.register("tourmaline", () ->  new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIDEREAL_INGOT = ITEMS.register("sidereal_ingot", () ->  new ShiftTooltipItem(new Item.Properties(), Component.translatable("tooltip.randomholos.sidereal_ingot.shift")));
    public static final RegistryObject<Item> STAR_DIVA_HAMMER = ITEMS.register("star_diva_hammer",
            () -> new StarDivaHammerItem(ModToolTiers.SIDEREAL, 5, -3.25f, 2, new Item.Properties().durability(4000)));

    public static final RegistryObject<Item> SORA_SPAWN_EGG = ITEMS.register("sora_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SORA, 0x0146ea, 0x266aff,
                    new Item.Properties()));
    public static final RegistryObject<Item> KUROSORA_SPAWN_EGG = ITEMS.register("kurosora_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KUROSORA, 0x0146ea, 0x000000,
                    new Item.Properties()));
    public static final RegistryObject<Item> NUNNUN_SPAWN_EGG = ITEMS.register("nunnun_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.NUNNUN, 0xffffff, 0xe84b6f,
                    new Item.Properties()));
    public static final RegistryObject<Item> ROBOCO_SPAWN_EGG = ITEMS.register("roboco_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ROBOCO, 0x804e7f, 0xd192fe,
                    new Item.Properties()));
    public static final RegistryObject<Item> SUISEI_SPAWN_EGG = ITEMS.register("suisei_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SUISEI, 0x2dcde4, 0x50e5f9,
                    new Item.Properties()));
    public static final RegistryObject<Item> MIKO_SPAWN_EGG = ITEMS.register("miko_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MIKO, 0xfe4b74, 0xfe4b74,
                    new Item.Properties()));
    public static final RegistryObject<Item> MIKOP_SPAWN_EGG = ITEMS.register("mikop_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MIKOP, 0xffffff, 0xfcaebc,
                    new Item.Properties()));
    public static final RegistryObject<Item> AZKI_SPAWN_EGG = ITEMS.register("azki_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.AZKI, 0xd11c76, 0xf4348b,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
