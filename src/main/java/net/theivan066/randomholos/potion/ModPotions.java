package net.theivan066.randomholos.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, RandomHolos.MOD_ID);

    public static final RegistryObject<Potion> SEVERE_POISON = POTIONS.register("severe_poison",
            () -> new Potion(new MobEffectInstance(MobEffects.POISON, 150, 2)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
