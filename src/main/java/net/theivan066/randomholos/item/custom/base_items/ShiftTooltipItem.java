package net.theivan066.randomholos.item.custom.base_items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ShiftTooltipItem extends Item {
    private Component discription;

    public ShiftTooltipItem(Properties pProperties, Component discription) {
        super(pProperties);
        this.discription = discription;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(discription);
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
