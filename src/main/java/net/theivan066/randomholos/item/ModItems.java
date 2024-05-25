package net.theivan066.randomholos.item;

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
import net.theivan066.randomholos.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RandomHolos.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test", () ->  new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().food(ModFoods.BAGUETTE)));
    public static final RegistryObject<Item> HUMONGOUS_BAGUETTE = ITEMS.register("humongous_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.HUMONGOUS_BAGUETTE)));
    public static final RegistryObject<Item> GARGANTUAN_BAGUETTE = ITEMS.register("gargantuan_baguette",
            () -> new Item(new Item.Properties().food(ModFoods.GARGANTUAN_BAGUETTE)));


    public static final RegistryObject<Item> SHAKEN_BOTTLE = ITEMS.register("shaken_bottle",
            () -> new ShakenBottleItem(new Item.Properties()));
    public static final RegistryObject<Item> SODA_WATER = ITEMS.register("soda_water",
            () -> new Item(new Item.Properties().food(ModFoods.SODA_WATER)));
    public static final RegistryObject<Item> CAN = ITEMS.register("can", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOKINO_SODA = ITEMS.register("tokino_soda",
            () -> new Item(new Item.Properties().food(ModFoods.TOKINO_SODA)));
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
    public static final RegistryObject<Item> STELLARITE = ITEMS.register("stellarite", () ->  new Item(new Item.Properties()));

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





    public static final RegistryObject<Item> SORA_SPAWN_EGG = ITEMS.register("sora_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SORA, 0x0146ea, 0x266aff,
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
