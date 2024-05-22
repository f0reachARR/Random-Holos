package net.theivan066.randomholos.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        SurfaceRules.ConditionSource belowY63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.ConditionSource aboveY55 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(55), 2);
        SurfaceRules.ConditionSource aboveY50 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(50), 4);
        SurfaceRules.ConditionSource aboveY40 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(40), 2);


        return SurfaceRules.sequence(

                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SAKURA_FOREST),
                                SurfaceRules.ifTrue(aboveY40,
                                        SurfaceRules.ifTrue(SurfaceRules.not(aboveY50),
                                                CALCITE)))),

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );








//        SurfaceRules.ConditionSource below97 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
//        SurfaceRules.ConditionSource below256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
//        SurfaceRules.ConditionSource above63 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
//        SurfaceRules.ConditionSource above74 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
//        SurfaceRules.ConditionSource below60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
//        SurfaceRules.ConditionSource below62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
//        SurfaceRules.ConditionSource below63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
//        SurfaceRules.ConditionSource water1 = SurfaceRules.waterBlockCheck(-1, 0);
//        SurfaceRules.ConditionSource nowater = SurfaceRules.waterBlockCheck(0, 0);
//        SurfaceRules.ConditionSource water6 = SurfaceRules.waterStartCheck(-6, -1);
//        SurfaceRules.ConditionSource hole = SurfaceRules.hole();
//        SurfaceRules.ConditionSource isfrozenoceans = SurfaceRules.isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
//        SurfaceRules.ConditionSource steep = SurfaceRules.steep();
//        SurfaceRules.RuleSource grassblockondirt = SurfaceRules.sequence(SurfaceRules.ifTrue(nowater, GRASS_BLOCK), DIRT);
//        SurfaceRules.RuleSource sandstoneOnCeilingorsand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
//        SurfaceRules.RuleSource stoneOnCeilingorgravel = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
//        SurfaceRules.ConditionSource warmOceanOrBeach = SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH);
//        SurfaceRules.ConditionSource desert = SurfaceRules.isBiome(Biomes.DESERT);
//        SurfaceRules.RuleSource stonypeak = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.STONY_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D), CALCITE), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.STONY_SHORE), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05D, 0.05D), stoneOnCeilingorgravel), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE)), SurfaceRules.ifTrue(warmOceanOrBeach, sandstoneOnCeilingorsand), SurfaceRules.ifTrue(desert, sandstoneOnCeilingorsand), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE));
//        SurfaceRules.RuleSource powdersnow1 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D), SurfaceRules.ifTrue(nowater, POWDER_SNOW));
//        SurfaceRules.RuleSource powdersnow2 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D), SurfaceRules.ifTrue(nowater, POWDER_SNOW));
//        SurfaceRules.RuleSource snowplacer1 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(steep, PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5D, 0.2D), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625D, 0.025D), ICE), SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), powdersnow1, SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(powdersnow1, DIRT)), stonypeak, SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA), SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), stoneOnCeilingorgravel), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), DIRT), stoneOnCeilingorgravel)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD), DIRT);
////        SurfaceRules.RuleSource snowplacer2 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(steep, PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE), SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), powdersnow2, SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.GROVE), SurfaceRules.sequence(powdersnow2, SurfaceRules.ifTrue(nowater, SNOW_BLOCK))), stonypeak, SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5D), COARSE_DIRT))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), stoneOnCeilingorgravel), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0D), grassblockondirt), stoneOnCeilingorgravel)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), PODZOL))), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.ICE_SPIKES), SurfaceRules.ifTrue(nowater, SNOW_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD), SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM), grassblockondirt);
//        SurfaceRules.ConditionSource surfacenoice1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
//        SurfaceRules.ConditionSource surfacenoice2 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
//        SurfaceRules.ConditionSource surfacenoice3 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);
//
//        SurfaceRules.RuleSource surfacerules$rulesource8 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WOODED_BADLANDS),
//                                        SurfaceRules.ifTrue(below97, SurfaceRules.sequence(
//                                                SurfaceRules.ifTrue(surfacenoice1, COARSE_DIRT),
//                                                SurfaceRules.ifTrue(surfacenoice2, COARSE_DIRT),
//                                                SurfaceRules.ifTrue(surfacenoice3, COARSE_DIRT),
//                                                grassblockondirt))),
//                                SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SWAMP),
//                                        SurfaceRules.ifTrue(below62,
//                                                SurfaceRules.ifTrue(
//                                                        SurfaceRules.not(below63), SurfaceRules.ifTrue(
//                                                                SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)))),
//                                SurfaceRules.ifTrue(
//                                        SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP),
//                                        SurfaceRules.ifTrue(
//                                                below60, SurfaceRules.ifTrue(
//                                                        SurfaceRules.not(below63), SurfaceRules.ifTrue(
//                                                                SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER))))))
//                , SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
//                                        SurfaceRules.sequence(SurfaceRules.ifTrue(below256, ORANGE_TERRACOTTA),
//                                                SurfaceRules.ifTrue(above74, SurfaceRules.sequence(
//                                                        SurfaceRules.ifTrue(surfacenoice1, TERRACOTTA),
//                                                        SurfaceRules.ifTrue(surfacenoice2, TERRACOTTA),
//                                                        SurfaceRules.ifTrue(surfacenoice3, TERRACOTTA),
//                                                        SurfaceRules.bandlands())),
//                                                SurfaceRules.ifTrue(water1, SurfaceRules.sequence(
//                                                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE),
//                                                        RED_SAND)),
//                                                SurfaceRules.ifTrue(SurfaceRules.not(hole), ORANGE_TERRACOTTA),
//                                                SurfaceRules.ifTrue(water6, WHITE_TERRACOTTA), stoneOnCeilingorgravel))
//                                , SurfaceRules.ifTrue(above63
//                                        , SurfaceRules.sequence(SurfaceRules.ifTrue(below63
//                                                        , SurfaceRules.ifTrue(SurfaceRules.not(above74), ORANGE_TERRACOTTA))
//                                                , SurfaceRules.bandlands()))
//                                , SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR
//                                        , SurfaceRules.ifTrue(water6, WHITE_TERRACOTTA))))
//                , SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR
//                        , SurfaceRules.ifTrue(water1
//                                , SurfaceRules.sequence(SurfaceRules.ifTrue(isfrozenoceans
//                                        , SurfaceRules.ifTrue(hole
//                                                , SurfaceRules.sequence(SurfaceRules.ifTrue(nowater, AIR)
//                                                        , SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE), WATER))), snowplacer2)))
//                , SurfaceRules.ifTrue(water6
//                        , SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR
//                                        , SurfaceRules.ifTrue(isfrozenoceans
//                                                , SurfaceRules.ifTrue(hole, WATER)))
//                                , SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, snowplacer1)
//                                , SurfaceRules.ifTrue(warmOceanOrBeach
//                                        , SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE))
//                                , SurfaceRules.ifTrue(desert
//                                        , SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE))))
//                , SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR
//                        , SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE)
//                                , SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
//                                        sandstoneOnCeilingorsand),
//                                stoneOnCeilingorgravel)));
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
