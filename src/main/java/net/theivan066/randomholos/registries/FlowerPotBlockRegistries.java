package net.theivan066.randomholos.registries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.theivan066.randomholos.block.ModBlocks;

public class FlowerPotBlockRegistries {

    public static void registerFlowerPots() {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MAPLE_SAPLING.getId(), ModBlocks.POTTED_MAPLE_SAPLING);
    }
}
