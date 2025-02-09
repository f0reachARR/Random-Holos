package net.theivan066.randomholos.client;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.enchantment.ModEnchantments;
import net.theivan066.randomholos.item.custom.MikoBowItem;
import net.theivan066.randomholos.item.custom.MikometBowItem;
import net.theivan066.randomholos.item.custom.RobosniperItem;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class PovModifyEvent {

    //bow FOVs
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof MikoBowItem) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20.0F;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof MikometBowItem) {
            int fastDraw = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FAST_DRAW, event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND));
            float i = fastDraw * 5;
            float totalDrawTime = 40 - i;
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / totalDrawTime;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.5F;
            event.setNewFovModifier(fovModifier);
        }
        if (event.getPlayer().getOffhandItem().getItem() instanceof RobosniperItem) {
            event.setNewFovModifier(0.2F);
        }
    }
}

