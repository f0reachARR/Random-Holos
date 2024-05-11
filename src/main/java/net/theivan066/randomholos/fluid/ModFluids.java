package net.theivan066.randomholos.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, RandomHolos.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_ELITE_LAVA = FLUIDS.register("elite_lava_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.ELITE_LAVA_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ELITE_LAVA = FLUIDS.register("flowing_elite_lava_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.ELITE_LAVA_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties ELITE_LAVA_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.ELITE_LAVA_FLUID_TYPE, SOURCE_ELITE_LAVA, FLOWING_ELITE_LAVA)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.ELITE_LAVA_BLOCK)
            .bucket(ModItems.ELITE_LAVA_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
