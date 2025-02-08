package net.theivan066.randomholos.entity.trade;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.potion.ModPotions;

import java.util.List;
import java.util.Optional;

public class ModTrades {
    public static final List<MerchantOffer> MIKOP_TRADES = List.of(
            new MerchantOffer(
                    new ItemCost(ModItems.AHOGE, 2),
                    Optional.empty(),
                    new ItemStack(Items.DIAMOND, 1),
                    5, 0, 0.05f),
            new MerchantOffer(
                    new ItemCost(Items.SLIME_BALL, 32),
                    Optional.empty(),
                    new ItemStack(Items.DIAMOND, 1),
                    2, 0, 0.05f
            ),
            new MerchantOffer(
                    new ItemCost(Items.ARROW, 8),
                    Optional.of(new ItemCost(ModItems.METEORITE_PIECE, 1)),
                    new ItemStack(ModItems.MIKOMET_ARROW.getDelegate(), 8),
                    6, 0, 0.05f
            ),
            new MerchantOffer(
                    new ItemCost(Items.BREAD, 2),
                    Optional.empty(),
                    new ItemStack(ModItems.BAGUETTE.getDelegate(), 1),
                    3, 0, 0.05f
            ),
            new MerchantOffer(
                    new ItemCost(Items.GLASS_BOTTLE, 1),
                    Optional.of(new ItemCost(Items.PUFFERFISH, 4)),
                    PotionContents.createItemStack(Items.POTION, ModPotions.SEVERE_POISON),
                    2, 0, 0.05f
            )
    );

}
