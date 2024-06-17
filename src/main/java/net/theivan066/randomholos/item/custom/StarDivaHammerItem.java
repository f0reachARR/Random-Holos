package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.theivan066.randomholos.item.custom.base_items.HammerItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StarDivaHammerItem extends HammerItem {
    public StarDivaHammerItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, int range, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, range, pProperties);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.star_diva_hammer.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
