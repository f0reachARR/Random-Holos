package net.theivan066.randomholos.datagen;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.loot.AddItemModifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, RandomHolos.MOD_ID);
    }

    @Override
    protected void start() {

        add("rice_from_seagrass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SEAGRASS).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(
                        ItemPredicate.Builder.item()
                                .withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                                        ItemEnchantmentsPredicate.enchantments(
                                                List.of(
                                                        new EnchantmentPredicate(
                                                                registries.holderOrThrow(Enchantments.SILK_TOUCH),
                                                                MinMaxBounds.Ints.atLeast(1)
                                                        )
                                                )
                                        )
                                )
                )).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ModItems.RAW_RICE.get()));

        add("rice_from_tall_seagrass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_SEAGRASS).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                        ItemEnchantmentsPredicate.enchantments(
                                List.of(
                                        new EnchantmentPredicate(
                                                registries.holderOrThrow(Enchantments.SILK_TOUCH),
                                                MinMaxBounds.Ints.atLeast(1)
                                        )
                                )
                        )
                ))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ModItems.RAW_RICE.get()));

        add("curry_from_fern", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                        ItemEnchantmentsPredicate.enchantments(
                                List.of(
                                        new EnchantmentPredicate(
                                                registries.holderOrThrow(Enchantments.SILK_TOUCH),
                                                MinMaxBounds.Ints.atLeast(1)
                                        )
                                )
                        )
                ))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()
        }, ModItems.CURRY.get()));

        add("curry_from_large_fern", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.LARGE_FERN).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                        ItemEnchantmentsPredicate.enchantments(
                                List.of(
                                        new EnchantmentPredicate(
                                                registries.holderOrThrow(Enchantments.SILK_TOUCH),
                                                MinMaxBounds.Ints.atLeast(1)
                                        )
                                )
                        )
                ))).build(),
                InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()
        }, ModItems.CURRY.get()));

        add("shrimp_from_fishing", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("gameplay/fishing/fish")).build(),
                LootItemRandomChanceCondition.randomChance(0.095F).build(),
        }, ModItems.SHRIMP.get()));

        add("chives_from_plain_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.CHIVES.get()));

        add("scallion_from_plain_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.SCALLION.get()));

        add("chinese_cabbage_from_plain_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.CHINESE_CABBAGE.get()));

        add("chives_from_taiga_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_taiga_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.CHIVES.get()));

        add("scallion_from_taiga_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_taiga_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.SCALLION.get()));

        add("chinese_cabbage_from_taiga_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_taiga_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.CHINESE_CABBAGE.get()));

        add("red_pepper_from_savanna_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.RED_CHILI_PEPPER.get()));

        add("green_pepper_from_savanna_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.GREEN_CHILI_PEPPER.get()));

        add("ghost_pepper_from_savanna_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.01F).build(),
        }, ModItems.GHOST_PEPPER.get()));

        add("rice_from_savanna_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.RAW_RICE.get()));

        add("curry_from_savanna_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_savanna_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.CURRY.get()));

        add("ghost_pepper_from_desert_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_desert_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.02F).build(),
        }, ModItems.GHOST_PEPPER.get()));

        add("curry_from_desert_village_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_desert_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.07F).build(),
        }, ModItems.CURRY.get()));

        add("rice_from_village_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.RAW_RICE.get()));

        add("rice_from_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.RAW_RICE.get()));

        add("tourmaline_from_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.TOURMALINE.get()));

        add("tourmaline_from_simple_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.085F).build(),
        }, ModItems.TOURMALINE.get()));

        add("tourmaline_from_shipwreck_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.TOURMALINE.get()));

        add("tourmaline_from_pillager_outpost", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.TOURMALINE.get()));

        add("meteorite_piece_from_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("meteorite_piece_from_ruin_small", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("meteorite_piece_from_ruin_big", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("meteorite_piece_from_shipwreck_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("meteorite_piece_from_pillager_outpost", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("meteorite_piece_from_ruined_portal", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.075F).build(),
        }, ModItems.METEORITE_PIECE.get()));

        add("shrimp_from_shipwreck_supply", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build(),
        }, ModItems.SHRIMP.get()));

        add("stellarite_from_end_city_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01F).build(),
        }, ModItems.STELLARITE.get()));
    }
}
