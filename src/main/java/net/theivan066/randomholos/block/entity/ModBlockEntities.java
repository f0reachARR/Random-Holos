package net.theivan066.randomholos.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RandomHolos.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManufacturingTableBlockEntity>> MANUFACTURING_TABLE_BE =
            BLOCK_ENTITIES.register("manufacturing_table_block_entity", () ->
                    BlockEntityType.Builder.of(ManufacturingTableBlockEntity::new,
                            ModBlocks.MANUFACTURING_TABLE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HumidifierBlockEntity>> HUMIDIFIER_BE =
            BLOCK_ENTITIES.register("humidifier_block_entity", () ->
                    BlockEntityType.Builder.of(HumidifierBlockEntity::new,
                            ModBlocks.HUMIDIFIER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
