package net.theivan066.randomholos.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, RandomHolos.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> ZERO_GUESSER_EFFECT = MOB_EFFECTS.register("zero_guesser",
            () -> new ZeroGuesserEffect(MobEffectCategory.BENEFICIAL, 0xf4348b));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
