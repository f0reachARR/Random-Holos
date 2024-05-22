package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.theivan066.randomholos.enchantment.ModEnchantments;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.custom.MikometArrowEntity;
import net.theivan066.randomholos.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class MikometBowItem extends BowItem {


    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            Random random = new Random();
            boolean infiniteArrows = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, pStack) > 0;
            ItemStack itemstack = player.getProjectile(pStack);

            int drawtime = this.getUseDuration(pStack) - pTimeLeft;
            drawtime = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, drawtime, !itemstack.isEmpty() || infiniteArrows);
            if (drawtime < 0) return;

            if (!itemstack.isEmpty() || infiniteArrows) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModItems.MIKOMET_ARROW.get());
                }

                float velocity = getPowerForTime(drawtime);
                if (!((double)velocity < 0.1D)) {
                    boolean infin = player.getAbilities().instabuild || (itemstack.getItem() instanceof MikometArrowItem && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FAST_DRAW.get(), pStack) > 0);
                    if (!pLevel.isClientSide) {
                        MikometArrowEntity mikometArrow = new MikometArrowEntity(ModEntities.MIKOMET_ARROW.get(), player, pLevel);
                        mikometArrow = (MikometArrowEntity) customArrow(mikometArrow);
                        double dmg = mikometArrow.getBaseDamage();
                        mikometArrow.setBaseDamage(dmg);
                        if (random.nextInt(1, 100) < 35) {
                            mikometArrow.setPierceLevel((byte) random.nextInt(3,5));
                        }
                        mikometArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity * 4.141F, 1.0F);
                        if (velocity == 1.0F) {
                            mikometArrow.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
                        if (j > 0) {
                            mikometArrow.setBaseDamage(dmg + (double)j * 0.75D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
                        if (k > 0) {
                            mikometArrow.setKnockback((int) Math.floor(k * 0.85));
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
                            mikometArrow.setSecondsOnFire(100);
                        }

                        pStack.hurtAndBreak(1, player, (p_289501_) -> {
                            p_289501_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        pLevel.addFreshEntity(mikometArrow);
                    }

                    pLevel.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

                    if (!infin && !player.getAbilities().instabuild) {
                        takeArrows(itemstack, player);
                    } else if (infin) {
                        int l = random.nextInt(1, 100);
                        if (l < 50) {
                            takeArrows(itemstack, player);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public @Nullable Entity createEntity(Level level, Entity location, ItemStack stack) {
        MikometArrowItem arrowitem = (MikometArrowItem) (stack.getItem() instanceof ArrowItem ? stack.getItem() : ModEntities.MIKOMET_ARROW.get());
        return arrowitem.createArrow(level, stack, (LivingEntity) location);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (itemStack) -> itemStack.is(ModItems.MIKOMET_ARROW.get());
    }

    private void takeArrows(ItemStack itemStack, Player player) {
        itemStack.shrink(1);
        if (itemStack.isEmpty()) {
            player.getInventory().removeItem(itemStack);
        };
    }

    public static float chargeTime = 40F;

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;

        if (!pPlayer.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            float fastDraw = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FAST_DRAW.get(), pPlayer.getItemInHand(pHand));
            if (fastDraw > 0) {
                float i = fastDraw * 5;
                chargeTime = 40 - i;
            } else {
                chargeTime = 40;
            }
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    /**
     * Gets the velocity of the arrow entity from the bow's charge
     */

    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / chargeTime;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.mikomet_bow.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public MikometBowItem(Properties pProperties) {
        super(pProperties);
    }
}
