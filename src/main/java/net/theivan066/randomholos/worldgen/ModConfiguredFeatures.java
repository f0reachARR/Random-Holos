package net.theivan066.randomholos.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.worldgen.tree.trunk_placer.MapleTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TOURMALINE_ORE_KEY = registerKey("tourmaline_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_LEAVES_KEY = registerKey("fallen_leaves");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //ore
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest andesiteReplacables = new BlockMatchTest(Blocks.ANDESITE);
        RuleTest dioriteReplacables = new BlockMatchTest(Blocks.DIORITE);
        RuleTest graniteReplacables = new BlockMatchTest(Blocks.GRANITE);
        RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldTourmalineOres = List.of(
                OreConfiguration.target(stoneReplaceabeles, ModBlocks.TOURMALINE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_TOURMALINE_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_TOURMALINE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTourmalineOres, 10));


        //tree
        register(context, MAPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_LOG.get()),
                new MapleTrunkPlacer(4, 1, 2),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES.get()),
                new MegaPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2),ConstantInt.of(8)),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, FALLEN_LEAVES_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.FALLEN_LEAVES.get())))));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RandomHolos.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
