package net.theivan066.randomholos.worldgen.dimension;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);
    private static final SurfaceRules.RuleSource ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
    private static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final SurfaceRules.RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final SurfaceRules.RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource ICE = makeStateRule(Blocks.ICE);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);

    private static SurfaceRules.RuleSource makeStateRule(Block pBlock) {
        return SurfaceRules.state(pBlock.defaultBlockState());
    }
    public static SurfaceRules.RuleSource generateRules(boolean p_198381_, boolean pBedrockRoof, boolean pBedrockFloor) {


        SurfaceRules.ConditionSource yBlockCheck97 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
        SurfaceRules.ConditionSource yBlockCheck256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
        SurfaceRules.ConditionSource yStartCheck63 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        SurfaceRules.ConditionSource yStartCheck74 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
        SurfaceRules.ConditionSource yBlockCheck60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        SurfaceRules.ConditionSource yBlockCheck62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource yBlockCheck63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.ConditionSource waterDepth1 = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource waterDepth0 = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.ConditionSource waterDepth6 = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.ConditionSource isHole = SurfaceRules.hole();
        SurfaceRules.ConditionSource isFrozenOceans = SurfaceRules.isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        SurfaceRules.ConditionSource isSteep = SurfaceRules.steep();
        SurfaceRules.RuleSource placeGrassAndDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(waterDepth0, GRASS_BLOCK), DIRT);
        SurfaceRules.RuleSource placeSandAndSandstone = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
        SurfaceRules.RuleSource placeStoneAndGravel = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
        SurfaceRules.ConditionSource isWarmOceanOrBeach = SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH);
        SurfaceRules.ConditionSource isDesert = SurfaceRules.isBiome(Biomes.DESERT);

        SurfaceRules.RuleSource placeDripstoneCalciteSurfaceBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.STONY_PEAKS),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D),
                                CALCITE),
                        STONE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.STONY_SHORE),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05D, 0.05D),
                                        placeStoneAndGravel),
                                STONE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS),
                        SurfaceRules.ifTrue(
                                surfaceNoiseAbove(1.0D), STONE)),
                SurfaceRules.ifTrue(
                        isWarmOceanOrBeach,
                        placeSandAndSandstone),
                SurfaceRules.ifTrue(isDesert,
                        placeSandAndSandstone),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES),
                        STONE)
        );




        SurfaceRules.ConditionSource islandStartHeight = SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(150), 3);
        SurfaceRules.ConditionSource grassHeight = SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(180), 2);
        SurfaceRules.ConditionSource islandEndHeight = SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(182), 2);
        SurfaceRules.ConditionSource sandStartHeight = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(45), 2);
        SurfaceRules.ConditionSource sandEndHeight = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(49), 1);
        SurfaceRules.ConditionSource waterHeight = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(50), 1);
        SurfaceRules.ConditionSource waterEndHeight = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(64), 0);

