package net.theivan066.randomholos.datagen;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.loot.AddItemModifier;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, RandomHolos.MOD_ID);
    }

    @Override
    protected void start() {

        add("rice_from_seagrass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SEAGRASS).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ModItems.RAW_RICE.get()));

        add("rice_from_tall_seagrass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_SEAGRASS).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ModItems.RAW_RICE.get()));

        add("curry_from_fern", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()
        }, ModItems.CURRY.get()));

        add("curry_from_large_fern", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.LARGE_FERN).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()
        }, ModItems.CURRY.get()));

        add("shrimp_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/fish")).build(),
                LootItemRandomChanceCondition.randomChance(0.095F).build(),
        }, ModItems.SHRIMP.get()));
    }
}
