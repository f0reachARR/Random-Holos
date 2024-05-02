package net.theivan066.randomholos.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.item.custom.GlassHeelsBootItem;
import net.theivan066.randomholos.item.custom.PsychopathAxeItem;
import net.theivan066.randomholos.item.custom.SingleGlassHeelItem;

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

    public static final RegistryObject<Item> GLASS_HEELS = ITEMS.register("glass_heels",
            () -> new GlassHeelsBootItem(ModArmorMaterials.GLASS, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> PSYCHOPATH_AXE = ITEMS.register("psychopath_axe",
            () -> new PsychopathAxeItem(Tiers.DIAMOND, 5.5f, -3.0f, new Item.Properties().durability(1024)));
    public static final RegistryObject<Item> SINGLE_GLASS_HEEL = ITEMS.register("glass_heel_single_side", () ->  new SingleGlassHeelItem(new Item.Properties()));

    public static final RegistryObject<Item> RHINO_SPAWN_EGG = ITEMS.register("suisei_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SUISEI, 0x2dcde4, 0x50e5f9,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
