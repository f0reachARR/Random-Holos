package net.theivan066.randomholos.worldgen.tree.trunk_placer;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.worldgen.tree.trunk_placer.MapleTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, RandomHolos.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<MapleTrunkPlacer>> MAPLE_TRUNK_PLACER =
            TRUNK_PLACERS.register("maple_trunk_placer", () -> new TrunkPlacerType<>(MapleTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<BushTrunkPlacer>> BUSH_TRUNK_PLACER =
            TRUNK_PLACERS.register("bush_trunk_placer", () -> new TrunkPlacerType<>(BushTrunkPlacer.CODEC));


    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}
