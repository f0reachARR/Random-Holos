package net.theivan066.randomholos.worldgen.tree.trunk_placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class MapleTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<MapleTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(mapleTrunkPlacerInstance ->
            trunkPlacerParts(mapleTrunkPlacerInstance).apply(mapleTrunkPlacerInstance, MapleTrunkPlacer::new));

    public MapleTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.MAPLE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {

        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        int height = pFreeTreeHeight + pRandom.nextInt(heightRandA, heightRandA + 3) + pRandom.nextInt(heightRandB - 1, heightRandB + 1);

        if (pRandom.nextFloat() < 0.5f) {
            for (int i = 0; i < height; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
            }
            return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
        } else {
            for (int i = 0; i < 4; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
            }
            int times = (int) Math.floor((double) height / 4 - 1);
            int leftover = height % 4;

            if (pRandom.nextFloat() < 0.25f) {
                for (int j = 0; j < times + 1; j++) {
                    for (int k = 0; k < 4; k++) {
                        placeLog(pLevel, pBlockSetter, pRandom, pPos.east(j).above(j * 4 + k), pConfig);
                    }
                }
                for (int l = 0; l < leftover; l++) {
                    placeLog(pLevel, pBlockSetter, pRandom, pPos.east(times).above(height - (height % 4) + l), pConfig);
                }
                return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.east(times).above(height), 0, false));
            } else if (0.25f < pRandom.nextFloat() && pRandom.nextFloat() < 0.5f) {
                for (int j = 0; j < times + 1; j++) {
                    for (int k = 0; k < 4; k++) {
                        placeLog(pLevel, pBlockSetter, pRandom, pPos.south(j).above(j * 4 + k), pConfig);
                    }
                }
                for (int l = 0; l < leftover; l++) {
                    placeLog(pLevel, pBlockSetter, pRandom, pPos.south(times).above(height - (height % 4) + l), pConfig);
                }
                return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.south(times).above(height), 0, false));
            } else if (0.5f < pRandom.nextFloat() && pRandom.nextFloat() < 0.75f) {
                for (int j = 0; j < times + 1; j++) {
                    for (int k = 0; k < 4; k++) {
                        placeLog(pLevel, pBlockSetter, pRandom, pPos.west(j).above(j * 4 + k), pConfig);
                    }
                }
                for (int l = 0; l < leftover; l++) {
                    placeLog(pLevel, pBlockSetter, pRandom, pPos.west(times).above(height - (height % 4) + l), pConfig);
                }
                return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.west(times).above(height), 0, false));
            } else {
                for (int j = 0; j < times + 1; j++) {
                    for (int k = 0; k < 4; k++) {
                        placeLog(pLevel, pBlockSetter, pRandom, pPos.north(j).above(j * 4 + k), pConfig);
                    }
                }
                for (int l = 0; l < leftover; l++) {
                    placeLog(pLevel, pBlockSetter, pRandom, pPos.north(times).above(height - (height % 4) + l), pConfig);
                }
                return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.north(times).above(height), 0, false));
            }
        }
    }
}
