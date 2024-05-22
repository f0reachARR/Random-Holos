package net.theivan066.randomholos.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.theivan066.randomholos.item.custom.MikometBowItem;

public class FastDrawEnchantment extends Enchantment {
    public FastDrawEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof MikometBowItem;
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 12 + (pEnchantmentLevel - 1) * 15;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return 45;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

}
