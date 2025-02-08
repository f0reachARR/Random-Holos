package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

@SuppressWarnings("deprecation")
public class MikoBowItem extends BowItem {
//    /**
//     * Called when the player stops using an Item (stops holding the right mouse button).
//     */
//    @Override
//    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
//        if (pEntityLiving instanceof Player player) {
//            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, pStack) > 0;
//            ItemStack itemstack = player.getProjectile(pStack);
//
//            int i = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
//            i = EventHooks.onArrowLoose(pStack, pLevel, player, i, !itemstack.isEmpty() || flag);
//            if (i < 0) return;
//
//            if (!itemstack.isEmpty() || flag) {
//                if (itemstack.isEmpty()) {
//                    itemstack = new ItemStack(Items.ARROW);
//                }
//
//                float f = getPowerForTime(i);
//                if (!((double) f < 0.1D)) {
//                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, pStack, player));
//                    if (!pLevel.isClientSide) {
//                        ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
//                        AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, pEntityLiving.getEyePosition(), itemstack, player);
//                        abstractarrow = customArrow(abstractarrow);
//                        double dmg = abstractarrow.getBaseDamage() + 1;
//                        abstractarrow.setBaseDamage(dmg);
//                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.5F, 1.0F);
//                        if (f == 1.0F) {
//                            abstractarrow.setCritArrow(true);
//                        }
//
//                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
//                        if (j > 0) {
//                            abstractarrow.setBaseDamage(dmg + (double) j * 0.5D + 0.5D);
//                        }
//
//                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
//                        if (k > 0) {
//                            abstractarrow.setKnockback(k);
//                        }
//
//                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
//                            abstractarrow.setSecondsOnFire(100);
//                        }
//
//                        pStack.hurtAndBreak(1, player, (p_289501_) -> {
//                            p_289501_.broadcastBreakEvent(player.getUsedItemHand());
//                        });
//                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
//                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
//                        }
//
//                        pLevel.addFreshEntity(abstractarrow);
//                    }
//
//                    pLevel.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
//                    if (!flag1 && !player.getAbilities().instabuild) {
//                        itemstack.shrink(1);
//                        if (itemstack.isEmpty()) {
//                            player.getInventory().removeItem(itemstack);
//                        }
//                    }
//
//                    player.awardStat(Stats.ITEM_USED.get(this));
//                }
//            }
//        }
//    }

//    /**
//     * Gets the velocity of the arrow entity from the bow's charge
//     */
//    public static float getPowerForTime(int pCharge) {
//        float f = (float) pCharge / 20.0F;
//        f = (f * f + f * 2.0F) / 3.0F;
//        if (f > 1.0F) {
//            f = 1.0F;
//        }
//        return f;
//    }

    public void appendHoverText(ItemStack pStack, TooltipContext tooltipContext, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.miko_bow.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, tooltipContext, pTooltipComponents, pIsAdvanced);
    }

    public MikoBowItem(Properties pProperties) {
        super(pProperties);
    }
}
