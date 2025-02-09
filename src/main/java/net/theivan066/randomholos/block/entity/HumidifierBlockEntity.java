package net.theivan066.randomholos.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.IFluidTank;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.block.custom.HumidifierBlock;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.screen.HumidifierMenu;
import net.theivan066.randomholos.util.ModEnergyStorage;
import net.theivan066.randomholos.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HumidifierBlockEntity extends BlockEntity implements MenuProvider {
    public final HumidifierBlock humidifierBlock;


    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;


    protected final ContainerData data;


    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return switch (slot) {
                    case 0 -> stack.is(ModTags.Items.HUMIDIFIER_USABLE);
                    case 1 -> false;
                    default -> super.isItemValid(slot, stack);
                };
            }
        };
    }

    private FluidTank createFluidTank() {
        return new FluidTank(64000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if (!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return true;
            }
        };
    }

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(64000, 1000) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();
    private final FluidTank FLUID_TANK = createFluidTank();
    private final Lazy<ItemStackHandler> itemHandler = Lazy.of(this::createItemHandler);

    public IEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    public IFluidTank getFluidTank() {
        return this.FLUID_TANK;
    }

    public ItemStackHandler getItemHandler() {
        return this.itemHandler.get();
    }

    private BlockCapabilityCache<IItemHandler, @Nullable Direction> itemHandlerCache;
    private BlockCapabilityCache<IEnergyStorage, @Nullable Direction> energyStorageCache;
    private BlockCapabilityCache<IFluidHandler, @Nullable Direction> fluidHandlerCache;

    private void extractEnergy() {
        this.ENERGY_STORAGE.extractEnergy(1, false);
    }

    private void getEnergyFromBlocks(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = pos.relative(direction);

                IEnergyStorage capability = level.getCapability(Capabilities.EnergyStorage.BLOCK,
                        adjacentPos, direction.getOpposite());
                if (capability != null && getEnergyValue() < 64000) {
                    int extracted = capability.extractEnergy(1000, false);
                    this.ENERGY_STORAGE.receiveEnergy(extracted, false);
                }
            }
        }
    }

    private int getEnergyValue() {
        return this.ENERGY_STORAGE.getEnergyStored();
    }


    //load and save
    @Override
    public void onLoad() {
        super.onLoad();
        if (level.isClientSide()) {
            return;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.get().serializeNBT(registries));
        tag.putInt("energy", ENERGY_STORAGE.getEnergyStored());
        tag = FLUID_TANK.writeToNBT(registries, tag);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.get().deserializeNBT(registries, tag.getCompound("inventory"));
        ENERGY_STORAGE.setEnergy(tag.getInt("energy"));
        FLUID_TANK.readFromNBT(registries, tag);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        getEnergyFromBlocks(level, pPos);
        extractEnergy();
        fillOrDrainFluid();

        humidify(pPos, pState);
    }

    private void humidify(BlockPos pPos, BlockState pState) {
        FluidStack fluidStack = this.FLUID_TANK.drain(2, IFluidHandler.FluidAction.EXECUTE);
        if (fluidStack.is(Fluids.WATER)) {
            this.getLevel().setBlock(pPos, pState.setValue(HumidifierBlock.WITH_WATER, true)
                    .setValue(HumidifierBlock.WITH_LAVA, false), 3);
            AABB box = new AABB(pPos).inflate(10);
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, box);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.isOnFire()) {
                        livingEntity.clearFire();
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                this.getLevel().addParticle(ParticleTypes.BUBBLE, pPos.getX(), pPos.getY() + i, pPos.getZ(), 1, 1, 1);
            }
        } else if (fluidStack.is(Fluids.LAVA)) {
            this.getLevel().setBlock(pPos,
                    pState
                            .setValue(HumidifierBlock.WITH_WATER, false)
                            .setValue(HumidifierBlock.WITH_LAVA, true),
                    3);
            AABB box = new AABB(pPos).inflate(8);
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, box);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.setRemainingFireTicks(30);
                }
            }
            for (int i = 0; i < 2; i++) {
                this.getLevel().addParticle(ParticleTypes.LAVA, pPos.getX(), pPos.getY() + i, pPos.getZ(), 1, 1, 1);
            }
        } else if (fluidStack.is(ModFluids.SOURCE_ELITE_LAVA)) {
            this.getLevel()
                    .setBlock(pPos, pState
                                    .setValue(HumidifierBlock.WITH_WATER, true)
                                    .setValue(HumidifierBlock.WITH_LAVA, false),
                            3);
            AABB box = new AABB(pPos).inflate(10);
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, box);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.isOnFire()) {
                        livingEntity.clearFire();
                    }
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 35, 0, true, false));
                }
            }
            for (int i = 0; i < 2; i++) {
                this.getLevel().addParticle(ParticleTypes.CHERRY_LEAVES, pPos.getX(), pPos.getY() + i, pPos.getZ(), 1, 1, 1);
            }
        } else {
            this.getLevel().setBlock(pPos, pState.setValue(HumidifierBlock.WITH_WATER, false).setValue(HumidifierBlock.WITH_LAVA, false), 3);
        }
    }

    private void fillOrDrainFluid() {
        if (hasFluidSource(INPUT_SLOT)) {
            transferFluidIn(INPUT_SLOT);
        } else if (getItemHandler().getStackInSlot(INPUT_SLOT).is(ModItems.ELITE_LAVA_BUCKET.get())) {
            eliteLava();
        } else if (getItemHandler().getStackInSlot(INPUT_SLOT).is(Items.BUCKET)) {
            drainFluid();
        }
    }

    private void eliteLava() {
        if (this.FLUID_TANK.isEmpty() || this.FLUID_TANK.getFluid().is(ModFluids.SOURCE_ELITE_LAVA) && isOutputSlotAvailable()) {
            int drainAmount = Math.min(this.FLUID_TANK.getSpace(), 1000);
            FluidStack stack = new FluidStack(ModFluids.SOURCE_ELITE_LAVA.get(), 1000);
            fillTankWithFluid(stack, new ItemStack(Items.BUCKET));
        }
    }

    private void drainFluid() {
        if (this.FLUID_TANK.getFluidAmount() >= 1000 && isOutputSlotAvailable()) {
            FluidStack fluidStack = this.FLUID_TANK.drain(1000, IFluidHandler.FluidAction.SIMULATE);
            if (!fluidStack.isEmpty()) {
                fluidStack = this.FLUID_TANK.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                ItemStack filledBucket = FluidUtil.getFilledBucket(fluidStack);
                if (!filledBucket.isEmpty()) {
                    getItemHandler().setStackInSlot(OUTPUT_SLOT, filledBucket);
                    getItemHandler().extractItem(INPUT_SLOT, 1, false);
                }
            }
        }
    }

    private void transferFluidIn(int fluidInputSlot) {
        IFluidHandlerItem capability = getItemHandler().getStackInSlot(fluidInputSlot)
                .getCapability(Capabilities.FluidHandler.ITEM);
        if (capability == null) {
            return;
        }

        if (this.FLUID_TANK.isEmpty() ||
                this.FLUID_TANK.getFluid().is(capability.getFluidInTank(fluidInputSlot).getFluid()) && isOutputSlotAvailable()) {
            int drainAmount = Math.min(this.FLUID_TANK.getSpace(), 1000);
            FluidStack stack = capability.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
            fillTankWithFluid(stack, capability.getContainer());
        }
    }

    private void fillTankWithFluid(FluidStack stack, ItemStack container) {
        this.FLUID_TANK.fill(new FluidStack(stack.getFluid(), stack.getAmount()), IFluidHandler.FluidAction.EXECUTE);
        if (isOutputSlotAvailable()) {
            getItemHandler().extractItem(INPUT_SLOT, 1, false);
            getItemHandler().setStackInSlot(OUTPUT_SLOT, new ItemStack(container.getItem(),
                    getItemHandler().getStackInSlot(OUTPUT_SLOT).getCount() + container.getCount()));
        }
    }

    private boolean hasFluidSource(int fluidInputSlot) {
        return !getItemHandler().getStackInSlot(fluidInputSlot).is(Items.BUCKET)
                && getItemHandler().getStackInSlot(fluidInputSlot).getCount() > 0
                && getItemHandler().getStackInSlot(fluidInputSlot).getCapability(Capabilities.FluidHandler.ITEM) != null;
    }

    private boolean isOutputSlotAvailable() {
        ItemStack outputSlotStack = getItemHandler().getStackInSlot(OUTPUT_SLOT);
        return outputSlotStack.isEmpty() || (outputSlotStack.getCount() < outputSlotStack.getMaxStackSize());
    }


    public HumidifierBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.HUMIDIFIER_BE.get(), pPos, pBlockState);
        this.humidifierBlock = (HumidifierBlock) ModBlocks.HUMIDIFIER.get();
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue) {
            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(getItemHandler().getSlots());
        for (int i = 0; i < getItemHandler().getSlots(); i++) {
            inventory.setItem(i, getItemHandler().getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("gui.randomholos.humidifier");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new HumidifierMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
