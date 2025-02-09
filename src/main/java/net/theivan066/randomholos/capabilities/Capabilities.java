package net.theivan066.randomholos.capabilities;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.theivan066.randomholos.RandomHolos;
import org.jetbrains.annotations.Nullable;

public class Capabilities {
    public static final BlockCapability<IItemHandler, @Nullable Void> ITEM_HANDLER_BLOCK =
            BlockCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "item_handler"),
                    IItemHandler.class
            );
    public static final BlockCapability<IFluidHandler, @Nullable Void> FLUID_HANDLER_BLOCK =
            BlockCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "fluid_handler"),
                    IFluidHandler.class
            );
    public static final BlockCapability<IEnergyStorage, @Nullable Void> ENERGY_STORAGE_BLOCK =
            BlockCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "energy_handler"),
                    IEnergyStorage.class
            );
}
