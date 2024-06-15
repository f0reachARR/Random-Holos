package net.theivan066.randomholos.registries;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.item.ModItems;

public class CompostableRegistries {
    public static void registerCompostableItems(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.CHIVES.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.SCALLION.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.RED_CHILI_PEPPER.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.GREEN_CHILI_PEPPER.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.GHOST_PEPPER.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CHINESE_CABBAGE.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MAPLE_LEAVES.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MAPLE_SAPLING.get(), 0.3f);

            ComposterBlock.COMPOSTABLES.put(Items.SUGAR, 0.3f);
        });
    }
}
