package net.theivan066.randomholos.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, RandomHolos.MOD_ID);

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_ELITE_LAVA = FLUIDS.register("elite_lava_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.ELITE_LAVA_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_ELITE_LAVA = FLUIDS.register("flowing_elite_lava_fluid",
            () -> new BaseFlowingFluid.Flowing(ModFluids.ELITE_LAVA_FLUID_PROPERTIES));


    public static final BaseFlowingFluid.Properties ELITE_LAVA_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.ELITE_LAVA_FLUID_TYPE, SOURCE_ELITE_LAVA, FLOWING_ELITE_LAVA)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.ELITE_LAVA_BLOCK)
            .bucket(ModItems.ELITE_LAVA_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
