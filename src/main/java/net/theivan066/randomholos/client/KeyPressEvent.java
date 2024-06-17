package net.theivan066.randomholos.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;

@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyPressEvent {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if(ModKeyBindings.INSTANCE.ABILITY_KEY.consumeClick() && minecraft.player != null) {
           //WIP
        }

        if(ModKeyBindings.INSTANCE.SECONDARY_ABILITY_KEY.consumeClick() && minecraft.player != null) {
           //WIP
        }
    }
}
