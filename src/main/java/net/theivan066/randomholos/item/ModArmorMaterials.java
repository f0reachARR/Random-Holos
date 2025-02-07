package net.theivan066.randomholos.item;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

import java.util.Collections;
import java.util.EnumMap;

@SuppressWarnings("deprecation")
public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, RandomHolos.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GLASS = ARMOR_MATERIALS.register("glass", () ->
            new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266652_) -> {
                p_266652_.put(ArmorItem.Type.BOOTS, 1);
                p_266652_.put(ArmorItem.Type.LEGGINGS, 1);
                p_266652_.put(ArmorItem.Type.CHESTPLATE, 1);
                p_266652_.put(ArmorItem.Type.HELMET, 1);
            }), 5, SoundEvents.ARMOR_EQUIP_GOLD, () -> Ingredient.of(Items.GLASS), Collections.emptyList(), 0.0F, 0.2F));

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
}
