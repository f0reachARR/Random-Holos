package net.theivan066.randomholos.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.theivan066.randomholos.RandomHolos;
import org.jetbrains.annotations.Nullable;

public class Capabilities {
    public static final BlockCapability<IItemHandler, @Nullable Direction> ITEM_HANDLER_BLOCK =
            BlockCapability.create(
                    ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "item_handler"),
                    IItemHandler.class,
                    Direction.class
            );
    public static final BlockCapability<IFluidHandler, @Nullable Direction> FLUID_HANDLER_BLOCK =
            BlockCapability.create(
                    ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "fluid_handler"),
                    IFluidHandler.class,
                    Direction.class
            );
}
