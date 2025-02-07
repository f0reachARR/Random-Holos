package net.theivan066.randomholos.worldgen.tree.trunk_placer;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, RandomHolos.MOD_ID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MapleTrunkPlacer>> MAPLE_TRUNK_PLACER =
            TRUNK_PLACERS.register("maple_trunk_placer", () -> new TrunkPlacerType<>(MapleTrunkPlacer.CODEC));

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BushTrunkPlacer>> BUSH_TRUNK_PLACER =
            TRUNK_PLACERS.register("bush_trunk_placer", () -> new TrunkPlacerType<>(BushTrunkPlacer.CODEC));


    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}
