package net.theivan066.randomholos.client;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.theivan066.randomholos.RandomHolos;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyPressEvent {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (ModKeyBindings.INSTANCE.ABILITY_KEY.consumeClick() && minecraft.player != null) {
            //WIP
        }

        if (ModKeyBindings.INSTANCE.SECONDARY_ABILITY_KEY.consumeClick() && minecraft.player != null) {
            //WIP
        }
    }
}
