package net.theivan066.randomholos.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import org.joml.Vector3f;

public class ModFluidTypes {

    public static final ResourceLocation LAVA_STILL_RL = new ResourceLocation("block/lava_still");
    public static final ResourceLocation LAVA_FLOWING_RL = new ResourceLocation("block/lava_flow");
    public static final ResourceLocation LAVA_OVERLAY_RL = new ResourceLocation("block/lava_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, RandomHolos.MOD_ID);

    public static final RegistryObject<FluidType> ELITE_LAVA_FLUID_TYPE = registerFluidType("elite_lava_fluid",
            new BaseFluidType(LAVA_STILL_RL, LAVA_FLOWING_RL, LAVA_OVERLAY_RL, 0xA1D3530E,
                    new Vector3f(211f / 255f, 83f / 255f, 14f / 255f),
                    FluidType.Properties.create().lightLevel(10).viscosity(10).density(15).temperature(35)
                            .supportsBoating(true).canPushEntity(true).canSwim(true).canDrown(false).canExtinguish(true)
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)));
    private static RegistryObject<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
