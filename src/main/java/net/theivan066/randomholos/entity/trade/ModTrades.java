package net.theivan066.randomholos.entity.trade;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.potion.ModPotions;

import javax.annotation.Nullable;
import java.util.List;

public class ModTrades {

    public static final Int2ObjectMap<ModTrades.ItemListing[]> MIKOP_TRADES = toIntMap(ImmutableMap.of(1, new ModTrades.ItemListing[]{
            new ItemToItem(ModItems.AHOGE.get(), 2, Items.DIAMOND, 1, 5),
            new ItemToItem(Items.SLIME_BALL, 32, Items.DIAMOND, 1, 2),
            new ItemAndItemToItem(Items.ARROW, 8, ModItems.METEORITE_PIECE.get(), 1, ModItems.MIKOMET_ARROW.get(), 8, 6),
            new ItemToItem(Items.BREAD, 2, ModItems.BAGUETTE.get(), 1, 3),
            new ItemAndItemToItem(Items.GLASS_BOTTLE, 1, Items.PUFFERFISH, 4, PotionUtils.setPotion(Items.POTION.getDefaultInstance(), ModPotions.SEVERE_POISON.get()).getItem(), 1, 2)
    }));

    private static Int2ObjectMap<ModTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, ModTrades.ItemListing[]> pMap) {
        return new Int2ObjectOpenHashMap<>(pMap);
    }

    public interface ItemListing {
        @Nullable
        MerchantOffer getOffer(Entity pTrader, RandomSource pRandom);
    }

    static class ItemAndItemToItem implements ModTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final float priceMultiplier;

        public ItemAndItemToItem(ItemLike pFromItem, int pFromCount, ItemLike pFromItem2, int pFromCount2, Item pToItem, int pToCount, int pMaxUses) {
            this.fromItem = new ItemStack(pFromItem);
            this.fromCount = pFromCount;
            this.fromItem2 = new ItemStack(pFromItem2);
            this.fromCount2 = pFromCount2;
            this.toItem = new ItemStack(pToItem);
            this.toCount = pToCount;
            this.maxUses = pMaxUses;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.fromItem2.getItem(), this.fromCount2), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, 0, this.priceMultiplier);
        }
    }

    static class ItemToItem implements ModTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final float priceMultiplier;

        public ItemToItem(ItemLike fromItem, int fromCount, ItemLike toItem, int toCount, int pMaxUses) {
            this.fromItem = new ItemStack(fromItem);
            this.fromCount = fromCount;
            this.toItem = new ItemStack(toItem);
            this.toCount = toCount;
            this.maxUses = pMaxUses;
            this.priceMultiplier = 0.05f;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, 0, this.priceMultiplier);
        }
    }

    static class ItemAndItemToPotionedItem implements ModTrades.ItemListing {
        /**
         * An ItemStack that can have potion effects written to it.
         */
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final Item fromItem;
        private final int fromCount;
        private final Item fromItem2;
        private final int fromCount2;

        public ItemAndItemToPotionedItem(Item pFromItem, int pFromCount, Item pFromItem2, int pFromCount2, Item pToItem, int pToCount, int pMaxUses) {
            this.toItem = new ItemStack(pToItem);
            this.maxUses = pMaxUses;
            this.fromItem = pFromItem;
            this.fromCount = pFromCount;
            this.fromItem2 = pFromItem2;
            this.fromCount2 = pFromCount2;
            this.toCount = pToCount;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            List<Potion> list = BuiltInRegistries.POTION.stream().filter((p_35804_) -> {
                return !p_35804_.getEffects().isEmpty() && PotionBrewing.isBrewablePotion(p_35804_);
            }).toList();
            Potion potion = list.get(pRandom.nextInt(list.size()));
            ItemStack potionItemStack = PotionUtils.setPotion(new ItemStack(this.toItem.getItem(), this.toCount), potion);
            return new MerchantOffer(new ItemStack(this.fromItem, this.fromCount), new ItemStack(this.fromItem2, this.fromCount2), potionItemStack, this.maxUses, 0, 0.05f);
        }
    }
}
