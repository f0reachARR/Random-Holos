package net.theivan066.randomholos.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;

public class ModToolTiers {
    public static final Tier ABOVE_DIAMOND =
            new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL, 1500, 9f, 2.5f, 26,
                    () -> Ingredient.of(ModItems.TEST.get()));
    public static final Tier SIDEREAL =
            new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL, 4000, 9.5f, 5f, 26,
                    () -> Ingredient.of(ModItems.SIDEREAL_INGOT.get()));
}
