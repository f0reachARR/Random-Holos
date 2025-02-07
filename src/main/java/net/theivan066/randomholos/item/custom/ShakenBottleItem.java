package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.theivan066.randomholos.item.ModItems;

import java.util.List;

public class ShakenBottleItem extends Item {
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            if (blockhitresult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockpos = blockhitresult.getBlockPos();
                if (!pLevel.mayInteract(pPlayer, blockpos)) {
                    return InteractionResultHolder.pass(itemstack);
                }
                if (pLevel.getFluidState(blockpos).is(FluidTags.WATER)) {
                    pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                    itemstack.shrink(1);
                    pPlayer.addItem(new ItemStack(ModItems.SODA_WATER.get()));
                }
                if (pLevel.getFluidState(blockpos).is(FluidTags.LAVA)) {
                    pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                    itemstack.shrink(1);
                    pPlayer.addItem(new ItemStack(ModItems.OIL.get()));
                }
            }
            return InteractionResultHolder.pass(itemstack);
        }
    }

    public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.shaken_bottle.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public ShakenBottleItem(Properties pProperties) {
        super(pProperties);
    }
}
