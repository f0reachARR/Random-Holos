package net.theivan066.randomholos.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class GhostPepperItem extends ItemNameBlockItem {
    public GhostPepperItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        Player player = (Player)pLivingEntity;
        player.setSecondsOnFire(10);
        player.displayClientMessage(Component.translatable("messages.randomholos.ghost_pepper"),true);
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
