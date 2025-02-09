package net.theivan066.randomholos.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.theivan066.randomholos.recipe.ManufacturingRecipe;
import net.theivan066.randomholos.recipe.ModRecipes;
import net.theivan066.randomholos.screen.ManufacturingTableMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ManufacturingTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> true;
                case 10 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int INPUT_SLOT_5 = 4;
    private static final int INPUT_SLOT_6 = 5;
    private static final int INPUT_SLOT_7 = 6;
    private static final int INPUT_SLOT_8 = 7;
    private static final int INPUT_SLOT_9 = 8;
    private static final int INPUT_SLOT_PATTERN = 9;
    private static final int OUTPUT_SLOT = 10;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("Inventory", itemHandler.serializeNBT(registries));
        tag.putInt("Progress", progress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("Inventory"));
        progress = tag.getInt("Progress");
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public ManufacturingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MANUFACTURING_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ManufacturingTableBlockEntity.this.progress;
                    case 1 -> ManufacturingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> ManufacturingTableBlockEntity.this.progress = pValue;
                    case 1 -> ManufacturingTableBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.randomholos.manufacturing_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ManufacturingTableMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    //crafting
    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (isOutputSlotAvailable() && hasRecipe()) {
            this.progress++;
            setChanged(level, pPos, pState);
            if (isProgressDone()) {
                craftItem();
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }
    }

    private boolean isProgressDone() {
        return this.progress >= this.maxProgress;
    }

    private boolean hasRecipe() {
        Optional<ManufacturingRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
        return isOutputtable(resultItem.getItem(), resultItem.getCount());
    }

    private void craftItem() {
        Optional<ManufacturingRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_2, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_3, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_4, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_5, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_6, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_7, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_8, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_9, 1, false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private Optional<ManufacturingRecipe> getCurrentRecipe() {
        RecipeWrapper recipeWrapper = new RecipeWrapper(this.itemHandler);
        Optional<RecipeHolder<ManufacturingRecipe>> recipeFor = this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.MANUFACTURING_TYPE.get(), recipeWrapper, level);
        return recipeFor.map(RecipeHolder::value);
    }

    private boolean isOutputtable(Item item, int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize()
                >= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count
                && (
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()
                        || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item)
        );
    }

    private boolean isOutputSlotAvailable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()
                        < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
