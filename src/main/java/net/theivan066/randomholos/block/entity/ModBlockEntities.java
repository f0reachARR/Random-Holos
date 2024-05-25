package net.theivan066.randomholos.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RandomHolos.MOD_ID);

    public static final RegistryObject<BlockEntityType<ManufacturingTableBlockEntity>> MANUFACTURING_TABLE_BE =
            BLOCK_ENTITIES.register("manufacturing_table_block_entity", () ->
                    BlockEntityType.Builder.of(ManufacturingTableBlockEntity::new,
                            ModBlocks.MANUFACTURING_TABLE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
