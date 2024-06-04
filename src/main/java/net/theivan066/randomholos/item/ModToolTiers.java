package net.theivan066.randomholos.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.theivan066.randomholos.RandomHolos;

import java.util.List;

public class ModToolTiers {
    public static final Tier ABOVE_DIAMOND = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1500, 9f, 2.5f, 26,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.TEST.get())),
            new ResourceLocation(RandomHolos.MOD_ID, "above_diamond"), List.of(Tiers.IRON), List.of());
}
