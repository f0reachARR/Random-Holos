package net.theivan066.randomholos.potion;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, RandomHolos.MOD_ID);

    public static final DeferredHolder<Potion, Potion> SEVERE_POISON = POTIONS.register("severe_poison",
            () -> new Potion(new MobEffectInstance(MobEffects.POISON, 150, 2)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
