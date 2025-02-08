package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.theivan066.randomholos.item.ModItems;

import java.util.List;
import java.util.function.Predicate;

public class MikometBowItem extends BowItem {
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (itemStack) -> itemStack.is(ModItems.MIKOMET_ARROW.get());
    }
//
//   TODO: public static float chargeTime = 40F;

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext tooltipContext, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.mikomet_bow.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, tooltipContext, pTooltipComponents, pIsAdvanced);
    }

    public MikometBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 25;
    }
}
