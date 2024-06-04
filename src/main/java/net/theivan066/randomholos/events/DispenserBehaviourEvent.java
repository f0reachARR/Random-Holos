package net.theivan066.randomholos.events;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.blockbehaviour.GlassHeelProjectileDispenseBehaviour;
import net.theivan066.randomholos.blockbehaviour.GuesserPinProjectileDispenseBehaviour;
import net.theivan066.randomholos.blockbehaviour.MikometArrowDispenseBehaviour;
import net.theivan066.randomholos.item.ModItems;

@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DispenserBehaviourEvent {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        registerDispenserBehavior();
    }

    private static void registerDispenserBehavior() {
        DispenseItemBehavior glassHeel = new GlassHeelProjectileDispenseBehaviour();
        DispenserBlock.registerBehavior(ModItems.SINGLE_GLASS_HEEL.get(), glassHeel);
        DispenseItemBehavior pin = new GuesserPinProjectileDispenseBehaviour();
        DispenserBlock.registerBehavior(ModItems.GUESSER_PIN.get(), pin);
        DispenseItemBehavior mikometArrow = new MikometArrowDispenseBehaviour();
        DispenserBlock.registerBehavior(ModItems.MIKOMET_ARROW.get(), mikometArrow);
    }
}