//        SurfaceRules.RuleSource skylandRules = SurfaceRules.ifTrue(
//                SurfaceRules.isBiome(ModBiomes.SKYLAND),
//                SurfaceRules.sequence(
//                        SurfaceRules.ifTrue(sandStartHeight,
//                                SurfaceRules.ifTrue(SurfaceRules.not(sandEndHeight), placeSandAndSandstone)),
//                        SurfaceRules.ifTrue(waterHeight,
//                                SurfaceRules.ifTrue(SurfaceRules.not(waterEndHeight), WATER)),
//                        SurfaceRules.ifTrue(waterEndHeight,
//                                SurfaceRules.ifTrue(SurfaceRules.not(islandStartHeight), AIR)),
//                        SurfaceRules.ifTrue(islandStartHeight,
//                                SurfaceRules.ifTrue(SurfaceRules.not(grassHeight), STONE)),
//                        SurfaceRules.ifTrue(grassHeight,
//                                SurfaceRules.ifTrue(SurfaceRules.not(islandEndHeight), GRASS_BLOCK))
//                )
//        );



        SurfaceRules.RuleSource placePowderSnow = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D), SurfaceRules.ifTrue(waterDepth0, POWDER_SNOW));
        SurfaceRules.RuleSource placePowderSnow1 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D), SurfaceRules.ifTrue(waterDepth0, POWDER_SNOW));

        SurfaceRules.RuleSource generateSurfaceBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5D, 0.2D), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625D, 0.025D), ICE),
                                SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                placePowderSnow,
                                SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(placePowderSnow, DIRT)),
                placeDripstoneCalciteSurfaceBlocks,
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA), SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), placeStoneAndGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), DIRT),
                                placeStoneAndGravel
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
                DIRT
        );

        SurfaceRules.RuleSource generateSurfaceBlocksVariant = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE),
                                SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                placePowderSnow1,
                                SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.JAGGED_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isSteep, STONE),
                                SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.GROVE),
                        SurfaceRules.sequence(placePowderSnow1, SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK))
                ),
                placeDripstoneCalciteSurfaceBlocks,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5D), COARSE_DIRT)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), placeStoneAndGravel),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), placeGrassAndDirt),
                                placeStoneAndGravel
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), COARSE_DIRT),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), PODZOL)
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.ICE_SPIKES), SurfaceRules.ifTrue(waterDepth0, SNOW_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM),
                placeGrassAndDirt
        );
        SurfaceRules.ConditionSource noiseCondition = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
        SurfaceRules.ConditionSource noiseCondition1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
        SurfaceRules.ConditionSource noiseCondition2 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);

        SurfaceRules.RuleSource surfacerules$rulesource8 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WOODED_BADLANDS),
                                        SurfaceRules.ifTrue(
                                                yBlockCheck97,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(noiseCondition, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(noiseCondition1, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(noiseCondition2, COARSE_DIRT),
                                                        placeGrassAndDirt
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.SWAMP),
                                        SurfaceRules.ifTrue(
                                                yBlockCheck62,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(yBlockCheck63),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP),
                                        SurfaceRules.ifTrue(
                                                yBlockCheck60,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(yBlockCheck63),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)
                                                )
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(yBlockCheck256, ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(
                                                        yStartCheck74,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(noiseCondition, TERRACOTTA),
                                                                SurfaceRules.ifTrue(noiseCondition1, TERRACOTTA),
                                                                SurfaceRules.ifTrue(noiseCondition2, TERRACOTTA),
                                                                SurfaceRules.bandlands()
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(
                                                        waterDepth1,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(SurfaceRules.not(isHole), ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(waterDepth6, WHITE_TERRACOTTA),
                                                placeStoneAndGravel
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        yStartCheck63,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        yBlockCheck63,
                                                        SurfaceRules.ifTrue(SurfaceRules.not(yStartCheck74), ORANGE_TERRACOTTA)
                                                ),
                                                SurfaceRules.bandlands()
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(waterDepth6, WHITE_TERRACOTTA)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                waterDepth1,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                isFrozenOceans,
                                                SurfaceRules.ifTrue(
                                                        isHole,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(waterDepth0, AIR),
                                                                SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE),
                                                                WATER
                                                        )
                                                )
                                        ),
                                        generateSurfaceBlocksVariant
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        waterDepth6,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                isFrozenOceans,
                                                SurfaceRules.ifTrue(isHole, WATER)
                                        )
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, generateSurfaceBlocks),
                                SurfaceRules.ifTrue(
                                        isWarmOceanOrBeach,
                                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE)
                                ),
                                SurfaceRules.ifTrue(
                                        isDesert,
                                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS),
                                        STONE
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                                        placeSandAndSandstone
                                ),
                                placeStoneAndGravel
                        )
                )
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        if (pBedrockRoof) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
        }

        if (pBedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surfacerules$rulesource8);
        builder.add(p_198381_ ? surfacerules$rulesource9 : surfacerules$rulesource8);
//        builder.add(skylandRules);
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double pValue) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, pValue / 8.25D, Double.MAX_VALUE);
    }
}
